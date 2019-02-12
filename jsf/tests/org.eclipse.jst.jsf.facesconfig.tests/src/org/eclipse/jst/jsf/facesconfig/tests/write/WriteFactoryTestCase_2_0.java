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

import org.eclipse.jst.jsf.facesconfig.emf.ExceptionHandlerFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ExternalContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.PartialViewContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.TagHandlerDelegateFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.ViewDeclarationLanguageFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.VisitContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.tests.util.CommonStructuresUtil;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;

public class WriteFactoryTestCase_2_0 extends WriteFactoryTestCase_1_2 {
    private final static String  EXCEPTIONHANDLER_FACTORY = "exception-handler-factory";
    private final static String  EXTERNALCONTEXT_FACTORY = "external-context-factory";
    private final static String  PARTIALVIEWCONTEXT_FACTORY = "partial-view-context-factory";
    private final static String  VIEWDECLARATIONLANGUAGE_FACTORY = "view-declaration-language-factory";
    private final static String  TAGHANDLERDELEGATE_FACTORY = "tag-handler-delegate-factory";
    private final static String  VISITCONTEXT_FACTORY = "visit-context-factory";
    private final static String FACTORY_ID = CommonStructuresUtil
    .createPreficedString(FACTORY+"20", CommonStructuresUtil.ID);

    public WriteFactoryTestCase_2_0(String name) {
        super(name);
    }

