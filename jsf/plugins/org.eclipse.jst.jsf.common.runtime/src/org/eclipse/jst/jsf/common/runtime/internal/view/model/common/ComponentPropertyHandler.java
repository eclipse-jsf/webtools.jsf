/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

/**
 * Default implementation of IComponentPropertyHandler.
 * 
 * @author cbateman
 * 
 */
public class ComponentPropertyHandler extends TagAttributeHandler implements
        IComponentPropertyHandler
{
    /**
     * 
     */
    private static final long serialVersionUID = 8614562842401106572L;
    private final String  _propertyName;

    /**
     * @param customHandler
     * @param name
     * @param elAllowed
     * @param propertyName
     */
    public ComponentPropertyHandler(final String customHandler,
            final String name, final boolean elAllowed,
            final String propertyName)
    {
        super(customHandler, name, elAllowed);
        _propertyName = propertyName;
    }

    public String getPropertyName()
    {
        return _propertyName;
    }
}
