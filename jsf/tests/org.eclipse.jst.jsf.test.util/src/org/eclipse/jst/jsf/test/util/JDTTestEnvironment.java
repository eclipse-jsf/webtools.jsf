package org.eclipse.jst.jsf.test.util;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;

/**
 * Test environment for doing project tests involving JDT dependencies
 * @author cbateman
 *
 */
public class JDTTestEnvironment {
	
	private final ProjectTestEnvironment  _projectTestEnvironment;
	
	/**
	 * @param projectTestEnvironment
	 */
	public JDTTestEnvironment(ProjectTestEnvironment projectTestEnvironment)
	{
		_projectTestEnvironment = projectTestEnvironment;
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
		}
		
		return folder;
	}
	
	/**
	 * @param folderName
	 * @return the package fragment root for the folder name; resource may or may not exist
	 * @throws CoreException 
	 */
	public IPackageFragmentRoot getPackageFragmentRoot(final String folderName) throws CoreException
	{
		final IJavaProject javaProject = JavaCore.create(_projectTestEnvironment.getTestProject());
		return javaProject.getPackageFragmentRoot(getSourceFolder(folderName));
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
     * @return the IJavaProject instance for this test environment object
     */
    public IJavaProject getJavaProject() 
    {
        return JavaCore.create(_projectTestEnvironment.getTestProject());
    }
}
