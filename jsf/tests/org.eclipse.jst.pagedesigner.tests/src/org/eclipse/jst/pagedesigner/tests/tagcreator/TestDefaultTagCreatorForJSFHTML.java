package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.BaseTagCreatorTestCase;

public class TestDefaultTagCreatorForJSFHTML extends BaseTagCreatorTestCase
{
    public TestDefaultTagCreatorForJSFHTML()
    {
        super("h", "jsf");
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
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "jsp", "jsp", 358);
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "jspx", "jspx", 495);
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "xhtml", "xhtml", 350);
    }

    public void testCreateCommandLink() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDLINK, "jsp", "jsp", 358);
    }

    public void testCreatePanelGrid() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID, "jsp", "jsp", 358);
    }
    
    public void testCreateDataTable() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_DATA_TABLE, "jsp", "jsp", 358);
    }
    
    public void testCreateForm() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_FORM, "jsp", "jsp", 358);
    }
    
    public void testCreateGraphicImage() throws  Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_GRAPHICIMAGE, "jsp", "jsp", 358);
    }
    
    public void testCreateInputHidden() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_INPUTHIDDEN, "jsp", "jsp", 358);
    }
    
    public void testCreateMessage() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_MESSAGE, "jsp", "jsp", 358);
    }
    
    public void testCreateMessages() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_MESSAGES, "jsp", "jsp", 358);
    }
    
    public void testCreateOutputFormat() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_OUTPUTFORMAT, "jsp", "jsp", 358);
    }
    
    public void testCreateOutputLabel() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_OUTPUTLABEL, "jsp", "jsp", 358);
    }
    
    public void testCreateOutputText() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_OUTPUTTEXT, "jsp", "jsp", 358);
    }

    public void testCreatePanelGroup() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_PANEL_GROUP, "jsp", "jsp", 358);
    }

    public void testCreateInputSecret() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_INPUTSECRET, "jsp", "jsp", 358);
    }
    
    public void testCreateSelectBooleanCheckbox() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SELECTBOOLEANCHECKBOX, "jsp", "jsp", 358);
    }

    public void testCreateSelectManyCheckbox() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SELECTMANYCHECKBOX, "jsp", "jsp", 358);
    }
    
    public void testCreateSelectManyListbox() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SELECTMANYLISTBOX, "jsp", "jsp", 358);
    }

    public void testCreateSelectManyMenu() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SELECTMANYMENU, "jsp", "jsp", 358);
    }

    public void testCreateSelectOneListbox() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SELECTONELISTBOX, "jsp", "jsp", 358);
    }

    public void testCreateSelectOneMenu() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SELECTONEMENU, "jsp", "jsp", 358);
    }

    public void testCreateSelectOneRadio() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_SELECTONERADIO, "jsp", "jsp", 358);
    }

    public void testCreateInputText() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_INPUTTEXT, "jsp", "jsp", 358);
    }

    public void testCreateInputTextArea() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_INPUTTEXTAREA, "jsp", "jsp", 358);
    }

}