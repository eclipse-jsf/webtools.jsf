package org.eclipse.jst.jsf.ui.internal.classpath;

/**
 * Listeners of  {@link JSFLibraryValidationEvent}s should implement
 *
 */
public interface JSFLibraryValidationListener {
	/**
	 * Callback 
	 * @param JSFLibraryValidationEvent
	 */
	public void notifyValidation(JSFLibraryValidationEvent e);
}
