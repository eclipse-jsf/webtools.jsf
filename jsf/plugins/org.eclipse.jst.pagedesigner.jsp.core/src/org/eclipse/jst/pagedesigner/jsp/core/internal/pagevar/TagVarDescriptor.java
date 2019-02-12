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
package org.eclipse.jst.pagedesigner.jsp.core.internal.pagevar;

/**
 * @author mengbo
 * @version 1.5
 */
public class TagVarDescriptor {
	private String _varName;

	private boolean _varNameIsAttr;

	private String _varTypeString;

	private boolean _varTypeStringIsAttr;

	private int _varTypeMode;

	private String _tagName;

	/**
	 * @return the varName
	 */
	public String getVarName() {
		return _varName;
	}

	/**
	 * @param varName
	 */
	public void setVarName(String varName) {
		this._varName = varName;
	}

	/**
	 * @return true if var name is attribute
	 */
	public boolean isVarNameIsAttr() {
		return _varNameIsAttr;
	}

	/**
	 * @param varNameIsAttr
	 */
	public void setVarNameIsAttr(boolean varNameIsAttr) {
		this._varNameIsAttr = varNameIsAttr;
	}

	/**
	 * @return the variable type mode
	 */
	public int getVarTypeMode() {
		return _varTypeMode;
	}

	/**
	 * @param varTypeMode
	 */
	public void setVarTypeMode(int varTypeMode) {
		this._varTypeMode = varTypeMode;
	}

	/**
	 * @return the variable type string
	 */
	public String getVarTypeString() {
		return _varTypeString;
	}

	/**
	 * @param varTypeString
	 */
	public void setVarTypeString(String varTypeString) {
		this._varTypeString = varTypeString;
	}

	/**
	 * @return true if variable type string is attr
	 */
	public boolean isVarTypeStringIsAttr() {
		return _varTypeStringIsAttr;
	}

	/**
	 * @param varTypeStringIsAttr
	 */
	public void setVarTypeStringIsAttr(boolean varTypeStringIsAttr) {
		this._varTypeStringIsAttr = varTypeStringIsAttr;
	}

	/**
	 * @param tag
	 */
	public void setTagName(String tag) {
		this._tagName = tag;
	}

	/**
	 * @return the tag name
	 */
	public String getTagName() {
		return this._tagName;
	}
}
