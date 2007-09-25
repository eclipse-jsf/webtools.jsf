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
package org.eclipse.jst.pagedesigner.parts;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.w3c.dom.Node;

/**
 * The model here can only be Document/Element/Text.
 * 
 * @author mengbo
 */
public class HTMLEditPartsFactory implements EditPartFactory {
	private IDOMDocument _destDocument;

	/**
	 * @param destDoc
	 */
	public HTMLEditPartsFactory(IDOMDocument destDoc) {
		this._destDocument = destDoc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 *      java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
        NodeEditPart part = null;
        
        if (model instanceof Node)
        {
    		Node node = (Node) model;
    		if (node.getNodeType() == Node.DOCUMENT_NODE) {
    			part = new DocumentEditPart();
    		} else if (node.getNodeType() == Node.ELEMENT_NODE) {
    			// String tag = ((Element)node).getTagName();
    			// if ("TABLE".equalsIgnoreCase(tag))
    			// part = new HTMLTableEditPart();
    			// else
                    part = new ElementEditPart();
    		} else if (node.getNodeType() == Node.TEXT_NODE
    				|| node.getNodeType() == Node.CDATA_SECTION_NODE) {
    			part = new TextEditPart();
    		}
        }
        // XXX: we need to create wrapper that allows us to know when
        // a model object represents a non-visual decorator
        else if (model instanceof ITagConverter)
        {
            part = new NonVisualComponentEditPart();
        }
        
		if (part != null) {
			part.setDestDocumentForDesign(this._destDocument);
			part.setModel(model);
		}
		return part;
	}

}
