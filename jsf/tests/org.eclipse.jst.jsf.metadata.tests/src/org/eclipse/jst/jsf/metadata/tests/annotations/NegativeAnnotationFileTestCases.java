package org.eclipse.jst.jsf.metadata.tests.annotations;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.wtp.jsf.contentmodel.annotation.internal.provisional.CMAnnotationHelper;

/**
 * Intended to test system level setup problems
 * 
 * @author Gerry Kessler - Oracle
 */
public class NegativeAnnotationFileTestCases extends TestCase {
	private String missing_uri = "http://org.eclipse.jsf/missing";
	private String missing_file_uri = "http://org.eclipse.jsf/missingFile";
	private String good_uri = "http://org.eclipse.jsf/test";
	
	private String bad_bundle ="foo";
	private String cmElementName = "FoO";
	private String meta_prop_name = "TypE";

	public void testMissingAnnotationFile(){
		//missing uri
		Assert.assertNull(CMAnnotationHelper.getCMElementProperties(missing_uri, cmElementName, meta_prop_name));
		//missing annotation file for uri - error should be logged
		Assert.assertTrue(CMAnnotationHelper.getCMElementProperties(missing_file_uri, cmElementName, meta_prop_name).isEmpty());
	
	}
	
	public void testBadPassedBundle(){
		Assert.assertTrue( CMAnnotationHelper.getCMElementProperties(bad_bundle, good_uri, cmElementName, meta_prop_name).isEmpty());
	}
}
