/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

/**
 * Translate from the source model metadata structure into a standard metadata domain of metadata.
 *
 */
public interface IMetaDataTranslator {
	/**
	 * @param assistant perform translation using the IMetaDataModelMergeAssistant
	 */
	public void translate(IMetaDataModelMergeAssistant assistant);
	/**
	 * @param modelProvider - cannot be null
	 * @return true if this translator can translate this model source
	 */
	public boolean canTranslate(IMetaDataSourceModelProvider modelProvider);
}
