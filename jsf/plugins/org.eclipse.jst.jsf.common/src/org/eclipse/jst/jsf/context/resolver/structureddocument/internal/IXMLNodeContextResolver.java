/*******************************************************************************
 * Copyright (c) 2013, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.context.resolver.structureddocument.internal;

import org.eclipse.jst.jsf.context.IModelContext;


/**
 * A resolver that, like IDOMContextResolver, allows walking a DOM-like structure, but in this case we don't
 * necessarily need a DOM.
 *
 */
public interface IXMLNodeContextResolver
{
    /**
     * @return the resolver for the parent node.
     */
    IXMLNodeContextResolver getParentNodeResolver();

    /**
     * @return true if this resolver's context is on an attribute
     */
    boolean isAttribute();

    /**
     * @return the value of the context if it is an attribute (attribute value) or null otherwise
     */
    String getValue();

    /**
     * @return the local name of the context if it is an attribute or element
     */
    String getLocalName();

    /**
     * @return the namespace of the element or null if the context is not on a uri
     */
    String getNamespaceURI();
    
    /**
     * Set the model context on the resolver.  This is optional and depends on the resolver factory.
     * @param context
     */
    void setContext(IModelContext context);
}
