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
import org.eclipse.jst.jsf.facesconfig.emf.IfType;
import org.eclipse.jst.jsf.facesconfig.emf.NameType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.RedirectType;
import org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType;
import org.eclipse.jst.jsf.facesconfig.emf.ValueType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/**
 * @author cbateman
 *
 */
public class WriteNavigationRuleTestCase_2_0 extends
        WriteNavigationRuleTestCase_1_2 {
    private final static String NAVIGATION_RULE_ID = "navigation-rule-id-20";
    private final static String NAVIGATION_CASE_ID = "navigation-case-id-20";
    private final static String IFEXPRESSION = "if-expr";
    private final static String NAME = "name";
    private final static String VALUE = "value";

    public WriteNavigationRuleTestCase_2_0(String name) {
        super(name);
    }

    public void testNavigationRule20() {
        FacesConfigArtifactEdit edit = null;

        try {
            edit = getArtifactEditForWrite();
            assertNotNull(edit.getFacesConfig());
            FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
            FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

            NavigationRuleType navigationRule = facesConfigFactory.createNavigationRuleType();
            navigationRule.setId(NAVIGATION_RULE_ID);
            
            NavigationCaseType navigationCase = facesConfigFactory.createNavigationCaseType();
            navigationCase.setId(NAVIGATION_CASE_ID);
            navigationRule.getNavigationCase().add(navigationCase);
            
            IfType ifexpr = facesConfigFactory.createIfType();
            ifexpr.setTextContent(IFEXPRESSION);
            navigationCase.setIf(ifexpr);

            RedirectType redirect = facesConfigFactory.createRedirectType();
            redirect.setIncludeViewParams(true);
            navigationCase.setRedirect(redirect);
            
            RedirectViewParamType viewParam = facesConfigFactory.createRedirectViewParamType();
            redirect.getViewParam().add(viewParam);
            
            NameType name = facesConfigFactory.createNameType();
            name.setTextContent(NAME);
            viewParam.setName(name);
            ValueType value = facesConfigFactory.createValueType();
            value.setTextContent(VALUE);
            viewParam.setValue(value);
            
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
                            .getNavigationRule(), NAVIGATION_RULE_ID);
            assertNotNull(navigationRule);
            
            NavigationCaseType navigationCase = (NavigationCaseType) FacesConfigModelUtil
            		.findEObjectElementById(navigationRule.getNavigationCase(), NAVIGATION_CASE_ID);
            assertNotNull(navigationCase);
    
            IfType ifexpr = navigationCase.getIf();
            assertNotNull(ifexpr);
            assertEquals(IFEXPRESSION, ifexpr.getTextContent().trim());
            
            RedirectType redirect = navigationCase.getRedirect();
            assertNotNull(redirect);
            assertEquals(true, redirect.isIncludeViewParams());
            
            assertEquals(1, redirect.getViewParam().size());
            RedirectViewParamType viewParam = (RedirectViewParamType)redirect.getViewParam().get(0);
            assertEquals(NAME, viewParam.getName().getTextContent().trim());
            assertEquals(VALUE, viewParam.getValue().getTextContent().trim());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}
