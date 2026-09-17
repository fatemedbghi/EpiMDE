/**
 */
package compartmentalmodel.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;
import compartmentalmodel.Compartment;
import compartmentalmodel.CompartmentalmodelFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Compartment</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class CompartmentTest extends TestCase {

	/**
	 * The fixture for this Compartment test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Compartment fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(CompartmentTest.class);
	}

	/**
	 * Constructs a new Compartment test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CompartmentTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Compartment test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(Compartment fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Compartment test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Compartment getFixture() {
		return fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CompartmentalmodelFactory.eINSTANCE.createCompartment());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

} //CompartmentTest
