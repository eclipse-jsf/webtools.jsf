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
public class NavigationCaseTranslator extends Translator {

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public NavigationCaseTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
			new DescriptionTranslator("description", facesPackage.getNavigationCaseType_Description()), //$NON-NLS-1$
			new DisplayNameTranslator("display-name", facesPackage.getNavigationCaseType_DisplayName()), //$NON-NLS-1$
			new IconTranslator("icon", facesPackage.getNavigationCaseType_Icon()), //$NON-NLS-1$
			new FromActionTranslator("from-action", facesPackage.getNavigationCaseType_FromAction()), //$NON-NLS-1$
			new FromOutcomeTranslator("from-outcome", facesPackage.getNavigationCaseType_FromOutcome()), //$NON-NLS-1$
			new ToViewIdTranslator("to-view-id", facesPackage.getNavigationCaseType_ToViewId()), //$NON-NLS-1$
			new RedirectTranslator("redirect", facesPackage.getNavigationCaseType_Redirect()), //$NON-NLS-1$
			new Translator("id", facesPackage.getNavigationCaseType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
