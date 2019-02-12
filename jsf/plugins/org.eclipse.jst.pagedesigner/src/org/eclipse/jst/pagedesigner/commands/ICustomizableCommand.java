/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
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
package org.eclipse.jst.pagedesigner.commands;

import org.eclipse.core.runtime.IAdaptable;

/**
 * A command that can be customized with drop customizable command
 * 
 * This is not public API.  Clients should not use.
 * 
 * @author cbateman
 *
 */
public interface ICustomizableCommand
{
    /**
     * Sets the customization data
     * 
     * @param customizationData
     */
    public void setCustomizationData(IAdaptable customizationData);
}
