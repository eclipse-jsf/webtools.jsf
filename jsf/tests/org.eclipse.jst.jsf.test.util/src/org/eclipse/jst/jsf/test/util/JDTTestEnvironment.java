/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.test.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.osgi.framework.Bundle;

/**
 * Test environment for doing project tests involving JDT dependencies
 * @author cbateman
 *
 */
public class JDTTestEnvironment {
	
	private final ProjectTestEnvironment  _projectTestEnvironment;
	
	/**
	 * @param projectTestEnvironment
	 * @throws CoreException 
	 */
	public JDTTestEnvironment(ProjectTestEnvironment projectTestEnvironment) throws CoreException
	{
		_projectTestEnvironment = projectTestEnvironment;
        
        IProject project = _projectTestEnvironment.getTestProject();
        if (!project.hasNature(JavaCore.NATURE_ID)) {
            IProjectDescription description = project.getDescription();
            String[] prevNatures= description.getNatureIds();
            String[] newNatures= new String[prevNatures.length + 1];
            System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
            newNatures[prevNatures.length]= JavaCore.NATURE_ID;
            description.setNatureIds(newNatures);
            project.setDescription(description, new NullProgressMonitor());
        }
        
        IJavaProject javaProject = JavaCore.create(project);
        
        // make sure that the project root is not on the classpath
        IClasspathEntry[] entries = 
            javaProject.getRawClasspath();
        List<IClasspathEntry> preservedEntries = new ArrayList<IClasspathEntry>();
        
        for (int i=0; i < entries.length; i++)
        {
            IClasspathEntry entry = entries[i];
            
            if (!entry.getPath().equals(project.getFullPath()))
            {
                preservedEntries.add(entry);
            }
        }
        
        javaProject.setRawClasspath(preservedEntries.toArray(new IClasspathEntry[0]), 
                                    new NullProgressMonitor());
	}

	/**
	 * @return the supporting IProject test environment
	 */
	public ProjectTestEnvironment getProjectEnvironment()
	{
		return _projectTestEnvironment;
	}
	
	/**
	 * @param name
	 * @return the IFolder handle, creating it if it doesn't exist
	 * @throws CoreException 
	 */
	public IFolder getSourceFolder(final String name) throws CoreException
	{
		IProject project = _projectTestEnvironment.getTestProject();
		IFolder folder = project.getFolder(name);
 
		if (!folder.exists())
		{
			folder.create(true, true, new NullProgressMonitor());
            IPath path = project.getFullPath().append("bin");
            IClasspathEntry entry = JavaCore.newSourceEntry(folder.getFullPath(), new IPath[]{path}, path);
            addClasspathEntry(entry);
        }


		return folder;
	}
	
    /**
     * Tries to add the newEntry to the java project's classpath
     * @param newEntry
     */
    public void addClasspathEntry(IClasspathEntry newEntry) throws JavaModelException
    {
        IClasspathEntry[] entries = getJavaProject().getRawClasspath();
        IClasspathEntry[] newEntries = new IClasspathEntry[entries.length+1];
        System.arraycopy(entries, 0, newEntries, 0, entries.length);
        newEntries[entries.length] = newEntry;
        getJavaProject().setRawClasspath(newEntries, new NullProgressMonitor());
    }
    
    /**
     * Try to load the jar file at pathToJarFile in bundle as a jar classpath entry
     * 
     * @param bundle
     * @param pathToJarFile
     * @return the class path entry or null if unable to create
     * @throws JavaModelException if a problem occurs adding the classpath entry
     */
    public IClasspathEntry addJarClasspathEntry(Bundle bundle, String pathToJarFile) throws JavaModelException, IOException, URISyntaxException
    {
        IPath path = JSFTestUtil.getAbsolutePath(bundle, pathToJarFile);
        IClasspathEntry entry = JavaCore.newLibraryEntry(path, null, null);
        
        addClasspathEntry(entry);
        
        return entry;
    }
    
	/**
	 * @param folderName
	 * @return the package fragment root for the folder name; resource may or may not exist
	 * @throws CoreException 
	 */
	public IPackageFragmentRoot getPackageFragmentRoot(final String folderName) throws CoreException
	{
		final IJavaProject javaProject = JavaCore.create(_projectTestEnvironment.getTestProject());
        IFolder folder = getSourceFolder(folderName);
		IPackageFragmentRoot root = javaProject.getPackageFragmentRoot(folder);
       
        return root;
	}
	
	/**
	 * Creates a new Java class file in the folder/package/file name specified with contents as the
	 * the Java code.
	 * 
	 * @param folder
	 * @param packageName
	 * @param className
	 * @param contents
	 * @return the created compiliation unit
	 * @throws CoreException 
	 * @throws JavaModelException
	 */
	public ICompilationUnit addSourceFile(final String folder, final String packageName, final String className, final String contents) throws CoreException, JavaModelException
	{
		IPackageFragmentRoot root = getPackageFragmentRoot(folder);
        IPackageFragment packageFrag = root.getPackageFragment(packageName);
		
		if (!packageFrag.exists())
		{
	        packageFrag = 
	            root.createPackageFragment(packageName, 
	                                true, new NullProgressMonitor());
		}

		ICompilationUnit newCompUnit = 
            packageFrag.createCompilationUnit(className+".java", contents, true, new NullProgressMonitor());
        return newCompUnit;
	}

    /**
     * @param folderName
     * @param src
     * @param path 
     * @param fileName 

     * @return a new IFile corresponding to src being created and copied
     * into a new file at folderName/IPath
     * @throws IOException
     * @throws CoreException
     */
    public IFile addResourceFile(final String folderName, final InputStream src, final String path, final String fileName) throws CoreException
    {
        IPackageFragmentRoot root = getPackageFragmentRoot(folderName);
        
        IPackageFragment packageFrag = root.getPackageFragment(path);
        
        if (!packageFrag.exists())
        {
            packageFrag = 
                root.createPackageFragment(path, 
                                    true, new NullProgressMonitor());
            packageFrag.open(null);
        }

        final IFolder folder = (IFolder) packageFrag.getUnderlyingResource();
        
        IFile file = folder.getFile(new Path(fileName));
        file.create(src, true, null);
        return file;
    }

    /**
     * @return the IJavaProject instance for this test environment object
     */
    public IJavaProject getJavaProject() 
    {
        return JavaCore.create(_projectTestEnvironment.getTestProject());
    }
}
