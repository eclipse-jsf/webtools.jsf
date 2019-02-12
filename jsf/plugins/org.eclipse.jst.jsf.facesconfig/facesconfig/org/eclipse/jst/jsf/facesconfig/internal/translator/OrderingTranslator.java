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
 *   Oracle Corporation - added id attribute
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author xnjiang, itrimble
 *
 */
public class OrderingTranslator extends Translator {
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public OrderingTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
				new OrderingOrderingTranslator("after", facesPackage.getOrderingType_After()), //$NON-NLS-1$
				new OrderingOrderingTranslator("before", facesPackage.getOrderingType_Before()) //$NON-NLS-1$
//				new Translator("id", facesPackage.getOrderingType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
