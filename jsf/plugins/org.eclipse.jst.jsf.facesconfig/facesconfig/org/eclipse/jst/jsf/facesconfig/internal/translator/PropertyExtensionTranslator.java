/***************************************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others. 
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

/**
 * @author xnjiang, itrimble
 *
 */
public class PropertyExtensionTranslator extends ExtensionTypeTranslator {

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public PropertyExtensionTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}
}
