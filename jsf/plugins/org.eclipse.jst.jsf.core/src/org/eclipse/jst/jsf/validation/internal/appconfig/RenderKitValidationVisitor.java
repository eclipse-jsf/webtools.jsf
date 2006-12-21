package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitClassType;

/**
 * Validates the renderkit
 */
public class RenderKitValidationVisitor extends EObjectValidationVisitor 
{

    /**
     * @param version
     */
    public RenderKitValidationVisitor(String version) 
    {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_RenderKit()
                ,version);
    }

    protected void doValidate(EObject object, List messages, IFile file) 
    {
        // nothing to do 
    }

    protected EObjectValidationVisitor[] getChildNodeValidators()
    {
        return new EObjectValidationVisitor[]
        {
            new RenderKitClassValidationVisitor(getVersion()),
            new RendererValidationVisitor(getVersion())
        };
    }

    private static class RenderKitClassValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        /**
         * @param version
         */
        public RenderKitClassValidationVisitor(String version) 
        {
            super(FacesConfigPackage.eINSTANCE.getRenderKitType_RenderKitClass()
                    , version);
        }

        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((RenderKitClassType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return "javax.faces.render.RenderKit";
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            return NO_CHILDREN;
        }
    }
}
