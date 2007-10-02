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
public class AttributeTranslator extends Translator {

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public AttributeTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new DescriptionTranslator("description", facesPackage.getAttributeType_Description()), //$NON-NLS-1$
			new DisplayNameTranslator("display-name", facesPackage.getAttributeType_DisplayName()), //$NON-NLS-1$
			new IconTranslator("icon", facesPackage.getAttributeType_Icon()), //$NON-NLS-1$
			new AttributeNameTranslator("attribute-name", facesPackage.getAttributeType_AttributeName()), //$NON-NLS-1$
			new AttributeClassTranslator("attribute-class", facesPackage.getAttributeType_AttributeClass()), //$NON-NLS-1$
			new DefaultValueTranslator("default-value", facesPackage.getAttributeType_DefaultValue()), //$NON-NLS-1$
			new SuggestedValueTranslator("suggested-value", facesPackage.getAttributeType_SuggestedValue()), //$NON-NLS-1$
			new AttributeExtensionTranslator("attribute-extension", facesPackage.getAttributeType_AttributeExtension()), //$NON-NLS-1$
			new Translator("id", facesPackage.getAttributeType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
