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

import java.util.List;

/**
 * Defines the interface for a Design-Time (DT) "Resource Provider", which
 * offers such services as might be expected to be provided at runtime by, for
 * example, a resource servlet.
 * 
 * @author Ian Trimble - Oracle
 */
public interface IDTResourceProvider {

	/**
	 * Returns this instance's ID.
	 * 
	 * @return This instance's ID.
	 */
	public String getId();

	/**
	 * Returns a List of IDTSkin instances.
	 * 
	 * @return A List of IDTSkin instances. May return an empty List, but should
	 * not return null.
	 */
	public List<IDTSkin> getSkins();

}
