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
package org.eclipse.jst.pagedesigner.dtmanager.converter;

import org.w3c.dom.Element;

/**
 * Transforms an original input Element instance to an output Element instance
 * by invoking a collection of ITransformOperation instances.  
 * 
 * @author Ian Trimble - Oracle
 */
public interface ITransformer {

	/**
	 * Appends an ITransformOperation instance to the collection.
	 * 
	 * @param operation ITransformOperation instance to be appended.
     * API: does this need to be on the interface or could it be pushed
     * into the instance constructor (i.e. factory).
	 */
	public void appendTransformOperation(ITransformOperation operation);

	/**
	 * Transforms an original input Element instance to an output Element
	 * instance, typically by invoking each ITransformOperation instance in
	 * this instance's collection.
	 * 
	 * @param srcElement Original input Element instance.
	 * @return Transformed output Element instance.
     * API: I'd like to consider templating the generic ITransformer
     * interface to ITransformer<I,O> and making this a normative instance
     * case of ITransformer<Element, Element>
	 */
	public Element transform(Element srcElement);

	/**
	 * Sets the ITagConverterContext instance allowing access to context and
	 * functionality of the current ITagConverter instance.
	 * 
	 * @param tagConverterContext ITagConverterContext instance allowing access
	 * to context and functionality of the current ITagConverter instance.
     * API: does this need to be on the interface?  Why couldn't this be set
     * at construction time from a factory?  Alternatively, should it be passed
     * as an argument to tranform()?
	 */
	public void setTagConverterContext(ITagConverterContext tagConverterContext);

}
