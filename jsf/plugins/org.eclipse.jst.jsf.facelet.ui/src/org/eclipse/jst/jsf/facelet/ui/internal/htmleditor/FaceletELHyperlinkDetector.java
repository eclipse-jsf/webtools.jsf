/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.facelet.ui.internal.htmleditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.ui.internal.jspeditor.AbstractELHyperlinkDetector;

/**
 * This HyperlinkDetector creates hyperlinks for symbols in JSF EL expressions
 * inside facelet files.
 */
public class FaceletELHyperlinkDetector extends AbstractELHyperlinkDetector {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.jsf.ui.internal.jspeditor.AbstractELHyperlinkDetector#isEnabled(org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext)
	 */
	@Override
	protected boolean isEnabled(IStructuredDocumentContext context) {
		boolean enabled = false;
		if (context != null) {
			IWorkspaceContextResolver resolver =
				IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(context);
			if (resolver != null) {
				IResource resource = resolver.getResource();
				if (resource instanceof IFile) {
					IFile file = (IFile)resource;
					String filename = file.getFullPath().toString();
					enabled =
						hasContentType(filename, "jsf.facelet") || //$NON-NLS-1$
						hasContentType(filename, "jsf.facelet.composite"); //$NON-NLS-1$
				}
			}
		}
		return enabled;
	}

}
