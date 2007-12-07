/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.pagedesigner;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadata.tests.util.JSPTestCase;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.QuickEditTabSections;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SECTION_TYPE;
import org.eclipse.jst.pagedesigner.editors.properties.quickedittabsections.SectionInfo;

//TODO:   can use beefing up
public class QuickEditSectionsTests extends JSPTestCase {
	private String _uri;
	private ITaglibDomainMetaDataModelContext _context;
	
	public QuickEditSectionsTests () {
		super(	JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");

	}
	public void setUp() throws Exception{
		super.setUp();
	    
		_uri = "http://org.eclipse.jsf/quickEditSectionTest";
		_context = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(_testEnv.getTestProject(), _uri);
	}
	
	public void testQuickEditTabSections(){
		Model model = TaglibDomainMetaDataQueryHelper.getModel(_context);
		assertNotNull(model);		
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(model, "A");
		assertNotNull(entity);		
		Trait trait = TaglibDomainMetaDataQueryHelper.getTrait(entity , QuickEditTabSections.TRAIT_ID);
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
