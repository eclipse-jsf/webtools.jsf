/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.tests.write;

import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FromActionType;
import org.eclipse.jst.jsf.facesconfig.emf.FromOutcomeType;
import org.eclipse.jst.jsf.facesconfig.emf.FromViewIdType;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


public class WriteNavigationRuleTestCase extends TestCase {
	private static final String NAVIGATION_RULE = "navigation-rule";
    private static final String FROM_VIEW_ID = "from-view-id";
    private static final String FROM_VIEW_ID_ID = 
        CommonStructuresUtil.createPreficedString(FROM_VIEW_ID, CommonStructuresUtil.ID);

    private static final String NAVIGATION_CASE = "navigation-case";
    private static final String FROM_ACTION = "from-action";
    private static final String NAVIGATION_CASE_FROM_ACTION = 
        CommonStructuresUtil.createPreficedString(NAVIGATION_CASE,FROM_ACTION);
    private static final String FROM_OUTCOME = "from-outcome";
    private static final String NAVIGATION_CASE_FROM_OUTCOME =
        CommonStructuresUtil.createPreficedString(NAVIGATION_CASE,FROM_OUTCOME);
    private static final String TO_VIEW_ID = "from-view-id";
    private static final String NAVIGATION_CASE_TO_VIEW_ID =
        CommonStructuresUtil.createPreficedString(NAVIGATION_CASE,TO_VIEW_ID);
    
    
    IProject project = null;

