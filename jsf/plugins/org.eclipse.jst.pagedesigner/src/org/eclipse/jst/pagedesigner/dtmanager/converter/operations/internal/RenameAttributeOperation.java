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
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * ITransformOperation implementation that renames an attribute of the current
 * Element.
 * 
 * @author Ian Trimble - Oracle
 */
public class RenameAttributeOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 2) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		}
		
		String oldAttributeName = getParameters()[0];
		String newAttributeName = getParameters()[1];
		Assert.isNotNull(oldAttributeName);
		Assert.isNotNull(newAttributeName);
		
		if (curElement != null) {
			Attr oldAttribute = curElement.getAttributeNode(oldAttributeName);
			if (oldAttribute != null) {
				curElement.setAttribute(newAttributeName, oldAttribute.getValue());
				curElement.removeAttribute(oldAttributeName);
			}
		}
		return curElement;
	}

}
