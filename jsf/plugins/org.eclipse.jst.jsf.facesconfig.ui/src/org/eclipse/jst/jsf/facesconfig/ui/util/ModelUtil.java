/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.util;

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.ui.IFacesConfigConstants;

/**
 * @author Zhi-peng Zhang
 * @version
 */
public class ModelUtil {

	/**
	 * Get the super class's name of certain element. For example, a class for
	 * <action-listener> should implement interface
	 * "javax.faces.event.ActionListener".
	 * 
	 * 
	 * 
	 * @param key
	 *            the EClass object of the application child.
	 * @return the expected super type for a particular element
	 * TODO: could make common with the faces-config validator
	 */
	public static String getSuperType(Object key) {
		if (key == FacesConfigPackage.eINSTANCE.getActionListenerType()) {
			return IFacesConfigConstants.ACTION_LISTENER_INTERFACE;
		}
		if (key == FacesConfigPackage.eINSTANCE.getNavigationHandlerType()) {
			return IFacesConfigConstants.NAVIGATION_HANDLE_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getPropertyResolverType()) {
			return IFacesConfigConstants.PROPERTY_RESOLVER_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getStateManagerType()) {
			return IFacesConfigConstants.STATE_MANAGER_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getVariableResolverType()) {
			return IFacesConfigConstants.VARIABLE_RESOLVER_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getViewHandlerType()) {
			return IFacesConfigConstants.VIEW_HANDLER_SUPER_CLASS;
		}

		if (key == FacesConfigPackage.eINSTANCE.getApplicationFactoryType()) {
			return IFacesConfigConstants.APPLICATION_FACTORY_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getFacesContextFactoryType()) {
			return IFacesConfigConstants.FACES_CONTEXT_FACTORY_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getLifecycleFactoryType()) {
			return IFacesConfigConstants.LIFECYCLE_FACTORY_SUPER_CLASS;
		}
		if (key == FacesConfigPackage.eINSTANCE.getRenderKitFactoryType()) {
			return IFacesConfigConstants.RENDER_KIT_FACTORY_SUPER_CLASS;
		}

		if (key == FacesConfigPackage.eINSTANCE.getPhaseListenerType()) {
			return IFacesConfigConstants.PHASE_LISTENER_INTERFACE;
		}

		return null;
	}
	

	/**
	 * This will take the three pre-defined entities in XML 1.0 (used
	 * specifically in XML elements) and convert their character representation
	 * to the appropriate entity reference, suitable for XML element content.
	 * 
	 * @param str
	 *            <code>String</code> input to escape.
	 * @return <code>String</code> with escaped content.
	 */
	public static String escapeEntities(String str) {
		if (isEmptyString(str)) {
			return "";
		}

		StringBuffer buffer;
		char ch;
		String entity;

		buffer = null;
		for (int i = 0, n = str.length(); i < n; i++) {
			ch = str.charAt(i);
			switch (ch) {
			case '<':
				entity = "&lt;";
				break;
			case '>':
				entity = "&gt;";
				break;
			case '\'':
				entity = "&apos;";
				break;
			case '\"':
				entity = "&quot;";
				break;
			case '&':
				entity = "&amp;";
				break;
			case '\r':
				entity = "&#xD;";
				break;
			case '\t':
				entity = "&#x9;";
				break;
			case '\n':
				entity = "&#xA;";
				break;
			default:
				entity = null;
				break;
			}
			if (buffer == null) {
				if (entity != null) {
					// An entity occurred, so we'll have to use StringBuffer
					// (allocate room for it plus a few more entities).
					buffer = new StringBuffer(str.length() + 20);
					// Copy previous skipped characters and fall through
					// to pickup current character
					buffer.append(str.substring(0, i));
					buffer.append(entity);
				}
			} else {
				if (entity == null) {
					buffer.append(ch);
				} else {
					buffer.append(entity);
				}
			}
		}

		// If there were any entities, return the escaped characters
		// that we put in the StringBuffer. Otherwise, just return
		// the unmodified input string.
		return (buffer == null) ? str : buffer.toString();
	}

	/**
	 * @param str
	 * @return removes XML/HTML escaped characters from str.
	 */
	public static String unEscapeEntities(String str) {
		if (isEmptyString(str)) {
			return "";
		}

		String result = new String(str);

		result = result.replaceAll("&amp;", "&");
		result = result.replaceAll("&lt;", "<");
		result = result.replaceAll("&gt;", ">");
		result = result.replaceAll("&apos;", "\'");
		result = result.replaceAll("&quot;", "\"");
		result = result.replaceAll("&#xD;", "\r");
		result = result.replaceAll("&#x9;", "\t");
		result = result.replaceAll("&#xA;", "\n");

		return result;
	}

	private static boolean isEmptyString(String value) {
		if (value == null || value.length() == 0) {
			return true;
		}
		return false;
	}
}
