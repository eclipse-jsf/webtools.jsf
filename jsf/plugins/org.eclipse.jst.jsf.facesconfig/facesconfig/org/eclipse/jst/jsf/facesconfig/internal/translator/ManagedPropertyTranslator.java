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
public class ManagedPropertyTranslator extends Translator {

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public ManagedPropertyTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new DescriptionTranslator("description", facesPackage.getManagedPropertyType_Description()), //$NON-NLS-1$
			new DisplayNameTranslator("display-name", facesPackage.getManagedPropertyType_DisplayName()), //$NON-NLS-1$
			new IconTranslator("icon", facesPackage.getManagedPropertyType_Icon()), //$NON-NLS-1$
			new PropertyNameTranslator("property-name", facesPackage.getManagedPropertyType_PropertyName()), //$NON-NLS-1$
			new PropertyClassTranslator("property-class", facesPackage.getManagedPropertyType_PropertyClass()), //$NON-NLS-1$
			new MapEntriesTranslator("map-entries", facesPackage.getManagedPropertyType_MapEntries()), //$NON-NLS-1$
			new NullValueTranslator("null-value", facesPackage.getManagedPropertyType_NullValue()), //$NON-NLS-1$
			new ValueTranslator("value", facesPackage.getManagedPropertyType_Value()), //$NON-NLS-1$
			new ListEntriesTranslator("list-entries", facesPackage.getManagedPropertyType_ListEntries()), //$NON-NLS-1$
			new Translator("id", facesPackage.getManagedPropertyType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
