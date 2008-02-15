/**
 * Copyright (c) 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation
 */
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;

/**
 * Copies a single attribute from the source Element instance to the current
 * Element instance, renaming the attribute on the current Element instance.
 * 
 * @author Ian Trimble - Oracle
 */
public class CopyAttributeWithRenameOperation extends AbstractTransformOperation {

	/* (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (getParameters().length < 2) {
			getLog().error("Warning.TransformOperationFactory.TooFewParameters", getTransformOperationID()); //$NON-NLS-1$
			return null;
		}

		String srcAttributeName = getParameters()[0];
		String destAttributeName = getParameters()[1];
		Assert.isNotNull(srcAttributeName);
		Assert.isNotNull(destAttributeName);

		if (srcElement != null && curElement != null) {
			Attr attribute = srcElement.getAttributeNode(srcAttributeName);
			if (attribute != null) {
				curElement.setAttribute(destAttributeName, attribute.getValue());
			}
		}

		return curElement;
	}

}
