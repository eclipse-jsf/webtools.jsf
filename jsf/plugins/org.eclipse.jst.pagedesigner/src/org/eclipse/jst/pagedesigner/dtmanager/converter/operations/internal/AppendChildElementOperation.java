/*******************************************************************************
 * Copyright (c) 2005, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation that appends a child Element and
 * optionally makes the new Element current.
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class AppendChildElementOperation extends AbstractTransformOperation {

	private String tagName;
	private boolean makeChildCurrent = true;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 1) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		} else if (getParameters().length < 2) {
			tagName = getParameters()[0];
		} else {
			tagName = getParameters()[0];
			makeChildCurrent = Boolean.valueOf(getParameters()[1]).booleanValue();			
		}
		
		Assert.isNotNull(tagName);
		Element element = null;
		if (tagConverterContext != null && curElement != null && tagName != null && tagName.length() > 0) {
			Element childElement = tagConverterContext.createElement(tagName);
			curElement.appendChild(childElement);
			if (makeChildCurrent) {
				element = childElement;
			} else {
				element = curElement;
			}
		}
		return element;
	}

}
