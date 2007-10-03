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

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.contentmodel.annotation.CMAnnotationHelper;

/**
 * CMAnnotatations Metadata framework has been "taken out of service"
 * Please use org.eclipse.jst.jsf.common.metadata
 * 
 * 
 * Intended to test system level setup problems
 * 
 * @author Gerry Kessler - Oracle
 */
@SuppressWarnings("deprecation")
public class NegativeAnnotationFileTestCases extends TestCase {
	private String missing_uri = "http://org.eclipse.jsf/missing";
	private String missing_file_uri = "http://org.eclipse.jsf/missingFile";
	private String good_uri = "http://org.eclipse.jsf/test";
	
	private String bad_bundle ="foo";
	private String cmElementName = "FoO";
	private String meta_prop_name = "TypE";

	public void testMissingAnnotationFile(){
		//missing uri
		Assert.assertTrue(CMAnnotationHelper.getCMElementProperties(missing_uri, cmElementName, meta_prop_name).isEmpty());
		//missing annotation file for uri - error should be logged
		Assert.assertTrue(CMAnnotationHelper.getCMElementProperties(missing_file_uri, cmElementName, meta_prop_name).isEmpty());
	
	}
	
	public void testBadPassedBundle(){
		Assert.assertTrue( CMAnnotationHelper.getCMElementProperties(bad_bundle, good_uri, cmElementName, meta_prop_name).isEmpty());
	}
}
