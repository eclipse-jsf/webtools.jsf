/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.symbols;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.tld.LoadBundleUtil;

class ResourceBundleMapSource extends AbstractMap
{
    private static final String   PROPERTY_QUALIFIER = 
    	"org.eclipse.jst.jsf.designtime.internal.jsp"; //$NON-NLS-1$
    private static final String   SESSION_PROPERTY_NAME_PROJECT = 
    	"ResourceBundleMapSource"; //$NON-NLS-1$
    private static final QualifiedName  SESSION_PROPERTY_KEY_PROJECT = 
        new QualifiedName(PROPERTY_QUALIFIER, SESSION_PROPERTY_NAME_PROJECT);

    private static IFile    getCachedBundleFile(final IProject project, final String baseName)
    {
        if (project != null)
        {
            return (IFile) getBundleFileCache(project).get(baseName);
        }

        return null;
    }

    private static Map getBundleFileCache(IProject project)
    {
        synchronized(project)
        {
            Map bundleFileCache = null;

            try
            {
                bundleFileCache = 
                    (Map) project.getSessionProperty(SESSION_PROPERTY_KEY_PROJECT);

                if (bundleFileCache == null)
                {
                    bundleFileCache = new HashMap();
                    project.setSessionProperty(SESSION_PROPERTY_KEY_PROJECT, bundleFileCache);
                }
            }
            catch (CoreException ce)
            {
                JSFCorePlugin.log("Error creating bundle file cache", ce); //$NON-NLS-1$
            }

            return bundleFileCache;
        }
    }

    private static IFile createCachedBundleFile(final IProject project, 
                                                   final String  resourcePathStr)
                      throws IOException, CoreException
    {
        IStorage storage = LoadBundleUtil.getLoadBundleResource(project, resourcePathStr);

        IFile bundleRes = null;

        if (storage != null
                && storage.getAdapter(IFile.class) != null)
        {
            bundleRes = (IFile) storage.getAdapter(IFile.class);
            getBundleFileCache(project).put(resourcePathStr, bundleRes);
            return bundleRes;
        }

        throw new IOException("Bundle "+resourcePathStr+" not found in classpath for project: "+project.getName()); //$NON-NLS-1$ //$NON-NLS-2$
    }

    private Properties                  _resourceBundle; // = null; set on first access or changes
    private final IFile                 _bundleFile;   // the resource
    private final String                _resourcePathStr; // the key used in the file cache
    // as returned by IResource.getModificationStamp() 
    // the last time _resourceBundle was loaded
    private long                        _lastModificationStamp;

    ResourceBundleMapSource(final IProject context, 
                            final String  resourcePathStr)
                                throws IOException, JavaModelException, CoreException
    {
        IFile cachedBundleFile = getCachedBundleFile(context, resourcePathStr);

        if (cachedBundleFile == null)
        {
            cachedBundleFile = createCachedBundleFile(context, resourcePathStr);
        }

        _bundleFile = cachedBundleFile;
        _resourcePathStr = resourcePathStr;
    }

    private void checkAndRefreshBundle()
    {
        if (_bundleFile.isAccessible())
        {
            if (_resourceBundle == null
                    || _bundleFile.getModificationStamp() 
                            != _lastModificationStamp)
            {
                InputStream  bundleStream = null;
                try
                {
                	// force refresh if out of sync
                    bundleStream = _bundleFile.getContents(true);
                    _resourceBundle = new Properties();
                    _resourceBundle.load(bundleStream);
                    _lastModificationStamp = _bundleFile.getModificationStamp();
                }
                catch (CoreException ce)
                {
                    JSFCorePlugin.log("Error refreshing bundle", ce); //$NON-NLS-1$
                }
                catch (IOException ioe)
                {
                    JSFCorePlugin.log("Error refreshing bundle", ioe); //$NON-NLS-1$
                }
                finally
                {
                    if (bundleStream != null)
                    {
                        try
                        {
                            bundleStream.close();
                        }
                        catch (IOException ioe)
                        {
                            JSFCorePlugin.log("Error closing bundle", ioe); //$NON-NLS-1$
                        }
                    }
                }
            }
        }
        else
        {
            // bundle no longer exists so remove it
            Map bundleFileCache = getBundleFileCache(_bundleFile.getProject());

            if (bundleFileCache.containsKey(_resourcePathStr))
            {
                bundleFileCache.remove(_resourcePathStr);
                _resourceBundle = null;
            }
        }
    }

    public Set entrySet() 
    {
        checkAndRefreshBundle();

        if (_resourceBundle == null)
        {
        	return Collections.EMPTY_SET;
        }
        return _resourceBundle.entrySet();
    }

    /** 
     * @see java.util.AbstractMap#get(java.lang.Object)
     * @overrride to optimize for the fact that we are doing a hash get
     */
    //
    public Object get(Object key) 
    {
        checkAndRefreshBundle();

        if (_resourceBundle == null)
        {
        	return null;
        }
        return _resourceBundle.get(key);
    }
}