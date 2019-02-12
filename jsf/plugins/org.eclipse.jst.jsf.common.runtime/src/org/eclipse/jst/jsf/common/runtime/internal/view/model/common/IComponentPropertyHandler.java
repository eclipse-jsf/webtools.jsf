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
 *    Cameron Bateman, Oracle - initial API and implementation
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
