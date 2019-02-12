/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
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

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl;

import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMAttr;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * @author cbateman
 *
 * Defines common utility methods for document resolvers
 */
final class StructuredDocumentResolverUtil 
{
    static IDOMDocument getDOMDocument(IStructuredDocument document)
    {
        IStructuredModel model = getStructuredModel(document);
        
        try
        {
            if (model instanceof IDOMModel)
            {
                return ((IDOMModel)model).getDocument();
            }
        }
        finally
        {
            if (model != null)
            {
                model.releaseFromRead();
            }
        }
        
        return null;
    }
    
    static IndexedRegion getIndexedRegion(final IStructuredDocument document, final int documentOffset)
    {
        // C.B: most of this logic was copied from ContentAssistUtils.getNodeAt
        // I chose to copy rather than just call that because ContentAssistUtils is
        // internal
        IStructuredModel model = getStructuredModel(document);
        IndexedRegion             region = null;
        if (model != null)
        {
            try
            {
                int lastOffset = documentOffset;
                region = model.getIndexedRegion(documentOffset);
                while (region == null && lastOffset >= 0) {
                    lastOffset--;
                    region = model.getIndexedRegion(lastOffset);
                }
                
                // now we assume we have an element.  But our context may be
                // on an attribute in that node, so we need to check
                if (region instanceof IDOMElement)
                {
                    IDOMElement domElement = (IDOMElement) region;
                    
                    NamedNodeMap attributes = domElement.getAttributes();
                    
                    for (int i = 0; i < attributes.getLength(); i++)
                    {
                        Node  attrNode = attributes.item(i);
                        
                        if (attrNode instanceof IDOMAttr)
                        {
                            IDOMAttr attr = (IDOMAttr) attrNode;
                            
                            if (documentOffset >= attr.getStartOffset()
                                    && documentOffset < attr.getEndOffset())
                            {
                                region = attr;
                                break;
                            }
                        }
                    }
                }
            }
            finally
            {
                model.releaseFromRead();
            }
        }

        return region;
    }
    
    /**
     * @param document
     * @return a structured model or null if one cannot be opened for document.
     * Note: the caller is responsible for releasing the instance of structured
     * model that gets returned.
     */
    private static IStructuredModel getStructuredModel(IStructuredDocument document)
    {
        IModelManager modelManager = StructuredModelManager.getModelManager();
        
        if (modelManager != null)
        {
            return StructuredModelManager.getModelManager().getModelForRead(document); 
        }
        
        return null;
    }
}
