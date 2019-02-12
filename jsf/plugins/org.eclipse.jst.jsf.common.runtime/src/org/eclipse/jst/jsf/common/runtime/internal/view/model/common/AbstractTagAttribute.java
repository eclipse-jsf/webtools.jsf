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
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

/**
 * Super-class of all ITagAttribute concrete implementations.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractTagAttribute implements ITagAttribute
{
    /**
     * 
     */
    private static final long serialVersionUID = 6364594863141579928L;

    public abstract String getName();

    public abstract String getTargetNamespace();

    public abstract String getDescription();

    public abstract String getDisplayName();

    public abstract boolean isRequired();
}
