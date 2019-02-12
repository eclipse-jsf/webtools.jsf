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

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IHandlerTagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A TLD-defined tag (i.e. JSP) that maps to a known tag handler.
 * 
 * @author cbateman
 *
 */
public class TLDTagHandlerElement extends TLDJSFTagElement implements
IHandlerTagElement
{
    /**
     * 
     */
    private static final long serialVersionUID = 8984277085824995102L;
    private final TagHandlerType      _tagHandlerType;

    /**
     * @param elementDecl
     * @param tagHandlerType
     * @param advisor 
     */
    public TLDTagHandlerElement(final TLDElementDeclaration elementDecl, final TagHandlerType tagHandlerType, final IAttributeAdvisor advisor)
    {
        super(elementDecl, advisor);
        _tagHandlerType = tagHandlerType;
    }

    public TagHandlerType getTagHandlerType()
    {
        return _tagHandlerType;
    }

    @Override
    public TagType getType()
    {
        return TagType.HANDLER;
    }
}
