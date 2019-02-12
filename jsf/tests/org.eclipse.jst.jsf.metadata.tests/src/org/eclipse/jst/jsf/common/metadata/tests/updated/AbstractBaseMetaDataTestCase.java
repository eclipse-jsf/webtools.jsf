/*******************************************************************************
 * Copyright (c) 2001, 2011 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.tests.updated;

import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.traittypes.traittypes.ListOfValues;
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
	protected static final String domain = IMetaDataDomainContext.TAGLIB_DOMAIN_CONTEXT_ID;
	protected static final String badDomain = "TagLibDomain";
	protected static final String baseTestUri = "http://org.eclipse.jsf/test";
	
	protected static final String TYPE_TAG_FILE = "tagFile";
	protected static final String TYPE_TAG = "tag";
	protected static final String TYPE_TAG_ATTRIBUTE = "attribute";
	
	protected IProject project;
	protected WebProjectTestEnvironment projectTestEnvironment;
	protected IStructuredDocumentContext docContext;

	private boolean debug_info = false;
	private long startTime;
	private String debugTitle;
	
	public void setUp() throws Exception {
		String path = "/WebContent/TestJSP.jsp";
		int offset = 33;// # not important to tests
		
//		MockWorkspaceContext context = new MockWorkspaceContext();
//		project = context.createProject("TestCommonMetadataProject"+"_"+getName());
//		IFolder folder = project.getFolder("/WebContent");
//        MockResource webContentFolder = context.getResource(folder.getFullPath());
//        assertNotNull(webContentFolder);
//        IFile myJsp = project.getFile(path);
//        URL url = FileLocator.find(MetadataTestsPlugin.getDefault().getBundle(), new Path("/testfiles/metadata/TestJSP.jsp"), null);
//        URL fileUrl = FileLocator.toFileURL(url);
//        File file = new File(fileUrl.toURI());
////        File file = new File(MetadataTestsPlugin.getDefault().getBundle().getLocation()+ "/testfiles/metadata/TestJSP.jsp");
//        if (file.exists()) 
//        	myJsp.setContents(new FileInputStream(file), 0, null);

        
	    super.setUp();
	    
	    debug_info = false;
	    
	    JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com", "80");
	    
	    JSFTestUtil.setValidationEnabled(false);
	    
	    projectTestEnvironment = 
	        new WebProjectTestEnvironment(projName/*+"_"+getClass().getName()+"_"+getName()*/);
	    projectTestEnvironment.createProject(true);
	    
	    project = projectTestEnvironment.getTestProject();
	    
	    projectTestEnvironment.loadResourceInWebRoot(MetadataTestsPlugin.getDefault().getBundle(),
	            "/testfiles/metadata/TestJSP.jsp",
	            "/TestJSP.jsp");
	    
	    
		
		docContext = getDocContext(path, offset);
		
		
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
    
	protected void showDebugInfo(boolean show){
		debug_info = show;
	}
	
	protected void dumpMDTree(Entity entity, int indent) {
		if (debug_info){
			printLine("Entity: "+entity.getId(),indent);
			indent++;
			for(Iterator<?> it=entity.getTraits().iterator();it.hasNext();){
				Trait t = (Trait)it.next();
				printLine("Trait: "+t.getId()+"["+ getValue(t)+ "]", indent);
			}
			for (Iterator<?> it=entity.getChildEntities().iterator();it.hasNext();){
				dumpMDTree((Entity)it.next(), indent);
			}
		}
	}
	
	private String getValue(Trait trait) {
		if (trait.getValue() instanceof ListOfValues){
			List<?> l = TraitValueHelper.getValueAsListOfStrings(trait);
			StringBuffer buf = new StringBuffer();
			for (Iterator<?> it=l.iterator();it.hasNext();){
				buf.append((String)it.next());
				buf.append(", ");
			}
			if (buf.toString().length() > 0)
				return buf.toString().substring(0, buf.toString().length() - 2);
			return null;
		}
		return 	TraitValueHelper.getValueAsString(trait);
	}

	private void printLine(String line, int indent) {
		StringBuffer buf = new StringBuffer();
		for (int i=0;i<indent;i++){
			buf.append("    ");
		}
		buf.append(line);
		System.out.println(buf.toString());
	}

	protected void endTime() {
		if (debug_info){
			long delta = System.nanoTime() - startTime;
			printLine("Time for "+debugTitle+"(ms)= "+String.valueOf(delta/1000000), 0);
		}	
	}

	protected void startTime(String debugTitle) {
		if (debug_info){
			this.debugTitle = debugTitle;
			startTime = System.nanoTime();
			printLine("",0);
		}		
	}
}
