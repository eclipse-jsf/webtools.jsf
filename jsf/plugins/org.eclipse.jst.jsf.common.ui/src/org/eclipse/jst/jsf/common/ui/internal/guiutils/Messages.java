package org.eclipse.jst.jsf.common.ui.internal.guiutils;

import org.eclipse.osgi.util.NLS;

/**
 * String resource handler.
 * 
 * @author Debajit Adhikary - Oracle
 */

public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.common.ui.internal.guiutils.messages"; //$NON-NLS-1$
	
	/**
	 * See messages.properties.
	 */
	public static String IntroductionSection_noIntroDescription;
	
	/**
	 * See messages.properties.
	 */
	public static String IntroductionSection_noIntroTitle;
	
	/**
	 * See messages.properties.
	 */
	public static String BrowserWindow_loading;

	
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
