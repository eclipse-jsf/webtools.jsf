/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - fixed getChildren()
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;


import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author xjiang, itrimble
 *
 */
public class ManagedBeanTranslator extends Translator {

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public ManagedBeanTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new DescriptionTranslator("description", facesPackage.getManagedBeanType_Description()), //$NON-NLS-1$
			new DisplayNameTranslator("display-name", facesPackage.getManagedBeanType_DisplayName()), //$NON-NLS-1$
			new IconTranslator("icon", facesPackage.getManagedBeanType_Icon()), //$NON-NLS-1$
			new ManagedBeanNameTranslator("managed-bean-name", facesPackage.getManagedBeanType_ManagedBeanName()), //$NON-NLS-1$
			new ManagedBeanClassTranslator("managed-bean-class", facesPackage.getManagedBeanType_ManagedBeanClass()), //$NON-NLS-1$
			new ManagedBeanScopeTranslator("managed-bean-scope", facesPackage.getManagedBeanType_ManagedBeanScope()), //$NON-NLS-1$
			new ManagedPropertyTranslator("managed-property", facesPackage.getManagedBeanType_ManagedProperty()), //$NON-NLS-1$
			new MapEntriesTranslator("map-entries", facesPackage.getManagedBeanType_MapEntries()), //$NON-NLS-1$
			new ListEntriesTranslator("list-entries", facesPackage.getManagedBeanType_ListEntries()), //$NON-NLS-1$
            new ManagedBeanExtensionTranslator("managed-bean-extension", facesPackage.getManagedBeanType_ManagedBeanExtension()), //$NON-NLS-1$
			new Translator("id", facesPackage.getManagedBeanType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
