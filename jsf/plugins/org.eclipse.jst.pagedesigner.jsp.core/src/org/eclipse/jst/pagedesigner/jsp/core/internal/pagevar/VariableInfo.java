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

import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IVariableInfo;

/**
 * @author mengbo
 * @version 1.5
 */
public class VariableInfo implements IVariableInfo {
	private String _typeInfo;

	private int _mode;

	private String _name;

	/**
	 * @param name 
	 * @param mode 
	 * @param typeInfo 
	 * 
	 */
	public VariableInfo(String name, int mode, String typeInfo) {
		this._name = name;
		this._mode = mode;
		this._typeInfo = typeInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.IVariableInfo#getName()
	 */
	public String getName() {
		return _name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.IVariableInfo#getMode()
	 */
	public int getMode() {
		return _mode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.jsp.core.pagevar.IVariableInfo#getTypeInfoString()
	 */
	public String getTypeInfoString() {
		return _typeInfo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
    // TODO: no hashcode override?
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof VariableInfo) {
			VariableInfo info = (VariableInfo) obj;
			return this._mode == info._mode && equals(this._name, info._name)
					&& equals(this._typeInfo, info._typeInfo);
		}
        return false;
	}

	private boolean equals(String s1, String s2) {
		if (s1 == null) {
			return s2 == null;
		}
        return s1.equals(s2);
	}
}
