package org.eclipse.jst.jsf.designtime.tests.views.persistence;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.zip.ZipFile;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.CMNodeNamedMapAdapter;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDTagElement;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IAttributeAdvisor.NullAttributeAdvisor;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class TestSerializableTLDTagElement extends TestCase
{

    private WebProjectTestEnvironment _webProject;
    private TLDElementDeclaration _sampleTldElementDeclaration;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
        /* https://bugs.eclipse.org/bugs/show_bug.cgi?id=296496
        final ZipFile zipFile = JSFTestUtil.createZipFile(TestsPlugin
                .getDefault().getBundle(), "/testfiles/testzips/TLDTests.zip");

        _webProject = new WebProjectTestEnvironment(this,
                JavaFacetUtils.JAVA_50, ProjectFacetsManager.getProjectFacet(
                        "jst.web").getVersion("2.4"));
        _webProject.createFromZip(zipFile, true);
        */
        final ZipFile zipFile = JSFTestUtil.createZipFile(
        		TestsPlugin.getDefault().getBundle(),
        		"/testfiles/testzips/TLDTests2.zip");
        _webProject = new WebProjectTestEnvironment(
        		this,
        		JavaFacetUtils.JAVA_50,
        		ProjectFacetsManager.getProjectFacet("jst.web").getVersion("2.4"));
        _webProject.createFromZip2(zipFile, true);

        assertNotNull(_webProject);

        _sampleTldElementDeclaration = findElementDeclaration(_webProject
                .getTestProject(), "uri-supplied", "useBean");
        assertNotNull(_sampleTldElementDeclaration);
    }

    // regression for: https://bugs.eclipse.org/bugs/show_bug.cgi?id=260931
    public void testSerialization() throws Exception
    {
         final TLDTagElement tldTagElement = new TLDTagElement(_sampleTldElementDeclaration, new NullAttributeAdvisor());
         verifyUseBean(tldTagElement);
         ByteArrayOutputStream  out = new ByteArrayOutputStream();
         ObjectOutputStream outStream = new ObjectOutputStream(out);
         outStream.writeObject(tldTagElement);
         ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
         ObjectInputStream inStream = new ObjectInputStream(in);
         Object readObject = inStream.readObject();
         assertNotNull(readObject);
         assertTrue(readObject instanceof TLDTagElement);
         verifyUseBean((TLDTagElement) readObject);
         // from the bug: CMNodeNamedMapAdapter should never get serialized as the attribute handler map.
         assertFalse(((TLDTagElement)readObject).getAttributeHandlers() instanceof CMNodeNamedMapAdapter);
    }

    @SuppressWarnings("unchecked")
    private void verifyUseBean(final TLDTagElement tldTagElement)
    {
        assertEquals("useBean", tldTagElement.getName());
        assertEquals("uri-supplied", tldTagElement.getUri());
        assertEquals("sample.http.taglib.UseBeanTag", tldTagElement.getTagHandlerClassName());
        Map attributeHandlers = tldTagElement.getAttributeHandlers();
        assertTrue(attributeHandlers.containsKey("id"));
        assertTrue(attributeHandlers.containsKey("cls"));
        assertTrue(attributeHandlers.containsKey("type"));
        assertTrue(attributeHandlers.containsKey("request"));
        assertTrue(attributeHandlers.containsKey("scope"));
        assertEquals(5, attributeHandlers.size());
    }
    
    private static TLDElementDeclaration findElementDeclaration(
            final IProject project, final String shortName, final String name)
    {
        final ITaglibRecord tagRecord = findTagRecord(project, shortName);
        assertNotNull(tagRecord);
        final CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
        final TLDDocument doc = (TLDDocument) factory
                .createCMDocument(tagRecord);
        assertNotNull(doc);
        return (TLDElementDeclaration) doc.getElements().getNamedItem(name);
    }

    private static ITaglibRecord findTagRecord(final IProject project,
            final String shortName)
    {
        for (final ITaglibRecord record : TaglibIndex
                .getAvailableTaglibRecords(project.getFullPath()))
        {
            if (shortName.equals(record.getDescriptor().getShortName()))
            {
                return record;
            }
        }
        return null;
    }

}
