package compartmentalmodel.editor.actions;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationDescriptor;
import org.eclipse.sirius.business.api.query.DRepresentationQuery;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.core.runtime.NullProgressMonitor;
import java.util.Collection;
public class SiriusTest {
    public void test(Resource targetRes, EObject targetEObject, Session session) {
        Collection<DRepresentationDescriptor> descriptors = DialectManager.INSTANCE.getRepresentationDescriptors(targetEObject, session);
        for (DRepresentationDescriptor desc : descriptors) {
            DialectManager.INSTANCE.deleteRepresentation(desc, session);
        }
    }
}
