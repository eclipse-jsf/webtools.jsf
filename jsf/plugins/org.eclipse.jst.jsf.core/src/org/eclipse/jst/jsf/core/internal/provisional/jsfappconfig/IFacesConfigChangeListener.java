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
package org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig;

import org.eclipse.emf.common.notify.Notification;

/**
 * IFacesConfigChangeListener is the interface that must be implemented by
 * objects interested in receiving notification of changes to features of
 * application configuration models.
 * 
 * @author Ian Trimble - Oracle
 */
public interface IFacesConfigChangeListener {

	/**
	 * Called when a change in a feature of an application configuration model
	 * for which this listener has been registered occurs.
	 * 
	 * @param notification EMF {@link Notification} instance that describes the
	 * model's feature change.
	 */
	public void notifyChanged(Notification notification);

}
