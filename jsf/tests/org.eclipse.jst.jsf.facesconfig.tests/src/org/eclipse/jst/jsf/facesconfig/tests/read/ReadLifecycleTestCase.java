/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
/*
 * This Junit class is used to test the lifecycle which is one of 
 * many items inside the root elemnt faces-config in the configuration
 * information hierarchy of the faces-config.xml file 
 *
 */
public class ReadLifecycleTestCase extends BaseReadTestCase {

	public ReadLifecycleTestCase(String name) {
		super(name);
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
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getLifecycle().size());
            assertNotNull(getLifecycleType1(edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    LifecycleType getLifecycleType1(FacesConfigType  facesConfig)
    {
        return (LifecycleType)
            FacesConfigModelUtil
                .findEObjectElementById(facesConfig.getLifecycle(), 
                                        "lifecycle1");
    }
    
	// Test for the Descirption
	public void testPhaseListener() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            LifecycleType lifecycle1 = getLifecycleType1(edit.getFacesConfig());
            assertNotNull(lifecycle1);
            PhaseListenerType phaseListener1 = (PhaseListenerType) FacesConfigModelUtil
                .findEObjectElementById(lifecycle1.getPhaseListener(), "phaseListener1");
            assertNotNull(phaseListener1);
            assertEquals("org.eclipse.wtp.jsf.tests.ValueResourcePhaseListener",
                         phaseListener1.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}