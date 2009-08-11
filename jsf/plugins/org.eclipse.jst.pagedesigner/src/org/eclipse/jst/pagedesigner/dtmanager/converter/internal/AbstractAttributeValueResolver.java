package org.eclipse.jst.pagedesigner.dtmanager.converter.internal;

import org.w3c.dom.Element;

/**
 *
 *
 */
public abstract class AbstractAttributeValueResolver implements
        IAttributeValueResolver
{

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.dtmanager.converter.internal.IAttributeValueResolver#canResolve(org.w3c.dom.Element, org.w3c.dom.Element, java.lang.String)
     */
    public abstract boolean canResolve(Element originalElement,
            Element convertedElement, String convertedAttrName,
            final String convertedAttrValue);

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.dtmanager.converter.internal.IAttributeValueResolver#resolveAttribute(org.w3c.dom.Element, org.w3c.dom.Element, java.lang.String)
     */
    public abstract String resolveAttribute(Element originalElement,
            Element convertedElement, String convertedAttrName,
            final String convertedAttrValue);

}
