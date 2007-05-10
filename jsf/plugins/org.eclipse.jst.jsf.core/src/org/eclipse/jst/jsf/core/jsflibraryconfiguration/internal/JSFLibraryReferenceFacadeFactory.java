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
package org.eclipse.jst.jsf.core.jsflibraryconfiguration.internal;

import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryConfigurationHelper;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReference;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReferenceServerSupplied;

/**
 * Factory for producing facade objects for references to the internal EMF JSF Library classes
 */
public class JSFLibraryReferenceFacadeFactory {
	/**
	 * Returns a JSFLibraryReferenceUserDefined or JSFLibraryReferencePluginProvided instance.
	 * Will not create a JSFLibraryReferenceServerSupplied as there is no cp entry.   Use createServerSuppliedJSFLibRef instead.
	 * @param cpEntry
	 * @return an instance of JSFLibraryInternalReference.  Null will be returned if the cpEntry is not a 
	 */
	public static JSFLibraryReference create(final IClasspathEntry cpEntry) {
		if (JSFLibraryConfigurationHelper.isJSFLibraryContainer(cpEntry)){
			return createReference(cpEntry);
		}
		return null;
	}

	/**
	 * @return instance of {@link JSFLibraryReferenceServerSupplied}
	 */
	public static JSFLibraryReferenceServerSupplied createServerSuppliedJSFLibRef(){
		return new JSFLibraryReferenceServerSuppliedImpl();
	}


	/**
	 * @param cpEntry
	 * @return {@link JSFLibraryReference}
	 */
	private static JSFLibraryReference createReference(
			final IClasspathEntry cpEntry) {
		
		String libID = cpEntry.getPath().segment(1).toString();
		org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference libRef = JSFLibraryRegistryUtil.getInstance().getJSFLibraryReferencebyID(libID);
		if (libRef!= null){
			boolean isDeployed = getJ2EEModuleDependency(cpEntry);
			if (libRef.getLibrary() != null && libRef.getLibrary() instanceof PluginProvidedJSFLibrary)
				return new JSFLibraryReferencePluginProvidedImpl(libRef, isDeployed);
			
			return new JSFLibraryReferenceUserDefinedImpl(libRef, isDeployed);
		}
		return null;
	}

	private static boolean getJ2EEModuleDependency(IClasspathEntry cpEntry) {
		IClasspathAttribute[] attrs = cpEntry.getExtraAttributes();
		for (int i=0;i<attrs.length;i++){
			IClasspathAttribute attr = attrs[i];
			if (attr.getName().equals(IClasspathDependencyConstants.CLASSPATH_COMPONENT_DEPENDENCY)){
				return true;
			}
		}
		return false;
	}
}
