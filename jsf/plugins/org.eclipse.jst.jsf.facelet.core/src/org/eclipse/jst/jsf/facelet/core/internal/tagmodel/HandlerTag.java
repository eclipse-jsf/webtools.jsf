/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.facelet.core.internal.tagmodel;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IHandlerTagElement;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IAttributeAdvisor;
import org.eclipse.jst.jsf.facelet.core.internal.cm.FaceletDocumentFactory;


/**
 * A basic handler tag that has no direct effect on creation of components,
 * converters or validators.
 * 
 * @author cbateman
 *
 */
public class HandlerTag extends FaceletTag
{
    /**
     * 
     */
    private static final long serialVersionUID = 8882557774865456522L;
    private final IHandlerTagElement.TagHandlerType _handlerType;
    /**
     * @param uri
     * @param name
     * @param handlerType
     * @param handlerClassName
     * @param factory
     * @param advisor
     */
    public HandlerTag(final String uri, final String name, final IHandlerTagElement.TagHandlerType handlerType,
            final String handlerClassName, final FaceletDocumentFactory factory, 
            final IAttributeAdvisor advisor) {
        super(uri, name, TagType.HANDLER, handlerClassName, factory, advisor);
        _handlerType = handlerType;
    }

    /**
     * @return the handler type
     */
    public IHandlerTagElement.TagHandlerType getHandlerType()
    {
        return _handlerType;
    }

    @Override
    public String toString()
    {
        String toString = super.toString();

        toString += "Handler Class: " + getTagHandlerClassName() + "\n"; //$NON-NLS-1$ //$NON-NLS-2$

        return toString;
    }


}
