/**
 */
package compartmentalmodel.tests;

import junit.textui.TestRunner;

import compartmentalmodel.ContactFlow;
import compartmentalmodel.CompartmentalmodelFactory;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Contact Flow</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ContactFlowTest extends FlowTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ContactFlowTest.class);
	}

	/**
	 * Constructs a new Contact Flow test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ContactFlowTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Contact Flow test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ContactFlow getFixture() {
		return (ContactFlow)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(CompartmentalmodelFactory.eINSTANCE.createContactFlow());
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

} //ContactFlowTest
