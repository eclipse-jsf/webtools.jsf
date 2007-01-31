package org.eclipse.jst.pagedesigner.editpolicies;

import org.eclipse.gef.Request;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;

/**
 * A type of provider that can provide request information for an edit part
 * when it is being dropped into an arbitrary location
 * 
 * @author cbateman
 *
 */
public interface IDropRequestorProvider
{
    /**
     * @param request
     * @return a drop location strategy that may be used with 'request' that 
     * will help calculate validate drop locations for this policy's host
     * edit part, or null if no such strategy exists
     */
    IDropLocationStrategy getDropRequestorLocationStrategy(Request request);
}
