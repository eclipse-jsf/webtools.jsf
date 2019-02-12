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

import java.io.Serializable;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;

/**
 * Represents design time information about the ValueHolder behavioural
 * interface.
 * 
 * @author cbateman
 *
 */
public interface IValueHolderInfo extends Serializable
{
    /**
     * @return the value, may be null
     */
    Object getValue();
    
    /**
     * @return the raw value without expression value evaluation.  Note that 
     * {@link #getValue()} may return the same value in cases where this value
     * holder is derived at design time without EL expression evaluation. May be null.
     */
    Object getLocalValue();
    
    /**
     * @return the converter for this value holder or null if none.
     */
    ConverterDecorator getConverter();
}
