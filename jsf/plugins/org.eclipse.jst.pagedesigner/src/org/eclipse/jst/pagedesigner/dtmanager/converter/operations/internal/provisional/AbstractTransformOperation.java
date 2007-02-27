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

import org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITagConverterContext;
import org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITransformOperation;
import org.w3c.dom.Element;

/**
 * Abstract ITransformOperation implementation. Maintains ITagConverterContext
 * instance.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractTransformOperation implements ITransformOperation {

	/**
	 * ITagConverterContext instance.
	 */
	protected ITagConverterContext tagConverterContext;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public abstract Element transform(Element srcElement, Element curElement);

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITransformOperation#setTagConverterContext(org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITagConverterContext)
	 */
	public void setTagConverterContext(ITagConverterContext tagConverterContext) {
		this.tagConverterContext = tagConverterContext;
	}

}
