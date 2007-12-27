/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.test.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.internal.core.builder.JavaBuilder;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;

/**
 * Test utility to create project and its files.
 * 
 * @author Yang Liu, Xiao-guang Zhang
 * 
 * @version
 */
public class TestUtil {
	//private static final String TEST_FILE_FOLDER = "../org.eclipse.jst.jsf.facesconfig.ui.test/test-file/";
	private static final String TEST_FILE_FOLDER = "/test-file/";
	/**
	 * 
	 * @param prjname
	 * @param path
	 *            relative to this plugin's "casefiles/projects" folder.
	 * @return
	 * @throws Exception
	 */
	public static IProject createProjectFromZip(String prjname, String path)
			throws Exception {
	
		URL url = FileLocator.find(EditorPlugin.getDefault().getBundle(), new Path(TEST_FILE_FOLDER + path), null);

		InputStream stream = url.openStream();
		return createProjectFromZip(prjname, stream);
	}

	/**
	 * create a project from a zip file.
	 * 
	 * @param prjname
	 * @param zipStream
	 * @throws Exception
	 */
	public static IProject createProjectFromZip(final String prjname,
			final InputStream zipStream) throws Exception {
		final IProject[] holder = new IProject[1];
		IWorkspaceRunnable r = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				IProject prj = ResourcesPlugin.getWorkspace().getRoot()
						.getProject(prjname);
				if (!prj.exists()) {
					prj.create(null);
				}
				prj.open(null);
				try {
					expandZip(zipStream, prj);
				} catch (Exception ex) {
					throw new CoreException(new Status(0,
							"org.eclipse.jst.jsf.facesconfig.ui.test", 0, ex
									.getMessage(), ex));
				}
				holder[0] = prj;
			}
		};

		ResourcesPlugin.getWorkspace().run(r, null);
		return holder[0];
	}

	/**
	 * build a project
	 * 
	 * @param project
	 * @param monitor
	 */
	public static void buildProject(IProject project, IProgressMonitor monitor) {
		try {
			project.build(IncrementalProjectBuilder.FULL_BUILD, monitor);
		} catch (CoreException e) {
		    e.printStackTrace(System.err);
		}
	}

	/**
	 * expand the zip stream into the specified folder.
	 * 
	 * @param zipStream
	 * @param dir
	 * @throws Exception
	 */
	public static void expandZip(InputStream zipStream, IContainer dir)
			throws Exception {
		ZipInputStream zis = null;

		try {
			// first, count number of items in zip file
			zis = new ZipInputStream(zipStream);
			ZipEntry ze = zis.getNextEntry();
			while (ze != null) {
				if (ze.isDirectory()) {
					IFolder folder = dir.getFolder(new Path(ze.getName()));
					if (!folder.exists()) {
						ensurePath(folder);
						folder.create(true, true, null);
					}
				} else {
					IFile file = dir.getFile(new Path(ze.getName()));
					ensurePath(file);
					// use ZipStreamWrapper to prevent zis being closed
					if (file.exists()) {
						file.setContents(new ZipStreamWrapper(zis),
								IResource.NONE, null);
					} else {
						file.create(new ZipStreamWrapper(zis), true, null);
					}
				}

				ze = zis.getNextEntry();
			}
		} finally {
			try {
				if (zis != null)
					zis.close();
			} catch (Exception ex) {
				// ignore
			}
		}
	}

	/**
	 * @param file
	 * @throws CoreException
	 */
	private static void ensurePath(IResource file) throws CoreException {
		IContainer container = file.getParent();
		if (!container.exists()) {
			ensurePath(container);
			((IFolder) container).create(true, true, null);
		}
	}

	/**
	 * this method will copy file from the sourcePath folder of this plugin into
	 * the target path related with the destination project.
	 * 
	 * @param project
	 * @param sourcePath -
	 *            Source path, must be a relative path to test plugin
	 * @param targetPath -
	 *            Target path, must be relative path to eclispe project.
	 * 
	 * @return
	 */
	public static IFile copyFile(IProject project, String targetPath,
			String sourcePath) throws Exception {

		URL url = FileLocator.find(EditorPlugin.getDefault().getBundle(), new Path(TEST_FILE_FOLDER + sourcePath), null);
		InputStream stream = url.openStream();

		IFile file = null;
		IPath path = new Path(sourcePath);
		if (targetPath != null && targetPath.length() > 0) {
			IFolder folder = project.getFolder(targetPath);
			file = folder.getFile(path.lastSegment());
		} else {
			file = project.getFile(path.lastSegment());
		}

		if (!file.exists()) {
			ensurePath(file);
			file.create(stream, true, null);
		} else {
			file.setContents(stream, IFile.FORCE, null);
		}

		return file;
	}

	/**
	 * this method will create page file from the "pages" folder of this plugin
	 * into the webroot folder of the destination project.
	 * 
	 * @param filePath -
	 *            file path, must be relative path to destination project.
	 * 
	 * @return
	 */
	public static IFile createFile(IProject project, String filePath,
			String content) throws Exception {
		IFile file = project.getFile(filePath);
		ensurePath(file);
		ByteArrayInputStream stream = new ByteArrayInputStream(content
				.getBytes());
		file.create(stream, true, null);
		return file;
	}

	/**
	 * this method will get the page file from the "pages" folder of this plugin
	 * as a string.
	 * 
	 * @param path -
	 *            related with plugin
	 * @return
	 * @throws Exception
	 */
	public static String getFileAsString(String path) throws Exception {

		URL url = FileLocator.find(EditorPlugin.getDefault().getBundle(), new Path(TEST_FILE_FOLDER + path), null);
		InputStream stream = url.openStream();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(stream));
		StringBuffer buffer = new StringBuffer();
		char[] temp = new char[256];
		int count;
		while ((count = reader.read(temp)) > 0) {
			buffer.append(temp, 0, count);
		}
		reader.close();
		stream.close();
		return buffer.toString();
	}

	public static String removeAllWhitespace(String s) {
		StringBuffer buffer = new StringBuffer(s.length());
		for (int i = 0, length = s.length(); i < length; i++) {
			if (!Character.isWhitespace(s.charAt(i))) {
				buffer.append(s.charAt(i));
			}
		}
		return buffer.toString();
	}

	/**
	 * remove resource, following schedule rule.
	 * 
	 * @param prj
	 * @throws CoreException
	 */
	public static void removeResource(final IResource prj) throws CoreException {
		if (prj instanceof IFile) {
			((IFile) prj).delete(true, false, null);
			return;
		}
		IWorkspaceRunnable r = new IWorkspaceRunnable() {
			public void run(IProgressMonitor monitor) throws CoreException {
				prj.delete(true, monitor);
			}
		};
		ResourcesPlugin.getWorkspace().run(r, prj, 0, null);
	}

	/**
	 * To verify if this java resource(such as project or folder) can be
	 * compiled.
	 * 
	 * @param javaFile
	 * @return true if the resource contains no error, otherwise return false.
	 */
	public static boolean isValidJavaResource(IResource resource) {
		IMarker[] markers = JavaBuilder.getProblemsFor(resource);
		if (markers != null && markers.length > 0) {
			for (int i = 0; i < markers.length; i++) {
				IMarker curr = markers[i];
				if (curr == null) {
					continue;
				}
				if (curr.getAttribute(IMarker.SEVERITY, -1) == IMarker.SEVERITY_ERROR) {
					return false;
				}
			}
		}
		return true;
	}

}
