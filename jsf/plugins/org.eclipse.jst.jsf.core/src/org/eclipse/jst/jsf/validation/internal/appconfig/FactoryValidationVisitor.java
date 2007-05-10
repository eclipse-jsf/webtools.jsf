/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType;

/**
 * @author cbateman
 *
 */
public class FactoryValidationVisitor extends EObjectValidationVisitor 
{
    /**
     * @param version
     */
    public FactoryValidationVisitor(String version) {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_Factory()
                ,version);
    }

    protected void doValidate(EObject object, List messages, IFile file) {
        // nothing to do except vaildate children
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() 
    {
        return new EObjectValidationVisitor[]
        {
                new ApplicationFactoryValidationVisitor(getVersion())
                , new FacesContextFactoryValidationVisitor(getVersion())
                , new LifecycleFactoryValidationVisitor(getVersion())
                , new RenderkitFactoryValidationVisitor(getVersion())
        };
    }

    private static class ApplicationFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        ApplicationFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_ApplicationFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((ApplicationFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return "javax.faces.application.ApplicationFactory";
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }
        
    }
    
    private static class FacesContextFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        FacesContextFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_FacesContextFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((FacesContextFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return "javax.faces.context.FacesContextFactory";
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }
        
    }

    private static class LifecycleFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        LifecycleFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_LifecycleFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((LifecycleFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return "javax.faces.lifecycle.LifecycleFactory";
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }
        
    }

    private static class RenderkitFactoryValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        RenderkitFactoryValidationVisitor(final String version)
        {
            super(FacesConfigPackage.eINSTANCE.getFactoryType_RenderKitFactory(),
                    version);
        }
        
        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((RenderKitFactoryType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return "javax.faces.render.RenderKitFactory";
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            // none
            return NO_CHILDREN;
        }
        
    }

}
