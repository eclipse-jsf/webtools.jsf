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
package org.eclipse.jst.pagedesigner.dtmanager;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.DTInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo;

/**
 * Default IDTInfo implementation.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public class DefaultDTInfo implements IDTInfo {

	private DTInfo dtInfo = null;
	private Trait trait = null;

	/**
	 * Constructs an instance that wraps the specified DTInfo instance.
	 * 
	 * @param dtInfo DTInfo (EMF model object) instance.
	 * @param trait Trait instance that was queried to load this data.
	 */
	public DefaultDTInfo(DTInfo dtInfo, Trait trait) {
		this.dtInfo = dtInfo;
		this.trait = trait;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.IDTInfo#getTagConvertInfo()
	 */
	public TagConvertInfo getTagConvertInfo() {
		return dtInfo.getTagConvertInfo();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.IDTInfo#getTagDecorateInfos()
	 */
	public List getTagDecorateInfos() {
		return dtInfo.getTagDecorateInfos();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.IDTInfo#getTagDecorateInfo(java.lang.String)
	 */
	public TagDecorateInfo getTagDecorateInfo(String id) {
		TagDecorateInfo tdInfo = null;
		EList tdInfos = dtInfo.getTagDecorateInfos();
		Iterator itTDInfos = tdInfos.iterator();
		while (itTDInfos.hasNext()) {
			TagDecorateInfo curTDInfo = (TagDecorateInfo)itTDInfos.next();
			if (curTDInfo.getId().equals(id)) {
				tdInfo = curTDInfo;
				break;
			}
		}
		return tdInfo;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.internal.provisional.IDTInfo#getTrait()
	 */
	public Trait getTrait() {
		return trait;
	}

}
