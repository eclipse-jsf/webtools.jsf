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
 * Provides tooling support for taglibraries using the Design-time Metadata
 * feature (org.eclipse.jst.jsf.common.metadata).
 * <p>
 * The MetaDataEnabledProcessingFactory singleton instance is the entry point.   The tool requests
 * the IMetaDataEnabled feature for a particular tag attribute, and through metadata, will locate the 
 * "metadata processing" feature using the attribute value runtime type system.
 * <p><b>Provisional API - subject to change</b></p>
 */
package org.eclipse.jst.jsf.metadataprocessors;
