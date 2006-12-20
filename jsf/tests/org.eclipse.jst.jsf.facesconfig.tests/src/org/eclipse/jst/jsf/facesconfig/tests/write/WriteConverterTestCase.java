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
import org.eclipse.jst.jsf.facesconfig.emf.ConverterClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterForClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterIdType;
import org.eclipse.jst.jsf.facesconfig.emf.ConverterType;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.DisplayNameType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.IconType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteConverterTestCase extends TestCase {
	private static final String WEB_INF_FACES_CONFIG2_XML = "WEB-INF/faces-config2.xml";
    IProject project = null;

    private static final String CONVERTER = "converter";
    private static final String CONVERTER_CLASS = 
        CommonStructuresUtil.createPreficedString(CONVERTER, CommonStructuresUtil.CLASS);
    private static final String CONVERTER_CLASS_FOR =
        CommonStructuresUtil.createPreficedString(CONVERTER
                , CommonStructuresUtil.createPreficedString(CommonStructuresUtil.CLASS, "for"));
    private static final String CONVERTER_ID =
        CommonStructuresUtil.createPreficedString(CONVERTER, CommonStructuresUtil.ID);

	public WriteConverterTestCase(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		WizardUtil.createProject(getName());
		project = WizardUtil.getTestProject(getName());
	}
	
	public void testWriteConverter() {
		FacesConfigArtifactEdit edit = null;
		
		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
					project, WEB_INF_FACES_CONFIG2_XML);
			assertNotNull(edit.getFacesConfig());
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

			ConverterType converter = facesConfigFactory.createConverterType();

            converter.getDescription().add(CommonStructuresUtil.createDescription(CONVERTER));
            converter.getDisplayName().add(CommonStructuresUtil.createDisplayName(CONVERTER));
            converter.getIcon().add(CommonStructuresUtil.createIcon(CONVERTER));
			
            {
    			ConverterClassType converterClassType = facesConfigFactory.createConverterClassType();
    			converterClassType.setTextContent(CONVERTER_CLASS);
                converterClassType.setId(CommonStructuresUtil.createPreficedString(CONVERTER_CLASS
                        , CommonStructuresUtil.ID));
    			converter.setConverterClass(converterClassType);
            }
            
            {
    			ConverterForClassType converterForClassType = facesConfigFactory.createConverterForClassType();
    			converterForClassType.setTextContent(CONVERTER_CLASS_FOR);
                converterForClassType.setId(CommonStructuresUtil.createPreficedString(CONVERTER_CLASS_FOR
                        , CommonStructuresUtil.ID));
    			converter.setConverterForClass(converterForClassType);
            }
            {
                ConverterIdType converterId = facesConfigFactory.createConverterIdType();
                converterId.setTextContent(CONVERTER_ID);
                converterId.setId(CommonStructuresUtil.createPreficedString(CONVERTER_CLASS_FOR
                        , CommonStructuresUtil.ID));
                converter.setConverterId(converterId);			
            }
            
            converter.getAttribute().add(CommonStructuresUtil.createAttribute(CONVERTER));
            converter.getProperty().add(CommonStructuresUtil.createProperty(CONVERTER));

            converter.setId(CommonStructuresUtil.createPreficedString(CONVERTER
                                                    , CommonStructuresUtil.ID));
            
			edit.getFacesConfig().getConverter().add(converter);
			edit.save(null);
		} finally {
			if (edit != null) {
				edit.dispose();
                assertTrue(edit.isDisposed());
                edit = null;
			}
		}

		try {
			edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForRead(
					project, WEB_INF_FACES_CONFIG2_XML);
			assertNotNull(edit.getFacesConfig());
            
            assertEquals(1, edit.getFacesConfig().getConverter().size());
            ConverterType converter = 
                (ConverterType) edit.getFacesConfig().getConverter().get(0);

            assertEquals(1, converter.getDescription().size());
            CommonStructuresUtil.assertMatchesDescription
                (CONVERTER, (DescriptionType) converter.getDescription().get(0));

            assertEquals(1, converter.getDisplayName().size());
            CommonStructuresUtil.assertMatchesDisplayName
                (CONVERTER, (DisplayNameType) converter.getDisplayName().get(0));

            assertEquals(1, converter.getIcon().size());
            CommonStructuresUtil.assertMatchesIcon
                (CONVERTER, (IconType) converter.getIcon().get(0));

            ConverterClassType converterClassType = converter.getConverterClass();
            assertEquals(CONVERTER_CLASS, converterClassType.getTextContent());
            assertEquals(CommonStructuresUtil.createPreficedString(CONVERTER_CLASS
                            , CommonStructuresUtil.ID)
                        , converterClassType.getId());

            ConverterForClassType converterForClassType = converter.getConverterForClass();
            assertEquals(CONVERTER_CLASS_FOR, converterForClassType.getTextContent());
            assertEquals(CommonStructuresUtil.createPreficedString(CONVERTER_CLASS_FOR
                            , CommonStructuresUtil.ID)
                         , converterForClassType.getId());

            ConverterIdType converterId = converter.getConverterId();
            assertEquals(CONVERTER_ID, converterId.getTextContent());
            assertEquals(CommonStructuresUtil.createPreficedString(CONVERTER_CLASS_FOR
                            , CommonStructuresUtil.ID)
                         , converterId.getId());

            converter.getAttribute().add(CommonStructuresUtil.createAttribute(CONVERTER));
            converter.getProperty().add(CommonStructuresUtil.createProperty(CONVERTER));

            assertEquals(CommonStructuresUtil.createPreficedString(CONVERTER
                                                    , CommonStructuresUtil.ID)
                         , converter.getId());
		} finally {
			//assertTrue(result != null && result.equals(sTestString));
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}

