/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.internal.appconfig;


/**
 * @author cbateman
 *
 */
public abstract class ViewIdValidator extends NodeValidationVisitor 
{
    /**
     * @param nodeType
     * @param nodeName
     */
    protected ViewIdValidator(final short nodeType, final String nodeName) 
    {
        super(nodeType, nodeName);
    }

}
