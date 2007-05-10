/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.annotations;

import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.contentmodel.annotation.CMAnnotationHelper;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;

public class DisableCMAnnotationFilesAPITest extends TestCase implements ILogListener {
	private boolean errorLogged;
	private ILog log;
	
	public void setUp() throws Exception{
		super.setUp();
		
		log = Platform.getLog(Platform.getBundle("org.eclipse.jst.jsf.common"));
		log.addLogListener(this);
	}
	
	public void tearDown() throws Exception{
		log.removeLogListener(this);
		super.tearDown();
	}
	
	public void testDisablement(){
		String uri = "http://java.sun.com/jsf/html";
		String cmElementName = "inputText";
		String cmAttrName = "type";
		String meta_prop_name = MetaDataEnabledProcessingFactory.ATTRIBUTE_VALUE_RUNTIME_TYPE_PROP_NAME;
		
		List props = CMAnnotationHelper.getCMAttributeProperties(uri, cmElementName, cmAttrName, meta_prop_name);
		assertEquals(Collections.EMPTY_LIST, props);
		assertTrue(errorLogged);
		
	}

	public void logging(IStatus status, String plugin) {
		String ERRMSG = "Attempted metadata access using CMAnnotationFiles for uri:";		
		if (status.getMessage().substring(0, ERRMSG.length()).equals(ERRMSG))
			errorLogged = true;
		
	}
}
