package org.eclipse.jst.jsf.ui.internal.classpath;

import org.eclipse.core.runtime.IStatus;

/**
 * Validation event used by JSFLibraryControl to notify containers of updates
 *
 */
public class JSFLibraryValidationEvent {
	private String msg;
	private int severity;
	
	/**
	 * Constructor
	 * @param msg
	 * @param severity - IStatus int value
	 */
	public JSFLibraryValidationEvent(String msg, int severity) {
		this.msg = msg;
		this.severity = severity;
	}
	
	/**
	 * Constructs event with severity of IStatus.ERROR
	 * @param msg
	 */
	public JSFLibraryValidationEvent(String msg) {
		this(msg, IStatus.ERROR);
	}
	
	/**
	 * @return validation message
	 */
	public String getMessage(){
		return msg;
	}
	
	/**
	 * @return IStatus int value
	 */
	public int getSeverity(){
		return severity;
	}
}
