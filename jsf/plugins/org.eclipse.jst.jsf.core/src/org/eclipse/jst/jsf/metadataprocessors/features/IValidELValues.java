/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
