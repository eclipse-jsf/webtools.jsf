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
package org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional;

import org.w3c.dom.Element;

/**
 * Defines an operation used during transformation of input Element instances
 * to output Element instances.
 * 
 * @author Ian Trimble - Oracle
 */
public interface ITransformOperation {

	/**
	 * Transforms an input element instance to an output Element instance.
	 * 
	 * @param srcElement Original input Element instance.
	 * @param curElement Current Element instance.
	 * @return Resulting transformed Element instance.
	 */
	public Element transform(Element srcElement, Element curElement);

	/**
	 * Sets the ITagConverterContext instance allowing access to context and
	 * functionality of the current ITagConverter instance.
	 * 
	 * @param tagConverterContext ITagConverterContext instance allowing access
	 * to context and functionality of the current ITagConverter instance.
	 */
	public void setTagConverterContext(ITagConverterContext tagConverterContext);

}
