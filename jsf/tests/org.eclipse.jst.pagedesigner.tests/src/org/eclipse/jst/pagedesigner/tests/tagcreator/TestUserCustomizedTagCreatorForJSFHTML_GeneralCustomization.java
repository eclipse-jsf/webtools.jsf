package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.CustomizationDataImpl;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData;
import org.eclipse.jst.pagedesigner.tests.tagcreatorPlugin.UserCustomizedElementEditFactory;

/**
 * Test class for tag drop customization for parent tags, child tags, and
 * attributes.
 * 
 * @author Debajit Adhikary
 * 
 */
public class TestUserCustomizedTagCreatorForJSFHTML_GeneralCustomization extends BaseUserCustomizedTagCreatorTestCase 
{
	/**
	 * Path to directory where the test-data files are located. The "XML"
	 * generated from this test case is compared against this test data.
	 * 
	 */
	private static final String TESTDATA_FILES_PATH = "/testdata/UserCustomizedTagCreator/GeneralCustomization";
	
	
	public void testGeneralCustomization() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "jsp", "jsp", 358, false, getCustomizationData());
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "jspx", "jspx", 495, false, getCustomizationData());
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "xhtml", "xhtml", 350, false, getCustomizationData());
    }
	

    protected ICustomizationData getCustomizationData()
    {
    	IWritableCustomizationData tagDropped = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG1);
        tagDropped.addAttribute("attr1", "value1");
    	
        
        //---------------------------------------------------------------------
        // Add parent tags.
    	// We will create the hierarchy:
    	// f:view > TAG1 > TAG1 > TAG2 > TAG2 > TAG2 > TAG2 > TAG2 > TAG2 > TAG2 > TAG3 > TAG3 > TagDropped
        //---------------------------------------------------------------------

        // Setup parent <f:view>. (This will not use the UserCustomizedTagCreator but
        // will use its own tag creator)
        IWritableCustomizationData view = new CustomizationDataImpl(IJSFConstants.TAG_IDENTIFIER_VIEW);
        tagDropped.addParentData(view);
        
        IWritableCustomizationData tag11 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG1);
        tagDropped.addParentData(tag11);

        IWritableCustomizationData tag12 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG1);
        tagDropped.addParentData(tag12);

        IWritableCustomizationData tag21 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG2);
        tag21.addAttribute("tag21Attr1", "tag21Attr1Value");   
        tagDropped.addParentData(tag21);

        IWritableCustomizationData tag22 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG2);
        tag22.addAttribute("tag22Attr1", "tag22Attr1Value");   
        tagDropped.addParentData(tag22);

        IWritableCustomizationData tag23 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG2);
        tagDropped.addParentData(tag23);

        IWritableCustomizationData tag24 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG2);
        tagDropped.addParentData(tag24);

        IWritableCustomizationData tag25 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG2);
        tagDropped.addParentData(tag25);

        IWritableCustomizationData tag26 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG2);
        tagDropped.addParentData(tag26);

        IWritableCustomizationData tag27 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG2);
        tagDropped.addParentData(tag27);

        IWritableCustomizationData tag31 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG3);
        tagDropped.addParentData(tag31);

        IWritableCustomizationData tag32 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG3);
        tagDropped.addParentData(tag32);
    	
        
    	/*

          Add child tags. 
          We will create the child hierarchy:

          tagDropped
          |
          +----tag8
          |
          +----tag8
          |
          +----tag8
          |
          +----tag9
          |
          +----tag9
          |
          +----tag7
               |
               +----tag8
                    |
                    +----tag7
                         |
                         +----tag6
                         |
                         +----tag6

    	*/
    	
        IWritableCustomizationData tag81 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG8);
        tagDropped.addChildData(tag81);

        IWritableCustomizationData tag82 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG8);
        tagDropped.addChildData(tag82);

        IWritableCustomizationData tag83 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG8);
        tagDropped.addChildData(tag83);

        IWritableCustomizationData tag91 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG9);
        tag91.addAttribute("tag91Attr1", "tag91Attr1Value");
        tagDropped.addChildData(tag91);

        IWritableCustomizationData tag92 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG9);
        tagDropped.addChildData(tag92);

        IWritableCustomizationData tag71 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG7);
        IWritableCustomizationData tag84 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG8);
        tag84.addAttribute("attr1", "value1");
        IWritableCustomizationData tag73 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG7);

        IWritableCustomizationData tag61 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG6);
        tag73.addChildData(tag61);

        IWritableCustomizationData tag62 = new CustomizationDataImpl(UserCustomizedElementEditFactory.TAG6);
        tag73.addChildData(tag62);
        
        tag84.addChildData(tag73);
        tag71.addChildData(tag84);
        tagDropped.addChildData(tag71);

    	return tagDropped;
    }

    
	@Override
    protected final String getExpectedResult(final String tagName, final String outExt) throws Exception
    {
        final String ext = outExt == null ? "" : "." + outExt;
        final String fileName = "expectedResult_"
                + tagName.replaceAll(":", "_") + ext + ".data";
        final String pathStr = TESTDATA_FILES_PATH + "/" + _compareDataSubDir
                + "/" + fileName;
        return getExpectedResult(pathStr);
    }
}
