/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

/**
 * Experimental and not used at the moment.
 *
 */
public interface IMetaDataChangeNotificationEvent {
	//change to enums if we do Java5
	public static final int ADDED = 1;
	public static final int REMOVED = 2;
	public static final int CHANGED = 3;
	
	public String getURI();
	public int getEventType();
	public IMetaDataLocator getLocator();
}
