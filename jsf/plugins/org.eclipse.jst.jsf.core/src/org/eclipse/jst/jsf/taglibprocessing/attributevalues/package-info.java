/*******************************************************************************
 * Copyright (c) 2006, 2008 Oracle Corporation.
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

/**
 * Provides type system for tag attribute values so that the 
 * org.eclipse.jst.jsf.core.metadataprocessors.features can be applied.  
 * 
 * A type is added using the org.eclipse.jst.jsf.core.AttributeValueRuntimeTypes extension point.
 * These types are referenced using the design-time metadata framework in org.eclipse.jst.jsf.common
 * The org.eclipse.jst.jsf.metadataprocessors.features provides the interesting tooling services based upon the type.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 */
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;
