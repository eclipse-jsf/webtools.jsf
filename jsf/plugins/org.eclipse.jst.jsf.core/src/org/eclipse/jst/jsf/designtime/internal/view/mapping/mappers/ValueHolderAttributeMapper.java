/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view.mapping.mappers;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.designtime.internal.view.mapping.CustomViewMappingAdapter;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * Default mapper for value holders.
 * 
 * @author cbateman
 * 
 */
public class ValueHolderAttributeMapper extends CustomViewMappingAdapter
{
    @Override
    public void doAttributeActions(ComponentInfo bestComponent,
            Element srcElement, Attr attr)
    {
        final String name = attr.getNodeName();

        if ("converter".equals(name) //$NON-NLS-1$
                && bestComponent.getComponentTypeInfo().isInstanceOf(
                        ComponentFactory.INTERFACE_VALUEHOLDER))
        {
            final String value = attr.getValue();

            if (value != null)
            {
                ConverterTypeInfo typeInfo = null;

                if (!value.startsWith("#{")) //$NON-NLS-1$
                {
                    typeInfo = new ConverterTypeInfo(null, value);

                }
                else
                {
                    typeInfo = ConverterTypeInfo.UNKNOWN;
                }
                ConverterDecorator decorator = new ConverterDecorator(
                        bestComponent, typeInfo);
                bestComponent.addDecorator(decorator,
                        ComponentFactory.CONVERTER);
            }
        }
    }
}
