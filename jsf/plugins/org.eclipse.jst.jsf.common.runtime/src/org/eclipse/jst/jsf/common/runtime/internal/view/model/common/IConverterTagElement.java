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
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;

/**
 * A tag element that causes the construction registration of a converter on
 * a parent object.
 * 
 * @author cbateman
 *
 */
public interface IConverterTagElement extends IJSFTagElement
{
    /**
     * @return the type info for the converter represented by this
     * element
     */
    ConverterTypeInfo getConverter();
}
