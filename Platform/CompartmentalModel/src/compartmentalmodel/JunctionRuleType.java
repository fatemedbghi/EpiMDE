/**
 */
package compartmentalmodel;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Junction Rule Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Rule for computing flows at network junctions in traffic models. NONE is used for disease models which don't have junction constraints.
 * <!-- end-model-doc -->
 * @see compartmentalmodel.CompartmentalmodelPackage#getJunctionRuleType()
 * @model
 * @generated
 */
public enum JunctionRuleType implements Enumerator {
	/**
	 * The '<em><b>NONE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No junction rule (default for disease models). Flows are computed directly from rates without capacity constraints.
	 * <!-- end-model-doc -->
	 * @see #NONE_VALUE
	 * @generated
	 * @ordered
	 */
	NONE(0, "NONE", "NONE"),

	/**
	 * The '<em><b>PPFIFO</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Proportional Priority, First-In-First-Out rule (Coogan & Arcak 2015). Computes α_v(ρ) to scale demands proportionally while respecting supply constraints: α_v(ρ) = max{α ∈ [0,1] : α ∑_j β_jk Φ^out_j ≤ Φ^in_k ∀k}.
	 * <!-- end-model-doc -->
	 * @see #PPFIFO_VALUE
	 * @generated
	 * @ordered
	 */
	PPFIFO(1, "PPFIFO", "PPFIFO"),

	/**
	 * The '<em><b>CUSTOM</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * User-defined custom junction rule.
	 * <!-- end-model-doc -->
	 * @see #CUSTOM_VALUE
	 * @generated
	 * @ordered
	 */
	CUSTOM(2, "CUSTOM", "CUSTOM");

	/**
	 * The '<em><b>NONE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * No junction rule (default for disease models). Flows are computed directly from rates without capacity constraints.
	 * <!-- end-model-doc -->
	 * @see #NONE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int NONE_VALUE = 0;

	/**
	 * The '<em><b>PPFIFO</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Proportional Priority, First-In-First-Out rule (Coogan & Arcak 2015). Computes α_v(ρ) to scale demands proportionally while respecting supply constraints: α_v(ρ) = max{α ∈ [0,1] : α ∑_j β_jk Φ^out_j ≤ Φ^in_k ∀k}.
	 * <!-- end-model-doc -->
	 * @see #PPFIFO
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int PPFIFO_VALUE = 1;

	/**
	 * The '<em><b>CUSTOM</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * User-defined custom junction rule.
	 * <!-- end-model-doc -->
	 * @see #CUSTOM
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int CUSTOM_VALUE = 2;

	/**
	 * An array of all the '<em><b>Junction Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final JunctionRuleType[] VALUES_ARRAY =
		new JunctionRuleType[] {
			NONE,
			PPFIFO,
			CUSTOM,
		};

	/**
	 * A public read-only list of all the '<em><b>Junction Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<JunctionRuleType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Junction Rule Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static JunctionRuleType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JunctionRuleType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Junction Rule Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static JunctionRuleType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			JunctionRuleType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Junction Rule Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static JunctionRuleType get(int value) {
		switch (value) {
			case NONE_VALUE: return NONE;
			case PPFIFO_VALUE: return PPFIFO;
			case CUSTOM_VALUE: return CUSTOM;
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
	private JunctionRuleType(int value, String name, String literal) {
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
	
} //JunctionRuleType
