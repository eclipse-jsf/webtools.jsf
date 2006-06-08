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
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

//DefaultRenderKitIdType


public class FacesConfigFactoryImplForWriteLifecycleTwoFiles extends TestCase {
	IProject project = null;
	FacesConfigArtifactEdit edit1 = null;
	FacesConfigArtifactEdit edit2 = null;	

	String phaseListener1 = "test1";
	String phaseListener2 = "test2";
	
	public FacesConfigFactoryImplForWriteLifecycleTwoFiles(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}
	
	public void testWriteToFileTwo() {
		//IProject project = WizardUtil.getTestProject();
	
		//Local-Config is REMAINING HERE TO BE ADDED LATER ON.
		
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				LifecycleType newLifecycle = facesConfigFactory.createLifecycleType();
				PhaseListenerType newPhaseListener = facesConfigFactory.createPhaseListenerType();
				newPhaseListener.setTextContent(phaseListener2);
				newLifecycle.getPhaseListener().add(newPhaseListener);
				edit2.getFacesConfig().getLifecycle().add(newLifecycle);
				edit2.save(null);
			}
		} finally {
			if (edit2 != null) {
				edit2.dispose();
			}
		}
		String result = null;
		try {
			edit2 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config2.xml");
			if (edit2.getFacesConfig() != null) {
				EList lifecycles = edit2.getFacesConfig().getLifecycle();
				for (int i = 0; i < lifecycles.size(); i++) {
					LifecycleType lifecycle = (LifecycleType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getPhaseListener();
					for (int j=0; j<phaseListeners.size(); j++) {
						PhaseListenerType phaseLis = (PhaseListenerType)phaseListeners.get(j);
						result = phaseLis.getTextContent();
						System.out.println("The PhaseListener in lifecycle is (for file two) " + result);
						assertEquals(phaseListener2,result);
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit2 != null) {
				edit2.dispose();
			}
		}
	}
	public void testWriteToFileOne() {
		//IProject project = WizardUtil.getTestProject();
	
		//Local-Config is REMAINING HERE TO BE ADDED LATER ON.
		
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();
				LifecycleType newLifecycle = facesConfigFactory.createLifecycleType();
				PhaseListenerType newPhaseListener = facesConfigFactory.createPhaseListenerType();
				newPhaseListener.setTextContent(phaseListener1);
				newLifecycle.getPhaseListener().add(newPhaseListener);
				edit1.getFacesConfig().getLifecycle().add(newLifecycle);
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		//String result_one = null;
		String result = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList lifecycles = edit1.getFacesConfig().getLifecycle();
				for (int i = 0; i < lifecycles.size(); i++) {
					LifecycleType lifecycle = (LifecycleType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getPhaseListener();
					for (int j=0; j<phaseListeners.size(); j++) {
						PhaseListenerType phaseLis = (PhaseListenerType)phaseListeners.get(j);
						result = phaseLis.getTextContent();
						System.out.println("The PhaseListener in lifecycle is (for file two) " + result);
						assertEquals(phaseListener1,result);
						break;
					}
				}
			}
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
	}
}