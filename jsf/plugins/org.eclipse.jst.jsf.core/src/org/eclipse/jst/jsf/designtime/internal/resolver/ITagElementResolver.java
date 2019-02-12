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
package org.eclipse.jst.jsf.designtime.internal.resolver;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.IDocumentContextResolver;
import org.w3c.dom.Node;

/**
 * Defines a resolver that can resolve the ITagElement information from the
 * structured document region context.
 *
 */
public interface ITagElementResolver extends IDocumentContextResolver {
	
	/**
	 * @param node
	 * @return ITagElement 
	 */
	public ITagElement getTagElement(final Node node);
	
	public boolean canResolveContext(final IModelContext modelContext);
}
