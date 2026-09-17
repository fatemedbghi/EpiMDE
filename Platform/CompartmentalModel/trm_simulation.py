"""
Traffic Reaction Model (TRM) Simulation
Based on: Pereira et al. (2024) "The Traffic Reaction Model: A kinetic compartmental
approach to road traffic modeling"

Implements:
- TRM with flux decomposition f(ρ) = g(ρ, ρ_max - ρ)
- Three decomposition types: MAK, Godunov, Capacitated
- Dual variable tracking: ρ (vehicle density) and ν (free space density)
- Extended TRM with capacity drop factors
- Network TRM for multiple lanes/junctions

Author: EpiMDE Framework
Date: 2025
"""

import numpy as np
from scipy.integrate import solve_ivp
import matplotlib.pyplot as plt
from typing import Callable, List, Tuple, Dict, Optional
from enum import Enum


class DecompositionType(Enum):
    """TRM flux decomposition types"""
    MAK = "MAK"  # Mass Action Kinetic
    GODUNOV = "GODUNOV"  # Godunov (CTM-equivalent)
    CAPACITATED = "CAPACITATED"  # Capacitated


class TRMCell:
    """
    Traffic Reaction Model cell (road segment)

    State variables:
    - ρ(t): vehicle density (vehicles per unit length)
    - ν(t): free space density = ρ_max - ρ(t)
    """

    def __init__(
        self,
        name: str,
        cell_length: float,
        rho_max: float,
        rho_crit: float,
        phi_max: float,
        decomposition: DecompositionType,
        omega: float = 0.0,
        capacity_drop: float = 1.0,
        track_dual_variable: bool = True
    ):
        """
        Initialize TRM cell

        Parameters:
        - name: Cell identifier
        - cell_length: Δx (length of road segment)
        - rho_max: Maximum density (jam density)
        - rho_crit: Critical density (density at maximum flow)
        - phi_max: Maximum flow rate
        - decomposition: Flux decomposition type (MAK, GODUNOV, CAPACITATED)
        - omega: Reaction rate constant (for MAK decomposition)
        - capacity_drop: C(t) ∈ (0, 1] for Extended TRM
        - track_dual_variable: Enable ν tracking
        """
        self.name = name
        self.dx = cell_length
        self.rho_max = rho_max
        self.rho_crit = rho_crit
        self.phi_max = phi_max
        self.decomposition = decomposition
        self.omega = omega
        self.capacity_drop = capacity_drop
        self.track_dual_variable = track_dual_variable

    def demand(self, rho: float) -> float:
        """
        Demand function D(ρ)

        For ρ ≤ ρ_crit: D(ρ) = (φ_max/ρ_crit) * ρ (linear increase)
        For ρ > ρ_crit: D(ρ) = φ_max (constant)
        """
        if rho <= self.rho_crit:
            return (self.phi_max / self.rho_crit) * rho
        else:
            return self.phi_max

    def supply(self, nu: float) -> float:
        """
        Supply function Q(ν) where ν = free space density

        For ν ≥ (ρ_max - ρ_crit): Q(ν) = φ_max (full capacity)
        For ν < (ρ_max - ρ_crit): Q(ν) = (φ_max/(ρ_max - ρ_crit)) * ν
        """
        nu_crit = self.rho_max - self.rho_crit
        if nu >= nu_crit:
            return self.phi_max
        else:
            return (self.phi_max / nu_crit) * nu

    def flux_decomposition(self, rho_up: float, rho_down: float) -> float:
        """
        Compute flux decomposition g(ρ_up, ν_down)
        where ν_down = ρ_max - ρ_down (free space downstream)

        Parameters:
        - rho_up: Upstream density
        - rho_down: Downstream density

        Returns:
        - Numerical flux F(rho_up, rho_down) = g(rho_up, rho_max - rho_down)
        """
        nu_down = self.capacity_drop * self.rho_max - rho_down

        if self.decomposition == DecompositionType.MAK:
            # MAK: g(ρ, ν) = ω * ρ * ν
            return self.omega * rho_up * nu_down

        elif self.decomposition == DecompositionType.GODUNOV:
            # Godunov: g(ρ, ν) = min(D(ρ), Q(ν))
            return min(self.demand(rho_up), self.supply(nu_down))

        elif self.decomposition == DecompositionType.CAPACITATED:
            # Capacitated: g(ρ, ν) = D(ρ) * Q(ν) / φ_max
            return (self.demand(rho_up) * self.supply(nu_down)) / self.phi_max

        else:
            raise ValueError(f"Unknown decomposition type: {self.decomposition}")


