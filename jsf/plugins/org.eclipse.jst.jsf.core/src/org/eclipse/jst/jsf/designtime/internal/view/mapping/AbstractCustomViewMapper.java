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
 * Must be sub-classed by all ICustomViewMapper's.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractCustomViewMapper implements ICustomViewMapper
{
    public abstract PropertyMapping mapToComponentProperty(final String uri,
            final Element srcElement, final Attr attr);

    public abstract void doAttributeActions(final ComponentInfo bestComponent,
            final Element srcElement, final Attr attr);
}
