/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.metadataprocessors.internal;

/**
 * Interface used to bind feature extensions to metadata enabled types 
 *
 */
public interface IMetaDataEnabledFeatureExtension {
	/**
	 * @return bundle id
	 */
	public String getBundleID();
	/**
	 * @return type id
	 */
	public String getTypeID();
	/**
	 * @return class name to use for type.   Must be located within the bundle.
	 */
	public String getClassName();
}
