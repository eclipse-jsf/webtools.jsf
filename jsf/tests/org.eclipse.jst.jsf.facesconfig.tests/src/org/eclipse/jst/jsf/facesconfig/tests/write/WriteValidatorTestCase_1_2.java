/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
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

import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteValidatorTestCase_1_2 extends WriteValidatorTestCase {
    private final static String EXTENDED_VALIDATOR_ID = "extended-validator-id";

    private final static String VALIDATOR_EXTENSION = CommonStructuresUtil
            .createPreficedString(VALIDATOR, "extension");
    private final static String VALIDATOR_EXTENSION_ID = CommonStructuresUtil
            .createPreficedString(VALIDATOR_EXTENSION, "id");
    private final static String VALIDATOR_EXTENSION_TAG = CommonStructuresUtil
            .createPreficedString(VALIDATOR_EXTENSION, "tag");

    public WriteValidatorTestCase_1_2(String name) {
        super(name);
    }

    public void testValidatorExtension() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            ValidatorType validator = facesConfigFactory.createValidatorType();
            validator.setId(EXTENDED_VALIDATOR_ID);

            ValidatorExtensionType extensionType = facesConfigFactory
                    .createValidatorExtensionType();
            extensionType.setId(VALIDATOR_EXTENSION_ID);

            extensionType.getChildNodes().add(
                    createDynamicElement(VALIDATOR_EXTENSION_TAG));

            validator.getValidatorExtension().add(extensionType);
            edit.getFacesConfig().getValidator().add(validator);
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

            ValidatorType validator = (ValidatorType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig()
                            .getValidator(), EXTENDED_VALIDATOR_ID);
            assertNotNull(validator);

            assertEquals(1, validator.getValidatorExtension().size());
            ValidatorExtensionType extensionType = (ValidatorExtensionType) validator
                    .getValidatorExtension().get(0);
            assertEquals(VALIDATOR_EXTENSION_ID, extensionType.getId());

            assertEquals(1, extensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) extensionType
                    .getChildNodes().get(0);
            assertEquals(VALIDATOR_EXTENSION_TAG, element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
