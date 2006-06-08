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
import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class FacesConfigFactoryImplForWriteLifecycle extends TestCase {
	IProject project = null;

	public FacesConfigFactoryImplForWriteLifecycle(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject();
		project = WizardUtil.getTestProject();
	}

	public void testWriteLifecycleToFileTwo() {
		// IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit = null;

		String phaseListener = "test2";

		// Local-Config is REMAINING HERE TO BE ADDED LATER ON.

		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			if (edit.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();
				LifecycleType newLifecycle = facesConfigFactory
						.createLifecycleType();
				PhaseListenerType newPhaseListener = facesConfigFactory
						.createPhaseListenerType();
				newPhaseListener.setTextContent(phaseListener);
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
				EList lifecycles = edit.getFacesConfig().getFactory();
				for (int i = 0; i < lifecycles.size(); i++) {
					FactoryType lifecycle = (FactoryType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getApplicationFactory();
					for (int j = 0; j < phaseListeners.size(); j++) {
						ApplicationFactoryType phaseLis = (ApplicationFactoryType) phaseListeners
								.get(j);
						result = phaseLis.getTextContent();
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testWriteLifecycleToFileOne() {
		// IProject project = WizardUtil.getTestProject();
		FacesConfigArtifactEdit edit1 = null;

		String phaseListener = "test1";

		// Local-Config is REMAINING HERE TO BE ADDED LATER ON.

		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
				FacesConfigFactory facesConfigFactory = facesConfigPackage
						.getFacesConfigFactory();
				LifecycleType newLifecycle = facesConfigFactory
						.createLifecycleType();
				PhaseListenerType newPhaseListener = facesConfigFactory
						.createPhaseListenerType();
				newPhaseListener.setTextContent(phaseListener);
				newLifecycle.getPhaseListener().add(newPhaseListener);
				edit1.getFacesConfig().getLifecycle().add(newLifecycle);
				edit1.save(null);
			}
		} finally {
			if (edit1 != null) {
				edit1.dispose();
			}
		}
		String result = null;
		try {
			edit1 = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, "WEB-INF/faces-config1.xml");
			if (edit1.getFacesConfig() != null) {
				EList lifecycles = edit1.getFacesConfig().getFactory();
				for (int i = 0; i < lifecycles.size(); i++) {
					FactoryType lifecycle = (FactoryType) lifecycles.get(i);
					EList phaseListeners = lifecycle.getApplicationFactory();
					for (int j = 0; j < phaseListeners.size(); j++) {
						ApplicationFactoryType phaseLis = (ApplicationFactoryType) phaseListeners
								.get(j);
						result = phaseLis.getTextContent();
						break;
					}
				}
			}
		} finally {
			// assertTrue(result != null && result.equals(sTestString));
			if (edit1 != null) {
				edit1.dispose();
			}
		}
	}
}