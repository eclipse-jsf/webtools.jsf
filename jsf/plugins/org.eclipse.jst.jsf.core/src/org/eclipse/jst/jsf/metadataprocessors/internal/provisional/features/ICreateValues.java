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

package org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features;

import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature;

/**
 * @author Gerry Kessler - Oracle
 * 
 * Experimental
 *
 */
public interface ICreateValues extends IMetaDataEnabledFeature{
	public Object createValue();
}
