/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * A converter decorator.
 * 
 * @author cbateman
 *
 */
public class ConverterDecorator extends ComponentDecorator {

    private final ConverterTypeInfo     _typeInfo;
    
    /**
     * serializable id
     */
    private static final long serialVersionUID = 3838224353030247227L;
    
    
    /**
     * @param decorates
     * @param typeInfo
     */
    public ConverterDecorator(final ComponentInfo decorates, final ConverterTypeInfo typeInfo)
    {
        super(decorates);
        _typeInfo = typeInfo;
    }

    /**
     * @return the converter's type info.
     */
    public final ConverterTypeInfo getTypeInfo()
    {
        return _typeInfo;
    }
}
