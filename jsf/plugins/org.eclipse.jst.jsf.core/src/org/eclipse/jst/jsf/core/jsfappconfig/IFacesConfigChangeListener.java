/*******************************************************************************
 * Copyright (c) 2005, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.jsfappconfig;

import org.eclipse.emf.common.notify.Notification;

/**
 * IFacesConfigChangeListener is the interface that must be implemented by
 * objects interested in receiving notification of changes to application
 * configuration models.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public interface IFacesConfigChangeListener {

	/**
	 * Called when a change in an application configuration model for which
	 * this listener has been registered occurs.
	 * 
	 * @param notification EMF {@link Notification} instance that describes the
	 * model change.
	 */
	public void notifyChanged(Notification notification);

}
