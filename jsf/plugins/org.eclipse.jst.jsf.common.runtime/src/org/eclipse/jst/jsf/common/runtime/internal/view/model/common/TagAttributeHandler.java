/*******************************************************************************
 * Copyright (c) ${year} Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
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
