package org.eclipse.jst.jsf.core.tests.project.facet;

import static junit.framework.Assert.assertEquals;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.junit.Test;


public class TestJSFUtils20 extends TestJSFUtils12
{

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        assertEquals(JSFVersion.V2_0, _jsfUtils.getVersion());
    }

    @Override
    protected JSFVersion getVersionToTestIn()
    {
        return JSFVersion.V2_0;
    }

    @Test
    public void testUpdateWebApp_ExistingServlet_3_0()
    {
        super.testUpdateWebApp_ExistingServlet("3.0");
    }

    @Test
    public void testUpdateWebApp_NewServlet_3_0()
    {
        super.testUpdateWebApp_NewServlet("3.0");
    }

    @Test
    public void testRollbackWebApp_3_0()
    {
        super.testRollbackWebApp("3.0");
    }

    @Test
    public void testGetFileUrlPath_NonNullCases_3_0()
    {
        super.testGetFileUrlPath_NonNullCases("3.0");
    }

    @Test
    public void testGetFileUrlPath_NullCases_3_0()
    {
        super.testGetFileUrlPath_NullCases("3.0");
    }
}
