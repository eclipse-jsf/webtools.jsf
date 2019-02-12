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
package org.eclipse.jst.jsf.validation.internal;

/**
 * A factory that is to construct JSF validators
 * 
 * @author cbateman
 *
 */
public class JSFValidatorFactory
{
    /**
     * @return a default validator for XML-defined views.
     */
    public static IJSFViewValidator createDefaultXMLValidator()
    {
        return new XMLViewDefnValidator();
    }
}
