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
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadManagedBeanTestCase_2_0 extends ReadManagedBeanTestCase_1_2 
{
    public ReadManagedBeanTestCase_2_0(String name) {
        super(name);
    }

    protected void initialize(TestConfiguration testConfiguration) {
        // override base when not in a configurable test suite
        if(_testConfiguration == null)
        {
            _facesConfigFile = "WEB-INF/faces-config_2_0.xml";
            _facesVersion = IJSFCoreConstants.JSF_VERSION_2_0;
        }
        else
        {
            super.initialize(testConfiguration);
        }
    }

	public void testEagerAttribute() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
            final ManagedBeanType managedBean1 = 
                getManagedBean("managedBean1", edit.getFacesConfig());
            
            assertNotNull(managedBean1);
            assertEquals(true, managedBean1.isEager());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}
