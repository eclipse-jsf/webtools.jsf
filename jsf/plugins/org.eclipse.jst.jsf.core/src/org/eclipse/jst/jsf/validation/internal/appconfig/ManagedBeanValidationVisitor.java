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
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.emf.MapEntriesType;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

/**
 * Managed bean validator
 * 
 * @author cbateman
 *
 */
public class ManagedBeanValidationVisitor extends EObjectValidationVisitor 
{
    /**
     * @param version
     */
    public ManagedBeanValidationVisitor(final String version) 
    {
        super(FacesConfigPackage.eINSTANCE.getFacesConfigType_ManagedBean()
                ,version);
    }

    protected void doValidate(EObject object, List messages, IFile file) 
    {
        final ManagedBeanType managedBean = (ManagedBeanType) object;
        
        // TODO: validate managedBeanName is a valid Java id
//        final String managedBeanName = 
//            managedBean.getManagedBeanName().getTextContent();
        validateScope(managedBean.getManagedBeanScope(), messages, file);
        validateClass(managedBean.getManagedBeanClass(), messages, file);
        validateEntryTypes(managedBean, messages, file);
    }
    
    private void validateScope(ManagedBeanScopeType scope, List messages, IFile file)
    {
        if (scope != null && scope.getTextContent() != null)
        {
            addMessageInfo(messages
                    , AppConfigValidationUtil.validateManagedBeanScope(scope, file, getJSFVersion())
                    , scope, file);
        }
    }
    
    private void validateClass(ManagedBeanClassType classType, List messages, IFile file)
    {
        if (classType != null)
        {
            String classTypeValue = classType.getTextContent();
            addMessageInfo(messages
                , AppConfigValidationUtil.validateClassName
                    (classTypeValue == null ? "" : classTypeValue //$NON-NLS-1$
                            , null, true,   // a managed bean must be a class 
                            file.getProject())
                            , classType, file);
        }
    }
    
    private void validateEntryTypes(ManagedBeanType managedBeanType, List messages, IFile file)
    {
        // TODO: do a bean look-up for targetName to verify that it a) matches the type
        // and b) exists on the bean
        if (managedBeanType.getManagedBeanName()!= null
                && managedBeanType.getManagedBeanClass() != null)
        {
            final String propertyName =
                managedBeanType.getManagedBeanName().getTextContent();
            final String propertyClass =
                managedBeanType.getManagedBeanClass().getTextContent();
            
            if (propertyName == null || propertyClass == null
                    || "".equals(propertyName.trim()) //$NON-NLS-1$
                    || "".equals(propertyClass.trim())) //$NON-NLS-1$
            {
                return;
            }
            
            IMessage message = null;
            EObject eObj = null;
            if (managedBeanType.getMapEntries() != null)
            {
                eObj = managedBeanType.getMapEntries();
                message =
                    AppConfigValidationUtil
                        .validateMapEntries
                            (propertyName
                            , propertyClass
                            , (MapEntriesType) eObj
                            , file.getProject());
            }
            else if (managedBeanType.getListEntries() != null)
            {
                eObj = managedBeanType.getListEntries();
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
    }
    
    protected EObjectValidationVisitor[] getChildNodeValidators() {
        return new EObjectValidationVisitor[]
        {
            new ManagedPropertyValidationVisitor(getVersion())
        };
    }
}
