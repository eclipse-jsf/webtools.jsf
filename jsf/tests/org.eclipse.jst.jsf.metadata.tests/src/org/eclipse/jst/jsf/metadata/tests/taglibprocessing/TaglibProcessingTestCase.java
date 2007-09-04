/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import java.util.List;

import org.eclipse.jst.jsf.common.metadata.tests.AbstractBaseMetaDataTestCase;
import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.ICreateValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public abstract class TaglibProcessingTestCase extends AbstractBaseMetaDataTestCase {
//	protected IStructuredDocumentContext docContext;
	protected String uri = "http://org.eclipse.jsf/tagprocessing";
	protected String bundle = "org.eclipse.jst.jsf.core";
	protected String barkerBundle = MetadataTestsPlugin.ID_BUNDLE;
	protected String tag = "MyTag";
	protected String attributeName;
	
	protected List<?> possibleValueAdapters;
	protected List<?> validValuesAdapters;
	protected List<?> defaultValueAdapters;
	protected List<?> createValuesAdapters;
	protected List<?> validELValuesAdapters;
	
	public void setUp() throws Exception{
		super.setUp();
		
		possibleValueAdapters = getProcessorAdapters(IPossibleValues.class);
		validValuesAdapters = getProcessorAdapters(IValidValues.class);
		defaultValueAdapters = getProcessorAdapters(IDefaultValue.class);
		createValuesAdapters = getProcessorAdapters(ICreateValues.class);
		validELValuesAdapters = getProcessorAdapters(IValidELValues.class);
	}

	private String getAttributeNameFromTest(){
		if (attributeName == null){
			attributeName = this.getClass().getName();
			attributeName = attributeName.substring(attributeName.lastIndexOf(".") + 1);
			attributeName = attributeName.substring(0,attributeName.length() - 4);			
		}
        return attributeName;
	}
	
	protected List<?> getProcessorAdapters(Class<?> featureClass) {
		return MetaDataEnabledProcessingFactory.getInstance().
			getAttributeValueRuntimeTypeFeatureProcessors(featureClass, docContext, 
					uri, tag , getAttributeNameFromTest());
	}

}
