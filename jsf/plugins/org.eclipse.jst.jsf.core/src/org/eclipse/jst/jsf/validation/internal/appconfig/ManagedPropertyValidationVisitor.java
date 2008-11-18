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
import org.eclipse.jst.jsf.facesconfig.emf.ListEntriesType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedPropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Validates managed bean properties
 * 
 */
public class ManagedPropertyValidationVisitor extends EObjectValidationVisitor
{
    /**
     * @param version
     */
    public ManagedPropertyValidationVisitor(String version) {
        super(FacesConfigPackage.eINSTANCE.getManagedBeanType_ManagedProperty()
                ,version);
    }

    protected void doValidate(final EObject object, final List messages, final IFile file) 
    {
        // TODO: do a bean look-up for propertyName to verify that it a) matches the type
        // and b) exists on the bean
        final ManagedPropertyType managedPropertyType = 
            (ManagedPropertyType) object;

        if (managedPropertyType.getPropertyName() == null
                || managedPropertyType.getPropertyClass() == null)
        {
            // don't bother if the basics aren't correct
            return;
        }
        
        final String propertyName =
            managedPropertyType.getPropertyName().getTextContent();
        final String propertyClass =
            managedPropertyType.getPropertyClass().getTextContent();
        
        if (propertyName == null || propertyClass == null
                || "".equals(propertyName.trim()) //$NON-NLS-1$
                || "".equals(propertyClass.trim())) //$NON-NLS-1$
        {
            return;
        }
        
        IMessage message = null;
        EObject eObj = null;
        if (managedPropertyType.getMapEntries() != null)
        {
            eObj = managedPropertyType.getMapEntries();
            message =
                AppConfigValidationUtil
                    .validateMapEntries
                        (propertyName
                        , propertyClass
                        , (MapEntriesType) eObj
                        , file.getProject());
        }
        else if (managedPropertyType.getListEntries() != null)
        {
            eObj = managedPropertyType.getListEntries();
            message =
                AppConfigValidationUtil
                    .validateListEntries(
                        propertyName
                        , propertyClass
                        , (ListEntriesType) eObj
                        , file.getProject());
        }
        
        addMessageInfo(messages, message, eObj, file);
    }

    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
             new PropertyNameValidationVisitor(FacesConfigPackage.eINSTANCE.getManagedPropertyType_PropertyName(),
                     FacesConfigPackage.eINSTANCE.getManagedBeanType_ManagedBeanClass(), getVersion())
        };
    }
}
