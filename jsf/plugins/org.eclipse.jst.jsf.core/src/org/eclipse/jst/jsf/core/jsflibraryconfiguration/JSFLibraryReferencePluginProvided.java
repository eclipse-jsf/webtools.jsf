package org.eclipse.jst.jsf.core.jsflibraryconfiguration;

/**
 * A reference to a plugin defined JSF Library
 *
 */
public interface JSFLibraryReferencePluginProvided extends JSFLibraryReferenceUserDefined {
	/**
	 * @return plugin id.  May return null if plugin id cannot be determined.  
	 */
	public String getPluginId();
}
