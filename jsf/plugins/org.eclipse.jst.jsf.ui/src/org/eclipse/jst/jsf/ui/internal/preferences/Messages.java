package org.eclipse.jst.jsf.ui.internal.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * NLS messages
 * @author cbateman
 *
 */
public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.ui.internal.preferences.messages"; //$NON-NLS-1$
	/**
	 * see messages.properties
	 */
	public static String JSPTagRegistryPreferencePage_PanelDescription;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
