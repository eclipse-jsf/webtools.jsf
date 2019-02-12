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

import java.util.List;

import org.eclipse.jst.jsf.core.jsfappconfig.IJSFAppConfigLocater;

/**
 * Provides list of {@link IJSFAppConfigLocater}s
 *
 */
public interface IJSFAppConfigLocatorProvider {
	/**
	 * @return list of ILocators
	 */
	List<IJSFAppConfigLocater> getLocators();
}
