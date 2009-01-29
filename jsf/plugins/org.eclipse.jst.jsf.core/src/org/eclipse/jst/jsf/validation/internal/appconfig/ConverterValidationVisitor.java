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

        @Override
        protected boolean mustBeClass() {
            // can't be an enum
            return true;
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
            String className = ((ConverterForClassType)eobj).getTextContent();
            String typeName = AppConfigValidationUtil.getBaseType(className);
            if (typeName == null)
            {
                return className;
            }
            return typeName;
        }

        protected String getInstanceOf() {
            // no instanceof enforcement
            return null;
        }

        protected EObjectValidationVisitor[] getChildNodeValidators() {
            return NO_CHILDREN;
        }

        @Override
        protected boolean mustBeClass() {
            // could be a converter for all instances of an enum type
            return false;
        }
    }

}
