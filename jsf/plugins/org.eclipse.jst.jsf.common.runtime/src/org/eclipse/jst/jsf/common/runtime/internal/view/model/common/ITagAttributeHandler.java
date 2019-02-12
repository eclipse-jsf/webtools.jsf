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

import java.io.Serializable;

/**
 * Basic information on attributes.
 * TODO C.B: some question exists in my mind as to how/whether this should merge
 * with meta-data enabled type descriptors.
 * 
 * @author cbateman
 *
 */
public interface ITagAttributeHandler extends Serializable
{
    /**
     * @return the name of the attribute
     */
    String      getName();
    /**
     * @return the name of an extension point used to perform custom handling
     * of the attribute.
     */
    String      getCustomHandler();
    
    /**
     * @return true if the property allows EL expressions
     */
    boolean isELAllowed();
}
