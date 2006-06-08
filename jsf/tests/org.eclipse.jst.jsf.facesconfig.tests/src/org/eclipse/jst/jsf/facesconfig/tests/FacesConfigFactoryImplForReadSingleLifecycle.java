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
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
 * This Junit class is used to test the lifecycle which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class FacesConfigFactoryImplForReadSingleLifecycle extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForReadSingleLifecycle(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	/*
	 * The following method is used to test for the empty navigation rule. Since
	 * I am supplying a single faces-config.xml file as a testing file, I had to
	 * testcases fit in to it by controlling the conditions
	 * 
	 */
	public void testLifecycle() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList lifecycle = edit.getFacesConfig().getLifecycle();
				assertTrue(!lifecycle.isEmpty());
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	// Test for the Descirption
	public void testPhaseListener() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit
					.getFacesConfigArtifactEditForRead(project);
			if (edit.getFacesConfig() != null) {
				EList lifecycle = edit.getFacesConfig().getLifecycle();
				assertTrue(!lifecycle.isEmpty());
				for (int i = 0; i < lifecycle.size(); i++) {
					LifecycleType item = (LifecycleType) lifecycle.get(i);

					EList phaseListener = item.getPhaseListener();

					assertTrue(!phaseListener.isEmpty());
					// sassertEquals(2,navCases.size());
					System.out.println(" size of description is : "
							+ phaseListener.size());
				}
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}