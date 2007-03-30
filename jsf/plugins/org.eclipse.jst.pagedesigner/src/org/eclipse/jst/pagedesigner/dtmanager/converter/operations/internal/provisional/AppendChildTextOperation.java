/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional;

import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * ITransformOperation implementation that appends a child Text.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 * API: should this be public or should we restrict so can only be constructed
 * through a factory?
 */
public class AppendChildTextOperation extends AbstractTransformOperation {

	private String content;

	/**
	 * Constructs an instance with the specified content.
	 * 
	 * @param content Content of child Text to be created.
	 */
	public AppendChildTextOperation(String content) {
		this.content = content;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (tagConverterContext != null && curElement != null && content != null && content.length() > 0) {
			Text childText = tagConverterContext.createText(content);
			curElement.appendChild(childText);
		}
		return curElement;
	}

}
