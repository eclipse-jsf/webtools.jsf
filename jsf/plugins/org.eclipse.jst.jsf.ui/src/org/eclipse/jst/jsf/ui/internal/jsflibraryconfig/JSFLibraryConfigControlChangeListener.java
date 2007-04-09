package org.eclipse.jst.jsf.ui.internal.jsflibraryconfig;


/**
 * Interface to be implemented to listen to changes in the JSFLibraryConfigControl object
 *
 */
public interface JSFLibraryConfigControlChangeListener {
	/**
	 * Callback method
	 * @param e
	 */
	public void changed(JSFLibraryConfigControlChangeEvent e);
}