	public void testFactory20() {
		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForWrite();
			assertNotNull(edit.getFacesConfig());
			FacesConfigPackage facesConfigPackage = FacesConfigPackage.eINSTANCE;
			FacesConfigFactory facesConfigFactory = facesConfigPackage.getFacesConfigFactory();

			FactoryType newfactory = facesConfigFactory.createFactoryType();

			{
				ExceptionHandlerFactoryType newExceptionHandlerFactory = facesConfigFactory.createExceptionHandlerFactoryType();
				newExceptionHandlerFactory.setTextContent(EXCEPTIONHANDLER_FACTORY);
				newExceptionHandlerFactory.setId(CommonStructuresUtil
                        .createPreficedString(EXCEPTIONHANDLER_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getExceptionHandlerFactory().add(newExceptionHandlerFactory);
            }
            
            {
            	ExternalContextFactoryType externalContextFactory = facesConfigFactory.createExternalContextFactoryType();
            	externalContextFactory.setTextContent(EXTERNALCONTEXT_FACTORY);
            	externalContextFactory.setId(CommonStructuresUtil
                        .createPreficedString(EXTERNALCONTEXT_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getExternalContextFactory().add(externalContextFactory);
            }	
			
            {
    			PartialViewContextFactoryType partialViewContextFactory = facesConfigFactory.createPartialViewContextFactoryType();
    			partialViewContextFactory.setTextContent(PARTIALVIEWCONTEXT_FACTORY);
                partialViewContextFactory.setId(CommonStructuresUtil
                        .createPreficedString(PARTIALVIEWCONTEXT_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getPartialViewContextFactory().add(partialViewContextFactory);
            }
            
            {
    			ViewDeclarationLanguageFactoryType viewDeclarationLanguageFactory = facesConfigFactory.createViewDeclarationLanguageFactoryType();
    			viewDeclarationLanguageFactory.setTextContent(VIEWDECLARATIONLANGUAGE_FACTORY);
    			viewDeclarationLanguageFactory.setId(CommonStructuresUtil
                        .createPreficedString(VIEWDECLARATIONLANGUAGE_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getViewDeclarationLanguageFactory().add(viewDeclarationLanguageFactory);
            }			

            {
            	TagHandlerDelegateFactoryType tagHandlerDelegateFactory = facesConfigFactory.createTagHandlerDelegateFactoryType();
    			tagHandlerDelegateFactory.setTextContent(TAGHANDLERDELEGATE_FACTORY);
    			tagHandlerDelegateFactory.setId(CommonStructuresUtil
                        .createPreficedString(TAGHANDLERDELEGATE_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getTagHandlerDelegateFactory().add(tagHandlerDelegateFactory);
            }			

            {
            	VisitContextFactoryType visitContextFactory = facesConfigFactory.createVisitContextFactoryType();
    			visitContextFactory.setTextContent(VISITCONTEXT_FACTORY);
    			visitContextFactory.setId(CommonStructuresUtil
                        .createPreficedString(VISITCONTEXT_FACTORY, CommonStructuresUtil.ID));
    			newfactory.getVisitContextFactory().add(visitContextFactory);
            }			

            newfactory.setId(FACTORY_ID);

			edit.getFacesConfig().getFactory().add(newfactory);
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
            
            FactoryType newFactory = 
                (FactoryType) FacesConfigModelUtil
                    .findEObjectElementById(
                            edit.getFacesConfig().getFactory(), FACTORY_ID);
            assertNotNull(newFactory);

            {
                assertEquals(1, newFactory.getExceptionHandlerFactory().size());
                ExceptionHandlerFactoryType newExceptionHandlerFactory = 
                    (ExceptionHandlerFactoryType) newFactory.getExceptionHandlerFactory().get(0);
                
                assertEquals(EXCEPTIONHANDLER_FACTORY, newExceptionHandlerFactory.getTextContent());
                assertEquals(CommonStructuresUtil
                                .createPreficedString(EXCEPTIONHANDLER_FACTORY, CommonStructuresUtil.ID)
                        , newExceptionHandlerFactory.getId());
            }
            
            {
                assertEquals(1, newFactory.getExternalContextFactory().size());
                ExternalContextFactoryType externalContextFactory = 
                    (ExternalContextFactoryType) newFactory.getExternalContextFactory().get(0);

                assertEquals(EXTERNALCONTEXT_FACTORY, externalContextFactory.getTextContent());
                assertEquals(CommonStructuresUtil
                                .createPreficedString(EXTERNALCONTEXT_FACTORY, CommonStructuresUtil.ID)
                             , externalContextFactory.getId());
            }   
            
            {
                assertEquals(1, newFactory.getPartialViewContextFactory().size());
                PartialViewContextFactoryType partialViewContextFactory = 
                    (PartialViewContextFactoryType) newFactory.getPartialViewContextFactory().get(0);
                
                assertEquals(PARTIALVIEWCONTEXT_FACTORY, partialViewContextFactory.getTextContent());
                assertEquals(CommonStructuresUtil
                                .createPreficedString(PARTIALVIEWCONTEXT_FACTORY, CommonStructuresUtil.ID)
                             , partialViewContextFactory.getId());
            }
            
            {
                assertEquals(1, newFactory.getViewDeclarationLanguageFactory().size());
                ViewDeclarationLanguageFactoryType ViewDeclarationLanguageFactory = 
                    (ViewDeclarationLanguageFactoryType) newFactory.getViewDeclarationLanguageFactory().get(0);
                
                assertEquals(VIEWDECLARATIONLANGUAGE_FACTORY, ViewDeclarationLanguageFactory.getTextContent());
                assertEquals(CommonStructuresUtil
                                  .createPreficedString(VIEWDECLARATIONLANGUAGE_FACTORY, CommonStructuresUtil.ID)
                             ,ViewDeclarationLanguageFactory.getId());
            }           
            
            {
                assertEquals(1, newFactory.getTagHandlerDelegateFactory().size());
                TagHandlerDelegateFactoryType tagHandlerDelegateFactory = 
                    (TagHandlerDelegateFactoryType) newFactory.getTagHandlerDelegateFactory().get(0);
                
                assertEquals(TAGHANDLERDELEGATE_FACTORY, tagHandlerDelegateFactory.getTextContent());
                assertEquals(CommonStructuresUtil
                                  .createPreficedString(TAGHANDLERDELEGATE_FACTORY, CommonStructuresUtil.ID)
                             ,tagHandlerDelegateFactory.getId());
            }           
            
            {
                assertEquals(1, newFactory.getVisitContextFactory().size());
                VisitContextFactoryType visitContextFactory = 
                    (VisitContextFactoryType) newFactory.getVisitContextFactory().get(0);
                
                assertEquals(VISITCONTEXT_FACTORY, visitContextFactory.getTextContent());
                assertEquals(CommonStructuresUtil
                                  .createPreficedString(VISITCONTEXT_FACTORY, CommonStructuresUtil.ID)
                             ,visitContextFactory.getId());
            }           
            
            assertEquals(FACTORY_ID, newFactory.getId());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
}
