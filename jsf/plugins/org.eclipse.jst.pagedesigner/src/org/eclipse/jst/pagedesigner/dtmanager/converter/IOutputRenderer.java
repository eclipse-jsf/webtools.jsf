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
 * Produces output rendering for ITagConverter instances.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public interface IOutputRenderer {

	/**
	 * Uses the ITagConverterContext argument to produce output rendering.
	 * 
	 * @param tagConverterContext ITagConverterContext instance providing
	 * ITagConverter-specific context and functionality.
	 * @return Element instance representing top Element in resulting output
	 * tree.
	 * 
     * TODO: let's parameterize the return type either with a generic type
     * or with Object so that sub-classes can co-vary
	 */
	public Element render(ITagConverterContext tagConverterContext);

}
