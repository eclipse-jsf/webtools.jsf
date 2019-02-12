/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.util;

import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValue;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

public abstract class SingleJSPTestCase extends JSPTestCase 
{
    
    public static final String FACES_CONFIG_FILE_NAME_1_1 = "/testfiles/web/faces-config_1_1.xml.data";
    public static final String FACES_CONFIG_FILE_NAME_1_2 = "/testfiles/web/faces-config_1_2.xml.data";
    
    /**
     * The file handle to the JSP in the workspace
     */
    protected IFile                             _testJSP;
    /**
     * The SSE structured model for the JSP
     */
    protected IStructuredModel                  _structuredModel;
    /**
     * The SSE structured document for the JSP
     */
    protected IStructuredDocument               _structuredDocument;
    /**
     * Name of the test data file containing the JSP source for this test
     */
    private final String                        _srcFileName;
    
    /**
     * Name of the file and path where the JSP source should be put in the
     * test project
     */
    protected final String                      _destFileName;
    

    protected SingleJSPTestCase(final String srcFileName, final String destFileName, final JSFVersion defaultJSFVersion, final String defaultFacesConfigFile)
    {
        super(defaultJSFVersion, defaultFacesConfigFile);
        _srcFileName = srcFileName;
        _destFileName = destFileName;
    }    

    protected void setUp() throws Exception 
    {
        super.setUp();

        _testJSP = loadJSP(_srcFileName, _destFileName);
        
        _structuredModel = StructuredModelManager.getModelManager().getModelForRead(_testJSP);
        _structuredDocument = _structuredModel.getStructuredDocument();
    }

    protected void tearDown() throws Exception 
    {
        super.tearDown();
        
        if (_structuredModel != null)
        {
            _structuredModel.releaseFromRead();
        }
    }
	
	protected IMetaDataEnabledFeature getProcessor(Class<?> klass, String uri, String tagname, String attrName) {
		List<?> ret = MetaDataEnabledProcessingFactory.getInstance().
		getAttributeValueRuntimeTypeFeatureProcessors(klass, 
				getStructuredDocumentContext(_structuredDocument, 1), //just need to establish the project; don't really care about offset.  should consider improving API
				uri, 
				tagname,
				attrName);
		
		if (ret.isEmpty())
			return null;
		
		return (IMetaDataEnabledFeature)ret.get(0);
					
	}
	
	protected void assertPossibleValues(List<?> possibleValues, String[] strings) {
		
		for (int i=0;i < strings.length;i++) {
			boolean found = false;
			for (int j=0;j < possibleValues.size();j++) {
				IPossibleValue val = (IPossibleValue)possibleValues.get(j);
				if (strings[i].equals(val.getValue())){
					found = true;
					break;
				}
			}
			if (! found)
				fail(strings[i] + " was not found as a possible value");
		}
	}

}
