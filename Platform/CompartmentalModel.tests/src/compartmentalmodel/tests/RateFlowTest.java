/**
 */
package compartmentalmodel.tests;

import junit.textui.TestRunner;

import compartmentalmodel.RateFlow;
import compartmentalmodel.CompartmentalmodelFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Rate Flow</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class RateFlowTest extends FlowTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(RateFlowTest.class);
	}

	/**
	 * Constructs a new Rate Flow test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RateFlowTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Rate Flow test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected RateFlow getFixture() {
		return (RateFlow)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CompartmentalmodelFactory.eINSTANCE.createRateFlow());
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

} //RateFlowTest
