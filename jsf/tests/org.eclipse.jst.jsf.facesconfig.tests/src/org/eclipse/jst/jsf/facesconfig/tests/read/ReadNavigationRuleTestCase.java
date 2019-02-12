/***************************************************************************************************
 * Copyright (c) 2005, 2014 IBM Corporation and others. 
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
package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

/*
 * This Junit class is used to test the FacesConfigFactoryImpl
 * class. 
 *
 */


public class ReadNavigationRuleTestCase extends BaseReadTestCase {

	public ReadNavigationRuleTestCase(String name) {
		super(name);
	}

	/*
	 * The following method is used to test for the existence of at least
	 * one navigation-rule
	 */
	public void testSingleNavigationRule() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
			EList navRules = edit.getFacesConfig().getNavigationRule();
			assertTrue(!navRules.isEmpty());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    /**
     * Verify that display is populated
     */
    public void testDisplayName()
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
            
            // verify that xml:lang's are being populated
            navRule = FacesConfigModelUtil.
                        findNavigationHandlerRuleByDescriptionText(navRules, 
                            "A navigation rule with lang attributes");
            assertNotNull(navRule);
           
            DisplayNameType displayNameType = FacesConfigModelUtil.
                        findDisplayNameType(navRule.getDisplayName(), "English Display Name");
            assertNotNull(displayNameType);
            assertEquals("en", displayNameType.getLang());
            
            displayNameType = FacesConfigModelUtil.
                findDisplayNameType(navRule.getDisplayName(), "Nom D'Affichage De Francais");
            assertNotNull(displayNameType);
            assertEquals("fr", displayNameType.getLang());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
    
	// Test for the Descirption

	public void testNonEmptyDescription() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
            assertNotNull(edit);
			EList navRules = edit.getFacesConfig().getNavigationRule();
			assertTrue(!navRules.isEmpty());
			NavigationRuleType navRule =
                    FacesConfigModelUtil.
                        findNavigationHandlerRuleByDescriptionText(navRules, 
                                "Descri 1");
            assertNotNull(navRule);
            
            // verify that xml:lang's are being populated
            navRule = FacesConfigModelUtil.
                        findNavigationHandlerRuleByDescriptionText(navRules, 
                            "A navigation rule with lang attributes");
            assertNotNull(navRule);
            
            DescriptionType descType = FacesConfigModelUtil.
                        findDescriptionType(navRule.getDescription(), "A navigation rule with lang attributes");
            assertNotNull(descType);
            assertEquals("en", descType.getLang());
            
            descType = FacesConfigModelUtil.
            findDescriptionType(navRule.getDescription(), "What color is it?");
            assertNotNull(descType);
            assertEquals("en-US", descType.getLang());
            
            descType = FacesConfigModelUtil.
            findDescriptionType(navRule.getDescription(), "What colour is it?");
            assertNotNull(descType);
            assertEquals("en-GB", descType.getLang());

            descType = FacesConfigModelUtil.
            findDescriptionType(navRule.getDescription(), "Quelle couleur est lui?");
            assertNotNull(descType);
            assertEquals("fr", descType.getLang());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	/*
	 * Test for the exisence of navigation-cases
	 */

	public void testNonEmptyNavigationCases() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			EList navRules = edit.getFacesConfig().getNavigationRule();
			assertTrue(!navRules.isEmpty());
            
            // test first navigation rule
            NavigationRuleType navRule = 
                FacesConfigModelUtil
                 .findNavigationHandlerRuleByDisplayName
                     (navRules, "display name 1");
            assertNotNull(navRule);
            
            assertEquals(2, navRule.getNavigationCase().size());
            NavigationCaseType navCase = FacesConfigModelUtil.
                findNavigationCaseByDisplayName(navRule.getNavigationCase(),
                                                    "what display name");
            assertNotNull(navCase);
            
            assertNotNull(navCase.getFromAction());
            assertEquals("#{from-action}", navCase.getFromAction().getTextContent());
            
            assertNotNull(navCase.getFromOutcome());
            assertEquals("drilldown", navCase.getFromOutcome().getTextContent());
            
