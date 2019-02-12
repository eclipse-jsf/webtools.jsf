/*******************************************************************************
 * Copyright (c) 2005, 2007 Oracle Corporation.
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
