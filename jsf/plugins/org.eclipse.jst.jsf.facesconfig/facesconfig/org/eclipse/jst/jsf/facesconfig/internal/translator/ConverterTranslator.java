/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
public class ConverterTranslator extends Translator {
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public ConverterTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new DescriptionTranslator("description", facesPackage.getConverterType_Description()), //$NON-NLS-1$
			new DisplayNameTranslator("display-name", facesPackage.getConverterType_DisplayName()), //$NON-NLS-1$
			new IconTranslator("icon", facesPackage.getConverterType_Icon()), //$NON-NLS-1$
			new ConverterIdTranslator("converter-id", facesPackage.getConverterType_ConverterId()), //$NON-NLS-1$
			new ConverterForClassTranslator("converter-for-class", facesPackage.getConverterType_ConverterForClass()), //$NON-NLS-1$
			new ConverterClassTranslator("converter-class", facesPackage.getConverterType_ConverterClass()), //$NON-NLS-1$
			new AttributeTranslator("attribute", facesPackage.getConverterType_Attribute()), //$NON-NLS-1$
			new PropertyTranslator("property", facesPackage.getConverterType_Property()), //$NON-NLS-1$
            new ConverterExtensionTranslator("converter-extension", facesPackage.getConverterType_ConverterExtension()), //$NON-NLS-1$
			new Translator("id", facesPackage.getConverterType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
