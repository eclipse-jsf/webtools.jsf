/*******************************************************************************
 * Copyright (c) 2001, 2006 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleExtensionType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/**
 * @author cbateman
 *
 */
public class WriteNavigationRuleTestCase_1_2 extends
        WriteNavigationRuleTestCase {
    private final static String EXTENDED_NAVIGATION_RULE_ID = "extended-navigation-rule-id";

    private final static String NAVIGATION_RULE_EXTENSION = CommonStructuresUtil
            .createPreficedString(NAVIGATION_RULE, "extension");
    private final static String NAVIGATION_RULE_EXTENSION_ID = CommonStructuresUtil
            .createPreficedString(NAVIGATION_RULE_EXTENSION, "id");
    private final static String NAVIGATION_RULE_EXTENSION_TAG = CommonStructuresUtil
            .createPreficedString(NAVIGATION_RULE_EXTENSION, "tag");

    public WriteNavigationRuleTestCase_1_2(String name) {
        super(name);
    }

    public void testNavigationRuleExtension() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage
                    .getFacesConfigFactory();

            NavigationRuleType navigationRule = facesConfigFactory.createNavigationRuleType();
            navigationRule.setId(EXTENDED_NAVIGATION_RULE_ID);

            NavigationRuleExtensionType extensionType = facesConfigFactory
                    .createNavigationRuleExtensionType();
            extensionType.setId(NAVIGATION_RULE_EXTENSION_ID);

            extensionType.getChildNodes().add(
                    createDynamicElement(NAVIGATION_RULE_EXTENSION_TAG));

            navigationRule.getNavigationRuleExtension().add(extensionType);
            edit.getFacesConfig().getNavigationRule().add(navigationRule);
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

            NavigationRuleType navigationRule = (NavigationRuleType) FacesConfigModelUtil
                    .findEObjectElementById(edit.getFacesConfig()
                            .getNavigationRule(), EXTENDED_NAVIGATION_RULE_ID);
            assertNotNull(navigationRule);

            assertEquals(1, navigationRule.getNavigationRuleExtension().size());
            NavigationRuleExtensionType extensionType = (NavigationRuleExtensionType) navigationRule
                    .getNavigationRuleExtension().get(0);
            assertEquals(NAVIGATION_RULE_EXTENSION_ID, extensionType.getId());

            assertEquals(1, extensionType.getChildNodes().size());
            DynamicElement element = (DynamicElement) extensionType
                    .getChildNodes().get(0);
            assertEquals(NAVIGATION_RULE_EXTENSION_TAG, element.getName());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
