package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * This a fallback strategy that always creates an element for a tld decl.  This
 * can be tacked to the end of a composite strategy (or used alone) to ensure
 * that a basic ITagElement is always created for a TLDElementDeclaration.
 * 
 * @author cbateman
 *
 */
public class UnresolvedJSPTagResolvingStrategy extends JSPTagResolvingStrategy
{
    /**
     * the identifier of this strategy
     */
    public final static String ID = "org.eclipse.jst.jsf.designtime.UnresolvedJSPTagResolvingStrategy";
    /**
     * the displayable name
     */
    public final static String DISPLAY_NAME = "Unresolved Tag Resolver";

    @Override
    public String getId()
    {
        return ID;
    }

    public String getDisplayName()
    {
        return DISPLAY_NAME;
    }

    @Override
    public ITagElement resolve(TLDElementDeclaration element)
    {
        // just create a tag element
        return new TLDTagElement(element);
    }

}
