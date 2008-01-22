package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IJSFTagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A JSF tag element.
 * 
 * @author cbateman
 * 
 */
public abstract class TLDJSFTagElement extends TLDTagElement implements
        IJSFTagElement
{
    /**
     * @param elementDecl
     */
    public TLDJSFTagElement(final TLDElementDeclaration elementDecl)
    {
        super(elementDecl);
    }

    public abstract TagType getType();

}
