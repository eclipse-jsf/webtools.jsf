/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsp.core.pagevar;

/**
 * @author mengbo
 * @version 1.5
 */
public interface IVariableInfo {
	public String getName();

	public static final int CLASSNAME = 0;

	public static final int EXPRESSION = 1;

	public static final int EXPRESSION_LISTITEM = 2;

	public static final int RESOURCEBUNDLE = 3;

	public int getMode();

	/**
	 * type info string will have different meaning for different mode.
	 * 
	 * @return
	 */
	public String getTypeInfoString();

}
