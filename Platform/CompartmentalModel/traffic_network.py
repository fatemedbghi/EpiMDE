"""
Traffic Network Compartmental Model
Based on: Coogan & Arcak (2015)
"A Compartmental Model for Traffic Networks and its Dynamical Behavior"

This implementation includes:
- Supply and demand functions for traffic links
- PP/FIFO (Proportional Priority, First-In-First-Out) junction rule
- ODE dynamics for vehicle density evolution
- Ramp metering optimization via linear programming
- Example 2 from the paper with validation
"""

import numpy as np
import matplotlib.pyplot as plt
from scipy.integrate import solve_ivp
from scipy.optimize import linprog
from dataclasses import dataclass
from typing import Dict, List, Tuple, Optional


@dataclass
class TrafficLink:
    """
    Represents a traffic link (road segment) in the network.

    For ordinary links: bounded density ρ ∈ [0, ρ_jam]
    For onramps: unbounded density ρ ∈ [0, ∞) (acts as queue)
    """
    link_id: int
    is_onramp: bool
    rho_jam: Optional[float] = None      # Jam density (ordinary links only)
    rho_crit: Optional[float] = None     # Critical density (ordinary links only)
    phi_crit: Optional[float] = None     # Critical flow (ordinary links only)
    phi_max: Optional[float] = None      # Maximum demand (onramps only)

    def demand(self, rho: float) -> float:
        """
        Demand function Φ^out_l(ρ): traffic wishing to flow downstream

        For ordinary links (triangular fundamental diagram):
        - Increasing from 0 to Φ^crit as ρ goes from 0 to ρ^crit
        - Constant at Φ^crit for ρ > ρ^crit

        For onramps:
        - Bounded above by Φ^max
        """
        if self.is_onramp:
            # Onramp demand saturates at phi_max
            return min(max(rho, self.phi_max), self.phi_max) if rho > 1 else self.phi_max
        else:
            if rho <= self.rho_crit:
                # Free-flow region: linear increase
                return (self.phi_crit / self.rho_crit) * rho
            else:
                # Congested region: constant max flow
                return self.phi_crit

    def supply(self, rho: float) -> float:
        """
        Supply function Φ^in_l(ρ): capacity available for incoming traffic

        For ordinary links:
        - Constant at Φ^crit for ρ ≤ ρ^crit
        - Decreasing linearly for ρ > ρ^crit, reaches 0 at ρ^jam

        For onramps: infinite supply (acts as queue)
        """
        if self.is_onramp:
            return float('inf')
        else:
            if rho <= self.rho_crit:
                # Free-flow region: full capacity available
                return self.phi_crit
            else:
                # Congested region: decreasing capacity
                slope = self.phi_crit / (self.rho_jam - self.rho_crit)
                supply = slope * (self.rho_jam - rho)
                return max(0, supply)  # Ensure non-negative


