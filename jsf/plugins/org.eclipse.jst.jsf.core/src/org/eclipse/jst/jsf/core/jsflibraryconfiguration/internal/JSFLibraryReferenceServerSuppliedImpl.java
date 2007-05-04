package org.eclipse.jst.jsf.core.jsflibraryconfiguration.internal;

import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryReferenceServerSupplied;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFVersion;

/**
 * @author gekessle
 *
 */
public class JSFLibraryReferenceServerSuppliedImpl 
	extends AbstractJSFLibraryReferenceImpl 
	implements JSFLibraryReferenceServerSupplied {
	
	/**
	 * Constructor
	 */
	public JSFLibraryReferenceServerSuppliedImpl(){
		//TODO: replace label with constant
		super(JSFLibraryReferenceServerSupplied.ID, "Server Supplied", true);
	}
	
	public String toString() {
		StringBuffer buf = new StringBuffer("ServerSupplied: (");
		buf.append(super.toString());
		buf.append(")");
		
		return buf.toString();
	}
	
	protected JSFLibrary getLibrary(){
		return null;
	}
	
	public JSFVersion getMaxSupportedVersion() {
		return JSFVersion.UNKNOWN;
	}
//
}
