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
package org.eclipse.jst.jsf.metadata.tests.metadataprocessing.features;

import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;

public interface IBarker extends IMetaDataEnabledFeature {

	public boolean canBark();
	public List<?> getBarks();
}
