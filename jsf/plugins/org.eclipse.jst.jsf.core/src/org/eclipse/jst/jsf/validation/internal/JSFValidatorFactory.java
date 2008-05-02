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
