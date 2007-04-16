/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context.structureddocument;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.context.IDocumentContext;

/**
 * Encapsulates runtime context in an SSE IStructuredDocument
 * @author cbateman
 *
 * Interface may sub-classed or implemented by clients.
 *
 */
public interface IStructuredDocumentContext extends IDocumentContext
{
	/**
	 * @return the text viewer whose context we are in
	 */
	IDocument getStructuredDocument();
	/**
	 * @return the document position within the text viewer's context
	 * where we are.
	 */
	int getDocumentPosition();
}
