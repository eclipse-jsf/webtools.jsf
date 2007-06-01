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
