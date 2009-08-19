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


package org.eclipse.jst.jsf.common.facet.libraryprovider.jsf;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.common.project.facet.core.libprov.user.UserLibraryProviderInstallOperationConfig;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;


/**
 * Utility methods for JSF libraries.
 *
 * @author Debajit Adhikary
 *
 */
public class JsfLibraryUtil
{
    /**
     * JSF library vendor type
     */
    public static enum JsfLibraryVendorType
    {
        /** Unknown JSF library type */
        UNKNOWN,
        
        /** Apache MyFaces JSF library */
        MYFACES,
        
        /** Sun-RI JSF library */
        SUN_RI
    }


    /**
     * @param libConfig UserLibraryProviderInstallOperationConfig
     * @return JsfLibraryVendorType
     */
    public static JsfLibraryVendorType getJsfLibraryVendorType (final UserLibraryProviderInstallOperationConfig libConfig)
    {
        final String MYFACES_IDENTIFYING_CLASS = "org/apache/myfaces/el/VariableResolverImpl.class"; //$NON-NLS-1$
        final String SUNRI_IDENTIFYING_CLASS = "com/sun/faces/el/VariableResolverImpl.class"; //$NON-NLS-1$


        for (final IClasspathEntry cpe : libConfig.resolve())
        {
            if (isLibrary(cpe))
            {
                final File libraryFile = cpe.getPath().toFile();

                if (!libraryFile.exists())
                    continue;

                ZipFile zipFile = null;

                try
                {
                    zipFile = new ZipFile(libraryFile);

                    for (final Enumeration<? extends ZipEntry> entries = zipFile.entries(); entries.hasMoreElements();)
                    {
                        final ZipEntry entry = entries.nextElement();
                        final String entryName = entry.getName();

                        if (entryName.equals(MYFACES_IDENTIFYING_CLASS))
                            return JsfLibraryVendorType.MYFACES;

                        if (entryName.equals(SUNRI_IDENTIFYING_CLASS))
                            return JsfLibraryVendorType.SUN_RI;
                    }
                }
                catch (final ZipException e)
                {
                    JSFCommonPlugin.log(e, e.getLocalizedMessage());
                }
                catch (final IOException e)
                {
                    JSFCommonPlugin.log(e, e.getLocalizedMessage());
                }
                finally
                {
                    try
                    {
                        if (zipFile != null)
                            zipFile.close();
                    }
                    catch (final IOException e)
                    {
                        JSFCommonPlugin.log(e, e.getLocalizedMessage());
                    }
                }
            }
        }

        return JsfLibraryVendorType.UNKNOWN;
    }


    /**
     * @param cpe
     * @return True if the classpath entry is a library.
     */
    public static boolean isLibrary (final IClasspathEntry cpe)
    {
        return cpe.getEntryKind() == IClasspathEntry.CPE_LIBRARY;
    }
}
