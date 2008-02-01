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
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * ITransformOperation implementation that converts an attribute to a child
 * Text Node and optionally removes the specified attribute.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class ConvertAttributeToTextOperation extends AbstractTransformOperation {

	private String attributeName;
	private boolean removeAttribute = true;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 1) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		} else if (getParameters().length < 2) {
			attributeName = getParameters()[0];
		} else {
			attributeName = getParameters()[0];
			removeAttribute = Boolean.valueOf(getParameters()[1]).booleanValue();
		}		
		
		Assert.isNotNull(attributeName);
		if (tagConverterContext != null && srcElement != null && curElement != null) {
			String content = srcElement.getAttribute(attributeName);
			if (content != null && content.length() > 0) {
				Text text = tagConverterContext.createText(content);
				curElement.appendChild(text);
				if (removeAttribute) {
					curElement.removeAttribute(attributeName);
				}
			}
		}
		return curElement;
	}

}
