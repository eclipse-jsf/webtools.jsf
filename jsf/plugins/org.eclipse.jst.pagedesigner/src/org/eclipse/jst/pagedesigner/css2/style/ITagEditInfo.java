/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.css2.style;

/**
 * TODO: TagConversion decorator should augment/replace
 * @author mengbo
 * @version 1.5
 */
public interface ITagEditInfo 
{
	/**
	 * @return true if is a widget
	 */
	public boolean isWidget();

	/**
	 * @return true if a border decorator is needed
	 */
	public boolean needBorderDecorator();

	/**
	 * @return true if a table decorator is needed
	 */
	public boolean needTableDecorator();

	/**
	 * for some element in design mode we want to them to have a default min
	 * size.
	 * 
	 * @return positive value means an expected min size.
	 */
	public int getMinWidth();

	/**
	 * @return the minimum height
	 */
	public int getMinHeight();
}
