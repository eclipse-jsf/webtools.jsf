/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;

/**
 * The util for pageflow and faces-config transforming
 * 
 * @author hmeng
 */

public class TransformUtil {

	/**
	 * To see if the faces-config element is on a valid hirachy.
	 */
	public static boolean isValidFacesConfigElement(EObject object) {
		boolean result = false;
		EObject parent = object;
		while ((parent != null)) {
			if (parent instanceof FacesConfigType) {
				result = true;
				break;
			}
			parent = parent.eContainer();
		}
		return result;
	}

	/**
	 * To see if the pageflow element is on a valid hirachy.
	 */
	public static boolean isValidPageflowElement(EObject element) {
		boolean result = false;
		EObject parent = element;
		while (parent != null) {
			if (parent instanceof Pageflow) {
				result = true;
				break;
			}
			parent = parent.eContainer();
		}
		return result;
	}

	public static boolean isValidLink(PageflowLink link) {
		return isValidPageflowElement(link) && link.getSource() != null
				&& link.getTarget() != null;
	}

	public static String getFromViewID(NavigationRuleType rule) {
		String result = "*";
		if (rule.getFromViewId() != null) {
			result = rule.getFromViewId().getTextContent();
		}
		return result;
	}

	public static String getToViewID(NavigationCaseType navCase) {
		String result = "*";
		if (navCase.getToViewId() != null) {
			result = navCase.getToViewId().getTextContent();
		}
		return result;
	}
}
