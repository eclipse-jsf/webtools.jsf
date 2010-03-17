/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * Translator for the redirect
 *
 */
public class RedirectTranslator extends Translator {

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public RedirectTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature, EMPTY_TAG|END_TAG_NO_INDENT);
	}

	public boolean isSetMOFValue(EObject emfObject) {
		return feature != null && emfObject.eIsSet(feature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new RedirectViewParamTranslator("view-param", facesPackage.getRedirectType_ViewParam()), //$NON-NLS-1$
			new Translator("include-view-params", facesPackage.getRedirectType_IncludeViewParams(), DOM_ATTRIBUTE), //$NON-NLS-1$
			new Translator("id", facesPackage.getRedirectType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
