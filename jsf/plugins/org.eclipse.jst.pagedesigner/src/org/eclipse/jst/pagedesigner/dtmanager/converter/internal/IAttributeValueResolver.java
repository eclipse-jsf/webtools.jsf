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
 * Marks an object that can resolve the runtime value of an attribute at 
 * design time.  The "runtime" value may be a best-guess or simulated value.
 * 
 * @author cbateman
 *
 */
public interface IAttributeValueResolver
{
    /**
     * Passed as convertedAttrName if the convertedElement's text content 
     * is the 'attribute' to be resolved.
     */
    public static final String TEXT_NODE_KEY = "#text"; //$NON-NLS-1$

    /**
     * @param originalElement
     * @param convertedElement 
     * @param convertedAttrName 
     * @param convertedAttrValue 
     * 
     * @return true if this resolver can resolve the value of attrName on domNode
     */
    public abstract boolean canResolve(final Element originalElement,
            final Element convertedElement,
            final String convertedAttrName,
            final String convertedAttrValue);

    /**
     * @param originalElement 
     * @param convertedElement 
     * @param convertedAttrName 
     * @param convertedAttrValue 
     * @return the resolved String value of attrName.  A value of null does not necessarily
     * mean that the value couldn't be resolved.
     */
    public abstract String resolveAttribute(final Element originalElement,
            final Element convertedElement, final String convertedAttrName,
            final String convertedAttrValue);
}