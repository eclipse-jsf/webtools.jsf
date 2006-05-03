package com.foobar;

import junit.framework.Assert;

import org.eclipse.wtp.jsf.contentmodel.annotation.internal.CMAnnotationFileParser;

public class DifferentParser extends CMAnnotationFileParser {
	public DifferentParser(){
		super();
		Assert.assertTrue("Loaded DifferentParser", true);
	}
}
