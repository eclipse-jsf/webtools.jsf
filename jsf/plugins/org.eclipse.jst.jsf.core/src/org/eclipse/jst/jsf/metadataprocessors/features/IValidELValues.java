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

package org.eclipse.jst.jsf.metadataprocessors.features;

import org.eclipse.jst.jsf.common.internal.types.CompositeType;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;

/**
 * An {@link IMetaDataEnabledFeature} for semantic validation of EL Values 
 * 
 * <p><b>Provisional API - subject to change</b></p>
 */
public interface IValidELValues extends IMetaDataEnabledFeature {
	/**
	 * Expected runtime type for EL value
	 * @return CompositeType
	 * @throws ELIsNotValidException
	 */
	public CompositeType getExpectedRuntimeType() throws ELIsNotValidException;
}
