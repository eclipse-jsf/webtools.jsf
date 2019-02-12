/*******************************************************************************
 * Copyright (c) 2006, 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.pagedesigner;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadata.tests.util.JSPTestCase;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo;

//TODO:   can use beefing up
public class QuickEditSectionsTests extends JSPTestCase {
	private String _uri;
	
	public QuickEditSectionsTests () {
		super(	JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");

	}
	public void setUp() throws Exception{
		super.setUp();
	    
		_uri = "http://org.eclipse.jsf/quickEditSectionTest";

	}
	
	public void testQuickEditTabSections(){
		final IMetaDataDomainContext 		context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(_testEnv.getTestProject());
		final ITaglibDomainMetaDataQuery 	query = MetaDataQueryFactory.getInstance().createQuery(context);
		
		Model model = query.findTagLibraryModel(_uri);
		assertNotNull(model);		
		Entity entity = query.findTagEntity(model, "A");
		assertNotNull(entity);		
		Trait trait = query.findTrait(entity , QuickEditTabSections.TRAIT_ID);
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertTrue(trait.getValue() instanceof QuickEditTabSections);
		QuickEditTabSections sections = (QuickEditTabSections)trait.getValue();
		assertEquals(3, sections.getSections().size());
		
		SectionInfo section = sections.getSections().get(0); 
		assertEquals("attr1", section.getId());
		assertEquals(SECTION_TYPE.ATTRIBUTE, section.getType());
		
		section = sections.getSections().get(1); 
		assertEquals("attr2", section.getId());
		assertEquals(SECTION_TYPE.ATTRIBUTE, section.getType());
		
		section = sections.getSections().get(2); 
		assertEquals("section1", section.getId());
		assertEquals(SECTION_TYPE.SECTION, section.getType());

	}

}
