/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: ComponentMappingXMLProcessor.java,v 1.1 2008/05/12 17:42:21 cbateman Exp $
 */
package org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.util;

import java.util.Map;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

import org.eclipse.jst.jsf.designtime.internal.view.mapping.viewmapping.ComponentMappingPackage;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class ComponentMappingXMLProcessor extends XMLProcessor
{

    /**
     * Public constructor to instantiate the helper.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentMappingXMLProcessor()
    {
        super((EPackage.Registry.INSTANCE));
        ComponentMappingPackage.eINSTANCE.eClass();
    }
    
    /**
     * Register for "*" and "xml" file extensions the ComponentMappingResourceFactoryImpl factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    protected Map<String, Resource.Factory> getRegistrations()
    {
        if (registrations == null)
        {
            super.getRegistrations();
            registrations.put(XML_EXTENSION, new ComponentMappingResourceFactoryImpl());
            registrations.put(STAR_EXTENSION, new ComponentMappingResourceFactoryImpl());
        }
        return registrations;
    }

} //ComponentMappingXMLProcessor