            assertNotNull(navCase.getToViewId());
            assertEquals("/edit.jsp", navCase.getToViewId().getTextContent());
            
            assertNotNull(navCase.getRedirect());
            
            
            navCase = FacesConfigModelUtil.
                findNavigationCaseByDisplayName(navRule.getNavigationCase(),
                                                "second navigation case");
            assertNotNull(navCase);
            
            
            assertNotNull(navCase.getFromAction());
            assertEquals("#{action2}", navCase.getFromAction().getTextContent());
            
            assertNotNull(navCase.getFromOutcome());
            assertEquals("outcome2", navCase.getFromOutcome().getTextContent());
            
            assertNotNull(navCase.getToViewId());
            assertEquals("/edit2.jsp", navCase.getToViewId().getTextContent());
            
            assertNull(navCase.getRedirect());

            // test second navigation rule
            navRule = 
                FacesConfigModelUtil
                 .findNavigationHandlerRuleByDisplayName
                     (navRules, "English Display Name");
            assertNotNull(navRule);
            
            navCase = FacesConfigModelUtil.
                findNavigationCaseByDisplayName(navRule.getNavigationCase(), 
                        "second navigation rule navcase");
            assertNotNull(navCase);
            
            assertNotNull(navCase.getFromAction());
            assertEquals("#{action3}", navCase.getFromAction().getTextContent());
            
            assertNotNull(navCase.getFromOutcome());
            assertEquals("outcome3", navCase.getFromOutcome().getTextContent());
            
            assertNotNull(navCase.getToViewId());
            assertEquals("/edit3.jsp", navCase.getToViewId().getTextContent());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	/*
	 * assert the existence of icon 
	 */

	public void testNonEmptyIcon() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			EList navRules = edit.getFacesConfig().getNavigationRule();
			assertTrue(!navRules.isEmpty());
			NavigationRuleType navRule = 
                FacesConfigModelUtil
                 .findNavigationHandlerRuleByDisplayName
                     (navRules, "display name 1");

            assertNotNull(navRule);
			EList icons = navRule.getIcon();
			assertEquals(1, icons.size());
            IconType iconType = (IconType) icons.get(0);
            assertNotNull(iconType.getSmallIcon());
            assertEquals("myicon", iconType.getSmallIcon().getTextContent());
            assertNull(iconType.getLargeIcon());
            
            navRule = 
                FacesConfigModelUtil
                 .findNavigationHandlerRuleByDisplayName
                     (navRules, "English Display Name");
            assertNotNull(navRule);
            
            icons = navRule.getIcon();
            assertEquals(2, icons.size());
            
            IconType icon = 
                FacesConfigModelUtil.
                  findIconTypeByLang(icons, null);
            assertNotNull(icon);
            assertNotNull(icon.getSmallIcon());
            assertEquals("small-icon", icon.getSmallIcon().getTextContent());
            assertNotNull(icon.getLargeIcon());
            assertEquals("large-icon", icon.getLargeIcon().getTextContent());
            
            icon = FacesConfigModelUtil.
                findIconTypeByLang(icons, "en");
            assertNotNull(icon);
            assertNotNull(icon.getSmallIcon());
            assertEquals("en_small-icon", icon.getSmallIcon().getTextContent());
            assertNotNull(icon.getLargeIcon());
            assertEquals("en_large-icon", icon.getLargeIcon().getTextContent());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
    
    public void testFromViewIdType()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            EList navRules = edit.getFacesConfig().getNavigationRule();
            assertTrue(!navRules.isEmpty());
            NavigationRuleType navRule = 
                FacesConfigModelUtil
                 .findNavigationHandlerRuleByDisplayName
                     (navRules, "display name 1");
            assertNotNull(navRule);
            assertEquals("/Page1.jsp",navRule.getFromViewId().getTextContent());
            
            navRule = 
                FacesConfigModelUtil
                 .findNavigationHandlerRuleByDisplayName
                     (navRules, "English Display Name");
            assertNotNull(navRule);
            assertEquals("/Page1.jsp",navRule.getFromViewId().getTextContent());
        }
        finally 
        {
            if (edit != null) {
                edit.dispose();
            }
        }
    }
}