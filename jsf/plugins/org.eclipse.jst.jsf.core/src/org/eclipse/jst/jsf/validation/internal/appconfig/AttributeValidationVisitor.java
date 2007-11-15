/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeClassType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

/**
 * Validates the <attribute> element of components, converters etc.
 * 
 * @author cbateman
 *
 */
public class AttributeValidationVisitor extends EObjectValidationVisitor {

    /**
     * @param feature
     * @param version
     */
    public AttributeValidationVisitor(EStructuralFeature feature, String version) {
        // this validator can be attached to numerous parents so it
        // cannot hard-code its feature
        super(feature,version);
    }

    protected void doValidate(EObject object, List messages, IFile file) {
        // nothing to do
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
             new AttributeClassValidationVisitor(getVersion())
        };
    }

    private static class AttributeClassValidationVisitor extends ClassNameEObjectValidationVisitor
    {
        /**
         * @param version
         */
        public AttributeClassValidationVisitor(
                String version) {
            super(FacesConfigPackage.eINSTANCE.getAttributeType_AttributeClass()
                    , version);
        }

        protected String getFullyQualifiedName(EObject eobj) {
            return ((AttributeClassType)eobj).getTextContent();
        }

        protected String getInstanceOf() {
            // null since there is no instance of enforcement here
            return null;
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() 
        {
            // attribute could be a class or an enum
            return false;
        }
        
    }
}
