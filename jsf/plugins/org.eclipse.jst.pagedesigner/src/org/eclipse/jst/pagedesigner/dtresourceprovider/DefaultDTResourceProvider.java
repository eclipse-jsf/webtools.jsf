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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Concrete implementation of IDTResourceProvider.
 * 
 * @author Ian Trimble - Oracle
 */
public class DefaultDTResourceProvider extends AbstractDTResourceProvider {

	/**
	 * List of IDTSkin instances.
	 */
	protected List<IDTSkin> skins;

	/**
	 * Constructs an instance.
	 * 
	 * @param id The ID of this provider.
	 */
	public DefaultDTResourceProvider(String id) {
		super(id);
		skins = new ArrayList<IDTSkin>();
	}

	/**
	 * Constructs an instance.
	 * 
	 * @param id The ID of this provider.
	 * @param skins The List of IDTSkin instances.
	 */
	public DefaultDTResourceProvider(String id, List<IDTSkin> skins) {
		super(id);
		if (skins != null) {
			this.skins = skins;
		} else {
			this.skins = new ArrayList<IDTSkin>();
		}
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtresourceprovider.IDTResourceProvider#getSkins()
	 */
	public List<IDTSkin> getSkins() {
		return Collections.unmodifiableList(skins);
	}

	/**
	 * Adds the specified IDTSkin instance to the List of IDTSkin instances.
	 * 
	 * @param skin IDTSkin instance to be added.
	 * @return true (as per the general contract of the Collection.add method).
	 */
	public boolean addSkin(IDTSkin skin) {
		boolean ret = false;
		if (skin != null) {
			ret = skins.add(skin);
		}
		return ret;
	}

}
