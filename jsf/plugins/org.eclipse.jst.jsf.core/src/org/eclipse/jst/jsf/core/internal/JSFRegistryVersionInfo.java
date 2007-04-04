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
