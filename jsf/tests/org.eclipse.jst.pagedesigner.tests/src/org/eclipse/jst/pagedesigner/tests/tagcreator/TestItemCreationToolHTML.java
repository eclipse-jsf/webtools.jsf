package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.BaseItemCreationToolTestCase;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;

/**
 * Some basic tests to cover HTML tags
 * @author cbateman
 *
 */
public class TestItemCreationToolHTML extends BaseItemCreationToolTestCase
{
    public TestItemCreationToolHTML()
    {
        super("html");
    }

    @Override
    protected void setUp() throws Exception 
    {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception 
    {
        super.tearDown();
    }

    public void testCreateButton() throws Exception
    {
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper(CMDocType.HTML_DOC_TYPE, "INPUT.BUTTON"), "html", "html", 358,false);
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper(CMDocType.HTML_DOC_TYPE, "INPUT.BUTTON"), "xhtml", "xhtml", 350,false);
    }
}
