package org.eclipse.jst.jsf.core.jsflibraryconfiguration.internal;

import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryInternalReference;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReferencePluginProvided;

/**
 *
 */
public class JSFLibraryReferencePluginProvidedImpl extends AbstractJSFLibraryReferenceImpl implements JSFLibraryReferencePluginProvided {

	/**
	 * Constructor
	 * @param libRef
	 * @param isDeployed 
	 */
	public JSFLibraryReferencePluginProvidedImpl(JSFLibraryInternalReference libRef, boolean isDeployed) {
		super(libRef, isDeployed);
	}

	public String getPluginId() {
		if (getLibrary() != null)
			return ((PluginProvidedJSFLibrary) getLibrary()).getPluginID();
		
		return null;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer("PluginProvided: (");
		buf.append(super.toString());
		
		return buf.toString();
	}
}
