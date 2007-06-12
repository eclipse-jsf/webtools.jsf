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

package org.eclipse.jst.jsf.metadataprocessors;


/**
 * Type descriptor that is created if no class if provided for the implementation 
 * in the AttributeValueRuntimeTypes extension point.
 * <p><b>Provisional API - subject to change</b></p>
 * @author Gerry Kessler - Oracle
 *
 */
public class DefaultTypeDescriptor extends AbstractRootTypeDescriptor {
    // abstract super contains no abstract methods, so do nothing: automatic default
}
