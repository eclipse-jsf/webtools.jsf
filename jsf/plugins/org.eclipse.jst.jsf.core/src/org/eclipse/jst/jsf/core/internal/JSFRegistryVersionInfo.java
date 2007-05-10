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
package org.eclipse.jst.jsf.core.internal;

import org.osgi.framework.Version;

/**
 * A value object the describes the current and expected state of the JSF Library
 * Registry
 * 
 * @author cbateman
 *
 */
final class JSFRegistryVersionInfo 
{
    private Version     _curVersion;        // the version of the current registry on disk
    private Version     _expectedVersion;   // the expected or required version of the registry
    
    JSFRegistryVersionInfo(Version oldVersion, Version expectedVersion) 
    {
        super();
        _curVersion = oldVersion;
        _expectedVersion = expectedVersion;
    }

    /**
     * @return the version of the registry found in the current workspace
     */
    public Version getCurVersion() {
        return _curVersion;
    }

    /**
     * @return the version of the registry that is expected
     */
    public Version getExpectedVersion() {
        return _expectedVersion;
    }
}
