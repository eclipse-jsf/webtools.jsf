package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.ResourceBundle;

public interface IResourceBundleProvider {
	/**
	 * @return ResourceBundle - implementers should eat exceptions and return null whenever resourceBundle cannot be returned
	 */
	public ResourceBundle getResourceBundle();
}
