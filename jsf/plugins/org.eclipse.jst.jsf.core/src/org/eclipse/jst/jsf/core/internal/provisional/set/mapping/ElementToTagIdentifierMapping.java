package org.eclipse.jst.jsf.core.internal.provisional.set.mapping;

import org.eclipse.jst.jsf.common.sets.internal.provisional.mapping.AbstractObjectInjectiveSetMapping;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.w3c.dom.Element;

/**
 * Converts a set of DOM element's to a set of (object) injective set
 * of TagIdentifiers.  If the element is a JSP tag, the tag uri will
 * be looked up.
 * 
 * IMPORTANT: the returned TagIdentifiers are wrapper objects, so they only
 * maintain immutability and idempotency as long as the input dom elements
 * do not change. 
 * 
 * @author cbateman
 *
 */
public class ElementToTagIdentifierMapping extends
        AbstractObjectInjectiveSetMapping 
{
    public Object map(Object element) {
        Element domElement = (Element) element;
        return TagIdentifierFactory.createDocumentTagWrapper(domElement);
    }
}
