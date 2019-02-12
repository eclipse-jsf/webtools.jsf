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

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.RedirectType;
import org.eclipse.jst.jsf.facesconfig.emf.RedirectViewParamType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadNavigationRuleTestCase_2_0 extends ReadNavigationRuleTestCase_1_2
{
    public ReadNavigationRuleTestCase_2_0(String name) {
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

    public void testIf()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit);
            EList navRules = edit.getFacesConfig().getNavigationRule();
            assertTrue(!navRules.isEmpty());
            NavigationRuleType  navRule = 
                FacesConfigModelUtil.
                    findNavigationHandlerRuleByDisplayName(navRules, "display name 1");
            assertNotNull(navRule);
            
            NavigationCaseType navCase = FacesConfigModelUtil.
            findNavigationCaseByDisplayName(navRule.getNavigationCase(),
                                                "what display name");
            assertNotNull(navCase);
            assertEquals("#{el-expression}", navCase.getIf().getTextContent());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

    public void testRedirect()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit);
            EList navRules = edit.getFacesConfig().getNavigationRule();
            assertTrue(!navRules.isEmpty());
            NavigationRuleType  navRule = 
                FacesConfigModelUtil.
                    findNavigationHandlerRuleByDisplayName(navRules, "display name 1");
            assertNotNull(navRule);
            
            NavigationCaseType navCase = FacesConfigModelUtil.
            findNavigationCaseByDisplayName(navRule.getNavigationCase(),
                                                "what display name");
            assertNotNull(navCase);

            RedirectType redirect = navCase.getRedirect();
            assertNotNull(redirect);
            assertEquals(true, redirect.isIncludeViewParams());
            redirect.getViewParam();
            RedirectViewParamType viewParam = 
                (RedirectViewParamType) 
                FacesConfigModelUtil.findEObjectElementById
                    (redirect.getViewParam(), "view-param-id");
            assertNotNull(viewParam);
            assertEquals("viewParamName", viewParam.getName().getTextContent().trim());     
            assertEquals("viewParamValue", viewParam.getValue().getTextContent().trim());     
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}
