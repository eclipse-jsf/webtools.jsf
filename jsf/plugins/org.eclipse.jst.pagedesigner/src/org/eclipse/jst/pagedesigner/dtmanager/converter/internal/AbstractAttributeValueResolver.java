/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
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
