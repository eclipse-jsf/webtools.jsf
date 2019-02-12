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
 *     Cameron Bateman, Oracle - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

/**
 * Default implementation of ITagAttributeHandler.
 * 
 * @author cbateman
 * 
 */
public class TagAttributeHandler implements ITagAttributeHandler
{
    /**
     * 
     */
    private static final long serialVersionUID = 8153903018703133004L;
    private final String      _customHandler;
    private final String      _name;
    private final boolean     _elAllowed;

    /**
     * @param customHandler
     * @param name
     * @param elAllowed
     */
    public TagAttributeHandler(final String customHandler, final String name,
            final boolean elAllowed)
    {
        super();
        _customHandler = customHandler;
        _elAllowed = elAllowed;
        _name = name;
    }

    public String getCustomHandler()
    {
        return _customHandler;
    }

    public String getName()
    {
        return _name;
    }

    public boolean isELAllowed()
    {
        return _elAllowed;
    }
}
