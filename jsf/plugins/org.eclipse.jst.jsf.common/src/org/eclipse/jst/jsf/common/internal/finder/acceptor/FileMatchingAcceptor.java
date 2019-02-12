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

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.finder.VisitorMatcher.MatchingAcceptor;

/**
 * A matching acceptor that traverses all of the children of an IContainer.
 * 
 * @author cbateman
 *
 */
public class FileMatchingAcceptor extends MatchingAcceptor<IContainer, IResource>
{
    @Override
    protected Collection<? extends IResource> getInputChildren(
            final IContainer container)
    {
        IResource[] members = new IResource[0];
        try
        {
            members = container.members();
        } catch (CoreException e)
        {
            JSFCommonPlugin.log(e);
        }
        return Arrays.asList(members);
    }

    @Override
    protected Collection<? extends IResource> getVisitableChildren(
            IResource visitType)
    {
        if (visitType instanceof IContainer)
        {
            return getInputChildren((IContainer)visitType);
        }
        return Collections.emptyList();
    }

}
