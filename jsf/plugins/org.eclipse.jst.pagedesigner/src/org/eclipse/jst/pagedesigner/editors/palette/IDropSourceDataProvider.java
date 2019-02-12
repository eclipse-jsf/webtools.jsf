/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
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
package org.eclipse.jst.pagedesigner.editors.palette;

/**
 * Used by objects (usually palette entries) that wish provide drop source data
 * to a client.
 * 
 * @author cbateman
 *
 */
public interface IDropSourceDataProvider
{
    /**
     * @return a drop source data in context
     */
    IDropSourceData  getDropSourceData();
}
