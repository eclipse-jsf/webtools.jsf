/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;

/**
 * A JSF tag element that maps one-to-one to a JSF UIComponent
 * 
 * @author cbateman
 *
 */
public interface IComponentTagElement extends IJSFTagElement 
{
    /**
     * @return the comopnent's type info.
     */
    ComponentTypeInfo getComponent();
}
