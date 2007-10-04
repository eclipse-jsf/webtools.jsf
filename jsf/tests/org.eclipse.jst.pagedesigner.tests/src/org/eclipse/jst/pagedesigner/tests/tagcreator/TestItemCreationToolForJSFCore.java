package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.BaseItemCreationToolTestCase;

public class TestItemCreationToolForJSFCore extends BaseItemCreationToolTestCase 
{
    public TestItemCreationToolForJSFCore()
    {
        super("jsf");
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

    public void testCreateActionListener() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_ACTIONLISTENER, "jsp", "jsp", 358,false);
    }
    
    public void testCreateAttribute() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_ATTRIBUTE, "jsp", "jsp", 358,false);
    }

    public void testCreateConvertDateTime() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_CONVERTDATETIME, "jsp", "jsp", 358,false);
    }
    public void testCreateConvertNumber() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_CONVERTNUMBER, "jsp", "jsp", 358,false);
    }

    /**
     * @throws Exception
     */
    public void testCreateConverter() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_CONVERTER, "jsp", "jsp", 358,false);
    }

    /**
     * @throws Exception
     */
    public void testCreateFacet() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_FACET, "jsp", "jsp", 358,false);
    }

    /**
     * @throws Exception
     */
    public void testCreateLoadBundle() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_LOADBUNDLE, "jsp", "jsp", 358,false);
    }

    /**
     * @throws Exception
     */
    public void testCreateParam() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_PARAM, "jsp", "jsp", 358,true);
    }

    /**
     * @throws Exception
     */
    public void testCreateSelectItem() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SELECTITEM, "jsp", "jsp", 358,false);
    }

    /**
     * @throws Exception
     */
    public void testCreateSelectItems() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SELECTITEMS, "jsp", "jsp", 358,false);
    }

    /**
     * @throws Exception
     */
    public void testCreateSubview() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SUBVIEW, "jsp", "jsp", 358,false);
    }

    /**
     * @throws Exception
     */
    public void testCreateValidateDoubleRange() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_VALIDATEDOUBLERANGE, "jsp", "jsp", 358,true);
    }

    /**
     * @throws Exception
     */
    public void testCreateValidateLength() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_VALIDATELENGTH, "jsp", "jsp", 358,true);
    }
    
    /**
     * @throws Exception
     */
    public void testCreateValidateLongRange() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_VALIDATELONGRANGE, "jsp", "jsp", 358,true);
    }

    /**
     * @throws Exception
     */
    public void testCreateValidator() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_VALIDATOR, "jsp", "jsp", 358,false);
    }
    
    /**
     * @throws Exception
     */
    public void testCreateValueChangeListener() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_VALUECHANGELISTENER, "jsp", "jsp", 358,false);
    }

    /**
     * @throws Exception
     */
    public void testCreateVerbatim() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_VERBATIM, "jsp", "jsp", 358,false);
    }

    /**
     * @throws Exception
     */
    public void testCreateView() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_VIEW, "jsp", "jsp", 358,false);
    }

}
