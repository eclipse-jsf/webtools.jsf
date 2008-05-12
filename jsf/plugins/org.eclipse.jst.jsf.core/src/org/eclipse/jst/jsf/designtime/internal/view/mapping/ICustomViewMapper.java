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
package org.eclipse.jst.jsf.designtime.internal.view.mapping;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * A custom view mapper to map from elements and attributes to view objects and
 * properties.
 * 
 * @author cbateman
 * 
 */
public interface ICustomViewMapper
{
    /**
     * @param uri
     * @param srcElement
     * @param attr
     * @return the name/value of the component property or null if no mapping.
     */
    PropertyMapping mapToComponentProperty(final String uri,
            final Element srcElement, final Attr attr);

    /**
     * Allows a mapper to make updates to a component based on its attribute.
     * May choose to do nothing.
     * 
     * @param bestComponent
     * @param srcElement
     * @param attr
     */
    void doAttributeActions(ComponentInfo bestComponent, Element srcElement,
            Attr attr);

    /**
     * The name/value pair of a component property mapping.
     * 
     * @author cbateman
     *
     */
    public static class PropertyMapping
    {
        private final String _name;
        private final Object _property;

        /**
         * @param name
         * @param property
         */
        public PropertyMapping(final String name, final Object property)
        {
            _name = name;
            _property = property;
        }

        /**
         * @return the property name
         */
        public final String getName()
        {
            return _name;
        }

        /**
         * @return the property value
         */
        public final Object getProperty()
        {
            return _property;
        }
    }
}
