/**
 */
package compartmentalmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Supply Function Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Type of supply/demand function for traffic links. TRIANGULAR is the standard model from traffic flow theory (Coogan & Arcak 2015, Daganzo 1994).
 * <!-- end-model-doc -->
 * @see compartmentalmodel.CompartmentalmodelPackage#getSupplyFunctionType()
 * @model
 * @generated
 */
public enum SupplyFunctionType implements Enumerator {
	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No supply function (default for disease models).
	 * <!-- end-model-doc -->
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "NONE", "NONE"),

	/**
	 * The '<em><b>TRIANGULAR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Triangular fundamental diagram: demand increases linearly until ρ^crit, supply decreases linearly from ρ^crit to ρ^jam. Standard traffic model.
	 * <!-- end-model-doc -->
	 * @see #TRIANGULAR_VALUE
	 * @generated
	 * @ordered
	 */
	TRIANGULAR(1, "TRIANGULAR", "TRIANGULAR"),

	/**
	 * The '<em><b>LINEAR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Simplified linear model (for testing or special cases).
	 * <!-- end-model-doc -->
	 * @see #LINEAR_VALUE
	 * @generated
	 * @ordered
	 */
	LINEAR(2, "LINEAR", "LINEAR"),

	/**
	 * The '<em><b>CUSTOM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * User-defined custom supply/demand functions.
	 * <!-- end-model-doc -->
	 * @see #CUSTOM_VALUE
	 * @generated
	 * @ordered
	 */
	CUSTOM(3, "CUSTOM", "CUSTOM"), /**
	 * The '<em><b>TRM MAK</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Traffic Reaction Model with Mass Action Kinetic decomposition: g(ρ, ν) = ω·ρ·ν where ν = ρ^max - ρ. Uses parameter ω (omega) for reaction rate. Kinetic/compartmental interpretation of traffic flow (Pereira et al. 2024).
	 * <!-- end-model-doc -->
	 * @see #TRM_MAK_VALUE
	 * @generated
	 * @ordered
	 */
	TRM_MAK(4, "TRM_MAK", "TRM_MAK"), /**
	 * The '<em><b>TRM CAPACITATED</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Traffic Reaction Model with Capacitated decomposition: g(ρ, ν) = D(ρ)·Q(ρ^max - ν)/Φ^max. Multiplicative coupling of demand and supply. Optional parameter C for capacity drop (Pereira et al. 2024).
	 * <!-- end-model-doc -->
	 * @see #TRM_CAPACITATED_VALUE
	 * @generated
	 * @ordered
	 */
	TRM_CAPACITATED(5, "TRM_CAPACITATED", "TRM_CAPACITATED");

	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No supply function (default for disease models).
	 * <!-- end-model-doc -->
	 * @see #NONE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;

	/**
	 * The '<em><b>TRIANGULAR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Triangular fundamental diagram: demand increases linearly until ρ^crit, supply decreases linearly from ρ^crit to ρ^jam. Standard traffic model.
	 * <!-- end-model-doc -->
	 * @see #TRIANGULAR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TRIANGULAR_VALUE = 1;

	/**
	 * The '<em><b>LINEAR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Simplified linear model (for testing or special cases).
	 * <!-- end-model-doc -->
	 * @see #LINEAR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int LINEAR_VALUE = 2;

	/**
	 * The '<em><b>CUSTOM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * User-defined custom supply/demand functions.
	 * <!-- end-model-doc -->
	 * @see #CUSTOM
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_VALUE = 3;

	/**
	 * The '<em><b>TRM MAK</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Traffic Reaction Model with Mass Action Kinetic decomposition: g(ρ, ν) = ω·ρ·ν where ν = ρ^max - ρ. Uses parameter ω (omega) for reaction rate. Kinetic/compartmental interpretation of traffic flow (Pereira et al. 2024).
	 * <!-- end-model-doc -->
	 * @see #TRM_MAK
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TRM_MAK_VALUE = 4;

	/**
	 * The '<em><b>TRM CAPACITATED</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Traffic Reaction Model with Capacitated decomposition: g(ρ, ν) = D(ρ)·Q(ρ^max - ν)/Φ^max. Multiplicative coupling of demand and supply. Optional parameter C for capacity drop (Pereira et al. 2024).
	 * <!-- end-model-doc -->
	 * @see #TRM_CAPACITATED
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int TRM_CAPACITATED_VALUE = 5;

	/**
	 * An array of all the '<em><b>Supply Function Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SupplyFunctionType[] VALUES_ARRAY =
		new SupplyFunctionType[] {
			NONE,
			TRIANGULAR,
			LINEAR,
			CUSTOM,
			TRM_MAK,
			TRM_CAPACITATED,
		};

	/**
	 * A public read-only list of all the '<em><b>Supply Function Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<SupplyFunctionType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Supply Function Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SupplyFunctionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SupplyFunctionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Supply Function Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SupplyFunctionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SupplyFunctionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Supply Function Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static SupplyFunctionType get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
			case TRIANGULAR_VALUE: return TRIANGULAR;
			case LINEAR_VALUE: return LINEAR;
			case CUSTOM_VALUE: return CUSTOM;
			case TRM_MAK_VALUE: return TRM_MAK;
			case TRM_CAPACITATED_VALUE: return TRM_CAPACITATED;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SupplyFunctionType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //SupplyFunctionType
