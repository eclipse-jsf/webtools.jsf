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
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author xnjiang
 *
 */
public class NameTranslator extends Translator {
	/**
	 * @param domNameAndPath 
	 * @param aFeature 
	 */
	public NameTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature, END_TAG_NO_INDENT);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new Translator(TEXT_ATTRIBUTE_VALUE, facesPackage.getNameType_TextContent()),
			new Translator("id", facesPackage.getNameType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
