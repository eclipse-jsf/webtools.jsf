/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.RendererClassType;

/**
 * Renderer validation
 *
 */
public class RendererValidationVisitor extends EObjectValidationVisitor {

    /**
     * @param version
     */
    public RendererValidationVisitor(String version)
    {
        super(FacesConfigPackage.eINSTANCE.getRenderKitType_Renderer(),
                version);
    }

    protected void doValidate(EObject object, List messages, IFile file) {
        // nothing to do
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
            new RendererClassValidationVisitor(getVersion())
            , new AttributeValidationVisitor
                (FacesConfigPackage.eINSTANCE.getRendererType_Attribute()
                        , getVersion())
        };
    }

    private static class RendererClassValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        /**
         * @param version
         */
        public RendererClassValidationVisitor(String version) 
        {
            super(FacesConfigPackage.eINSTANCE.getRendererType_RendererClass()
                    , version);
        }

        protected String getFullyQualifiedName(EObject eobj) 
        {
            return ((RendererClassType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            return "javax.faces.render.Renderer"; //$NON-NLS-1$
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // must be a class
            return true;
        }
    }
}
