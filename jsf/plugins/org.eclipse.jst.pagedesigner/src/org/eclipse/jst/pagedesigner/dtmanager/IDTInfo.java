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

import java.util.List;

import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagConvertInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.TagDecorateInfo;

/**
 * Encapsulates design-time (DT) information.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author Ian Trimble - Oracle
 */
public interface IDTInfo {

	/**
	 * Gets the encapsulated TagConvertInfo instance.
	 * 
	 * @return TagConvertInfo instance.
	 */
	public TagConvertInfo getTagConvertInfo();

	/**
	 * Gets the collection of encapsulated TagDecorateInfo instances.
	 * 
	 * @return Collection of TagDecorateInfo instances.
	 */
	public List getTagDecorateInfos();

	/**
	 * Gets an encapsulated TagDecorateInfo instance matching specified ID.
	 * 
	 * @param id ID of desired TagDecorateInfo instance.
	 * @return TagDecorateInfo instance matching specified ID.
	 */
	public TagDecorateInfo getTagDecorateInfo(String id);

	/**
	 * Gets the Trait instance that was queried to load the metadata.
	 * 
	 * @return Trait instance that was queried to load the metadata.
	 */
	public Trait getTrait();

}
