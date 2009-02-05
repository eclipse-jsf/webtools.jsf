/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.tld;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

/**
 * @author mengbo
 */
public class LoadBundleUtil {

	private LoadBundleUtil() {
	    // no external instantiation
	}


	/**
	 * @param project
	 * @param baseName
	 * @return an IStorage pointing to the request bundle or null if not found
	 * @throws CoreException if the search for the file encounters a problem
	 */
	public static IStorage getLoadBundleResource(final IProject project,
			final String baseName) throws CoreException {
		if (project == null || baseName == null) {
			return null;
		}
		IStorage loadBundleResource = null;
		if (project.hasNature(JavaCore.NATURE_ID)) {
			IJavaProject javaProject = JavaCore.create(project);
			IFile sourceFile = getSourceFile(javaProject, baseName);
			if (sourceFile == null || !sourceFile.exists()) {
				loadBundleResource = getJarFile(javaProject, baseName);
			} else {
				loadBundleResource = sourceFile;
			}
		}

		return loadBundleResource;
	}

	private static IFile getSourceFile(IJavaProject javaProject, String baseName)
			throws JavaModelException {
		IClasspathEntry[] classpathEntries = javaProject.getRawClasspath();
		for (int i = 0; i < classpathEntries.length; i++) {
			if (classpathEntries[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				IPath path = classpathEntries[i].getPath().append(
						getFilePath(baseName)).removeFirstSegments(1);
				path = javaProject.getProject().getFullPath().append(path);
				IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(
						path);
				if (file.exists()) {
					return file;
				}
			}
		}
		return null;
	}

	private static IPath getFilePath(String baseName) {
		IPath path = new Path(baseName.replace('.', '/'));//$NON-NLS-1$
		path = path.addFileExtension("properties");//$NON-NLS-1$
		return path;
	}

	private static IStorage getJarFile(IJavaProject javaProject, String baseName)
			throws JavaModelException {
		IClasspathEntry[] roots = javaProject.getRawClasspath();
		for (int i = 0; i < roots.length; i++) {
			if (roots[i].getEntryKind() != IClasspathEntry.CPE_LIBRARY) {
				continue;
			}

			IPackageFragmentRoot[] packageFragmentRoots = javaProject
					.findPackageFragmentRoots(roots[i]);
			for (int j = 0; j < packageFragmentRoots.length; j++) {
				String packageName = getPackageName(baseName);
				Object[] resources = null;
				if (packageName.length() == 0) {
					resources = packageFragmentRoots[j].getNonJavaResources();
				} else {
					IPackageFragment fragment = packageFragmentRoots[j]
							.getPackageFragment(getPackageName(baseName));
					if (fragment != null && fragment.exists()) {
						resources = fragment.getNonJavaResources();
					}
				}

				if (resources != null && resources.length > 0) {
					for (int k = 0; k < resources.length; k++) {
						if (resources[k] instanceof IStorage) {
							IStorage storage = (IStorage) resources[k];
							if (getFileName(baseName).equalsIgnoreCase(
									storage.getName())) {
								return storage;
							}
						}
					}
				}
			}
		}
		return null;
	}

	private static String getPackageName(String baseName) {
		int index = baseName.lastIndexOf('.');//$NON-NLS-1$
		if (index == -1) {
			return "";//$NON-NLS-1$
		}
        return baseName.substring(0, index);
	}

	private static String getFileName(String baseName) {
		int index = baseName.lastIndexOf('.');//$NON-NLS-1$
		if (index == -1) {
			return baseName + ".properties";
		}
        return baseName.substring(index + 1).concat(".properties");//$NON-NLS-1$
	}

    /**
     * Encapsulates the hiearchy of bundle data sources in the hierarchy
     * for a ResourceBundle base name.  In practice this is often simply
     * a single IFile for a 
     * @author cbateman
     *
     */
//    public static class BundleHierarchy
//    {
//        // list in order from most specific (first queried) to least,
//        // front to back
//        //private final List        _hierarchy; 
//    
//        /**
//         * Takes the list *by reference*.  Does not take a copy.
//         * @param hierarchy
//         */
//        public BundleHierarchy(List hierarchy)
//        {
//            _hierarchy = hierarchy;
//        }
//        
//        public BundleHierarchy()
//        {
//            _hierarchy = new ArrayList();
//        }
//    }
    
    /**
     * Used to describe the design time approximation of the locale lookup precendence
     * that will be used by ResourceBundle to find a localized resource bundle.
     * 
     * See the official JavaDoc for java.util.ResourceBundle.getBundle for docs on search
     * order.  
     * 
     * @author cbateman
     *
     */
    public static class LocaleDescriptor
    {
        private Locale            _locale;
        private List              _possibleSuffices;
        
        /**
         * @param language -- must not be null
         */
        public LocaleDescriptor(String language)
        {
            _locale = new Locale(language);
        }
        
        /**
         * All arguments must be non-null.  To set a language only descriptor,
         * see others.
         * 
         * @param language -- must not be null
         * @param country -- must not be null
         */
        public LocaleDescriptor(String language,
                String country)
        {
            _locale = new Locale(language, country);
        }
        
        /**
         * All arguments must be non-null. Null arguments will cause an exception.
         * To create descriptor without variant and/or country set, see other constructors
         * @param language -- must not be null
         * @param country -- must not be null
         * @param variant -- must not be null
         */
        public LocaleDescriptor(String language,
                String country, String variant) 
        {
            _locale = new Locale(language, country, variant);
        }
        
        /**
         * @param baseName
         * @return an iterator through all possible bundle names starting with the most
         * specific and becoming more general for base name based on this locale.
         * 
         * i.e. if baseName is "bundle" and the local is en_US_1 then in order the 
         * iterator will produce:
         * 
         * bundle_en_US_1
         * bundle_en_US
         * bundle_en
         * bundle
         * 
         * per the ResourceBundle API
         * 
         */
        public Iterator getBundleNameIterator(final String baseName)
        {
            
            return new Iterator()
            {
                final Iterator  it = getPossibleBaseNameSuffices().iterator();
                
                public boolean hasNext() {
                   return it.hasNext();
                }

                public Object next() {
                    return baseName+it.next(); 
                }

                public void remove() {
                    // delegate; should throw exception
                    it.remove();
                }
            };
        }

        private synchronized List getPossibleBaseNameSuffices()
        {
            if (_possibleSuffices == null)
            {
                List possibleSuffices = new ArrayList(3);

                final String language = _locale.getLanguage();
                final String country = _locale.getCountry();
                final String variant = _locale.getVariant();

                possibleSuffices.add("");
                possibleSuffices.add("_"+language);
                if (country != null)
                {
                    possibleSuffices.add(0, "_"+language + "_" + country);
                    if (variant != null)
                    {
                        possibleSuffices.add(0, "_"+language+"_"+country+"_"+variant);
                    }
                }
                _possibleSuffices = Collections.unmodifiableList(possibleSuffices);
            }

            return _possibleSuffices;
       }

        /**
         * @return the local information as a standard locale object
         */
        public Locale getLocale()
        {
            return _locale;
        }
    }
}