	public WriteNavigationRuleTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteNavigationCase() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, "WEB-INF/faces-config2.xml");
			assertNotNull(edit.getFacesConfig());
            
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

			NavigationRuleType navigationRule = facesConfigFactory.createNavigationRuleType();

            navigationRule.getDescription().add(CommonStructuresUtil.createDescription(NAVIGATION_RULE));
			navigationRule.getDisplayName().add(CommonStructuresUtil.createDisplayName(NAVIGATION_RULE));
			navigationRule.getIcon().add(CommonStructuresUtil.createIcon(NAVIGATION_RULE));
            
            
            FromViewIdType fromViewIdType = facesConfigFactory.createFromViewIdType();
			fromViewIdType.setTextContent(FROM_VIEW_ID);
            fromViewIdType.setId(FROM_VIEW_ID_ID);
			navigationRule.setFromViewId(fromViewIdType);
			
			NavigationCaseType navCaseType = facesConfigFactory.createNavigationCaseType();

            navCaseType.getDescription().add(CommonStructuresUtil.createDescription(NAVIGATION_CASE));
            navCaseType.getDisplayName().add(CommonStructuresUtil.createDisplayName(NAVIGATION_CASE));
            navCaseType.getIcon().add(CommonStructuresUtil.createIcon(NAVIGATION_CASE));

            {
    			FromActionType fromActionType = facesConfigFactory.createFromActionType();
    			fromActionType.setTextContent(NAVIGATION_CASE_FROM_ACTION);
                fromActionType.setId(
                        CommonStructuresUtil.createPreficedString(NAVIGATION_CASE_FROM_ACTION
                                , CommonStructuresUtil.ID));
    			navCaseType.setFromAction(fromActionType);
            }
            
            {
    			FromOutcomeType fromOutcomeType = facesConfigFactory.createFromOutcomeType();
    			fromOutcomeType.setTextContent(NAVIGATION_CASE_FROM_OUTCOME);
                fromOutcomeType.setId(
                        CommonStructuresUtil.createPreficedString(NAVIGATION_CASE_FROM_OUTCOME
                                , CommonStructuresUtil.ID));
    			navCaseType.setFromOutcome(fromOutcomeType);
            }
            
            {
    			ToViewIdType toViewIdType = facesConfigFactory.createToViewIdType();
    			toViewIdType.setTextContent(NAVIGATION_CASE_TO_VIEW_ID);
                toViewIdType.setId(
                        CommonStructuresUtil.createPreficedString(NAVIGATION_CASE_TO_VIEW_ID
                                , CommonStructuresUtil.ID));
    			navCaseType.setToViewId(toViewIdType);
            }
            
			navigationRule.getNavigationCase().add(navCaseType);
            navigationRule.setId(CommonStructuresUtil.createPreficedString(NAVIGATION_RULE, CommonStructuresUtil.ID));

			edit.getFacesConfig().getNavigationRule().add(navigationRule);
			edit.save(null);
		} finally {
			if (edit != null) {
				edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
			}
		}

        try
        {
            edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
                    project, "WEB-INF/faces-config2.xml");
            assertNotNull(edit.getFacesConfig());

            assertEquals(1, edit.getFacesConfig().getNavigationRule().size());
            NavigationRuleType navigationRule = (NavigationRuleType) 
                edit.getFacesConfig().getNavigationRule().get(0);

            assertEquals(1, navigationRule.getDescription().size());
            CommonStructuresUtil.assertMatchesDescription
                (NAVIGATION_RULE, (DescriptionType) navigationRule.getDescription().get(0));
            
            assertEquals(1, navigationRule.getDisplayName().size());
            CommonStructuresUtil.assertMatchesDisplayName
                (NAVIGATION_RULE, (DisplayNameType) navigationRule.getDisplayName().get(0));
            
            assertEquals(1, navigationRule.getIcon().size());
            CommonStructuresUtil.assertMatchesIcon
                (NAVIGATION_RULE, (IconType) navigationRule.getIcon().get(0));

            FromViewIdType fromViewIdType = navigationRule.getFromViewId();
            assertEquals(FROM_VIEW_ID, fromViewIdType.getTextContent());
            assertEquals(FROM_VIEW_ID_ID, fromViewIdType.getId());
            
            assertEquals(1, navigationRule.getNavigationCase().size());
            NavigationCaseType navCaseType = 
                (NavigationCaseType) navigationRule.getNavigationCase().get(0);

            assertEquals(1, navCaseType.getDescription().size());
            CommonStructuresUtil.assertMatchesDescription(NAVIGATION_CASE
                    ,(DescriptionType)navCaseType.getDescription().get(0));

            assertEquals(1, navCaseType.getDisplayName().size());
            CommonStructuresUtil.assertMatchesDisplayName(NAVIGATION_CASE
                    ,(DisplayNameType)navCaseType.getDisplayName().get(0));

            assertEquals(1, navCaseType.getIcon().size());
            CommonStructuresUtil.assertMatchesIcon(NAVIGATION_CASE
                    ,(IconType)navCaseType.getIcon().get(0));
            
            navCaseType.getIcon().add(CommonStructuresUtil.createIcon(NAVIGATION_CASE));

            {
                FromActionType fromActionType = navCaseType.getFromAction();
                assertEquals(NAVIGATION_CASE_FROM_ACTION
                        ,fromActionType.getTextContent());
                assertEquals(CommonStructuresUtil.createPreficedString(NAVIGATION_CASE_FROM_ACTION
                                    , CommonStructuresUtil.ID)
                             ,fromActionType.getId());
            }
            
            {
                FromOutcomeType fromOutcomeType = navCaseType.getFromOutcome();
                assertEquals(NAVIGATION_CASE_FROM_OUTCOME, fromOutcomeType.getTextContent());
                assertEquals(CommonStructuresUtil.createPreficedString(NAVIGATION_CASE_FROM_OUTCOME
                                    , CommonStructuresUtil.ID)
                             ,fromOutcomeType.getId());
            }
            
            {
                ToViewIdType toViewIdType = navCaseType.getToViewId();
                assertEquals(NAVIGATION_CASE_TO_VIEW_ID, toViewIdType.getTextContent());
                assertEquals(CommonStructuresUtil.createPreficedString(NAVIGATION_CASE_TO_VIEW_ID
                                    , CommonStructuresUtil.ID)
                             ,toViewIdType.getId());
            }
            
            assertEquals(CommonStructuresUtil.createPreficedString(NAVIGATION_RULE, CommonStructuresUtil.ID)
                         , navigationRule.getId());
        }
        finally
        {
            if (edit != null)
            {
                edit.dispose();
            }
        }
	}
}
