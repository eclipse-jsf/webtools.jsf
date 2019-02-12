/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
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


package org.eclipse.jst.jsf.common.webxml;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;


/**
 * Web.xml utilities.
 * 
 * @author Debajit Adhikary
 * 
 */
public class WebXmlUtils
{
    /**
     * Path to deployment descriptor of webapp
     */
    public static final IPath WEB_XML_PATH = new Path("WEB-INF").append("web.xml"); //$NON-NLS-1$ //$NON-NLS-2$
}