class TrafficNetwork:
    """
    Traffic network with PP/FIFO junction dynamics.

    Components:
    - Links: road segments with supply/demand functions
    - Junctions: merge points with split ratios
    - Dynamics: ρ̇ = f^in - f^out (conservation of vehicles)
    """

    def __init__(self):
        self.links: Dict[int, TrafficLink] = {}
        self.junctions: Dict[str, Dict] = {}
        self.split_ratios: Dict[Tuple[int, int], float] = {}
        self.input_demands: Dict[int, float] = {}
        self.metering: Dict[int, Optional[float]] = {}  # Onramp metering limits

    def add_link(self, link: TrafficLink):
        """Add a traffic link to the network."""
        self.links[link.link_id] = link

    def add_junction(self, junction_id: str, incoming: List[int], outgoing: List[int]):
        """
        Add a junction with incoming and outgoing links.

        Args:
            junction_id: Unique identifier
            incoming: List of incoming link IDs
            outgoing: List of outgoing link IDs
        """
        self.junctions[junction_id] = {
            'incoming': incoming,
            'outgoing': outgoing
        }

    def set_split_ratio(self, from_link: int, to_link: int, beta: float):
        """Set split ratio β_lk from link l to link k."""
        self.split_ratios[(from_link, to_link)] = beta

    def set_input_demand(self, onramp_link: int, demand: float):
        """Set exogenous input demand d_l for onramp link l."""
        self.input_demands[onramp_link] = demand

    def set_metering(self, onramp_link: int, metering_rate: Optional[float]):
        """Set ramp metering limit m_l for onramp link l."""
        self.metering[onramp_link] = metering_rate

    def compute_alpha(self, rho: np.ndarray, junction: Dict) -> float:
        """
        Compute α_v(ρ) for the PP/FIFO rule at junction v.

        The PP/FIFO rule ensures:
        1. Outflow proportional to demand (Proportional Priority)
        2. Supply constraints not violated (First-In-First-Out)

        α_v(ρ) = max{α ∈ [0,1] : α ∑_{j∈L^in_v} β_{jk} Φ^out_j(ρ_j) ≤ Φ^in_k(ρ_k) ∀k∈L^out_v}

        Returns:
            α ∈ [0, 1]: scaling factor for outflow
        """
        incoming = junction['incoming']
        outgoing = junction['outgoing']

        alpha = 1.0

        for out_link_id in outgoing:
            # Compute total demand for this outgoing link
            total_demand = 0.0
            for in_link_id in incoming:
                beta = self.split_ratios.get((in_link_id, out_link_id), 0.0)
                demand = self.links[in_link_id].demand(rho[in_link_id])

                # Apply metering if this is an onramp
                if self.links[in_link_id].is_onramp:
                    metering_limit = self.metering.get(in_link_id)
                    if metering_limit is not None:
                        demand = min(demand, metering_limit)

                total_demand += beta * demand

            # Check supply constraint
            if total_demand > 1e-10:  # Avoid division by zero
                supply = self.links[out_link_id].supply(rho[out_link_id])
                alpha = min(alpha, supply / total_demand)

        return max(0.0, min(1.0, alpha))

    def find_junction_for_link(self, link_id: int) -> Optional[Dict]:
        """Find the junction that link_id flows into (if any)."""
        for junction_id, junction in self.junctions.items():
            if link_id in junction['incoming']:
                return junction
        return None

    def find_incoming_links(self, link_id: int) -> List[int]:
        """Find all links that flow into link_id."""
        incoming = []
        for (from_link, to_link), beta in self.split_ratios.items():
            if to_link == link_id and beta > 0:
                incoming.append(from_link)
        return incoming

    def dynamics(self, t: float, rho: np.ndarray) -> np.ndarray:
        """
        Compute ρ̇ = dρ/dt for the traffic network dynamics.

        For onramps: ρ̇_l = d_l - f^out_l(ρ)
        For ordinary links: ρ̇_l = f^in_l(ρ) - f^out_l(ρ)

        Args:
            t: Current time
            rho: Current density vector [ρ_1, ρ_2, ..., ρ_n]

        Returns:
            drho_dt: Time derivative of density
        """
        n_links = len(self.links)
        drho_dt = np.zeros(n_links)

        # Step 1: Compute outflows f^out_l for all links
        flows_out = {}
        for link_id, link in self.links.items():
            junction = self.find_junction_for_link(link_id)
            if junction is not None:
                # Link flows into a junction: apply PP/FIFO rule
                alpha = self.compute_alpha(rho, junction)
                demand = link.demand(rho[link_id])

                # Apply metering if this is an onramp
                if link.is_onramp:
                    metering_limit = self.metering.get(link_id)
                    if metering_limit is not None:
                        demand = min(demand, metering_limit)

                flows_out[link_id] = alpha * demand
            else:
                # Link flows to sink: no downstream constraint
                flows_out[link_id] = link.demand(rho[link_id])

        # Step 2: Compute inflows and dynamics for each link
        for link_id, link in self.links.items():
            if link.is_onramp:
                # Onramp dynamics: ρ̇ = d - f^out
                d = self.input_demands.get(link_id, 0.0)
                drho_dt[link_id] = d - flows_out[link_id]
            else:
                # Ordinary link dynamics: ρ̇ = f^in - f^out
                f_in = 0.0
                for incoming_link in self.find_incoming_links(link_id):
                    beta = self.split_ratios.get((incoming_link, link_id), 0.0)
                    f_in += beta * flows_out[incoming_link]

                drho_dt[link_id] = f_in - flows_out[link_id]

        return drho_dt

    def simulate(self, rho0: np.ndarray, t_span: Tuple[float, float], n_points: int = 1000):
        """
        Simulate traffic network dynamics.

        Args:
            rho0: Initial density vector
            t_span: Time interval (t_start, t_end)
            n_points: Number of time points to return

        Returns:
            sol: Solution object from scipy
        """
        t_eval = np.linspace(t_span[0], t_span[1], n_points)
        sol = solve_ivp(
            fun=self.dynamics,
            t_span=t_span,
            y0=rho0,
            method='RK45',
            t_eval=t_eval,
            dense_output=True
        )
        return sol

    def compute_equilibrium_flows(self, rho_eq: np.ndarray) -> Dict[int, float]:
        """Compute equilibrium flows at given equilibrium densities."""
        flows = {}
        for link_id, link in self.links.items():
            junction = self.find_junction_for_link(link_id)
            if junction is not None:
                alpha = self.compute_alpha(rho_eq, junction)
                demand = link.demand(rho_eq[link_id])
                if link.is_onramp:
                    metering_limit = self.metering.get(link_id)
                    if metering_limit is not None:
                        demand = min(demand, metering_limit)
                flows[link_id] = alpha * demand
            else:
                flows[link_id] = link.demand(rho_eq[link_id])
        return flows

    def optimize_ramp_metering(self, onramp_ids: List[int]) -> Dict[int, float]:
        """
        Solve the ramp metering optimization problem (Equations 30-33 from paper).

        maximize    ∑_{l ∈ R} s_l
        subject to  f^e_O = A f^e_O + B s
                    0 ≤ s_l ≤ min{d_l, Φ^max_l}    ∀l ∈ R
                    0 ≤ f^e_l ≤ Φ^crit_l            ∀l ∈ O

        Args:
            onramp_ids: List of onramp link IDs

        Returns:
            Optimal metering rates {onramp_id: s_l}
        """
        # Build list of ordinary links
        ordinary_ids = [lid for lid, link in self.links.items() if not link.is_onramp]
        n_ordinary = len(ordinary_ids)
        n_onramps = len(onramp_ids)

        # Build matrices A and B for f^e_O = A f^e_O + B s
        # (I - A) f^e_O = B s
        # f^e_O = (I - A)^{-1} B s

        A = np.zeros((n_ordinary, n_ordinary))
        B = np.zeros((n_ordinary, n_onramps))

        for i, ordinary_id in enumerate(ordinary_ids):
            # Find incoming links for this ordinary link
            for incoming_id in self.find_incoming_links(ordinary_id):
                beta = self.split_ratios.get((incoming_id, ordinary_id), 0.0)

                if self.links[incoming_id].is_onramp:
                    # Onramp contribution: goes into B matrix
                    j = onramp_ids.index(incoming_id)
                    B[i, j] = beta
                else:
                    # Ordinary link contribution: goes into A matrix
                    j = ordinary_ids.index(incoming_id)
                    A[i, j] = beta

        # Compute (I - A)^{-1} B
        try:
            M = np.linalg.solve(np.eye(n_ordinary) - A, B)
        except np.linalg.LinAlgError:
            print("Warning: Singular matrix, using pseudo-inverse")
            M = np.linalg.lstsq(np.eye(n_ordinary) - A, B, rcond=None)[0]

        # Set up linear program
        # Variables: s_l for each onramp
        # Objective: maximize ∑ s_l = minimize -∑ s_l
        c = -np.ones(n_onramps)  # Coefficients for objective

        # Constraints: M @ s ≤ Φ^crit
        A_ub = M
        b_ub = np.array([self.links[lid].phi_crit for lid in ordinary_ids])

        # Bounds: 0 ≤ s_l ≤ min{d_l, Φ^max_l}
        bounds = []
        for onramp_id in onramp_ids:
            d_l = self.input_demands.get(onramp_id, 0)
            phi_max = self.links[onramp_id].phi_max
            upper_bound = min(d_l, phi_max) if phi_max is not None else d_l
            bounds.append((0, upper_bound))

        # Solve linear program
        result = linprog(c, A_ub=A_ub, b_ub=b_ub, bounds=bounds, method='highs')

        if result.success:
            optimal_metering = {onramp_ids[i]: result.x[i] for i in range(n_onramps)}
            return optimal_metering
        else:
            print(f"Optimization failed: {result.message}")
            return {onramp_id: self.input_demands.get(onramp_id, 0) for onramp_id in onramp_ids}


