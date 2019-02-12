/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.finder.matcher;

import java.util.jar.JarEntry;
import java.util.regex.Pattern;

import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.IMatcher;


/**
 * @author cbateman
 *
 */
public class TaglibJarEntryFinder extends TaglibFinder<JarEntry, JarEntry> implements IMatcher
{
    /**
     * Unique id of this strategy.
     */
    public static final String ID = TaglibJarEntryFinder.class.getCanonicalName();
    private static final String DISPLAY_NAME = "Display Name"; //$NON-NLS-1$
    private final Pattern _pattern;
    
    /**
     * @param pattern
     */
    public TaglibJarEntryFinder(final Pattern pattern)
    {
        super(ID, DISPLAY_NAME, null);
        _pattern = pattern;
    }

    @Override
    public JarEntry perform(JarEntry input) throws Exception
    {
        if (_pattern.matcher(input.getName()).matches())
        {
            return input;
        }
        return getNoResult();
    }

    public boolean matches(Object matchThis)
    {
        if (matchThis instanceof JarEntry)
        {
            try
            {
                return perform((JarEntry) matchThis) != getNoResult();
            } catch (Exception e)
            {
                JSFCommonPlugin.log(e, "While matching jar entry: "+matchThis); //$NON-NLS-1$
            }
        }
        return false;
    }
}