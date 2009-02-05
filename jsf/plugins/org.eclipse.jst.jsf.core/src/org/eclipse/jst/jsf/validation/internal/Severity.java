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
 * Severity levels of problems
 */
public enum Severity
{
    /**
     * Indicates a problem severity of Error
     */
    ERROR 
    {
        public java.lang.String toString() {
            return "error";
        }
    }
    ,
    /**
     * Indicates a problem severity of Warning
     */
    WARNING
    {
        public java.lang.String toString() {
            return "warning";
        }
    }
    , 
    /**
     * Indicates no problem should be shown
     */
    IGNORE
    {
        public java.lang.String toString() {
            return "ignore";
        }
    };
    
    /**
     * @param value
     * @return the enum based on the toString mappings
     */
    public static Severity valueOfString(String value)
    {
        if ("error".equals(value))
        {
            return ERROR;
        }
        else if ("warning".equals(value))
        {
            return WARNING;
        }
        else
        {
            return IGNORE;
        }
    }
 }