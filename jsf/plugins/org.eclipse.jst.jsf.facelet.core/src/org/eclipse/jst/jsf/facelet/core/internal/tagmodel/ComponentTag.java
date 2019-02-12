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

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IAttributeAdvisor;
import org.eclipse.jst.jsf.facelet.core.internal.cm.FaceletDocumentFactory;




/**
 * A basic JSF component facelet tag element
 * 
 * @author cbateman
 *
 */
public class ComponentTag extends FaceletTag implements IComponentTagElement
{
    /**
     * 
     */
    private static final long serialVersionUID = -7457091811357699617L;
    private final ComponentTypeInfo _typeInfo;

//    public ComponentTag(final String uri, final String name, final String componentType)
//    {
//        // renderType and handlerClass are (?) in the dtd
//        this(uri, name, componentType, null);
//    }

    /**
     * @param uri
     * @param name
     * @param typeInfo
     * @param handlerClass 
     * @param factory
     * @param advisor
     */
    public ComponentTag(final String uri,
            final String name,
            final ComponentTypeInfo typeInfo,
            final String handlerClass,
            final FaceletDocumentFactory factory, 
            final IAttributeAdvisor advisor)
    {
        super(uri, name, TagType.COMPONENT, handlerClass, factory, advisor);
        _typeInfo = typeInfo;
    }

    @Override
    public String toString() {
        String toString = super.toString();
        toString += "; Component Type: " + _typeInfo.getComponentType(); //$NON-NLS-1$

        final String rendererType = _typeInfo.getRenderFamily();
        if (rendererType != null)
        {
            toString += "; Renderer Type: " + rendererType; //$NON-NLS-1$
        }

        final String handlerClass = getTagHandlerClassName();
        if (handlerClass != null)
        {
            toString += "; Handler Class: " + handlerClass; //$NON-NLS-1$
        }

        return toString;
    }

    public ComponentTypeInfo getComponent()
    {
        return _typeInfo;
    }


}
