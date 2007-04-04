package org.eclipse.jst.jsf.core.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;

/**
 * Initialize JSF Libraries as classpath containers
 */
public class JSFLibrariesContainerInitializer extends
		ClasspathContainerInitializer {

	/**
	 * container id
	 */
	public static final String JSF_LIBRARY_CP_CONTAINER_ID="org.eclipse.jst.jsf.core.internal.jsflibrarycontainer";
	
	/**
	 * Constructor
	 */
	public JSFLibrariesContainerInitializer() {
		super();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#initialize(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public void initialize(IPath containerPath, IJavaProject project) throws CoreException {
		if (isJSFLibraryContainer(containerPath)) {
			String libId= containerPath.lastSegment();
						
			JSFLibrary ref= JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getJSFLibraryByID(libId);
//			JSFLibraryReference ref= JSFLibraryRegistryUtil.getInstance().getJSFLibryReferencebyID(libId);
			if (ref != null) {
				JSFLibraryClasspathContainer container= new JSFLibraryClasspathContainer(ref);
				JavaCore.setClasspathContainer(containerPath, new IJavaProject[] { project }, 	new IClasspathContainer[] { container }, null);
			}
		}
	}
	
	private boolean isJSFLibraryContainer(IPath path) {
		return path != null && path.segmentCount() == 2 && JSFLibrariesContainerInitializer.JSF_LIBRARY_CP_CONTAINER_ID.equals(path.segment(0));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#canUpdateClasspathContainer(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public boolean canUpdateClasspathContainer(IPath containerPath, IJavaProject project) {
		return isJSFLibraryContainer(containerPath); 
	}

	/**
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getDescription(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public String getDescription(IPath containerPath, IJavaProject project) {
		if (isJSFLibraryContainer(containerPath)) {
			String id = containerPath.lastSegment();
			JSFLibrary libref = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getJSFLibraryByID(id);
			String displayText = id;

			if (libref == null){
				displayText = displayText + " (missing JSF Library)";
			}
			
			return displayText;
		}
		return super.getDescription(containerPath, project);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getComparisonID(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public Object getComparisonID(IPath containerPath, IJavaProject project) {
		return containerPath;
	}

}
