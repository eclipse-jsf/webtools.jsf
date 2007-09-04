package org.eclipse.jst.pagedesigner.tests.tagcreator.base;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.internal.TagCreationFactory;
import org.eclipse.jst.pagedesigner.tests.PageDesignerTestsPlugin;
import org.w3c.dom.Element;

public class BaseTagCreatorTestCase extends BaseTestClass 
{
//    private Map<String, TaglibPaletteDrawer> _drawers = new HashMap<String, TaglibPaletteDrawer>();
    protected final String _defaultPrefix;
    
    public BaseTagCreatorTestCase(final String defaultPrefix, final String compareDataSubDir)
    {
        super(compareDataSubDir);
        this._defaultPrefix = defaultPrefix;
    }
    

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    protected final void doCreateTest(final TagIdentifier tagId, final String inExt, final String outExt, int offset) throws Exception 
    {
        final String uri = tagId.getUri();
        final String tagName = tagId.getTagName();

        IFile file = (IFile) _webProjectTestEnv.loadResourceInWebRoot(
                PageDesignerTestsPlugin.getDefault().getBundle(),
                "/testdata/tagcreator/tagCreator."+inExt+".data", "/tagCreator_"+tagName+"."+inExt);

        
        ITagCreator tagCreator = TagCreationFactory.getInstance()
                .createTagCreator(tagId);

        Element element = tagCreator.createTag(getCreationData(uri, tagName,
                _defaultPrefix, file, offset));

        System.out.println(element.toString());

        assertExpectedResult(file, tagName, outExt);
    }
}
