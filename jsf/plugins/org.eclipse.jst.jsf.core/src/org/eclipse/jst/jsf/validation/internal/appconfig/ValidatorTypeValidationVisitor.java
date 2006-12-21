package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType;

/**
 * Validation visitor for the Validator faces config model sub-type
 * @author cbateman
 *
 */
public class ValidatorTypeValidationVisitor extends EObjectValidationVisitor {

    /**
     * @param version
     */
    public ValidatorTypeValidationVisitor(final String version)
    {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_Validator(),
                version);
    }
    
    protected void doValidate(EObject object, List messages, IFile file) {
        // nothing in the tag to validate
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
            new ValidatorClassValidationVisitor(getVersion()),
            new AttributeValidationVisitor(FacesConfigPackage.eINSTANCE.getValidatorType_Attribute(), getVersion()),
            new PropertyValidationVisitor
                (FacesConfigPackage.eINSTANCE.getValidatorType_Property()
                ,FacesConfigPackage.eINSTANCE.getValidatorType_ValidatorClass()
                ,getVersion()),
        };
    }

    private static class ValidatorClassValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        ValidatorClassValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getValidatorType_ValidatorClass(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((ValidatorClassType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return "javax.faces.validator.Validator";
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            return NO_CHILDREN;
        }
    }
}
