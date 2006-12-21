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
public class ValidatorTranslator extends Translator {
	
	public ValidatorTranslator(String domNameAndPath,EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new DescriptionTranslator("description", facesPackage.getValidatorType_Description()), //$NON-NLS-1$
			new DisplayNameTranslator("display-name", facesPackage.getValidatorType_DisplayName()), //$NON-NLS-1$
			new IconTranslator("icon", facesPackage.getValidatorType_Icon()), //$NON-NLS-1$
			new ValidatorIdTranslator("validator-id", facesPackage.getValidatorType_ValidatorId()), //$NON-NLS-1$
			new ValidatorClassTranslator("validator-class", facesPackage.getValidatorType_ValidatorClass()), //$NON-NLS-1$
			new AttributeTranslator("attribute", facesPackage.getValidatorType_Attribute()), //$NON-NLS-1$
			new PropertyTranslator("property", facesPackage.getValidatorType_Property()), //$NON-NLS-1$
            new ValidatorExtensionTranslator("validator-extension", facesPackage.getValidatorType_ValidatorExtension()), //$NON-NLS-1$
			new Translator("id", facesPackage.getValidatorType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