def create_example_2_network() -> TrafficNetwork:
    """
    Create the traffic network from Example 2 (Figure 3) in the paper.

    Network structure:
        Onramp1 (1)
            |
            v
           (v1) -----> Link2 (2) -----> (v2)
            |                             |
            +--------> Link3 (3) ----+   |
                                      |   |
        Onramp4 (4) ----------------->+---+
                                      |
                                     (v4)
                                      |
                                      v
                                 Link5 (5) ----> (sink)

    Parameters from the paper:
    - Links 2, 3, 5: ρ^jam = 360, ρ^crit = 90, Φ^crit = 3000 (2,3) or 4000 (5)
    - Onramps 1, 4: Φ^max = 3000 (1) or 6000 (4)
    - Split ratios: β_12 = β_13 = 0.5, β_25 = β_35 = β_45 = 1.0
    - Input demands: d_1 = d_4 = 2500 vehicles/hour
    """
    network = TrafficNetwork()

    # Add links
    network.add_link(TrafficLink(link_id=1, is_onramp=True, phi_max=3000))
    network.add_link(TrafficLink(link_id=2, is_onramp=False, rho_jam=360, rho_crit=90, phi_crit=3000))
    network.add_link(TrafficLink(link_id=3, is_onramp=False, rho_jam=360, rho_crit=90, phi_crit=3000))
    network.add_link(TrafficLink(link_id=4, is_onramp=True, phi_max=6000))
    network.add_link(TrafficLink(link_id=5, is_onramp=False, rho_jam=360, rho_crit=90, phi_crit=4000))

    # Add junctions
    network.add_junction('v1', incoming=[1], outgoing=[2, 3])
    network.add_junction('v2', incoming=[2], outgoing=[5])
    network.add_junction('v3', incoming=[3], outgoing=[5])
    network.add_junction('v4', incoming=[4, 5], outgoing=[])  # Empty outgoing = sink

    # Set split ratios
    network.set_split_ratio(1, 2, 0.5)
    network.set_split_ratio(1, 3, 0.5)
    network.set_split_ratio(2, 5, 1.0)
    network.set_split_ratio(3, 5, 1.0)
    network.set_split_ratio(4, 5, 1.0)

    # Set input demands
    network.set_input_demand(1, 2500)
    network.set_input_demand(4, 2500)

    return network


