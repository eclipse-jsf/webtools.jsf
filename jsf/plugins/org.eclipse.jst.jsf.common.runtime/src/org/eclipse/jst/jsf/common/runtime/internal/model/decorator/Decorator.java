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

import java.io.Serializable;

import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;

/**
 * The abstract super-class of all view object decorators.
 * 
 * @author cbateman
 * 
 */
public abstract class Decorator extends ViewObject implements Serializable
{

    /**
     * serializable id
     */
    private static final long serialVersionUID = -3138829736243154022L;

}
