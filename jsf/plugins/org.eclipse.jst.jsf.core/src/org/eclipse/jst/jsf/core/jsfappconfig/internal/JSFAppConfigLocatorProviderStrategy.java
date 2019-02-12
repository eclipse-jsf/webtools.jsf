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
package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.strategy.ISimpleStrategy;

/**
 * Abstract implementation of a JSFAppConfigLocatorProviderStrategy 
 * implementing {@link ISimpleStrategy}
 */
public abstract class JSFAppConfigLocatorProviderStrategy 
	implements ISimpleStrategy<IProject, IJSFAppConfigLocatorProvider>  {

	public IJSFAppConfigLocatorProvider getNoResult() {
		return null;
	}
}
