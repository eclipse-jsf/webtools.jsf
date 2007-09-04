package org.eclipse.jst.pagedesigner.tests.tagcreator.base;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.tests.PageDesignerTestsPlugin;

public class BaseItemCreationToolTestCase extends BaseTestClass 
{

    public BaseItemCreationToolTestCase(final String compareDataSubDir) {
        super(compareDataSubDir);
    }

    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
    }
    
    protected final void doCreateTest(final TagIdentifier tagId, final String inExt, final String outExt, int offset) throws Exception
    {
        final String tagName = tagId.getTagName();

        final IFile file = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle(),
                "/testdata/tagcreator/tagCreator."+inExt+".data", "/tagCreator_"+tagName+"."+inExt);

        MockItemCreationTool tool = createMockItemCreationTool(file, offset, tagId);

        tool.customizeDropAndMaybeExecute(0);

        assertExpectedResult(file, tagName, outExt);
    }
}
