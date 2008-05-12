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
 * A no-op implementation that sub-classes can selectively override.
 * 
 * @author cbateman
 *
 */
public class CustomViewMappingAdapter extends AbstractCustomViewMapper
{

    @Override
    public void doAttributeActions(ComponentInfo bestComponent,
            Element srcElement, Attr attr)
    {
        // do nothing
    }

    @Override
    public PropertyMapping mapToComponentProperty(String uri,
            Element srcElement, Attr attr)
    {
        // no mappings
        return null;
    }
}
