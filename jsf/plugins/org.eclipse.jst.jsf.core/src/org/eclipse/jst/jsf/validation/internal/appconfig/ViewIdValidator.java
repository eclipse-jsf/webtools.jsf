/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
