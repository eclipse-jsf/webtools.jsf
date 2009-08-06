/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
