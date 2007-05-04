/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.internal.types;

/**
 * @author cbateman
 *
 */
public class TypeConstants 
{
    /**
     * we overload "void" to represent null
     */
    public final static String  TYPE_NULL = "V"; //$NON-NLS-1$
    /**
     * unboxed boolean 
     */
    public final static String  TYPE_BOOLEAN = "Z"; //$NON-NLS-1$
    /**
     * string type
     */
    public final static String   TYPE_STRING = "Ljava.lang.String;"; //$NON-NLS-1$
    /**
     * big integer
     */
    public final static String   TYPE_BIG_INTEGER = "Ljava.math.BigInteger;"; //$NON-NLS-1$
    
    /**
     * big double
     */
    public final static String   TYPE_BIG_DOUBLE = "Ljava.math.BigDecimal;"; //$NON-NLS-1$
    
    /* boxed types */
    /**
     * Boxed byte
     */
    public final static String   TYPE_BOXED_BYTE = "Ljava.lang.Byte;"; //$NON-NLS-1$
    /**
     * Boxed short
     */
    public final static String   TYPE_BOXED_SHORT = "Ljava.lang.Short;"; //$NON-NLS-1$
    /**
     * Boxed int
     */
    public final static String   TYPE_BOXED_INTEGER = "Ljava.lang.Integer;"; //$NON-NLS-1$
    /**
     * Boxed long
     */
    public final static String   TYPE_BOXED_LONG = "Ljava.lang.Long;"; //$NON-NLS-1$
    /**
     * Boxed float
     */
    public final static String   TYPE_BOXED_FLOAT = "Ljava.lang.Float;"; //$NON-NLS-1$
    /**
     * Boxed double
     */
    public final static String   TYPE_BOXED_DOUBLE = "Ljava.lang.Double;"; //$NON-NLS-1$
    /**
     * Boxed boolean 
     */
    public final static String   TYPE_BOXED_BOOLEAN = "Ljava.lang.Boolean;"; //$NON-NLS-1$
    /**
     * Boxed char 
     */
    public final static String   SIGNATURE_BOXED_CHARACTER = "Ljava.lang.Character"; //$NON-NLS-1$
    /**
     * Map type
     */
    public final static String   TYPE_MAP = "Ljava.util.Map;"; //$NON-NLS-1$
    /**
     * Collection type
     */
    public final static String   TYPE_COLLECTION = "Ljava.util.Collection;"; //$NON-NLS-1$
    /**
     * Comparable type
     */
    public final static String   TYPE_COMPARABLE = "Ljava.lang.Comparable;"; //$NON-NLS-1$
    
    /**
     * List type
     */
    public final static String   TYPE_LIST = "Ljava.util.List;"; //$NON-NLS-1$
    
    /**
     * java.lang.Object type signature
     */
    public static final String TYPE_JAVAOBJECT = "Ljava.lang.Object;"; //$NON-NLS-1$
}