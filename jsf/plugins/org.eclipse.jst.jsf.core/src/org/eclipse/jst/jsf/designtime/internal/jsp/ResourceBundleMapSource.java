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
package org.eclipse.jst.jsf.designtime.internal.jsp;

import java.io.IOException;
import java.io.InputStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.IJavaSearchScope;
import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.core.search.SearchMatch;
import org.eclipse.jdt.core.search.SearchParticipant;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.core.search.SearchRequestor;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;

class ResourceBundleMapSource extends AbstractMap
{
    private static final String   PROPERTY_QUALIFIER = "org.eclipse.jst.jsf.designtime.internal.jsp";
    private static final String   SESSION_PROPERTY_NAME_PROJECT = "ResourceBundleMapSource";
    private static final QualifiedName  SESSION_PROPERTY_KEY_PROJECT 
        = new QualifiedName(PROPERTY_QUALIFIER, SESSION_PROPERTY_NAME_PROJECT);
 
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
                JSFCorePlugin.log("Error creating bundle file cache", ce);
            }
            
            return bundleFileCache;
        }
    }
    
    private static IFile    createCachedBundleFile(final IProject project, 
                                                   final String  resourcePathStr)
                      throws IOException, JavaModelException, CoreException
    {
        final IJavaProject javaProject = JavaCore.create(project);
        final String pathStr = 
            resourcePathStr.
                substring(0, resourcePathStr.lastIndexOf('.'));
        final String bundleName = 
            resourcePathStr.substring(resourcePathStr.lastIndexOf('.')+1);
        
        if (pathStr == null
                || pathStr.length() < 1
                || bundleName == null
                || bundleName.length() < 1)
        {
            throw new IOException("Cannot resolve bundle name to file");
        }

        final SearchPattern jdtSearchPattern = 
            SearchPattern.createPattern(pathStr, 
                                        IJavaSearchConstants.PACKAGE, 
                                        IJavaSearchConstants.DECLARATIONS, 
                                        SearchPattern.R_EQUIVALENT_MATCH);
        
        if (jdtSearchPattern == null)
        {
            throw new IOException("Cannot resolve bundle name to file");
        }
        
        final IJavaSearchScope searchScope = 
            SearchEngine.createJavaSearchScope(new IJavaElement[]{javaProject});
        
        final List matches = new ArrayList();
        
        final SearchRequestor requestor = new SearchRequestor()
        {
            public void acceptSearchMatch(SearchMatch match)
            {
                if (match.isExact()
                        && match.getResource() != null)
                {
                    matches.add(match);
                }
            }
        };
        
        final SearchEngine searchEngine = new SearchEngine();
        searchEngine.search(jdtSearchPattern, new SearchParticipant[] {SearchEngine.getDefaultSearchParticipant()}, searchScope, requestor, null);

        if (matches.size() < 1)
        {
            throw new IOException("Cannot resolve bundle name to package");
        }
        
        final SearchMatch firstMatch = ((SearchMatch)matches.get(0));
        final IResource res = firstMatch.getResource();
        
        if (res.getType() != IResource.FOLDER)
        {
            throw new IOException("Cannot resolve bundle package to folder");
        }
        
        final IFolder folder = (IFolder) res;
        
        final IResource bundleRes = folder.findMember(bundleName+".properties");
        
        if (bundleRes == null
                || bundleRes.getType() != IResource.FILE)
        {
            throw new IOException("Cannot resolve bundle name to file");
        }

        getBundleFileCache(project).put(resourcePathStr, bundleRes);
        
        return (IFile) bundleRes;
    }
    
    private Properties                  _resourceBundle; // = null; set on first access or changes
    private final IFile                 _bundleFile;   // the resource
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
    }

    private void checkAndRefreshBundle()
    {
        if (_resourceBundle == null
                || _bundleFile.getModificationStamp() != _lastModificationStamp)
        {
            InputStream  bundleStream = null;
            try
            {
                bundleStream = _bundleFile.getContents();
                _resourceBundle = new Properties();
                _resourceBundle.load(bundleStream);
                _lastModificationStamp = _bundleFile.getModificationStamp();
            }
            catch (Exception ioe)
            {
                JSFCorePlugin.log("Error refreshing bundle", ioe);
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
                        JSFCorePlugin.log("Error closing bundle", ioe);
                    }
                }
            }
        }
    }
    
    public Set entrySet() 
    {
        checkAndRefreshBundle();
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
        return _resourceBundle.get(key);
    }
}