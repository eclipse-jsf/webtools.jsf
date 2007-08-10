/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests;

import java.io.File;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;

/**
 * Web Page Editor test utilities.
 * 
 * @author Ian Trimble - Oracle
 */
public class Utils {

	public static String[] getJSFRuntimeJarNames(String jsfVersion) {
		String[] jarNames = null;
		String dirName = JSFTestUtil.getJSFRuntimeJarsDirectory(jsfVersion);
		if (dirName != null) {
			File dir = new File(dirName);
			if (dir.exists() && dir.isDirectory()) {
				File[] jars = dir.listFiles();
				if (jars != null && jars.length > 0) {
					jarNames = new String[jars.length];
					for (int i = 0; i < jars.length; i++) {
						jarNames[i] = jars[i].getAbsolutePath();
					}
				}
			}
		}
		return jarNames;
	}

	public static boolean isJSFRuntimeJarsDirectoryValid(String jsfVersion) {
		return getJSFRuntimeJarNames(jsfVersion) != null;
	}

	public static boolean addJSFRuntimeJarsToClasspath(String jsfVersion, JSFFacetedTestEnvironment jsfFacetedTestEnv) throws CoreException {
		boolean success = false;
		String[] jarNames = getJSFRuntimeJarNames(jsfVersion);
		if (jarNames != null) {
			JSFLibraryRegistry jsfLibRegistry = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry();
			String libIDandName = "_internalJSFRuntimeLibraryV" + jsfVersion + "_";
			JSFLibrary jsfImpl = JSFCoreUtilHelper.constructJSFLib(libIDandName, libIDandName, "", jarNames, true);
			jsfLibRegistry.addJSFLibrary(jsfImpl);
			jsfFacetedTestEnv.addJSFLibraryReference(jsfImpl, true);
			success = true;
		}
		return success;
	}

	public static String getTestRequiresJSFRuntimeMessage(Class testClass, String jsfVersion) {
		StringBuffer sb = new StringBuffer();
		sb.append("Unable to run test suite \"");
		sb.append(testClass.getName());
		sb.append("\"; JSF runtime (v");
		sb.append(jsfVersion);
		sb.append(") is required but not present.\nSystem property or environment variable \"jsfRuntimeJarsDirectoryV");
		sb.append(jsfVersion);
		sb.append("\" must be set to point to a directory containing JSF runtime (v");
		sb.append(jsfVersion);
		sb.append(") JARs.");
		return sb.toString();
	}

}
