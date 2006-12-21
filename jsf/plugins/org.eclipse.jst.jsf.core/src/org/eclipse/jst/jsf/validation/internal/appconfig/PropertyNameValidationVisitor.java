package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;

/**
 * Validate the validation property name
 * 
 * @author cbateman
 */
class PropertyNameValidationVisitor extends EObjectValidationVisitor
{
    private final EStructuralFeature   _parentClassNameFeature;
    
    /**
     * @param feature 
     * @param parentClassNameFeature 
     * @param version
     */
    public PropertyNameValidationVisitor(EStructuralFeature feature,
            EStructuralFeature parentClassNameFeature, String version)
    {
        super(feature, version);
        _parentClassNameFeature = parentClassNameFeature;
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() 
    {
        return PropertyValidationVisitor.NO_CHILDREN;
    }

    protected void doValidate(EObject object, List messages, IFile file) 
    {
        final String parentClassType = getParentClassType(object);
        
        if (parentClassType != null)
        {
            String typeSig = 
                PropertyValidationVisitor.validateProperty((PropertyNameType)object
                        , file.getProject(), parentClassType);
            final String propertyName = 
                ((PropertyNameType)object).getTextContent();
            
            if (typeSig == null)
            {
                PropertyValidationVisitor.addMessageInfo(messages,
                        DiagnosticFactory
                            .create_BEAN_PROPERTY_NOT_FOUND
                                (propertyName, parentClassType)
                        , object, file);
            }
        }
    }

    private String getParentClassType(EObject object)
    {
        String parentClassType = null;
        
        // need to derive the parent's type
        final EObject property = object.eContainer();
        if (property != null)
        {
            EObject owningObject = property.eContainer();
            
            if (owningObject != null)
            {
                final EObject parentClassTypeObject =
                    (EObject) owningObject.eGet(_parentClassNameFeature);

                if (parentClassTypeObject != null)
                {
                    final EStructuralFeature feature =
                        parentClassTypeObject.eClass()
                            .getEStructuralFeature("textContent");
                    
                    if (feature != null)
                    {
                        parentClassType = (String) 
                            parentClassTypeObject.eGet(feature);
                    }
                }
            }
        }
        
        return parentClassType;
    }
}