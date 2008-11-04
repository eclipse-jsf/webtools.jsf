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
 * @deprecated
 */
public class JSFLibraryReferenceFacadeFactory {
	/**
	 * Returns a JSFLibraryReferenceUserSpecified (or JSFLibraryReferenceUserDefined})  or JSFLibraryReferencePluginProvided instance.
	 * Will not create a JSFLibraryReferenceServerSupplied as there is no cp entry.   Use createServerSuppliedJSFLibRef instead.
	 * @param classpathEntry
	 * @return an instance of JSFLibraryInternalReference.  Null will be returned if the cpEntry is not a JSF Library reference.
	 */
	public static JSFLibraryReference create(final IClasspathEntry classpathEntry) {
		if (JSFLibraryConfigurationHelper.isJSFLibraryContainer(classpathEntry)){
			return createReference(classpathEntry);
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
	 * @param classpathEntry
	 * @return {@link JSFLibraryReference}
	 */
	private static JSFLibraryReference createReference(
			final IClasspathEntry classpathEntry) {
		
		String libID = classpathEntry.getPath().segment(1);
		org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference libRef = JSFLibraryRegistryUtil.getInstance().getJSFLibraryReferencebyID(libID);
		if (libRef!= null){
			boolean isDeployed = getJ2EEModuleDependency(classpathEntry);
			if (libRef.getLibrary() instanceof PluginProvidedJSFLibrary)
				return new JSFLibraryReferencePluginProvidedImpl(libRef, isDeployed);
			
			return new JSFLibraryReferenceUserSpecifiedImpl(libRef, isDeployed);
		}
		return null;
	}

	private static boolean getJ2EEModuleDependency(IClasspathEntry classpathEntry) {
		IClasspathAttribute[] attrs = classpathEntry.getExtraAttributes();
		for (int i=0;i<attrs.length;i++){
			IClasspathAttribute attr = attrs[i];
			if (attr.getName().equals(IClasspathDependencyConstants.CLASSPATH_COMPONENT_DEPENDENCY)){
				return true;
			}
		}
		return false;
	}
}
