package org.eclipse.jst.pagedesigner.properties;

import org.eclipse.osgi.util.NLS;

/**
 * Externalized Strings
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.pagedesigner.properties.messages"; //$NON-NLS-1$
	/**
	 * 
	 */
	public static String ITabbedPropertiesConstants_other_category;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
		//
	}
}
