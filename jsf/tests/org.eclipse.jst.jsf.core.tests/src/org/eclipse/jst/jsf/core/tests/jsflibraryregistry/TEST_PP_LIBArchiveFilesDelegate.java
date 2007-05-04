package org.eclipse.jst.jsf.core.tests.jsflibraryregistry;

import org.eclipse.jst.jsf.core.jsflibraryregistry.PluginProvidedJSFLibraryArchiveFilesDelegate;

public class TEST_PP_LIBArchiveFilesDelegate extends
		PluginProvidedJSFLibraryArchiveFilesDelegate {

	public TEST_PP_LIBArchiveFilesDelegate() {
		super();
	}

	@Override
	public void getArchiveFiles() {
		addArchiveFile("/lib/foo.jar");
		addArchiveFile("/boo.jar");
	}

}
