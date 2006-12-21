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

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ValidatorType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteValidatorTestCase extends BaseWriteTestCase {
    IProject project = null;

    protected final static String VALIDATOR = "validator";
    private final static String VALIDATOR_CLASS = 
        CommonStructuresUtil.createPreficedString(VALIDATOR, CommonStructuresUtil.CLASS);
    private final static String VALIDATOR_ID =
        CommonStructuresUtil.createPreficedString(VALIDATOR, CommonStructuresUtil.ID);
      
	public WriteValidatorTestCase(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteValidator() 
    {
		FacesConfigArtifactEdit edit = null;
		
		try 
        {
			edit = getArtifactEditForWrite();
			assertNotNull(edit.getFacesConfig());
            
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

			ValidatorType validator = facesConfigFactory.createValidatorType();
			
            {
    			ValidatorIdType validatorIdType = facesConfigFactory.createValidatorIdType();
    			validatorIdType.setTextContent(VALIDATOR_ID);
                validatorIdType.setId(
                    CommonStructuresUtil.createPreficedString(VALIDATOR_ID, CommonStructuresUtil.ID));
    			validator.setValidatorId(validatorIdType);
            }
            
            {
    			ValidatorClassType validatorClassType = facesConfigFactory.createValidatorClassType();
    			validatorClassType.setTextContent(VALIDATOR_CLASS);
                validatorClassType.setId(
                    CommonStructuresUtil.createPreficedString(VALIDATOR_CLASS, CommonStructuresUtil.ID));
    			validator.setValidatorClass(validatorClassType);
            }
            
            validator.getDescription().add(
                CommonStructuresUtil.createDescription(VALIDATOR));
            validator.getDisplayName().add(
                CommonStructuresUtil.createDisplayName(VALIDATOR));
            validator.getIcon().add(
                CommonStructuresUtil.createIcon(VALIDATOR));

            validator.getAttribute().add(
                    CommonStructuresUtil.createAttribute(VALIDATOR));
            validator.getProperty().add(
                CommonStructuresUtil.createProperty(VALIDATOR));

            validator.setId(VALIDATOR_ID);
			
			edit.getFacesConfig().getValidator().add(validator);
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
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            
            ValidatorType validator = (ValidatorType) FacesConfigModelUtil
            .findEObjectElementById(edit.getFacesConfig()
                    .getValidator(), VALIDATOR_ID);
            assertNotNull(validator);

            {
                ValidatorIdType validatorIdType = validator.getValidatorId();
                assertEquals(VALIDATOR_ID, validatorIdType.getTextContent());
                assertEquals(
                        CommonStructuresUtil.createPreficedString(VALIDATOR_ID, CommonStructuresUtil.ID)
                        , validatorIdType.getId()
                        );
            }

            {
                ValidatorClassType validatorClassType = validator.getValidatorClass();

                assertEquals(VALIDATOR_CLASS, validatorClassType.getTextContent());
                assertEquals(
                    CommonStructuresUtil.createPreficedString(VALIDATOR_CLASS, CommonStructuresUtil.ID)
                    , validatorClassType.getId());
            }

            assertEquals(1, validator.getDescription().size());
            CommonStructuresUtil.assertMatchesDescription(VALIDATOR
                , (DescriptionType) validator.getDescription().get(0));

            assertEquals(1, validator.getDisplayName().size());
            CommonStructuresUtil.assertMatchesDisplayName(VALIDATOR
                , (DisplayNameType) validator.getDisplayName().get(0));

            assertEquals(1, validator.getIcon().size());
            CommonStructuresUtil.assertMatchesIcon(VALIDATOR
                , (IconType) validator.getIcon().get(0));

            assertEquals(1, validator.getAttribute().size());
            CommonStructuresUtil.assertMatchAttribute(VALIDATOR
                , (AttributeType) validator.getAttribute().get(0));

            assertEquals(1, validator.getProperty().size());
            CommonStructuresUtil.assertMatchProperty(VALIDATOR
                , (PropertyType) validator.getProperty().get(0));

            assertEquals(CommonStructuresUtil.createPreficedString(VALIDATOR, CommonStructuresUtil.ID)
                , validator.getId());

		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
    }
}
