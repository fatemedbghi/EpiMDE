/**
 */
package compartmentalmodel.tests;

import junit.framework.TestCase;

import junit.textui.TestRunner;

import compartmentalmodel.CompartmentalmodelFactory;
import compartmentalmodel.StratumSpecificRate;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Stratum Specific Rate</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class StratumSpecificRateTest extends TestCase {

	/**
	 * The fixture for this Stratum Specific Rate test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StratumSpecificRate fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(StratumSpecificRateTest.class);
	}

	/**
	 * Constructs a new Stratum Specific Rate test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StratumSpecificRateTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Stratum Specific Rate test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(StratumSpecificRate fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Stratum Specific Rate test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StratumSpecificRate getFixture() {
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
		setFixture(CompartmentalmodelFactory.eINSTANCE.createStratumSpecificRate());
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

} //StratumSpecificRateTest
