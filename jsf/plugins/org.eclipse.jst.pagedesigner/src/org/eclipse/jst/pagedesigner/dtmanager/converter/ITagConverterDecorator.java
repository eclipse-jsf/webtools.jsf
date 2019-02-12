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

import org.eclipse.jst.pagedesigner.converter.ITagConverter;

/**
 * Decorates an ITagConverter instance as appropriate (generally
 * client-specific decoration performed after tag conversion).
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public interface ITagConverterDecorator {

	/**
	 * Decorates the ITagConverter instance as appropriate.
	 * 
	 * @param tagConverter ITagConverter instance to be decorated.
	 */
	public void decorate(ITagConverter tagConverter);

}
