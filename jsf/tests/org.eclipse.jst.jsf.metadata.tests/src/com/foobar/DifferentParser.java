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

package com.foobar;

import junit.framework.Assert;

import org.eclipse.jst.jsf.contentmodel.annotation.internal.CMAnnotationFileParser;

@SuppressWarnings("deprecation")
public class DifferentParser extends CMAnnotationFileParser {
	public DifferentParser(){
		super();
		Assert.assertTrue("Loaded DifferentParser", true);
	}
}
