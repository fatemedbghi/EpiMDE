/**
 */
package compartmentalmodel.tests;

import compartmentalmodel.CompartmentalmodelFactory;
import compartmentalmodel.ExternalSink;

import junit.framework.TestCase;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>External Sink</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExternalSinkTest extends TestCase {

	/**
	 * The fixture for this External Sink test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalSink fixture = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ExternalSinkTest.class);
	}

	/**
	 * Constructs a new External Sink test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExternalSinkTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this External Sink test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void setFixture(ExternalSink fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this External Sink test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExternalSink getFixture() {
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
		setFixture(CompartmentalmodelFactory.eINSTANCE.createExternalSink());
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

} //ExternalSinkTest
