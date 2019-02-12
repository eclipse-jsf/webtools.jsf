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
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadValidatorTestCase_1_2 extends ReadValidatorTestCase 
{
    public ReadValidatorTestCase_1_2(String name) {
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

    public void testValidatorExtension() {

        FacesConfigArtifactEdit edit = null;
        try 
        {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());

            ValidatorType validator1 = getValidator1(edit.getFacesConfig());
            assertNotNull(validator1);

            assertEquals(1, validator1.getValidatorExtension().size());
            ValidatorExtensionType validatorExtensionType = 
                (ValidatorExtensionType) validator1.getValidatorExtension().get(0);
            assertEquals(1, validatorExtensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) validatorExtensionType.getChildNodes().get(0);
            assertEquals("validator-extension-tag", element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
