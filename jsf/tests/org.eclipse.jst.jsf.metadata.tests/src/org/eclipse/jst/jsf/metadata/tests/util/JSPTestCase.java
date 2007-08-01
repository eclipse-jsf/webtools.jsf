/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman - initial implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.metadata.tests.util;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

public class JSPTestCase extends BaseTestCase 
{
    public static final String  FACES_CONFIG_FILE 	= "facesConfigFile";
    public static final String 	JSF_CORE_URI		= "http://java.sun.com/jsf/core";
    public static final String 	JSF_HTML_URI		= "http://java.sun.com/jsf/html";
    public static final String 	TAG_TEST_URI		= "http://org.eclipse.jsf/tagprocessing";
    public static final String 	TAG_TEST_TAG		= "MyTag";
    
    /**
     * Test config
     */
    private MyConfiguration                 _myConfig;
    private final       String              _defaultJSFVersion;
    private  final      String              _defaultFacesConfigFile; 
    
    private 			String[] 			_runtimeJars;
	
    
    public JSPTestCase(final String defaultJSFVersion, final String defaultFacesConfigFile) 
    {
        super(defaultJSFVersion);
        _defaultJSFVersion = defaultJSFVersion;
        _defaultFacesConfigFile = defaultFacesConfigFile;
    }

    protected void doStandaloneSetup() 
    {
        super.doStandaloneSetup();
        
        // NOTE: defaults to 1.1 tests for standalone testing
        _myConfig = new MyConfiguration
            (_defaultJSFVersion
                    , _defaultFacesConfigFile);
    }

    protected void doTestSuiteSetup() 
    {
        super.doTestSuiteSetup();
        
        _myConfig = new MyConfiguration(_testConfiguration);
    }
    
    @Override
    protected JSFFacetedTestEnvironment configureJSFEnvironment() throws Exception
    {
    	JSFFacetedTestEnvironment jsfFacedEnv = new JSFFacetedTestEnvironment(_testEnv);
    	jsfFacedEnv.initialize(_myConfig.getFacetVersion());
            
        try {
        	initializeJSFRuntime();
        	createRegistryAndAddReferences(_testEnv, jsfFacedEnv, _runtimeJars);
        } catch (NoJSFRuntimeFoundException e) {
        	//
		}
        	
        _testEnv.loadResourceInWebRoot(MetadataTestsPlugin.getDefault().getBundle(),
                                      _myConfig.getFacesConfigFile(), 
                                      "/WEB-INF/faces-config.xml");
        return jsfFacedEnv;
    }
    

	private void createRegistryAndAddReferences(WebProjectTestEnvironment projectTestEnvironment, JSFFacetedTestEnvironment jsfFacedEnv, String[] archiveFiles) throws CoreException {
		JSFLibraryRegistry jsfLibRegistry = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry();
		
		if (archiveFiles != null){
			JSFLibrary jsfImpl = JSFCoreUtilHelper.constructJSFLib("_internalJSFRuntimeLibrary_", "_internalJSFRuntimeLibrary_","", archiveFiles, true);
			jsfLibRegistry.addJSFLibrary(jsfImpl);
			jsfFacedEnv.addJSFLibraryReference(jsfImpl, true);
		}
	}

    private String[] getArchiveFiles() {
//    	getJsfImplEnv();
		return null;
	}

	protected IFile loadJSP(final String srcFileName, final String destFileName) throws Exception
    {
        return (IFile) _testEnv.loadResourceInWebRoot
            (MetadataTestsPlugin.getDefault().getBundle(),
                    srcFileName, destFileName);
    }
    
    /**
     * @param document
     * @param docPos
     * @return the text at docPos in document or null if no such text
     */
    protected String getText(IStructuredDocument document, int docPos)
    {
        final IStructuredDocumentContext context = 
        	getStructuredDocumentContext(document, docPos);
        final ITextRegionContextResolver resolver =
            IStructuredDocumentContextResolverFactory.INSTANCE.getTextRegionResolver(context);
        return stripQuotes(resolver.getRegionText());
    }
    
    protected IStructuredDocumentContext getStructuredDocumentContext(IStructuredDocument document, int docPos) {
    	return  IStructuredDocumentContextFactory.INSTANCE.getContext(document, docPos);
    }
    private String stripQuotes(String text) {
        if (text != null && text.startsWith("\""))
        	return text.substring(1, text.length() -1);
        return text;
	}

	protected static class MyConfiguration
    {
        private final String        _facetVersion;
        private final String        _facesConfigFile;
        
        MyConfiguration(String facetVersion, String facesConfigFile) 
        {
            super();
            _facetVersion = facetVersion;
            _facesConfigFile = facesConfigFile;
        }
        
        MyConfiguration(TestConfiguration testConfiguration)
        {
            _facetVersion = testConfiguration.get(BaseTestCase.JSF_FACET_VERSION);
            _facesConfigFile = testConfiguration.get(FACES_CONFIG_FILE);
        }

        public String getFacetVersion() {
            return _facetVersion;
        }

        public String getFacesConfigFile() {
            return _facesConfigFile;
        }
    }
	
	protected void initializeJSFRuntime() throws NoJSFRuntimeFoundException{
		String dir = JSFTestUtil.getJSFRuntimeJarsDirectory(_defaultJSFVersion);
		if (dir == null || !jarsFound(dir) )
			throw new NoJSFRuntimeFoundException();
	}
	
	private boolean jarsFound(String dir) {
		java.io.File dirFile = new java.io.File(dir);
		if (dirFile.exists() && dirFile.isDirectory()) {
			File [] files = dirFile.listFiles();
			if (files.length > 0) {
				_runtimeJars = new String[files.length];
				for (int i=0;i<files.length;i++){
					_runtimeJars[i] = files[i].getAbsolutePath();
				}
				return true;
			}
		}
		return false;
	}
}
