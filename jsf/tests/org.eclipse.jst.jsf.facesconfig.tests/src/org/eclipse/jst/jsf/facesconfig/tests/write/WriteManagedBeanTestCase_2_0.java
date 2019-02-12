/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteManagedBeanTestCase_2_0 extends WriteManagedBeanTestCase_1_2 {
    private final static String EXTENDED_MANAGED_BEAN_ID20 = "extended-managed-bean-id-20";

    public WriteManagedBeanTestCase_2_0(String name) {
        super(name);
    }

    public void testManagedBeanExtension() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

            ManagedBeanType managedBean = facesConfigFactory.createManagedBeanType();
            managedBean.setId(EXTENDED_MANAGED_BEAN_ID20);
            managedBean.setEager(true);

            edit.getFacesConfig().getManagedBean().add(managedBean);
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

            ManagedBeanType managedBean = (ManagedBeanType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig()
                            .getManagedBean(), EXTENDED_MANAGED_BEAN_ID20);
            assertNotNull(managedBean);
            assertEquals(true, managedBean.isEager());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
