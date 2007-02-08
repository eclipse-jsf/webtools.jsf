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
package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.w3c.dom.Element;

public interface ITransformer {

	public void appendTransformOperation(ITransformOperation operation);

	public Element transform(Element srcElement);

	public void setTagConverterContext(ITagConverterContext tagConverterContext);

}
