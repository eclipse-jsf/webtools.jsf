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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.w3c.dom.Element;

/**
 * Default ITransformer implementation.
 * 
 * @author Ian Trimble - Oracle
 */
public class DefaultTransformer implements ITransformer {

	private ITagConverterContext tagConverterContext;
    // synchronization occurs on this object, so it must never
    // be allowed to be null
	private final Collection transformOperations = new ArrayList();

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITransformer#appendTransformOperation(org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITransformOperation)
	 */
	public void appendTransformOperation(ITransformOperation operation) {
		synchronized(transformOperations) {
			if (operation != null) {
				operation.setTagConverterContext(tagConverterContext);
				transformOperations.add(operation);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITransformer#transform(org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement) {
		Element curElement = null;
		if (srcElement != null) {
			synchronized(transformOperations) {
				if (transformOperations.size() > 0) {
					curElement = srcElement;
					Iterator itOperations = transformOperations.iterator();
					while (itOperations.hasNext()) {
						ITransformOperation operation = (ITransformOperation)itOperations.next();
						curElement = operation.transform(srcElement, curElement);
					}
				}
			}
		}
		return curElement;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITransformer#setTagConverterContext(org.eclipse.jst.pagedesigner.dtmanager.converter.internal.provisional.ITagConverterContext)
	 */
	public void setTagConverterContext(ITagConverterContext tagConverterContext) {
		this.tagConverterContext = tagConverterContext;
	}

}
