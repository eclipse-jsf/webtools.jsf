/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;

/**
 * This HyperlinkDetector creates hyperlinks for symbols in JSF EL expressions
 * inside jsp files.
 */
public class JSPELHyperlinkDetector extends AbstractELHyperlinkDetector {

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
					enabled = hasContentType(filename, "org.eclipse.jst.jsp.core.jspsource"); //$NON-NLS-1$
				}
			}
		}
		return enabled;
	}

}