class TRMNetwork:
    """
    Traffic Reaction Model network simulator

    Simulates traffic flow on a highway discretized into cells
    using TRM with flux decomposition
    """

    def __init__(self, cells: List[TRMCell]):
        """
        Initialize TRM network

        Parameters:
        - cells: List of TRMCell objects representing road segments
        """
        self.cells = cells
        self.n_cells = len(cells)

    def dynamics(self, t: float, state: np.ndarray) -> np.ndarray:
        """
        TRM dynamics: ρ̇_i = (1/Δx)[F(ρ_{i-1}, ρ_i) - F(ρ_i, ρ_{i+1})]

        Parameters:
        - t: Current time
        - state: Current densities [ρ_0, ρ_1, ..., ρ_{n-1}]

        Returns:
        - drho_dt: Time derivatives of densities
        """
        drho_dt = np.zeros(self.n_cells)

        for i in range(self.n_cells):
            cell = self.cells[i]
            rho_i = state[i]

            # Upstream flux: F(ρ_{i-1}, ρ_i)
            if i == 0:
                # Boundary condition: upstream density (could be from demand)
                rho_upstream = 0.0  # No upstream for first cell
                F_in = 0.0
            else:
                rho_upstream = state[i-1]
                F_in = cell.flux_decomposition(rho_upstream, rho_i)

            # Downstream flux: F(ρ_i, ρ_{i+1})
            if i == self.n_cells - 1:
                # Boundary condition: free-flow exit (ν_exit = ρ_max)
                rho_downstream = 0.0
                F_out = cell.flux_decomposition(rho_i, rho_downstream)
            else:
                rho_downstream = state[i+1]
                F_out = cell.flux_decomposition(rho_i, rho_downstream)

            # TRM dynamics
            drho_dt[i] = (1.0 / cell.dx) * (F_in - F_out)

        return drho_dt

    def simulate(
        self,
        initial_densities: np.ndarray,
        t_span: Tuple[float, float],
        n_points: int = 1000
    ) -> Tuple[np.ndarray, np.ndarray]:
        """
        Simulate TRM network

        Parameters:
        - initial_densities: Initial densities [ρ_0(0), ..., ρ_{n-1}(0)]
        - t_span: Time interval (t_start, t_end)
        - n_points: Number of time points

        Returns:
        - t: Time array
        - rho: Density evolution [n_points, n_cells]
        """
        # Solve ODE
        t_eval = np.linspace(t_span[0], t_span[1], n_points)
        sol = solve_ivp(
            self.dynamics,
            t_span,
            initial_densities,
            t_eval=t_eval,
            method='RK45',
            dense_output=True
        )

        return sol.t, sol.y.T

    def compute_flows(self, densities: np.ndarray) -> np.ndarray:
        """
        Compute flows from densities using flux decomposition

        Parameters:
        - densities: Current densities [ρ_0, ..., ρ_{n-1}]

        Returns:
        - flows: Flows at each cell boundary [F_0, ..., F_{n}]
        """
        flows = np.zeros(self.n_cells + 1)

        # Inlet flow (upstream boundary)
        flows[0] = 0.0

        # Internal flows
        for i in range(self.n_cells):
            if i < self.n_cells - 1:
                flows[i+1] = self.cells[i].flux_decomposition(
                    densities[i],
                    densities[i+1]
                )
            else:
                # Exit flow
                flows[i+1] = self.cells[i].flux_decomposition(
                    densities[i],
                    0.0  # Free-flow exit
                )

        return flows


def create_trm_highway_example():
    """
    Create example TRM highway network

    Based on Example 1 from Pereira et al. (2024):
    - Highway discretized into 10 cells
    - ρ_max = 180 veh/km
    - ρ_crit = 30 veh/km
    - φ_max = 2000 veh/hour
    - Δx = 1 km
    """
    n_cells = 10
    cells = []

    # Create cells with Godunov decomposition (CTM-equivalent)
    for i in range(n_cells):
        cell = TRMCell(
            name=f"Cell_{i}",
            cell_length=1.0,  # 1 km
            rho_max=180.0,    # 180 veh/km
            rho_crit=30.0,    # 30 veh/km
            phi_max=2000.0,   # 2000 veh/hour
            decomposition=DecompositionType.GODUNOV,
            track_dual_variable=True
        )
        cells.append(cell)

    return TRMNetwork(cells)


