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

	public String getVarName() {
		return _varName;
	}

	public void setVarName(String varName) {
		this._varName = varName;
	}

	public boolean isVarNameIsAttr() {
		return _varNameIsAttr;
	}

	public void setVarNameIsAttr(boolean varNameIsAttr) {
		this._varNameIsAttr = varNameIsAttr;
	}

	public int getVarTypeMode() {
		return _varTypeMode;
	}

	public void setVarTypeMode(int varTypeMode) {
		this._varTypeMode = varTypeMode;
	}

	public String getVarTypeString() {
		return _varTypeString;
	}

	public void setVarTypeString(String varTypeString) {
		this._varTypeString = varTypeString;
	}

	public boolean isVarTypeStringIsAttr() {
		return _varTypeStringIsAttr;
	}

	public void setVarTypeStringIsAttr(boolean varTypeStringIsAttr) {
		this._varTypeStringIsAttr = varTypeStringIsAttr;
	}

	/**
	 * @param tag
	 */
	public void setTagName(String tag) {
		this._tagName = tag;
	}

	public String getTagName() {
		return this._tagName;
	}
}
