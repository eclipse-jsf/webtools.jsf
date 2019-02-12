/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadManagedBeanTestCase_1_2 extends ReadManagedBeanTestCase 
{
    public ReadManagedBeanTestCase_1_2(String name) {
        super(name);
    }

    protected void initialize(TestConfiguration testConfiguration) {
        // override base when not in a configurable test suite
        if(_testConfiguration == null)
        {
            _facesConfigFile = "WEB-INF/faces-config_1_2.xml";
            _facesVersion = IJSFCoreConstants.JSF_VERSION_1_2;
        }
        else
        {
            super.initialize(testConfiguration);
        }
    }

    public void testManagedBeanExtension() {

        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            ManagedBeanType managedBean1 = 
                getManagedBean("managedBean1", edit.getFacesConfig());
            assertNotNull(managedBean1);

            assertEquals(1, managedBean1.getManagedBeanExtension().size());
            ManagedBeanExtensionType managedBeanExtensionType = 
                (ManagedBeanExtensionType) managedBean1.getManagedBeanExtension().get(0);
            assertEquals(1, managedBeanExtensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) managedBeanExtensionType.getChildNodes().get(0);
            assertEquals("managed-bean-extension-tag", element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
