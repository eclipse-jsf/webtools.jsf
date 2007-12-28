package org.eclipse.jst.pagedesigner.tests.tabbed.properties.sections;

import java.io.IOException;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.common.internal.ITestTracker;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.jst.pagedesigner.properties.WPETabbedPropertySheetPage;
import org.eclipse.jst.pagedesigner.tests.PageDesignerTestsPlugin;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.w3c.dom.Node;

public class BaseTestCase extends TestCase {
	private static final String HTML_EDITOR_ID = "org.eclipse.jst.pagedesigner.PageDesignerEditor";
	protected WebProjectTestEnvironment             _webProjectTestEnv;
	protected WebProjectTestEnvironment             _webProjectTestEnv2;
	 
	   @Override
	    protected void setUp() throws Exception
	    {
	        super.setUp();
	        
	        JSFTestUtil.setValidationEnabled(false);

	        _webProjectTestEnv = new WebProjectTestEnvironment(
	                getClass().getName()+"_" + getName());
	        _webProjectTestEnv.createProject(false);
	        assertNotNull(_webProjectTestEnv);
	        assertNotNull(_webProjectTestEnv.getTestProject());
	        assertTrue(_webProjectTestEnv.getTestProject().isAccessible());
	        
	        _webProjectTestEnv.loadResourceInWebRoot(PageDesignerTestsPlugin.getDefault().getBundle()
	                , "/testdata/propertypages/testPropPages.tld.data"
	                , "/META-INF/testPropPages.tld");
	        
	       
	        	        
	    }
	   
	   protected IFile getJSPFile(String srcFileName, String destFileName) throws IOException, CoreException {
	       return  getJSPFile(srcFileName, destFileName, _webProjectTestEnv);
	   }
	   
	   private IFile getJSPFile(String srcFileName, String destFileName, WebProjectTestEnvironment webProjectTestEnv) throws IOException, CoreException {
	       return  (IFile)webProjectTestEnv.loadResourceInWebRoot(
	                PageDesignerTestsPlugin.getDefault().getBundle(),
	                srcFileName, destFileName);

	   }
	   protected IFile getJSPFileFromSecondProject(String srcFileName, String destFileName) throws IOException, CoreException {
		

	        _webProjectTestEnv2 = new WebProjectTestEnvironment(
	                getClass().getName()+"_" + getName()+"_2");
	        _webProjectTestEnv2.createProject(false);
	        assertNotNull(_webProjectTestEnv2);
	        assertNotNull(_webProjectTestEnv2.getTestProject());
	        assertTrue(_webProjectTestEnv2.getTestProject().isAccessible());
	        
	        _webProjectTestEnv2.loadResourceInWebRoot(PageDesignerTestsPlugin.getDefault().getBundle()
	                , "/testdata/propertypages/testPropPages.tld.data"
	                , "/META-INF/testPropPages.tld");
	        
	        
	        return getJSPFile(srcFileName, destFileName, _webProjectTestEnv2);
	   }
	   
	    private ContextWrapper getDocumentContext(IFile jspFile, int offset) throws Exception {
	        assertTrue(jspFile.exists());
	        final IModelManager modelManager = 
	            StructuredModelManager.getModelManager();
	        IStructuredModel model = null;
	        model = modelManager.getModelForRead(jspFile);
	        assertTrue(model instanceof DOMModelForJSP);
	        final IStructuredDocumentContext context =
	        	IStructuredDocumentContextFactory.INSTANCE.getContext(
	        			model.getStructuredDocument(),
	        			offset);
	        return new ContextWrapper(context, model);
	    }
	    
	    protected IStructuredSelection getSelection(Node node){
	    	return new StructuredSelection(node);
	    }
	    
	    protected ITabbedPropertySheetPageContributor getTabbedPropertyCont() {
			return new ITabbedPropertySheetPageContributor(){
		
				public String getContributorId() {
					return HTMLEditor.TABBED_PROPERTIES_CONTRIBUTOR_ID;
				}			
			};
		}

	    protected WPETabbedPropertySheetPage getWPETabbedPropertySheetPage(HTMLEditor ed){
	    	return (WPETabbedPropertySheetPage)ed.getAdapter(IPropertySheetPage.class);
//	    	return  new MockWPETabbedPropertySheet(getTabbedPropertyCont(), ed);
	    }
	    
		protected HTMLEditor openHTMLEditor(IFile file) {
		    IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		    assertNotNull(workbenchWindow);
		    IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();	    
		    try {
		    	IEditorInput input = new FileEditorInput(file);
		    	return (HTMLEditor)workbenchPage.openEditor(input, HTML_EDITOR_ID);
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		protected IEditorPart openXSDEditor(IFile file) {
		    IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		    assertNotNull(workbenchWindow);
		    IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();	    
		    try {
		    	IEditorInput input = new FileEditorInput(file);
		    	return workbenchPage.openEditor(input, "org.eclipse.wst.xsd.ui.internal.editor.InternalXSDMultiPageEditor");
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		protected IViewPart showPropertiesView() {
			IWorkbenchPage workbenchPage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		    IViewPart view = null;
			try {
				view = workbenchPage.showView(IPageLayout.ID_PROP_SHEET);		        
			} catch (PartInitException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return view;
		
		}

		protected Node getNode(IFile file, int offset) throws Exception {
			ContextWrapper wrapper = null;
			Node node = null;
			try {
				wrapper = getDocumentContext(file, offset);
				assertNotNull(wrapper);
				IStructuredDocumentContext context = wrapper.getContext();
			    IDOMContextResolver resolver =
			        IStructuredDocumentContextResolverFactory.INSTANCE.
			            getDOMContextResolver(context);
			    node = resolver.getNode();
			} finally {
				if (wrapper != null)
					wrapper.dispose();
			}
			return node;
		}

		protected void closeEditor(final IEditorPart ed) {
			IWorkbenchPage page = ed.getEditorSite().getPage();
			page.closeEditor(ed, false);	
		}

		public static class ContextWrapper 
	    {
	        private final IStructuredDocumentContext context;
	        private final IStructuredModel model;

	        public ContextWrapper(final IStructuredDocumentContext context,
	                final IStructuredModel model) {
	            super();
	            this.context = context;
	            this.model = model;
	        }
	        
	        public IStructuredDocumentContext getContext() {
	            return context;
	        }

	        public IStructuredModel getModel() {
	            return model;
	        }
	        

	        void dispose() 
	        {
	            model.releaseFromRead();
	        }
	    }
		
		private class LifecycleListener implements ITestTracker {

			public void fireEvent(Event event, long seqId, String eventLabel) {
				
				
			}
			
		}
		
}
