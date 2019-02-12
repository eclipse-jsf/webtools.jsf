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
import org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class ReadFactoryTestCase_2_0 extends ReadFactoryTestCase_1_2 {
    public ReadFactoryTestCase_2_0(String name) {
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

	public void testExceptionHandlerFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
			assertNotNull(factoryType1);
			ExceptionHandlerFactoryType exceptionHandlerFactory1 =
                (ExceptionHandlerFactoryType) FacesConfigModelUtil.findEObjectElementById
                (factoryType1.getExceptionHandlerFactory(), "exceptionHandlerFactory1");
            assertNotNull(exceptionHandlerFactory1);
			assertEquals("exception-handler-factory",
					exceptionHandlerFactory1.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testExternalContextFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
			assertNotNull(factoryType1);
			ExternalContextFactoryType externalContextFactory1 =
                (ExternalContextFactoryType) FacesConfigModelUtil.findEObjectElementById
                (factoryType1.getExternalContextFactory(), "externalContextFactory1");
            assertNotNull(externalContextFactory1);
			assertEquals("external-context-factory",
					externalContextFactory1.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testPartialViewContextFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
			assertNotNull(factoryType1);
			PartialViewContextFactoryType partialViewContextFactory1 =
                (PartialViewContextFactoryType) FacesConfigModelUtil.findEObjectElementById
                (factoryType1.getPartialViewContextFactory(), "partialViewContextFactory1");
            assertNotNull(partialViewContextFactory1);
			assertEquals("partial-view-context-factory",
					partialViewContextFactory1.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testViewDeclarationLanguageFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
			assertNotNull(factoryType1);
			ViewDeclarationLanguageFactoryType viewDeclarationLanguageFactory1 =
                (ViewDeclarationLanguageFactoryType) FacesConfigModelUtil.findEObjectElementById
                (factoryType1.getViewDeclarationLanguageFactory(), "viewDeclarationLanguageFactory1");
            assertNotNull(viewDeclarationLanguageFactory1);
			assertEquals("view-declaration-language-factory",
					viewDeclarationLanguageFactory1.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testTagHandlerDelegateFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
			assertNotNull(factoryType1);
			TagHandlerDelegateFactoryType tagHandlerDelegateFactory1 =
                (TagHandlerDelegateFactoryType) FacesConfigModelUtil.findEObjectElementById
                (factoryType1.getTagHandlerDelegateFactory(), "tagHandlerDelegateFactory1");
            assertNotNull(tagHandlerDelegateFactory1);
			assertEquals("tag-handler-delegate-factory",
					tagHandlerDelegateFactory1.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

	public void testVisitContextFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
			assertNotNull(factoryType1);
			VisitContextFactoryType visitContextFactory =
                (VisitContextFactoryType) FacesConfigModelUtil.findEObjectElementById
                (factoryType1.getVisitContextFactory(), "visitContextFactory1");
            assertNotNull(visitContextFactory);
			assertEquals("visit-context-factory",
					visitContextFactory.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

}
