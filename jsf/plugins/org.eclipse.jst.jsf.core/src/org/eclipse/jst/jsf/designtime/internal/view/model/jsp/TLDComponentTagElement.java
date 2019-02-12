/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;



/**
 * A TLD-defined tag (i.e. JSP) that maps one-to-one with a JSF UIComponent
 * @author cbateman
 *
 */
public class TLDComponentTagElement extends TLDJSFTagElement implements
IComponentTagElement
{
    /**
     * 
     */
    private static final long serialVersionUID = -6479445622102799425L;
    private final ComponentTypeInfo     _componentTypeInfo;

    /**
     * @param elementDecl
     * @param componentTypeInfo
     * @param advisor 
     */
    public TLDComponentTagElement(final TLDElementDeclaration elementDecl
            , final ComponentTypeInfo componentTypeInfo, final IAttributeAdvisor advisor)
    {
        super(elementDecl, advisor);
        _componentTypeInfo = componentTypeInfo;
    }

    public ComponentTypeInfo getComponent()
    {
        return _componentTypeInfo;
    }

    @Override
    public TagType getType()
    {
        return TagType.COMPONENT;
    }

    @Override
    public String toString()
    {
        final String attributes = constructAttributesString();
        return _componentTypeInfo.toString() +
            (!"".equals(attributes) ? "\n" + attributes : ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
