package org.eclipse.jst.jsf.core.internal;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.jsflibraryconfiguration.JSFLibraryConfigurationHelper;


/**
 * JSF Library classpath container
 */
public class JSFLibraryClasspathContainer implements IClasspathContainer {
	private static final String NON_IMPL_DESC = Messages.JSFLibraryClasspathContainer_NON_IMPL_LIBRARY;
	private static final String IMPL_DESC = Messages.JSFLibraryClasspathContainer_IMPL_LIBRARY;
	
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
		StringBuffer buf = new StringBuffer(lib.getLabel());
		buf.append(" "); //$NON-NLS-1$
		if (lib.isImplementation())
			buf.append(IMPL_DESC);
		else
			buf.append(NON_IMPL_DESC);
		
		return buf.toString();
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
		return new Path(JSFLibraryConfigurationHelper.JSF_LIBRARY_CP_CONTAINER_ID).append(this.lib.getID());
	}

}
