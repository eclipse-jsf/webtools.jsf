/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
 * Interface must NOT be implemented by clients.
 * Implementers should sub-class AbstractStructuredDocumentContext
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
