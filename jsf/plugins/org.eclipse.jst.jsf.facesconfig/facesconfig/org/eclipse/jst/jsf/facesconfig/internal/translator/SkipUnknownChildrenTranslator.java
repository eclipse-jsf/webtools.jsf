/*******************************************************************************
 * Copyright (c) 2005, 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * SkipUnknownChildrenTranslator has been implemented to temporarily allow
 * processing of application configuration resource files containing extension
 * elements (those defined with a content type of "ANY"); it essentially skips
 * all processing of child elements for which no child Translator has been
 * defined. This is not intended as a long-term solution.
 * 
 * @author Ian Trimble - Oracle
 */
public class SkipUnknownChildrenTranslator extends Translator {

	/**
	 * Creates an instance.
	 * 
	 * @param domNameAndPath DOM name and path for which this Translator is
	 * to be used.
	 * @param aFeature EStructuralFeature instance for which this Translator is
	 * to be used (may be null).
	 */
	public SkipUnknownChildrenTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#findChild(java.lang.String, java.lang.Object, int)
	 */
	public Translator findChild(String tagName, Object target, int versionID) {
		Translator childTranslator = super.findChild(tagName, target, versionID);
		if (childTranslator == null) {
			childTranslator = new SkipUnknownChildrenTranslator(tagName, (EStructuralFeature)null);
		}
		return childTranslator;
	}

}
