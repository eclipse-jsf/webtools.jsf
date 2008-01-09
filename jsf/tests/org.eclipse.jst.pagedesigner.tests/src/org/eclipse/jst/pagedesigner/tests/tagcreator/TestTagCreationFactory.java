package org.eclipse.jst.pagedesigner.tests.tagcreator;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.pagedesigner.itemcreation.CreationData;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.internal.TagCreationFactory;
import org.eclipse.jst.pagedesigner.tests.PageDesignerTestsPlugin;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.BaseTestClass;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.MockCreateItemCommand;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.MockItemCreationTool;
import org.eclipse.jst.pagedesigner.tests.tagcreatorPlugin.TestCreationFactory;
import org.eclipse.jst.pagedesigner.utils.CommandUtil;
import org.w3c.dom.Element;

/**
 * @author cbateman
 *
 */
public class TestTagCreationFactory extends BaseTestClass 
{
    public TestTagCreationFactory() {
        super("jsf");
    }

    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();

        _webProjectTestEnv.loadResourceInWebRoot
        (PageDesignerTestsPlugin.getDefault().getBundle()
            , "/testdata/tagcreator/testCreator.tld.data"
            , "/META-INF/testCreator.tld");
        // make sure the manager picks up the new TLD.
        _manager.reset();
    }

    @Override
    protected void tearDown() throws Exception 
    {
        super.tearDown();
    }

    public void testEnsureRequiredAttributes() throws Exception
    {
        IFile jspFile = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle()
                ,"/testdata/tagcreator/testTagCreation.jsp.data"
                ,"/testEnsureRequiredAttributes.jsp");

        CreationData creationData =
            getCreationData(TestCreationFactory.TAGCREATOR_URI_1
                   , "tagWithRequiredAttr", "test", jspFile, 501);
        Element element = CommandUtil.excuteInsertion(creationData.getTagEntry()
                                    , creationData.getModel()
                                    , creationData.getDomPosition()
                                    , creationData.getDropCustomizationData());

        assertNotNull(element);
        final TagIdentifier tagId = TagIdentifierFactory.createDocumentTagWrapper(element);
        assertEquals("test:tagWithRequiredAttr", element.getNodeName());
        // the framework will not force a value on this attribute
        assertNull(element.getAttribute(TestCreationFactory.REQUIRED_ATTR_1));

        // the framework will leave attr2 alone because the advisor is setting it
        assertEquals(TestCreationFactory.getDefaultAttributeValue
                            (tagId, TestCreationFactory.REQUIRED_ATTR_2)
                , element.getAttribute(TestCreationFactory.REQUIRED_ATTR_2));

        // test manually calling the ensure method for
        jspFile = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle()
                ,"/testdata/tagcreator/testTagCreation.jsp.data"
                ,"/testEnsureRequiredAttributes2.jsp");
        creationData =
            getCreationData(TestCreationFactory.TAGCREATOR_URI_1
                   , "tagWithRequiredAttr2", "test", jspFile, 501);
        element = CommandUtil.excuteInsertion(creationData.getTagEntry()
                                    , creationData.getModel()
                                    , creationData.getDomPosition()
                                    , creationData.getDropCustomizationData());
        
        // on this tag, the first required attr is set by metadata
        assertEquals("foobar", element.getAttribute(TestCreationFactory.REQUIRED_ATTR_1));
        // the second one is not set by meta-data, but is required and ensure is called
        // by the custom advisor, so it should be set to empty
        assertEquals("", element.getAttribute(TestCreationFactory.REQUIRED_ATTR_2));
        
        // the optional attribute is not set by meta-data and since it's not required,
        // the ensure method should not touch it either.
        assertNull(element.getAttribute("notRequiredAttr1"));
    }

    public void testTagCreationMetadata() throws Exception
    {
        IFile jspFile = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle()
                ,"/testdata/tagcreator/testTagCreation.jsp.data"
                ,"/testTagCreationMetadata.jsp");

        CreationData creationData =
            getCreationData(TestCreationFactory.TAGCREATOR_URI_1
                   , "tagWithMetadata", "test", jspFile, 501);
        Element element = CommandUtil.excuteInsertion(creationData.getTagEntry()
                                    , creationData.getModel()
                                    , creationData.getDomPosition()
                                    , creationData.getDropCustomizationData());

        assertNotNull(element);
        // ensure that the meta-data value for this required attribute
        // is set.
        assertEquals("foobar", element.getAttribute("requiredAttr1"));
        // we have not provided meta-data for this attribute but it is required
        // by default, the framework won't enforce so should be null
        assertNull(element.getAttribute("requiredAttr2"));
        // we have provided meta-data for this non-required attribute,
        // so ensure it's set
        assertEquals("someOtherFooBar", element.getAttribute("notRequiredAttr1"));
        // we have not provided m-d for this optional one, so since it's optional
        // it should be null.
        assertNull(element.getAttribute("notRequiredAttr2"));

        // check the child template was applied
        assertEquals(1, element.getChildNodes().getLength());
        // TODO: we should find the default prefix for outputText, but
        // we only check those taglibs already referenced in the document.
        assertEquals("p:outputText", element.getChildNodes().item(0).getNodeName());
    }

    /**
     * Test the situation that is common with JSPX where the f:view must be more than
     * 3 levels deep from the root (was causing duplicate view's to be added).
     */
    public void testBug197042() throws Exception
    {
        IFile jspFile = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle()
                ,"/testdata/tagcreator/tagCreator.jspx.data"
                ,"/testTagCreationMetadata.jsp");

        final String uri = 
            IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON.getUri();
        final String tagName = 
            IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON.getTagName();
        CreationData creationData =
            getCreationData(uri,tagName,"h", jspFile, 495);

        ITagCreator tagCreator = TagCreationFactory.getInstance()
            .createTagCreator(creationData.getTagId());

        Element element = tagCreator.createTag(creationData);

        System.out.println(element.toString());

        ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        getDocumentContext(0, jspFile).getModel().save(resultStream);

        final IPath expectedPath = JSFTestUtil.getAbsolutePath(
                PageDesignerTestsPlugin.getDefault().getBundle(),
                    "/testdata/tagcreator/jsf/expectedResult_commandButton.jspx.data");
        final String expected = getExpectedResult(expectedPath).trim();
        final String result = resultStream.toString().trim();

        assertEquals(expected, result);
    }

    public void testDropCustomizerThatCancels() throws Exception
    {
        IFile jspFile = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle()
                ,"/testdata/tagcreator/tagCreator.jsp.data"
                ,"/testCustomizerCancel.jsp");

        TestCreationFactory.setTestParametersForDropCustomer(Status.CANCEL_STATUS, null);

        MockItemCreationTool tool = 
            createMockItemCreationTool(jspFile, 358, TestCreationFactory.TAG_WITH_REQUIRED_ATTR, IStatus.CANCEL);

        tool.customizeDropAndMaybeExecute(0);

        assertExpectedResult(jspFile, "jsp");
    }

    @SuppressWarnings("unchecked")
    public void testDropCustomizationDataAcquistion() throws Exception
    {
        IFile jspFile = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle()
                ,"/testdata/tagcreator/tagCreator.jsp.data"
                ,"/testCustomizerCancel.jsp");

        IAdaptable adaptable = new IAdaptable()
        {
            public Object getAdapter(Class adapter) {
                if (Map.class == adapter)
                {
                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("color", "red");
                    return map;
                }
                return null;
            }
        };

        TestCreationFactory.setTestParametersForDropCustomer(Status.OK_STATUS, adaptable);

        MockItemCreationTool tool = 
            createMockItemCreationTool(jspFile, 358, TestCreationFactory.TAG_WITH_REQUIRED_ATTR, IStatus.CANCEL);

        tool.customizeDropAndMaybeExecute(0);
        Object[] commands = tool.getDomain().getCommandStack().getCommands();
        assertEquals(1, commands.length);
        assertTrue(commands[0] instanceof MockCreateItemCommand);
        
        IAdaptable value = ((MockCreateItemCommand)commands[0]).getCustomizationDataTesting();
        assertEquals(adaptable, value);
        Map map = (Map) value.getAdapter(Map.class);
        assertNotNull(map);
        assertEquals("red", map.get("color"));
    }
    
    private void assertExpectedResult(IFile file, String outExt) throws Exception
    {
        final ByteArrayOutputStream resultStream = new ByteArrayOutputStream();
        getDocumentContext(0, file).getModel().save(resultStream);

        final String expected = 
            getExpectedResult("/testdata/tagcreator/tagCreator."+outExt+".data").trim();
        final String result = resultStream.toString("ISO-8859-1").trim();

        assertEquals(expected, result);
    }
}
