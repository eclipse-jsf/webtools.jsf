/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.properties;

import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySourceProvider;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.ui.internal.properties.XMLPropertySource;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class AttributePropertySourceProvider implements IPropertySourceProvider {

	/**
	 * Constructor
	 */
	public AttributePropertySourceProvider() {
		//
	}

	public IPropertySource getPropertySource(Object object) {
		Element model = null;
		IPropertySource source = null;

		if ((model = DesignerPropertyTool.getElementNode(object)) != null) {
			source = (IPropertySource) ((INodeNotifier) (model))
					.getAdapterFor(IPropertySource.class);
			if (source == null) {
				source = new XMLPropertySource((INodeNotifier) model);
			}
		}
		if (source != null) {
			return new AttributePropertySource( model, source);
		}
		return source;
	}

}
