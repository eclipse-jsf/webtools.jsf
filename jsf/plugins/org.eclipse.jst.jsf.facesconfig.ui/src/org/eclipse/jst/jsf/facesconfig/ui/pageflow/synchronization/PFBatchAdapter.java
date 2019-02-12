/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.PlatformUI;

/**
 * @author hmeng
 */

public abstract class PFBatchAdapter extends AdapterImpl {
	private boolean needPostpone;

	AbstractGraphicalEditPart editPart;

	/**
	 * @param msg
	 * @return true postponement needed
	 */
	public boolean isNeedPostpone(Notification msg) {
		return needPostpone
				&& !(msg.getEventType() == FC2PFTransformer.MY_NOTIFICATION_TYPE || msg
						.getEventType() == FC2PFTransformer.MY_NOTIFICATION_TYPE1);
	}

	/**
	 * @param needPostpone
	 */
	public void setNeedPostpone(boolean needPostpone) {
		this.needPostpone = needPostpone;
	}

	final public void notifyChanged(final Notification msg) {
		if (!isNeedPostpone(msg)) {
			if (Thread.currentThread() == PlatformUI.getWorkbench().getDisplay().getThread())
				doNotifyChanged(msg);
			else {
				PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
					public void run() {
						doNotifyChanged(msg);
					}
				});
			}
		}
	}

	/**
	 * @param notification
	 */
	abstract public void doNotifyChanged(Notification notification);

}