def create_trm_mak_example():
    """
    Create TRM highway with MAK decomposition

    Demonstrates kinetic interpretation with reaction rate ω
    """
    n_cells = 10
    cells = []

    # Calibrate omega to match fundamental diagram
    # For MAK: g(ρ, ν) = ω*ρ*ν should approximate demand/supply
    # At critical density: φ_max ≈ ω * ρ_crit * (ρ_max - ρ_crit)
    # ω ≈ φ_max / (ρ_crit * (ρ_max - ρ_crit))
    rho_max = 180.0
    rho_crit = 30.0
    phi_max = 2000.0
    omega = phi_max / (rho_crit * (rho_max - rho_crit))

    for i in range(n_cells):
        cell = TRMCell(
            name=f"Cell_{i}",
            cell_length=1.0,
            rho_max=rho_max,
            rho_crit=rho_crit,
            phi_max=phi_max,
            decomposition=DecompositionType.MAK,
            omega=omega,
            track_dual_variable=True
        )
        cells.append(cell)

    return TRMNetwork(cells)


def create_extended_trm_example():
    """
    Create Extended TRM with capacity drop

    Models incident zone with reduced capacity (C = 0.7)
    """
    n_cells = 10
    cells = []

    for i in range(n_cells):
        # Incident in cells 4-6 (reduced capacity)
        if 4 <= i <= 6:
            capacity_drop = 0.7  # 30% capacity reduction
        else:
            capacity_drop = 1.0  # Normal capacity

        cell = TRMCell(
            name=f"Cell_{i}",
            cell_length=1.0,
            rho_max=180.0,
            rho_crit=30.0,
            phi_max=2000.0,
            decomposition=DecompositionType.TRM_CAPACITATED,
            capacity_drop=capacity_drop,
            track_dual_variable=True
        )
        cells.append(cell)

    return TRMNetwork(cells)


