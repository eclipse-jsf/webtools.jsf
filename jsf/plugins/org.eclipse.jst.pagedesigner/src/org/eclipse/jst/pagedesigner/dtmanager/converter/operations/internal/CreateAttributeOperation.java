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
 * ITransformOperation implementation that creates a new attribute on the
 * current Element.
 * 
 * @author Ian Trimble - Oracle
 */
public class CreateAttributeOperation extends AbstractTransformOperation {

	private String attributeName;
	private String attributeValue;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 2) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		}
		
		attributeName = getParameters()[0];
		attributeValue = getParameters()[1];		
		Assert.isNotNull(attributeName);
		Assert.isNotNull(attributeValue);
	
		if (curElement != null) {
			curElement.setAttribute(attributeName, attributeValue);
		}
		return curElement;
	}

}
