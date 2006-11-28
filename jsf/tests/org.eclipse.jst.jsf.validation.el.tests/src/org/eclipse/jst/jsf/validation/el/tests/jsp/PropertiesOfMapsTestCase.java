package org.eclipse.jst.jsf.validation.el.tests.jsp;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;

/**
 * Tests cases with the default property resolver where maps are being
 * queried for their properties.  This is different than with beans, since
 * for unconstrained maps, we often can't fully validate type
 * 
 * @author cbateman
 *
 */
public class PropertiesOfMapsTestCase extends SingleJSPTestCase 
{
    protected void setUp() throws Exception
    {
        _srcFileName = "/testdata/jsps/propertiesOfMaps.jsp.data";
        _destFileName = "/propertiesOfMaps.jsp";
        super.setUp();
    }

    public void testSanity()
    {
        assertEquals("beanWithMapProperties.integerProperty", getELText(_structuredDocument,906));
        assertEquals("bundle.bundleProp1", getELText(_structuredDocument,982));
        assertEquals("bundle.x.y", getELText(_structuredDocument,1034));
        assertEquals("beanWithMapProperties.treeMap.foo", getELText(_structuredDocument,1078));
        assertEquals("beanWithMapProperties.treeMap.foo.x", getELText(_structuredDocument,1145));
        assertEquals("beanWithMapProperties.mapProperty.foo", getELText(_structuredDocument,1214));
        assertEquals("beanWithMapProperties.mapProperty.foo.x", getELText(_structuredDocument,1285));
        assertEquals("mapBean.foo", getELText(_structuredDocument,1358));
        assertEquals("mapBean.foo.x", getELText(_structuredDocument,1403));
        assertEquals("mapBean.getIgnoredIntProperty", getELText(_structuredDocument,1450));
        assertEquals("bundle.y", getELText(_structuredDocument,1515));
        assertEquals("bundle.bundleProp1.z", getELText(_structuredDocument,1557));
    }
    
    public void testNoErrorExprs() 
    {
        assertNoError(906, Signature.SIG_INT);
        assertNoError(982, TypeConstants.TYPE_STRING);
        assertNoError(1034, TypeConstants.TYPE_STRING);
        assertNoError(1078, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1145, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1214, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1285, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1358, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1403, TypeConstants.TYPE_JAVAOBJECT);
        assertNoError(1450, TypeConstants.TYPE_JAVAOBJECT);
    }

    public void testWarningExprs() 
    {
        assertSemanticWarning(1515, null, 1);
        assertSemanticWarning(1557, null, 1);
    }

    public void testErrorExprs() 
    {
        // do nothing; no error cases
    }
}
