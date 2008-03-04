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
package org.eclipse.jst.jsf.apache.trinidad.tagsupport.converter.operations;

import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.TransformOperationFactory;
import org.w3c.dom.Element;

/**
 * Extends AbstractTransformOperation to supply extra convenience methods.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractTrinidadTransformOperation extends AbstractTransformOperation {

	protected void appendAttribute(
			Element element, String attributeName, String attributeValue) {
		ITransformOperation operation =
			TransformOperationFactory.getInstance().getTransformOperation(
					TransformOperationFactory.OP_CreateAttributeOperation,
					new String[]{attributeName, attributeValue});
		operation.transform(null, element);
	}

}
