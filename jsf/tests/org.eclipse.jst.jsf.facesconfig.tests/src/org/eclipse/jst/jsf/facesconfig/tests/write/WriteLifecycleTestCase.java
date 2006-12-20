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
package org.eclipse.jst.jsf.facesconfig.tests.write;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteLifecycleTestCase extends TestCase {
    private static final String WEB_INF_FACES_CONFIG2_XML = "WEB-INF/faces-config2.xml";
    IProject project = null;

    private final static String LIFECYCLE = "lifecycle";
    private final static String PHASELISTENER = "phase-listener";

    public WriteLifecycleTestCase(String name) {
        super(name);
    }

    protected void setUp() throws Exception {
        super.setUp();
        WizardUtil.createProject(getName());
        project = WizardUtil.getTestProject(getName());
    }

    public void testWriteLifecycle() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
                    project, WEB_INF_FACES_CONFIG2_XML);
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            LifecycleType newLifecycle = facesConfigFactory
                    .createLifecycleType();
            PhaseListenerType newPhaseListener = facesConfigFactory
                    .createPhaseListenerType();
            newPhaseListener.setTextContent(PHASELISTENER);
            newPhaseListener.setId(CommonStructuresUtil.createPreficedString(
                    PHASELISTENER, CommonStructuresUtil.ID));
            newLifecycle.getPhaseListener().add(newPhaseListener);

            newLifecycle.setId(CommonStructuresUtil.createPreficedString(
                    LIFECYCLE, CommonStructuresUtil.ID));

            edit.getFacesConfig().getLifecycle().add(newLifecycle);
            edit.save(null);
        } finally {
            if (edit != null) {
                edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
            }
        }

        try {
            edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
                    project, WEB_INF_FACES_CONFIG2_XML);
            assertNotNull(edit.getFacesConfig());

            assertEquals(1, edit.getFacesConfig().getLifecycle().size());
            LifecycleType newLifecycle = 
                (LifecycleType) edit.getFacesConfig().getLifecycle().get(0);
            
            assertEquals(1, newLifecycle.getPhaseListener().size());
            PhaseListenerType newPhaseListener = 
                (PhaseListenerType) newLifecycle.getPhaseListener().get(0);

            assertEquals(PHASELISTENER, newPhaseListener.getTextContent());
            assertEquals(CommonStructuresUtil.createPreficedString(
                            PHASELISTENER, CommonStructuresUtil.ID)
                         , newPhaseListener.getId());

            assertEquals(CommonStructuresUtil.createPreficedString(
                                LIFECYCLE, CommonStructuresUtil.ID)
                         , newLifecycle.getId());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}