package org.eclipse.jst.jsf.validation.el.tests.jsp;

import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

public class PropertiesOfListTestCase extends SingleJSPTestCase 
{
    public PropertiesOfListTestCase()
    {
        super("/testdata/jsps/propertiesOfMaps.jsp.data", "/propertiesOfMaps.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testErrorExprs() {
        // TODO Auto-generated method stub

    }

    @Override
    public void testNoErrorExprs() {
        // TODO Auto-generated method stub

    }

    @Override
    public void testWarningExprs() {
        // TODO Auto-generated method stub

    }

}
