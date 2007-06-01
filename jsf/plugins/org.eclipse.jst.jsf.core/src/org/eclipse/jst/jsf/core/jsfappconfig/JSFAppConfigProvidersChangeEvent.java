/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

/**
 * JSFAppConfigProvidersChangeEvent provides change information to instances of
 * IJSFAppConfigProvidersChangeListener.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public class JSFAppConfigProvidersChangeEvent {

	/**
	 * Event type; instance of IJSFAppConfigProvider was added (value == 1).
	 */
	public static final int ADDED = 1;

	/**
	 * Event type; instance of IJSFAppConfigProvider was removed (value == 2).
	 */
	public static final int REMOVED = 2;

	/**
	 * Instance of IJSFAppConfigProvider that was added or removed.
	 */
	protected IJSFAppConfigProvider configProvider = null;

	/**
	 * Event type.
	 */
	protected int eventType;

	/**
	 * Constructs an instance.
	 * 
	 * @param configProvider Instance of IJSFAppConfigProvider that was added
	 * or removed
	 * @param eventType Event type
	 */
	public JSFAppConfigProvidersChangeEvent(IJSFAppConfigProvider configProvider, int eventType) {
		this.configProvider = configProvider;
		this.eventType = eventType;
	}

	/**
	 * Gets the instance of IJSFAppConfigProvider that was added or removed.
	 * 
	 * @return Instance of IJSFAppConfigProvider that was added or removed
	 */
	public IJSFAppConfigProvider getJSFAppConfigProvider() {
		return configProvider;
	}

	/**
	 * Gets the event type.
	 * 
	 * @return Event type
	 */
	public int getEventType() {
		return eventType;
	}

}
