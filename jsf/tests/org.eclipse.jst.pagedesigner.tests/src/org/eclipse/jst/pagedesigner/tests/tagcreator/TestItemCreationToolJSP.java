package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.BaseItemCreationToolTestCase;

public class TestItemCreationToolJSP extends BaseItemCreationToolTestCase 
{
    public TestItemCreationToolJSP()
    {
        super("jsp");
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

    public void testIncludeDirective() throws Exception
    {
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper("jsp11", "jsp:directive.include"), "jsp", "jsp", 349);
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper("jsp11", "jsp:directive.include"), "jspx", "jspx", 492);
    }
}
