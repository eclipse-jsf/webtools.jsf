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
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleType;
import org.eclipse.jst.jsf.facesconfig.emf.PhaseListenerType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteLifecycleTestCase extends BaseWriteTestCase 
{
    protected final static String LIFECYCLE = "lifecycle";
    private final static String PHASELISTENER = "phase-listener";
    private final static String LIFECYCLE_ID =
        CommonStructuresUtil.createPreficedString(LIFECYCLE, CommonStructuresUtil.ID);

    public WriteLifecycleTestCase(String name) {
        super(name);
    }

    public void testWriteLifecycle() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
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

            newLifecycle.setId(LIFECYCLE_ID);

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
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            LifecycleType newLifecycle = 
                (LifecycleType) FacesConfigModelUtil
                    .findEObjectElementById(
                            edit.getFacesConfig().getLifecycle(), LIFECYCLE_ID);      
            assertNotNull(newLifecycle);
            
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