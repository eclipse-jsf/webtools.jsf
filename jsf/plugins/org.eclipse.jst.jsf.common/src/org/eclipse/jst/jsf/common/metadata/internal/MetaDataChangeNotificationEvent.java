/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

/**
 * Experimental.  Subject to change.
 *
 */
public class MetaDataChangeNotificationEvent implements
		IMetaDataChangeNotificationEvent {

	private String _uri;
	private IMetaDataLocator _locator;
	private int _type;

	/**
	 * Constructor
	 * @param locator
	 * @param uri
	 * @param type
	 */
	public MetaDataChangeNotificationEvent(IMetaDataLocator locator, String uri, int type){
		this._locator = locator;
		this._uri = uri;
		this._type = type;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataChangeNotificationEvent#getEventType()
	 */
	public int getEventType() {
		return _type;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataChangeNotificationEvent#getLocator()
	 */
	public IMetaDataLocator getLocator() {
		return _locator;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.IMetaDataChangeNotificationEvent#getURI()
	 */
	public String getURI() {
		return _uri;
	}

}
