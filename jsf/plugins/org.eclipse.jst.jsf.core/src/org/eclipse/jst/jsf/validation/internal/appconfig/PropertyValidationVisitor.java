package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.common.internal.provisional.util.JDTBeanIntrospector;
import org.eclipse.jst.jsf.common.internal.provisional.util.JDTBeanProperty;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyNameType;

/**
 * Validates property's
 * 
 */
public class PropertyValidationVisitor extends EObjectValidationVisitor 
{
    private final EStructuralFeature    _parentClassName;
    
    /**
     * @param feature
     * @param parentClassName 
     * @param version
     */
    public PropertyValidationVisitor(EStructuralFeature feature, EStructuralFeature parentClassName, String version) {
        // this validator can be attached to numerous parents so it
        // cannot hard-code its feature
        super(feature,version);
        _parentClassName = parentClassName;
    }

    protected void doValidate(EObject object, List messages, IFile file) {
        // validate the class type here because we need knowledge
        // of the property name to do it
        //TODO:
//         final PropertyType property = (PropertyType) object;
//         final String propertyClass = 
//             property.getPropertyClass().getTextContent();
//         final String propertySignature =
//             validateProperty(property.getPropertyName()
//                     , file.getProject(), _parentClassType);
//         
//         if (propertySignature != null
//                 && Signature.)
//         {
//             Signature.create
//         }
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
             new PropertyNameValidationVisitor(FacesConfigPackage.eINSTANCE.getPropertyType_PropertyName(),
                     _parentClassName, getVersion())
        };
    }

    static String validateProperty(PropertyNameType object, IProject project, String parentClassType)
    {
        String signatureBeanProperty = null;
        try
        {
           IJavaProject javaProject = JavaCore.create(project);
           IType type = javaProject.findType(parentClassType);
           
           if (type != null)
           {
               final JDTBeanIntrospector introspector =
                   new JDTBeanIntrospector(type);
               
               final Map properties = introspector.getProperties();

               final String propertyName = object.getTextContent(); 
               if (properties.containsKey(propertyName))
               {
                   final JDTBeanProperty beanProperty = 
                       (JDTBeanProperty) properties.get(propertyName);
                   signatureBeanProperty = 
                       beanProperty.getTypeSignature();
               }
           }
        }
        catch(JavaModelException jme)
        {
            JSFCorePlugin
                .log(new Exception(jme), 
                     "Problem validating on parent: "+parentClassType);
        }

        return signatureBeanProperty;
    }
}
