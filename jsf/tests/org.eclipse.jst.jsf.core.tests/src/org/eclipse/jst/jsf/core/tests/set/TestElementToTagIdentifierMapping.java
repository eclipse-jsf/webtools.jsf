package org.eclipse.jst.jsf.core.tests.set;

import junit.framework.TestCase;

import org.apache.xerces.dom.ElementImpl;
import org.eclipse.jst.jsf.core.tests.tagmatcher.BaseTagMatcherTestCase;
import org.w3c.dom.Element;

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
