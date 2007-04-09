package org.eclipse.jst.jsf.ui.internal.jsflibraryconfig;

import java.util.EventObject;

/**
 * Event that something has changed in the JSFLibraryConfigControl
 *
 */
public class JSFLibraryConfigControlChangeEvent {
	private EventObject event;

	/**
	 * Constructor
	 * @param ev
	 */
	public JSFLibraryConfigControlChangeEvent(EventObject ev){
		this.event = ev;
	}

	/**
	 * @return java.util.EventObject
	 */
	public EventObject getEvent(){
		return event;
	}
}
