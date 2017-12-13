/*******************************************************************************
 * Copyright (c) 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.test.util;

import java.lang.reflect.Field;

/**
 * A utility class used to hack other classes at test time. This should be
 * viewed as a last resort where state needs to be forced inside classes that we
 * cannot access in any other way.
 * 
 * @author cbateman
 * 
 */
public class ObjectHacker
{

    public void forceStaticField(final Class<?> clazz, final String fieldName,
            final Object value) throws NoSuchFieldException,
            IllegalAccessException
    {
        final Field declaredField = clazz.getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        declaredField.set(null, value);
    }

    public void forceField(final Object instance,
            final Class<?> declaringClass, final String fieldName,
            final Object value) throws SecurityException, NoSuchFieldException,
            IllegalArgumentException, IllegalAccessException
    {
        final Field declaredField = declaringClass.getDeclaredField(fieldName);
        declaredField.setAccessible(true);
        declaredField.set(instance, value);
    }
}
