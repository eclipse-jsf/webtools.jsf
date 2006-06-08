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

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class. 
 * 
 */
public class MultipleFacesConfigFilesTest extends TestCase {
	public MultipleFacesConfigFilesTest(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
	}
	public static Test suite() {
		return new TestSuite(MultipleFacesConfigFilesTest.class);
	}
	public void testReadFacesconfigFile() {
		IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		String sTestString = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit.getFacesConfig() != null) {
				EList lifecycles = edit.getFacesConfig().getLifecycle();
				for (int i = 0; i < lifecycles.size(); i++) {
					LifecycleType lifecycle = (LifecycleType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getPhaseListener();
					for (int j=0; j<phaseListeners.size(); j++) {
						PhaseListenerType phaseListener = (PhaseListenerType)phaseListeners.get(j);
						sTestString = phaseListener.getTextContent();
						System.out.println("What is read from faces-config1.xml is " + sTestString);
						break;
					}
				}
			}
		} finally {
			//assertTrue(sTestString != null && sTestString.equals("test1"));
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	public void testWriteFacesconfigFile() {
		IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;
		String sTestString = "";
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList lifecycles = edit.getFacesConfig().getLifecycle();
				for (int i = 0; i < lifecycles.size(); i++) {
					LifecycleType lifecycle = (LifecycleType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getPhaseListener();
					for (int j=0; j<phaseListeners.size(); j++) {
						PhaseListenerType phaseListener = (PhaseListenerType)phaseListeners.get(j);
						sTestString = phaseListener.getTextContent();
						break;
					}
				}
			}
		} finally {
			//assertNull(sTestString);
			if (edit != null) {
				edit.dispose();
			}
		}
		sTestString += "newTest2";
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");

			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				LifecycleType newLifecycle = facesConfigFactory.createLifecycleType();
				PhaseListenerType newPhaseListener = facesConfigFactory.createPhaseListenerType();
				newPhaseListener.setTextContent(sTestString);
				newLifecycle.getPhaseListener().add(newPhaseListener);
				edit.getFacesConfig().getLifecycle().add(newLifecycle);
				edit.save(null);
			}
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
		String result = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				EList lifecycles = edit.getFacesConfig().getLifecycle();
				for (int i = 0; i < lifecycles.size(); i++) {
					LifecycleType lifecycle = (LifecycleType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getPhaseListener();
					for (int j=0; j<phaseListeners.size(); j++) {
						PhaseListenerType phaseListener = (PhaseListenerType)phaseListeners.get(j);
						result = phaseListener.getTextContent();
						break;
					}
				}
			}
		} finally {
			assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}