def main():
    """Run Example 2 simulations and compare with paper results."""
    print("="*70)
    print("Traffic Network Simulation - Example 2 from Coogan & Arcak (2015)")
    print("="*70)

    # Create network
    network = create_example_2_network()

    # Scenario 1: No ramp metering
    print("\n" + "="*70)
    print("SCENARIO 1: No Ramp Metering")
    print("="*70)

    # Initial condition: start near zero density
    rho0 = np.array([0.1, 0.1, 0.1, 0.1, 0.1])

    # Simulate to equilibrium
    sol1 = network.simulate(rho0, t_span=(0, 100), n_points=1000)

    # Get equilibrium state
    rho_eq1 = sol1.y[:, -1]
    flows_eq1 = network.compute_equilibrium_flows(rho_eq1)

    print("\nEquilibrium densities:")
    for i, rho in enumerate(rho_eq1, 1):
        status = "∞" if rho > 500 else f"{rho:.1f}"
        print(f"  ρ_{i} = {status}")

    print("\nEquilibrium flows:")
    total_throughput1 = 0
    for link_id in sorted(flows_eq1.keys()):
        flow = flows_eq1[link_id]
        print(f"  f_{link_id} = {flow:.1f}")
        if network.links[link_id].is_onramp:
            total_throughput1 += flow

    print(f"\nTotal network throughput: {total_throughput1:.1f} vehicles/hour")
    print("\nExpected from paper:")
    print("  ρ: [∞, 270, 30, ∞, 90]")
    print("  f: [2000, 1000, 1000, 2000, 3000]")
    print("  Throughput: 4000 vehicles/hour")

    # Scenario 2: Optimal ramp metering
    print("\n" + "="*70)
    print("SCENARIO 2: Optimal Ramp Metering")
    print("="*70)

    # Compute optimal metering
    optimal_metering = network.optimize_ramp_metering(onramp_ids=[1, 4])
    print("\nOptimal metering rates:")
    for onramp_id, rate in optimal_metering.items():
        print(f"  m_{onramp_id} = {rate:.1f} vehicles/hour")

    print("\nExpected from paper:")
    print("  m_1 ≥ 2500 (not constrained)")
    print("  m_4 = 1750")

    # Apply metering and simulate
    for onramp_id, rate in optimal_metering.items():
        network.set_metering(onramp_id, rate)

    sol2 = network.simulate(rho0, t_span=(0, 100), n_points=1000)
    rho_eq2 = sol2.y[:, -1]
    flows_eq2 = network.compute_equilibrium_flows(rho_eq2)

    print("\nEquilibrium densities with metering:")
    for i, rho in enumerate(rho_eq2, 1):
        status = "∞" if rho > 500 else f"{rho:.1f}"
        print(f"  ρ_{i} = {status}")

    print("\nEquilibrium flows with metering:")
    total_throughput2 = 0
    for link_id in sorted(flows_eq2.keys()):
        flow = flows_eq2[link_id]
        print(f"  f_{link_id} = {flow:.1f}")
        if network.links[link_id].is_onramp:
            total_throughput2 += flow

    print(f"\nTotal network throughput: {total_throughput2:.1f} vehicles/hour")
    print(f"Improvement: {total_throughput2 - total_throughput1:.1f} vehicles/hour")
    print(f"Percentage increase: {100*(total_throughput2 - total_throughput1)/total_throughput1:.2f}%")

    print("\nExpected from paper:")
    print("  ρ: [∞, 37.5, 37.5, ∞, 90]")
    print("  f: [2500, 1250, 1250, 1750, 3000]")
    print("  Throughput: 4250 vehicles/hour")
    print("  Improvement: 250 vehicles/hour (6.25%)")

    # Visualization
    fig, axes = plt.subplots(2, 2, figsize=(14, 10))

    # Plot 1: Density evolution without metering
    ax = axes[0, 0]
    for i in range(5):
        label = f"Link {i+1}" if i in [1, 2, 4] else f"Onramp {i+1}"
        y_data = np.minimum(sol1.y[i, :], 500)  # Cap at 500 for visualization
        ax.plot(sol1.t, y_data, label=label, linewidth=2)
    ax.set_xlabel('Time (arbitrary units)', fontsize=11)
    ax.set_ylabel('Density ρ', fontsize=11)
    ax.set_title('Scenario 1: No Ramp Metering\nDensity Evolution', fontsize=12, fontweight='bold')
    ax.legend()
    ax.grid(True, alpha=0.3)

    # Plot 2: Density evolution with metering
    ax = axes[0, 1]
    for i in range(5):
        label = f"Link {i+1}" if i in [1, 2, 4] else f"Onramp {i+1}"
        y_data = np.minimum(sol2.y[i, :], 500)
        ax.plot(sol2.t, y_data, label=label, linewidth=2)
    ax.set_xlabel('Time (arbitrary units)', fontsize=11)
    ax.set_ylabel('Density ρ', fontsize=11)
    ax.set_title('Scenario 2: With Ramp Metering\nDensity Evolution', fontsize=12, fontweight='bold')
    ax.legend()
    ax.grid(True, alpha=0.3)

    # Plot 3: Equilibrium comparison - Densities
    ax = axes[1, 0]
    x = [1, 2, 3, 4, 5]
    rho1_plot = [min(r, 400) for r in rho_eq1]  # Cap for visualization
    rho2_plot = [min(r, 400) for r in rho_eq2]
    width = 0.35
    ax.bar([i - width/2 for i in x], rho1_plot, width, label='No Metering', alpha=0.8)
    ax.bar([i + width/2 for i in x], rho2_plot, width, label='With Metering', alpha=0.8)
    ax.set_xlabel('Link ID', fontsize=11)
    ax.set_ylabel('Equilibrium Density ρ*', fontsize=11)
    ax.set_title('Equilibrium Density Comparison', fontsize=12, fontweight='bold')
    ax.set_xticks(x)
    ax.legend()
    ax.grid(True, alpha=0.3, axis='y')

    # Plot 4: Equilibrium comparison - Flows
    ax = axes[1, 1]
    flows1_list = [flows_eq1[i] for i in range(1, 6)]
    flows2_list = [flows_eq2[i] for i in range(1, 6)]
    ax.bar([i - width/2 for i in x], flows1_list, width, label='No Metering', alpha=0.8)
    ax.bar([i + width/2 for i in x], flows2_list, width, label='With Metering', alpha=0.8)
    ax.set_xlabel('Link ID', fontsize=11)
    ax.set_ylabel('Equilibrium Flow f*', fontsize=11)
    ax.set_title('Equilibrium Flow Comparison', fontsize=12, fontweight='bold')
    ax.set_xticks(x)
    ax.legend()
    ax.grid(True, alpha=0.3, axis='y')

    plt.tight_layout()
    plt.savefig('/home/fatemeh/Documents/york/Code/Model/Model-Driven-Epidemiology/traffic_simulation_results.png', dpi=300, bbox_inches='tight')
    print(f"\n{'='*70}")
    print("Visualization saved to: traffic_simulation_results.png")
    print("="*70)
    plt.show()


if __name__ == "__main__":
    main()
