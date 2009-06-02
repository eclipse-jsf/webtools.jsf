/*******************************************************************************
 * Copyright (c) 2009 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.dtresourceprovider;

/**
 * Abstract implementation of IDTSkin that is intended for client extension.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractDTSkin implements IDTSkin {

	/**
	 * The human-readable name of the skin.
	 */
	protected String name;

	/**
	 * true if this instance is considered the default for its provider.
	 */
	protected boolean isDefault;

	/**
	 * Constructs an instance.
	 * 
	 * @param name The human-readable name of the skin.
	 */
	public AbstractDTSkin(String name) {
		this(name, false);
	}

	/**
	 * Constructs an instance.
	 * 
	 * @param name The human-readable name of the skin.
	 * @param isDefault true if this instance is considered the default for its
	 * provider.
	 */
	public AbstractDTSkin(String name, boolean isDefault) {
		setName(name);
		setDefault(isDefault);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtresourceprovider.IDTSkin#getName()
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the human-readable name of the skin.
	 * 
	 * @param name The human-readable name of the skin.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtresourceprovider.IDTSkin#isDefault()
	 */
	public boolean isDefault() {
		return isDefault;
	}

	/**
	 * Sets if this instance is considered the default for its provider.
	 * 
	 * @param isDefault	true if this instance is considered the default for its
	 * provider.
	 */
	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

}
