package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.BaseTagCreatorTestCase;

public class TestDefaultTagCreatorJSP extends BaseTagCreatorTestCase 
{
    public TestDefaultTagCreatorJSP()
    {
        super("jsp", "jsp");
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
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper("jsp11", "jsp:directive.include"), "jsp", "jsp", 349,false);
        doCreateTest(TagIdentifierFactory.createJSPTagWrapper("jsp11", "jsp:directive.include"), "jspx", "jspx", 492,false);
    }
}
