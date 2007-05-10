/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;

public abstract class AbstractBaseMetaDataTestCase extends TestCase {

	protected static final String projName = "TestCommonMetadataProject";
	protected static final String domain = "TagLibraryDomain";
	protected static final String badDomain = "TagLibDomain";
	protected static final String baseTestUri = "http://org.eclipse.jsf/test";
	
	protected static final String TYPE_TAG_FILE = "tagFile";
	protected static final String TYPE_TAG = "tag";
	protected static final String TYPE_TAG_ATTRIBUTE = "attribute";
	
	protected IProject project;
	protected WebProjectTestEnvironment projectTestEnvironment;
	protected IStructuredDocumentContext docContext;
	
	public void setUp() throws Exception {
	    super.setUp();
	    
	    JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
	    
	    projectTestEnvironment = 
	        new WebProjectTestEnvironment(projName/*+"_"+getClass().getName()+"_"+getName()*/);
	    projectTestEnvironment.createProject(true);
	    
	    project = projectTestEnvironment.getTestProject();
	    
	    projectTestEnvironment.loadResourceInWebRoot(MetadataTestsPlugin.getDefault().getBundle(),
	            "/testfiles/metadata/TestJSP.jsp",
	            "/TestJSP.jsp");
	    
	    String path = "/WebContent/TestJSP.jsp";
		int offset = 33;// # not important to tests
		
		docContext = getDocContext(path, offset);
	}
	
	@Override
    protected void tearDown() throws Exception {
        super.tearDown();
//        projectTestEnvironment.getTestProject().close(null);
//        projectTestEnvironment.getTestProject().delete(true, null);
    }



    private IStructuredDocumentContext getDocContext(String path, int offset) throws Exception{
		IFile jspFile = project.getFile(new Path(path));
		assertTrue(jspFile.exists());
	
		final IModelManager modelManager = StructuredModelManager
				.getModelManager();
	
		IStructuredModel model = null;
	
		model = modelManager.getModelForRead(jspFile);
		assertTrue(model instanceof DOMModelForJSP);
	
		return IStructuredDocumentContextFactory.INSTANCE.getContext(
				model.getStructuredDocument(), offset);
		
	}

}
