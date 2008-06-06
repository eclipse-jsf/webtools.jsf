package org.eclipse.jst.jsf.designtime.internal;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "org.eclipse.jst.jsf.designtime.internal.messages"; //$NON-NLS-1$
	public static String DefaultJSPTagResolver_DisplayName;
	public static String PersistedDataTagStrategy_DisplayName;
	public static String TagIntrospectingStrategy_DisplayName;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
