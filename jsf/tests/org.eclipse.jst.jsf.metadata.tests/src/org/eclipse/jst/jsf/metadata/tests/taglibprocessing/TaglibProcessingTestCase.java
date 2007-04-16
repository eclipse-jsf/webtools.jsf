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

import junit.framework.TestCase;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.metadata.tests.AbstractBaseMetaDataTestCase;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.ICreateValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public abstract class TaglibProcessingTestCase extends AbstractBaseMetaDataTestCase {
//	protected IStructuredDocumentContext docContext;
	protected String uri = "http://org.eclipse.jsf/tagprocessing";
	protected String bundle = "org.eclipse.jst.jsf.core";
	protected String barkerBundle = MetadataTestsPlugin.ID_BUNDLE;
	protected String tag = "MyTag";
	protected String attributeName;
	
	protected List possibleValueAdapters;
	protected List validValuesAdapters;
	protected List defaultValueAdapters;
	protected List createValuesAdapters;
	
	public void setUp() throws Exception{
		super.setUp();
		
		// TODO: This won't work currently docContext = getTestDocContext();
		possibleValueAdapters = getProcessorAdapters(IPossibleValues.class);
		validValuesAdapters = getProcessorAdapters(IValidValues.class);
		defaultValueAdapters = getProcessorAdapters(IDefaultValue.class);
		createValuesAdapters = getProcessorAdapters(ICreateValues.class);
	}
	
//	private IStructuredDocumentContext getTestDocContext() {
//		//IJavaProject jproj = getProject();
//		IDocument doc = null;//figure it out
//		return IStructuredDocumentContextFactory.INSTANCE.getContext(doc, 0);
//	}
//
//	private IJavaProject getProject() {
//		if (ResourcesPlugin.getWorkspace().getRoot().getProject("testProject")==null){
//			//do we import??? or create new???
//			Impor
//			IJavaProject jp = new JavaProject(
//		}
//		return JavaCore.create(ResourcesPlugin.getWorkspace().getRoot().getProject("testProject"));
//		return null;
//	}

	private String getAttributeNameFromTest(){
		if (attributeName == null){
			String test = this.getClass().getName();
			test = test.substring(test.lastIndexOf(".") + 1);
			test = test.substring(0,test.length() - 4);
			return test;
		}
        return attributeName;
	}
	
	protected List getProcessorAdapters(Class featureClass) {
		return MetaDataEnabledProcessingFactory.getInstance().
			getAttributeValueRuntimeTypeFeatureProcessors(featureClass, docContext, 
					uri, tag , getAttributeNameFromTest());
	}

//	private IMetaDataEnabledFeature getProcessorForBundle(List processors, String bundleID){
//		IMetaDataEnabledFeature ret = null;
//		Iterator it = processors.iterator();
//		while(it.hasNext()){
//			IMetaDataEnabledFeature feature = (IMetaDataEnabledFeature)it.next();
//			if (feature.getBundleID().equals(bundleID)){
//				ret = feature;
//				break;
//			}
//		}
//		return ret;
//	}
//	private ITypeDescriptor getType(String typeId){
//		return AttributeValueRuntimeTypeFactory.getInstance().getType(typeId);
//	}
	
//	protected IMetaDataEnabledFeature getBarkProcessingBundle(List processors) {
//		return getProcessorForBundle(processors, barkerBundle);		
//	}
//	
//	protected IMetaDataEnabledFeature getProcessorForTaglibProcessingBundle(List processors) {
//		return getProcessorForBundle(processors, bundle);
//	}
}
