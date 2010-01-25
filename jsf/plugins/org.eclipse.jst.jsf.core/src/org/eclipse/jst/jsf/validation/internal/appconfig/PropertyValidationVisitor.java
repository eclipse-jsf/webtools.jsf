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
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;

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


}
