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

package org.eclipse.jst.jsf.metadata.tests.annotations;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.contentmodel.annotation.CMAnnotationHelper;
import org.eclipse.jst.jsf.contentmodel.annotation.CMAnnotationPropertyValue;

/**
 * CMAnnotatations Metadata framework has been "taken out of service"
 * Please use org.eclipse.jst.jsf.common.metadata
 * 
 * Make sure that multiple annotations are being dealt with correctly.
 * Uses /testfiles/metadata/FileA.xml, FileB.xml
 * 
 * @author Gerry Kessler - Oracle
 */
@SuppressWarnings("deprecation")
public class DuplicateAnnotationsTestCases extends TestCase {
	private String uri = "http://org.eclipse.jsf/dupeTest";
	private String cmElementName = "Foo";
	private String cmAttributeName = "Value";
	private String meta_prop_name = "Type";
	private String meta_prop_value = "String";

	@SuppressWarnings("unchecked")
	public void testDupeElementAnnotations(){
		Assert.assertEquals(CMAnnotationHelper.getCMElementProperties(uri, cmElementName, meta_prop_name).size(), 2);
		List props = CMAnnotationHelper.getCMElementProperties(uri, cmElementName, meta_prop_name);
		for (int i = 0;i<props.size();i++){
			Assert.assertTrue(props.get(i) instanceof CMAnnotationPropertyValue);
			CMAnnotationPropertyValue prop = (CMAnnotationPropertyValue) props.get(i);
			Assert.assertTrue(prop.getPropertyValues().size() == 1);
			Assert.assertEquals(prop.getPropertyValue(), meta_prop_value + String.valueOf(i+1));
		}
	}
	
	@SuppressWarnings("unchecked")
	public void testDupeAttributeAnnotations(){
		Assert.assertEquals(CMAnnotationHelper.getCMAttributeProperties(uri, cmElementName, cmAttributeName, meta_prop_name).size(), 2);
		List props = CMAnnotationHelper.getCMAttributeProperties(uri, cmElementName, cmAttributeName, meta_prop_name);
		for (int i = 0;i<props.size();i++){
			Assert.assertTrue(props.get(i) instanceof CMAnnotationPropertyValue);
			CMAnnotationPropertyValue prop = (CMAnnotationPropertyValue) props.get(i);
			Assert.assertTrue(prop.getPropertyValues().size() == 1);
			Assert.assertEquals(prop.getPropertyValue(), meta_prop_value + String.valueOf(i+1));
		}
	}
	
}
