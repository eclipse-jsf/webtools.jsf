package org.eclipse.jst.jsf.core.tests.util;

import java.util.zip.ZipFile;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class TestCMUtil extends TestCase {

    private WebProjectTestEnvironment _webProject;
    private IProject 			  	 _project;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80");

        final ZipFile zipFile = JSFTestUtil.createZipFile(TestsPlugin.getDefault().getBundle()
                , "/testfiles/testzips/TLDTests.zip");

        _webProject = new WebProjectTestEnvironment(this, JavaFacet.VERSION_1_5, ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion("2.4"));
        _webProject.createFromZip(zipFile, true);
        assertNotNull(_webProject);
        
        _project = _webProject.getTestProject();
        assertNotNull(_project);
        assertTrue(_project.isAccessible());
        
    }

	public void testGetURIFromDoc() {
        
        for (ITaglibRecord tldRec : TaglibIndex.getAvailableTaglibRecords(_project.getFullPath())){
        	if (tldRec.getDescriptor().getShortName().equals("tags"))
        		assertEquals("/WEB-INF/tags", CMUtil.getURIFromTaglibRecord(tldRec, _project));
        	else if (tldRec.getDescriptor().getShortName().equals("moreTags"))
        		assertEquals("/WEB-INF/tags/moreTags", CMUtil.getURIFromTaglibRecord(tldRec, _project));
        	else if (tldRec.getDescriptor().getShortName().equals("mysample"))
        		assertEquals("/WEB-INF/SampleTagLib.tld", CMUtil.getURIFromTaglibRecord(tldRec, _project));
        	else if (tldRec.getDescriptor().getShortName().equals("XXXXmysample"))
        		assertEquals("/WEB-INF/tlds/XXXXSampleTagLib.tld", CMUtil.getURIFromTaglibRecord(tldRec, _project));
        	else if (tldRec.getDescriptor().getShortName().equals("uri-supplied"))
        		assertEquals("uri-supplied", CMUtil.getURIFromTaglibRecord(tldRec, _project));        	
        }
	}

	public void testGetURIFromTaglibRecord() {
		
        CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
        for (ITaglibRecord tldRec : TaglibIndex.getAvailableTaglibRecords(_project.getFullPath())){
        	TLDDocument tldDoc = (TLDDocument)factory.createCMDocument(tldRec);
        	if (tldRec.getDescriptor().getShortName().equals("tags"))
        		assertEquals("/WEB-INF/tags", CMUtil.getURIFromDoc(tldDoc, _project));
        	else if (tldRec.getDescriptor().getShortName().equals("moreTags"))
        		assertEquals("/WEB-INF/tags/moreTags", CMUtil.getURIFromDoc(tldDoc, _project));
        	else if (tldRec.getDescriptor().getShortName().equals("mysample"))
        		assertEquals("/WEB-INF/SampleTagLib.tld", CMUtil.getURIFromDoc(tldDoc, _project));
        	else if (tldRec.getDescriptor().getShortName().equals("XXXXmysample"))
        		assertEquals("/WEB-INF/tlds/XXXXSampleTagLib.tld", CMUtil.getURIFromDoc(tldDoc, _project));
        	else if (tldRec.getDescriptor().getShortName().equals("uri-supplied"))
        		assertEquals("uri-supplied", CMUtil.getURIFromDoc(tldDoc, _project));        	
        }
	}

	public void testIsTagDirDocument() {
		
		CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
	    for (ITaglibRecord tldRec : TaglibIndex.getAvailableTaglibRecords(_project.getFullPath())){
	
			TLDDocument tldDoc = (TLDDocument)factory.createCMDocument(tldRec);
	    	if (tldRec.getDescriptor().getShortName().equals("tags"))
	    		assertTrue(CMUtil.isTagDirDocument(tldDoc, _project));
	    	else if (tldRec.getDescriptor().getShortName().equals("moreTags"))
	    		assertTrue(CMUtil.isTagDirDocument(tldDoc, _project));
	    	else if (tldRec.getDescriptor().getShortName().equals("mysample"))
	    		assertFalse(CMUtil.isTagDirDocument(tldDoc, _project));
	    	else if (tldRec.getDescriptor().getShortName().equals("XXXXmysample"))
	    		assertFalse(CMUtil.isTagDirDocument(tldDoc, _project));
	    	else if (tldRec.getDescriptor().getShortName().equals("uri-supplied"))
	    		assertFalse(CMUtil.isTagDirDocument(tldDoc, _project));	
	    }
	}
}
