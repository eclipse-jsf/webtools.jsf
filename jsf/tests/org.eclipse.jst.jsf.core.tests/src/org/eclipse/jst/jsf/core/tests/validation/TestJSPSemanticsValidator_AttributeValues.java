package org.eclipse.jst.jsf.core.tests.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipFile;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.jsf.core.tests.TestsPlugin;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsf.validation.internal.JSPSemanticsValidator;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;

public class TestJSPSemanticsValidator_AttributeValues extends TestCase 
{
    private WebProjectTestEnvironment _webProject;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80");
        
        final ZipFile zipFile = JSFTestUtil.createZipFile(TestsPlugin.getDefault().getBundle()
                , "/testfiles/testzips/ValidationTestProject1.zip");

        _webProject = new WebProjectTestEnvironment(this, JavaFacetUtils.JAVA_50, ProjectFacetsManager.getProjectFacet( "jst.web" ).getVersion("2.4"));
        _webProject.createFromZip(zipFile, true);
    }

    public void testSanity() throws Exception
    {
    	final IProject project = _webProject.getTestProject();
    	assertNotNull(project);
    	assertTrue(project.isAccessible());
    	
    	final IFile jspFile = project.getFile(new Path("WebContent/NonELValidation.jsp"));
    	assertTrue(jspFile.isAccessible());

    	IStructuredModel jspModel = null;
    	try
    	{
    		jspModel = StructuredModelManager.getModelManager().getModelForRead(jspFile);
    		assert(jspModel instanceof DOMModelForJSP);
    	}
    	finally
    	{
    		if (jspModel != null)
    		{
    			jspModel.releaseFromRead();
    		}
    	}
    }
    
    public void testNonELValidation() throws Exception
    {
    	final IProject project = _webProject.getTestProject();
    	assertNotNull(project);
    	assertTrue(project.isAccessible());
    	
    	final IFile jspFile = project.getFile(new Path("WebContent/NonELValidation.jsp"));
    	assertTrue(jspFile.isAccessible());

    	final MockIReporter  mockReporter = new MockIReporter();
    	final MockIValidationContext validationContext =
    		new MockIValidationContext(Collections.singletonList(jspFile));
    	JSPSemanticsValidator validator = new JSPSemanticsValidator();
    	validator.validate(validationContext, mockReporter);
    	
    	// there should only be 3, but because we don't want to have the jars
    	// in the path, we trigger a containment warning on the loadBundle
    	// since the f:view in the doc can't be fully resolved.
    	assertEquals(4, mockReporter.getMessages().size());
    	
    	assertExpectedMessage(mockReporter, 591, 25, IMessage.HIGH_SEVERITY);
    	assertExpectedMessage(mockReporter, 936, 1, IMessage.NORMAL_SEVERITY);
    	assertExpectedMessage(mockReporter, 969, 9, IMessage.NORMAL_SEVERITY);
    }
    
    public void testELValidation() throws Exception
    {
    	final IProject project = _webProject.getTestProject();
    	assertNotNull(project);
    	assertTrue(project.isAccessible());
    	
    	final IFile jspFile = project.getFile(new Path("WebContent/ELValidation.jsp"));
    	assertTrue(jspFile.isAccessible());

    	final MockIReporter  mockReporter = new MockIReporter();
    	final MockIValidationContext validationContext =
    		new MockIValidationContext(Collections.singletonList(jspFile));
    	JSPSemanticsValidator validator = new JSPSemanticsValidator();
    	validator.validate(validationContext, mockReporter);
    	
    	// there should only be 5, but because we don't want to have the jars
    	// in the path, we trigger a containment warning on the loadBundle
    	// since the f:view in the doc can't be fully resolved.
    	// at 845 we also get two, one for syntax error and one for missing bracket
    	assertEquals(9, mockReporter.getMessages().size());
    	
    	assertExpectedMessage(mockReporter, 603, 2, IMessage.NORMAL_SEVERITY);
    	assertExpectedMessage(mockReporter, 649, 1, IMessage.NORMAL_SEVERITY);
    	assertExpectedMessage(mockReporter, 696, 5, IMessage.NORMAL_SEVERITY);
    	assertExpectedMessage(mockReporter, 753, 6, IMessage.NORMAL_SEVERITY);
    	assertExpectedMessage(mockReporter, 801, 4, IMessage.HIGH_SEVERITY);

    	// two on this one: syntax error and missing bracket
    	assertExpectedMessage(mockReporter, 845, 5, IMessage.HIGH_SEVERITY);
    	assertExpectedMessage(mockReporter, 848, 1, IMessage.NORMAL_SEVERITY);
    	
    	assertExpectedMessage(mockReporter, 963, 40, IMessage.HIGH_SEVERITY);
    }

    private void assertExpectedMessage(final MockIReporter reporter, 
    		final int offset, final int length, final int severity)
    {
    	final List<IMessage> messages = reporter.getMessageListForOffset(offset);
    	final List<IMessage> messagesNotMatching = new ArrayList<IMessage>();
    	
    	assertTrue(messages.size() > 0);

    	for (final IMessage message : messages)
    	{
    		if (message.getLength() == length && message.getSeverity() == severity)
    		{
    			// we found the expected message
    			return;
    		}
    		else
    		{
    			messagesNotMatching.add(message);
    		}
    	}

    	String failMessage = "";

    	for (final IMessage message : messages)
    	{
    		failMessage += "\n" + message.getText();
    	}
    	fail(String.format("Failed to find expected message at offset %d, found instead %s", offset, failMessage));
    }
}
