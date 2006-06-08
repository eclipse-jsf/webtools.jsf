/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
* This Junit class is used to test the existence of a navigation rule
* 
*/
public class FacesConfigFactoryImplForReadEmptyNavigationRule extends TestCase
{
	IProject project = null;
	
	public FacesConfigFactoryImplForReadEmptyNavigationRule(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}
/*
* The following method is used to test for the empty 
* navigation rule. Since I am supplying a single
* faces-config.xml file as a testing file,
*/	
		public void testEmptyNavigationRule(){
			FacesConfigArtifactEdit edit = null;
			try {
				edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(project);
				if (edit.getFacesConfig() != null) {
					EList navRules = edit.getFacesConfig().getNavigationRule();
					//assert that it is not empty
					assertTrue(!navRules.isEmpty());
					}
			} finally {
				if (edit != null) {
					edit.dispose();
				}
			}	
		}
}