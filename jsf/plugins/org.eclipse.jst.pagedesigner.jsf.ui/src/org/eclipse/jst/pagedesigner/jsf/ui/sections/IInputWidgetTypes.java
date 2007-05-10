/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.sections;

interface IInputWidgetTypes 
{
    /**
     *  a hidden input
     */
    public static final int       HIDDEN   = 0;
    /**
     * a secret input
     */
    public static final int       SECRET   = 1;
    /**
     * an input box
     */
    public static final int       TEXT     = 2;
    /**
     * an input text area
     */
    public static final int       TEXTAREA = 3;

}