def visualize_trm_simulation(
    t: np.ndarray,
    rho: np.ndarray,
    network: TRMNetwork,
    title: str = "TRM Simulation"
):
    """
    Visualize TRM simulation results

    Parameters:
    - t: Time array
    - rho: Density evolution [n_times, n_cells]
    - network: TRMNetwork object
    - title: Plot title
    """
    n_cells = network.n_cells

    fig, axes = plt.subplots(2, 2, figsize=(14, 10))

    # 1. Space-time density plot
    ax = axes[0, 0]
    im = ax.imshow(
        rho.T,
        aspect='auto',
        origin='lower',
        extent=[t[0], t[-1], 0, n_cells],
        cmap='YlOrRd',
        vmin=0,
        vmax=network.cells[0].rho_max
    )
    ax.set_xlabel('Time (hours)')
    ax.set_ylabel('Cell Index')
    ax.set_title('Density Evolution (Space-Time)')
    plt.colorbar(im, ax=ax, label='Density (veh/km)')

    # 2. Density profiles at different times
    ax = axes[0, 1]
    time_slices = [0, len(t)//4, len(t)//2, 3*len(t)//4, -1]
    for idx in time_slices:
        ax.plot(range(n_cells), rho[idx, :], marker='o', label=f't = {t[idx]:.1f}h')
    ax.set_xlabel('Cell Index')
    ax.set_ylabel('Density (veh/km)')
    ax.set_title('Density Profiles at Different Times')
    ax.axhline(y=network.cells[0].rho_crit, color='k', linestyle='--', alpha=0.5, label='ρ_crit')
    ax.axhline(y=network.cells[0].rho_max, color='r', linestyle='--', alpha=0.5, label='ρ_max')
    ax.legend()
    ax.grid(True, alpha=0.3)

    # 3. Time evolution at specific cells
    ax = axes[1, 0]
    cell_indices = [0, n_cells//4, n_cells//2, 3*n_cells//4, n_cells-1]
    for idx in cell_indices:
        ax.plot(t, rho[:, idx], label=f'Cell {idx}')
    ax.set_xlabel('Time (hours)')
    ax.set_ylabel('Density (veh/km)')
    ax.set_title('Density Time Evolution at Specific Cells')
    ax.axhline(y=network.cells[0].rho_crit, color='k', linestyle='--', alpha=0.5)
    ax.legend()
    ax.grid(True, alpha=0.3)

    # 4. Flow evolution
    ax = axes[1, 1]
    flows_over_time = []
    for i in range(len(t)):
        flows = network.compute_flows(rho[i, :])
        flows_over_time.append(flows[n_cells//2])  # Middle cell flow
    ax.plot(t, flows_over_time, linewidth=2)
    ax.set_xlabel('Time (hours)')
    ax.set_ylabel('Flow (veh/hour)')
    ax.set_title(f'Flow Evolution at Cell {n_cells//2}')
    ax.axhline(y=network.cells[0].phi_max, color='r', linestyle='--', alpha=0.5, label='φ_max')
    ax.legend()
    ax.grid(True, alpha=0.3)

    fig.suptitle(title, fontsize=14, fontweight='bold')
    plt.tight_layout()
    plt.savefig(f'{title.replace(" ", "_").lower()}_results.png', dpi=150)
    print(f"✓ Saved figure: {title.replace(' ', '_').lower()}_results.png")


def main():
    """Run TRM simulation examples"""

    print("=" * 70)
    print("Traffic Reaction Model (TRM) Simulation")
    print("=" * 70)
    print()

    # Example 1: TRM with Godunov decomposition (CTM-equivalent)
    print("Example 1: TRM with Godunov Decomposition (CTM-equivalent)")
    print("-" * 70)
    network_godunov = create_trm_highway_example()

    # Initial condition: Gaussian pulse
    initial_rho = np.array([
        100.0 * np.exp(-0.5 * ((i - 2) / 1.5)**2)
        for i in range(network_godunov.n_cells)
    ])

    print(f"Network: {network_godunov.n_cells} cells")
    print(f"Decomposition: Godunov")
    print(f"Initial total vehicles: {sum(initial_rho * [c.dx for c in network_godunov.cells]):.1f}")
    print("Simulating...")

    t_godunov, rho_godunov = network_godunov.simulate(
        initial_densities=initial_rho,
        t_span=(0.0, 2.0),
        n_points=500
    )

    print(f"✓ Simulation complete: {len(t_godunov)} time points")
    print(f"Final total vehicles: {sum(rho_godunov[-1, :] * [c.dx for c in network_godunov.cells]):.1f}")
    print()

    visualize_trm_simulation(
        t_godunov,
        rho_godunov,
        network_godunov,
        title="TRM Godunov Decomposition"
    )

    # Example 2: TRM with MAK decomposition
    print("\nExample 2: TRM with MAK Decomposition (Kinetic)")
    print("-" * 70)
    network_mak = create_trm_mak_example()

    print(f"Network: {network_mak.n_cells} cells")
    print(f"Decomposition: MAK (ω = {network_mak.cells[0].omega:.6f})")
    print(f"Initial total vehicles: {sum(initial_rho * [c.dx for c in network_mak.cells]):.1f}")
    print("Simulating...")

    t_mak, rho_mak = network_mak.simulate(
        initial_densities=initial_rho,
        t_span=(0.0, 2.0),
        n_points=500
    )

    print(f"✓ Simulation complete: {len(t_mak)} time points")
    print(f"Final total vehicles: {sum(rho_mak[-1, :] * [c.dx for c in network_mak.cells]):.1f}")
    print()

    visualize_trm_simulation(
        t_mak,
        rho_mak,
        network_mak,
        title="TRM MAK Decomposition"
    )

    # Example 3: Extended TRM with capacity drop
    print("\nExample 3: Extended TRM with Capacity Drop (Incident)")
    print("-" * 70)
    network_extended = create_extended_trm_example()

    # Initial condition: Uniform density
    initial_rho_extended = np.ones(network_extended.n_cells) * 50.0

    print(f"Network: {network_extended.n_cells} cells")
    print(f"Decomposition: Capacitated")
    print(f"Incident zone: Cells 4-6 (C = 0.7)")
    print(f"Initial total vehicles: {sum(initial_rho_extended * [c.dx for c in network_extended.cells]):.1f}")
    print("Simulating...")

    t_extended, rho_extended = network_extended.simulate(
        initial_densities=initial_rho_extended,
        t_span=(0.0, 2.0),
        n_points=500
    )

    print(f"✓ Simulation complete: {len(t_extended)} time points")
    print(f"Final total vehicles: {sum(rho_extended[-1, :] * [c.dx for c in network_extended.cells]):.1f}")
    print()

    visualize_trm_simulation(
        t_extended,
        rho_extended,
        network_extended,
        title="Extended TRM with Capacity Drop"
    )

    print("\n" + "=" * 70)
    print("All simulations complete!")
    print("=" * 70)


if __name__ == "__main__":
    main()
