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
 * Maps tag attributes to component properties.
 * 
 * @author cbateman
 *
 */
public interface IComponentPropertyHandler extends ITagAttributeHandler
{
    /**
     * @return the property name that this handler maps to.
     */
    String getPropertyName();
}
