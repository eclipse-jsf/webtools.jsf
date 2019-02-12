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
package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import org.eclipse.jst.jsf.common.runtime.internal.model.IDesigntimeAdapter;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;

/**
 * Implementation of the IValueHolderInfo.
 * 
 * @author cbateman
 *
 */
public class ValueHolderInfo implements IValueHolderInfo, IDesigntimeAdapter
{
    /**
     * 
     */
    private static final long serialVersionUID = 5000699728360703727L;

    /**
     * the value (may include EL evaluation)
     */
    protected final Object                  _value;
    /**
     * the raw value  before EL evaluation.
     */
    protected final Object                  _localValue;
    /**
     * the converter or null if none.
     */
    protected final ConverterDecorator      _converterDecorator;
    
    /**
     * @param converterDecorator
     * @param localValue
     * @param value
     */
    public ValueHolderInfo(final ConverterDecorator converterDecorator,
            final Object localValue, final Object value) {
        super();
        _converterDecorator = converterDecorator;
        _localValue = localValue;
        _value = value;
    }
    public final Object getValue() {
        return _value;
    }
    public final Object getLocalValue() {
        return _localValue;
    }
    public final ConverterDecorator getConverter() {
        return _converterDecorator;
    }
    public String[] getInterfaces()
    {
        return new String[] {ComponentFactory.INTERFACE_VALUEHOLDER};
    }
}
