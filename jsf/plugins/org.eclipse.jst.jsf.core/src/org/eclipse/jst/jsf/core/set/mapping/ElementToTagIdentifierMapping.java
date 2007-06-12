/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.set.mapping;

import org.eclipse.jst.jsf.common.sets.mapping.AbstractObjectInjectiveSetMapping;
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
 * <p><b>Provisional API - subject to change</b></p>
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
