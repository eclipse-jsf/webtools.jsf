package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util;

/**
 * Communicates the status of the JSF Library Registry
 *
 */
public class UpgradeStatus {
	public static final int OK = 0;
	public static final int UPGRADED = 1;
	public static final int CANNOT_UPGRADE = 2;
	
	
	private String shortMsg;
	private String msg;
	private int state = OK;
	private String helpRef;
	private String initialRegistry;
	private String upgradedRegistry;
	
	/**
	 * All-is-well UpgradeStatus constructor 
	 */
	public UpgradeStatus(){		
	}
	
	/**
	 * Constructor when registry upgrade has occured or there is a problem during upgrade
	 * 
	 * @param state
	 * @param shortMessage - cannot be null
	 * @param message - cannot be null
	 * @param helpRef - href to page with additional information.  may be null.
	 * @param initialRegistryURL - may be null
	 * @param upgradedRegistryURL - may be null
	 * 
	 */
	public UpgradeStatus(int state, String shortMessage, String message, String helpRef, String initialRegistryURL,  String upgradedRegistryURL){	
		this.state = state;
		this.shortMsg = shortMessage;
		this.msg = message;
		this.helpRef = helpRef;
		this.initialRegistry = initialRegistryURL;
		this.upgradedRegistry = upgradedRegistryURL;
	}

	/**
	 * @return message useful for dialog titles
	 */
	public String getShortMessage() {
		return shortMsg;
	}

	/**
	 * @return main message of upgrade status
	 */
	public String getMessage() {
		return msg;
	}

	/**
	 * @return OK, UPGRADED, CANNOT_UPGRADE
	 */
	public int getState() {
		return state;
	}

	/**
	 * @return intended to be the href string to a url with more help information
	 */
	public String getHelpReference() {
		return helpRef;
	}

	/**
	 * @return the URL of the registry needing upgrade
	 */
	public String getInitialRegistryURL() {
		return initialRegistry;
	}

	/**
	 * @return te URL of the registry that is/was to be upgraded to
	 */
	public String getUpgradedRegistryURL() {
		return upgradedRegistry;
	}
}
