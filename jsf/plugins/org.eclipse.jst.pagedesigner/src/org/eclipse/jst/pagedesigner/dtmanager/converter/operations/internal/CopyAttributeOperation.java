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
 * Copies a single attribute from the source Element instance to the current
 * Element instance, and optionally creates a new attribute on the current
 * Element instance if no such attribute exists on the source Element instance.
 * 
 * @author Ian Trimble - Oracle
 */
public class CopyAttributeOperation extends AbstractTransformOperation {

	private String attributeName;
	private boolean create;
	private String newAttributeValue;


	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 1) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		} else if (getParameters().length < 3) {
			attributeName = getParameters()[0];			
		} else {
			attributeName = getParameters()[0];
			create = Boolean.valueOf(getParameters()[1]).booleanValue();
			newAttributeValue = getParameters()[2];		
		}
		
		Assert.isNotNull(attributeName);
		if (srcElement != null && curElement != null) {
			Attr attribute = srcElement.getAttributeNode(attributeName);
			if (attribute != null) {
				curElement.setAttribute(attributeName, attribute.getValue());
			} else if (create && newAttributeValue != null) {
				curElement.setAttribute(attributeName, newAttributeValue);
			}
		}
		return curElement;
	}

}
