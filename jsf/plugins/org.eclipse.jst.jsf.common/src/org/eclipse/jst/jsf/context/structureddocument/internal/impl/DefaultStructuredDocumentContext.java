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

package org.eclipse.jst.jsf.context.structureddocument.internal.impl;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.context.structureddocument.internal.provisional.AbstractStructuredDocumentContext;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/*package*/ class DefaultStructuredDocumentContext extends AbstractStructuredDocumentContext
{
	private final IStructuredDocument		_structuredDocument;
	private final int						_documentPosition;
	
	/*package*/ DefaultStructuredDocumentContext(IStructuredDocument structuredDocument, int documentPosition)
	{
		_structuredDocument = structuredDocument;
		_documentPosition = documentPosition;
	}

	public int getDocumentPosition() 
	{
		return _documentPosition;
	}

	public IDocument getStructuredDocument()
	{
		return _structuredDocument;
	}
}
