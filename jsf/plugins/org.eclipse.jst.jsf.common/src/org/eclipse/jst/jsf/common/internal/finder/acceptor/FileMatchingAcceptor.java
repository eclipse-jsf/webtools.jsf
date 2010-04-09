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
