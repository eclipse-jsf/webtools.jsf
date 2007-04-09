package org.eclipse.jst.jsf.core.internal;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;

/**
 * JSF Library classpath container
 */
public class JSFLibraryClasspathContainer implements IClasspathContainer {

	private JSFLibrary lib;
	
	/**
	 * @param lib 
	 */
	public JSFLibraryClasspathContainer(JSFLibrary lib) {
		this.lib = lib;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getClasspathEntries()
	 */
	public IClasspathEntry[] getClasspathEntries() {
		return JSFLibraryRegistryUtil.getInstance().getClasspathEntries(lib);		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getDescription()
	 */
	public String getDescription() {
		return lib.getName() + " (JSF Library"+ (lib.isImplementation() ? " - implementation)" : ")");
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getKind()
	 */
	public int getKind() {
		return IClasspathContainer.K_APPLICATION;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.IClasspathContainer#getPath()
	 */
	public IPath getPath() {		
		return new Path(JSFLibrariesContainerInitializer.JSF_LIBRARY_CP_CONTAINER_ID).append(this.lib.getID());
	}

}
