package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A strategy for resolving JSP TLDs.
 * 
 * @author cbateman
 *
 */
public abstract class JSPTagResolvingStrategy extends
        AbstractTagResolvingStrategy<TLDElementDeclaration, String>
{
    @Override
    public abstract ITagElement resolve(TLDElementDeclaration element);

    public abstract String getId();
}
