package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * Validator the <application> node of the app config model
 * @author cbateman
 *
 */
public class ApplicationValidationVisitor extends EObjectValidationVisitor 
{

    /**
     * Default constructure
     * @param version 
     */
    public ApplicationValidationVisitor(String version) 
    {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_Application(), version);
    }

    protected void doValidate(EObject object, List messages, IFile file) {
        // nothing to validate
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
                // TODO: validate message and resource bundles
                // TODO: validate default render kit id
                // TODO: validate EL resolver
                new ActionListenerValidationVisitor(getVersion()),
                new NavigationHandlerValidationVisitor(getVersion()),
                new ViewHandlerValidationVisitor(getVersion()),
                new StateManagerValidationVisitior(getVersion()),
                new PropertyResolverValidationVisitor(getVersion()),
                new VariableResolverValidationVisitor(getVersion())
        };
    }

    private static class ActionListenerValidationVisitor extends ApplicationClassNameBasedValidationVisitor
    {
        /**
         * @param version 
         * @param structuralFeature
         */
        public ActionListenerValidationVisitor(final String version) {
            super(FacesConfigPackage.eINSTANCE.getApplicationType_ActionListener(),
                    version,"javax.faces.event.ActionListener");
        }
    }
    
    private static class NavigationHandlerValidationVisitor extends ApplicationClassNameBasedValidationVisitor
    {
        /**
         * @param version 
         * @param structuralFeature
         */
        public NavigationHandlerValidationVisitor(final String version) {
            super(FacesConfigPackage.eINSTANCE.getApplicationType_NavigationHandler(),
                    version,"javax.faces.application.NavigationHandler");
        }
    }
    
    private static class ViewHandlerValidationVisitor extends ApplicationClassNameBasedValidationVisitor
    {
        ViewHandlerValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getApplicationType_ViewHandler(),
                    version,"javax.faces.application.ViewHandler");
        }
    }
    
    private static class StateManagerValidationVisitior extends ApplicationClassNameBasedValidationVisitor
    {
        StateManagerValidationVisitior(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getApplicationType_StateManager(),
                    version,"javax.faces.application.StateManager");
        }    
    }
    
    private static class PropertyResolverValidationVisitor extends ApplicationClassNameBasedValidationVisitor
    {
        PropertyResolverValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getApplicationType_PropertyResolver(),
                    version,"javax.faces.el.PropertyResolver");
        }

        protected void doValidate(EObject object, List messages, IFile file) {
            super.doValidate(object, messages, file);
            
            // if this version greater than 1.1 then property resolvers are
            // deprecated in favour of el-resolvers
            final String version = getVersion();
            if (!"1.0".equals(version) && !"1.1".equals(version))
            {
                addMessageInfo(messages, 
                        DiagnosticFactory.create_API_DEPRECATED_AFTER_VERSION_ID
                            (getInstanceOf(), "1.1", "javax.el.ELResolver")
                        , object, file);
            }
        }
    }

    private static class VariableResolverValidationVisitor extends ApplicationClassNameBasedValidationVisitor
    {
        VariableResolverValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getApplicationType_VariableResolver(),
                    version, "javax.faces.el.VariableResolver");
        }

        protected void doValidate(EObject object, List messages, IFile file) {
            super.doValidate(object, messages, file);
            // if this version greater than 1.1 then property resolvers are
            // deprecated in favour of el-resolvers
            final String version = getVersion();
            if (!"1.0".equals(version) && !"1.1".equals(version))
            {
                addMessageInfo(messages, 
                        DiagnosticFactory.create_API_DEPRECATED_AFTER_VERSION_ID
                            (getInstanceOf(), "1.1", "javax.el.ELResolver")
                        , object, file);
            }

        }
    }

    
    private abstract static class ApplicationClassNameBasedValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        private final String _instanceOf;
        
        /**
         * @param feature 
         * @param version 
         * @param instanceOf 
         */
        protected ApplicationClassNameBasedValidationVisitor(EStructuralFeature feature,
                final String version, final String instanceOf) {
            super(feature, version);
            _instanceOf = instanceOf;
        }

        protected String getFullyQualifiedName(EObject eobj) 
        {
            EStructuralFeature feature = eobj.eClass().getEStructuralFeature("textContent");
            
            if (feature != null)
            {
                return (String)eobj.eGet(feature);
            }
            
            return null;
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // no children
            return NO_CHILDREN;
        }

        protected String getInstanceOf() {
            return _instanceOf;
        }
    }

}
