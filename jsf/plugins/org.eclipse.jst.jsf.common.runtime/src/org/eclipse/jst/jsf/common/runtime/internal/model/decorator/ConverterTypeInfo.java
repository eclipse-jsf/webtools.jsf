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
package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.types.ClassTypeInfo;

/**
 * Type information about a converter.
 * 
 * @author cbateman
 * 
 */
public class ConverterTypeInfo extends ClassTypeInfo
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = -7238952424045449907L;
    private static final String[]  NO_FOR_CLASS = new  String[0];
    private final String        _converterId;
    private final String[]      _forClasses;

    /**
     * For unknown converters, use the UNKNOWN constant.
     * 
     * @param className
     * @param converterId
     * @throws java.lang.IllegalArgumentException
     *             if both className and converterId are null.
     * 
     */
    public ConverterTypeInfo(String className, String converterId)
    {
        super(className, new String[0], new String[0]);
        if (className == null && converterId == null)
        {
            throw new IllegalArgumentException(
                    "converterClass and converterId must not both be null.  For unknown validator use the UNKNOWN constant"); //$NON-NLS-1$
        }

        _converterId = converterId;
        _forClasses = NO_FOR_CLASS;
    }

    /**
     * For unknown converters, use the UNKNOWN constant.
     * 
     * @param className
     * @param superClasses 
     * @param interfaces 
     * @param converterId
     * @param forClass 
     * @throws java.lang.IllegalArgumentException
     *             if both className and converterId are null.
     * 
     */
    public ConverterTypeInfo(String className, String[] superClasses,
            String[] interfaces, String converterId, String[] forClass)
    {
        super(className, superClasses, interfaces);
        if (className == null && converterId == null)
        {
            throw new IllegalArgumentException(
                    "converterClass and converterId must not both be null.  For unknown validator use the UNKNOWN constant"); //$NON-NLS-1$
        }

        _converterId = converterId;
        if (forClass == null)
        {
            _forClasses = NO_FOR_CLASS;
        }
        else
        {
            _forClasses = forClass;
        }
    }

    /**
     * Use when the converter's information unknown such as occurs when using
     * the f:converter tag.
     */
    public static final ConverterTypeInfo UNKNOWN = new ConverterTypeInfo(
            (Object) null);

    /**
     * A private constructor used to create the UNRESOLVED constant. We use an
     * Object arg here (which is discarded) rather than using the zero-arg
     * constructor so as not mess up anything like Serializable that may depend
     * on how zero-arg constructors are defined.
     * 
     * @param unresolved
     */
    private ConverterTypeInfo(Object unresolved)
    {
        super(null, new String[0], new String[0]);
        _converterId = null;
        _forClasses = NO_FOR_CLASS;
    }

    /**
     * @return the converter's id.
     */
    public final String getConverterId()
    {
        return _converterId;
    }

    /**
     * @return a copy of the listof classes that this type converters to.  May
     * be empty.  Never null.
     */
    public final String[] getForClass()
    {
        final String[]  returnArray = new String[_forClasses.length];
        System.arraycopy(_forClasses, 0, returnArray, 0, _forClasses.length);
        return returnArray;
    }

    public String toString()
    {
        String toString = ""; //$NON-NLS-1$

        if (_forClasses.length  > 0)
        {
            toString = "For-Classes: ["; //$NON-NLS-1$
            for (int i = 0; i < _forClasses.length; i++)
            {
                toString += _forClasses[i];
                if (i < _forClasses.length-1)
                {
                    toString += ", "; //$NON-NLS-1$
                }
            }
            toString += "], "; //$NON-NLS-1$
        }
        return toString + "Converter Type Info: type = " + _converterId + ", "+super.toString(); //$NON-NLS-1$ //$NON-NLS-2$
    }
}
