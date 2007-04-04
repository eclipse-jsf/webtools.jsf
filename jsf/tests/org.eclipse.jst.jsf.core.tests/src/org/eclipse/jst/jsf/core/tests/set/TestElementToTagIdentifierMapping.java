package org.eclipse.jst.jsf.core.tests.set;

import org.eclipse.jst.jsf.core.tests.tagmatcher.BaseTagMatcherTestCase;

public class TestElementToTagIdentifierMapping extends BaseTagMatcherTestCase
{
    protected void setUp() throws Exception {
        _srcFileName = "/testfiles/jsps/testdata1.jsp.data";
        _destFileName = "/testdata1.jsp";
        super.setUp();
    }

    public void testElementToIdMapping()
    {
        // TODO:
    }
}
