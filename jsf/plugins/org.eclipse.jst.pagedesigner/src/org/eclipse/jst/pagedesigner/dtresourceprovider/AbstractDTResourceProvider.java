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
 * Abstract implementation of IDTResourceProvider that is intended for client
 * extension.
 * 
 * @author Ian Trimble - Oracle
 */
public abstract class AbstractDTResourceProvider implements IDTResourceProvider {

	/**
	 * The ID of this provider.
	 */
	protected String id;

	/**
	 * Constructs an instance.
	 * 
	 * @param id The ID of this provider.
	 */
	public AbstractDTResourceProvider(String id) {
		setId(id);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtresourceprovider.IDTResourceProvider#getId()
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the ID of this provider.
	 * 
	 * @param id The ID of this provider.
	 */
	public void setId(String id) {
		this.id = id;
	}

}
