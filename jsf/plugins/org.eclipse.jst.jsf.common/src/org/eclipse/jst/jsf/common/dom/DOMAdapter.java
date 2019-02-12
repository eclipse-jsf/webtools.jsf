/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.dom;

/**
 * A generic adapter that can adapt some non-DOM
 * things to a dom-like structure like an IRegion to a DOM-like structure.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public abstract class DOMAdapter
{
    /**
     * @return the node type.  Conforms to Node.getNodeType.
     */
    public abstract short getNodeType();
    
    /**
     * @return the name prefix.  Conforms to W3C Node.getPrefix()
     */
    public abstract String getPrefix();
    
    /**
     * @return the local part of the name.  Conforms to W3C Node.getLocalName()
     */
    public abstract String getLocalName();
    
    /**
     * @return the name of the node.  Conforms the W3C Node.getNodeName interface
     */
    public abstract String getNodeName();
}
