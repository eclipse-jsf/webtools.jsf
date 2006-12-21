package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * Validates the converter
 */
public class ConverterValidationVisitor extends EObjectValidationVisitor
{

    /**
     * @param version
     */
    public ConverterValidationVisitor(final String version)
    {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_Converter(),
                version);
    }
    
    protected void doValidate(EObject object, List messages, IFile file) {
        // nothing in the tag to validate
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
            new ConverterClassValidationVisitor(getVersion())
            , new AttributeValidationVisitor(FacesConfigPackage.eINSTANCE.getConverterType_Attribute(), getVersion())
            , new PropertyValidationVisitor
                (FacesConfigPackage.eINSTANCE.getConverterType_Property()
                ,FacesConfigPackage.eINSTANCE.getConverterType_ConverterClass()
                ,getVersion())
            , new ConverterForClassValidationVisitor(getVersion())
        };
    }

    private static class ConverterClassValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        ConverterClassValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getConverterType_ConverterClass(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((ConverterClassType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return "javax.faces.convert.Converter";
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            return NO_CHILDREN;
        }
    }
    
    private static class ConverterForClassValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        ConverterForClassValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getConverterType_ConverterForClass(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((ConverterForClassType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            // no instanceof enforcement
            return null;
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            return NO_CHILDREN;
        }
    }

}
