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
package org.eclipse.jst.jsf.common.internal.finder.acceptor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher.MatchingAcceptor;

/**
 * An acceptor that gets the jar entries from a jar file.
 * 
 * @author cbateman
 *
 */
public class JarEntryMatchingAcceptor extends
        MatchingAcceptor<JarFile, JarEntry>
{

    @Override
    protected Collection<? extends JarEntry> getInputChildren(JarFile inputType)
    {
        final List<JarEntry>  children = new ArrayList<JarEntry>();
        Enumeration<JarEntry> entries = inputType.entries();
        while (entries.hasMoreElements())
        {
            children.add(entries.nextElement());
        }
        return children;
    }

    @Override
    protected Collection<? extends JarEntry> getVisitableChildren(
            JarEntry visitType)
    {
        return Collections.EMPTY_LIST;
    }

}
