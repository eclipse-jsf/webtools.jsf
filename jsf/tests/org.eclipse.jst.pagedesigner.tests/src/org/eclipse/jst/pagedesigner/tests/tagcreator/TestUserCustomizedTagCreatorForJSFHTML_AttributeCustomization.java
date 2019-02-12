/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.CustomizationDataImpl;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.IWritableCustomizationData;

/**
 * Test class to test attribute customization for a tag drop
 * 
 * @author Debajit Adhikary
 *
 */
public class TestUserCustomizedTagCreatorForJSFHTML_AttributeCustomization extends BaseUserCustomizedTagCreatorTestCase 
{
	/**
	 * Path to directory where the test-data files are located. The XML
	 * generated from this test case is compared against this test data.
	 * 
	 */
	private static final String TESTDATA_FILES_PATH = "/testdata/UserCustomizedTagCreator/AttributeCustomization";
	
	
	public void testAttributeInsertion() throws Exception
    {
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "jsp", "jsp", 358, false, getCustomizationData());
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "jspx", "jspx", 495, false, getCustomizationData());
        
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
        doCreateTest(IJSFConstants.TAG_IDENTIFIER_COMMANDBUTTON, "xhtml", "xhtml", 350, false, getCustomizationData());
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(null);
    }
	
	

    protected ICustomizationData getCustomizationData()
    {
    	// Setup tag
    	IWritableCustomizationData data = new CustomizationDataImpl(getTagIdentifier());
    	data.addAttribute("attr1", "value1");

    	return data;
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
