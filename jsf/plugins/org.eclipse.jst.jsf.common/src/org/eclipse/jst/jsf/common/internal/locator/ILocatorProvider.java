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

import java.util.List;

/**
 * An object that provides zero or more locators to a client.
 * 
 * @author cbateman
 * @param <LOCATORTYPE> 
 *
 */
public interface ILocatorProvider<LOCATORTYPE>
{
    /**
     * Must be called before getLocators is called.  Implementations MUST
     * support multiple calls to initialize on the same instance but need not
     * do anything on any of them.
     */
    void initialize();

    /**
     * @return the list of locators.
     */
    List<? extends LOCATORTYPE> getLocators();
}
