/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.locator;

import java.util.EventListener;
import java.util.EventObject;

/**
 * @author cbateman
 *
 */
public interface ILocatorChangeListener extends EventListener 
{
	/**
	 * @param event
	 */
	public void changed(final LocatorChangeEvent event);

	/**
	 * @author cbateman
	 *
	 */
	public static class LocatorChangeEvent extends EventObject 
	{
		/**
		 * @param source
		 */
		public LocatorChangeEvent(ILocator source) {
			super(source);
		}

		@Override
		public ILocator getSource() {
			return (ILocator) super.getSource();
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = -7930804700395142768L;
		
	}

}
