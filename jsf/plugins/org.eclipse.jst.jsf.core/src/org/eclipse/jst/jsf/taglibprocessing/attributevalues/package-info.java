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
