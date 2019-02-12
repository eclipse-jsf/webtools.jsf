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
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainEntityPredicateMatcher;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainPredicateMatcherFactory;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadata.tests.util.IJSFRuntimeRequiredV11;
import org.eclipse.jst.jsf.metadata.tests.util.JSPTestCase;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfo;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.PaletteInfos;
import org.eclipse.jst.jsf.tagdisplay.internal.paletteinfos.TagCreationInfo;
import org.eclipse.jst.jsf.test.util.mock.MockWorkspaceContext;

public class JSFHTMLTestCase extends JSPTestCase implements IJSFRuntimeRequiredV11{
	private String _uri;
	private static final boolean DEBUG = false;
	
	ITaglibDomainMetaDataQuery 	_query;
	
	public JSFHTMLTestCase () {
		super(	JSFVersion.V1_1,
				"/testfiles/web/faces-config_1_1.xml.data");

	}
	public void setUp() throws Exception{
		super.setUp();
	    
//	    projectTestEnvironment.loadResourceInWebRoot(MetadataTestsPlugin.getDefault().getBundle(),
//	            "/testfiles/html_basic.tld",
//	            "/WEB-INF/lib/html_basic.tld");
	    
		_uri = "http://java.sun.com/jsf/html";
		final IMetaDataDomainContext 		context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(_testEnv.getTestProject());
	 	_query = MetaDataQueryFactory.getInstance().createQuery(context);
	}
	
	public void testPaletteInfos(){
		
		Model model = _query.findTagLibraryModel(_uri);
		Trait trait = _query.findTrait(model , PaletteInfos.TRAIT_ID);
		
		assertNotNull(trait);
		assertTrue(trait.getValue() instanceof PaletteInfos);
		PaletteInfos pis = (PaletteInfos)trait.getValue();
		PaletteInfo cmdBtn = pis.findPaletteInfoById("commandButton");
		assertNotNull(cmdBtn);
		assertEquals("Command Button", cmdBtn.getDisplayLabel());
		
		PaletteInfo info = pis.findPaletteInfoById("inputText");
		assertNotNull(info);
		//requires jsf html tld to work!
		assertNotNull(info.getDisplayLabel());
		assertNotNull(info.getDescription());
		assertNull(info.getExpert());
		assertNull(info.getHidden());	
		assertFalse(info.isExpert());
		assertFalse(info.isHidden());	
		assertNotNull(info.getSmallIcon());
		assertNotNull(info.getLargeIcon());
	}
	
	public void testCreateInfos(){
		Trait trait = _query.getQueryHelper().getTrait(_uri, "dataTable", TagCreationInfo.TRAIT_ID);
		assertNotNull(trait);
		assertTrue(trait.getValue() instanceof TagCreationInfo);
		TagCreationInfo tci = (TagCreationInfo)trait.getValue();
		
		Object template = tci.getTemplate();
		assertNotNull(template);
		assertTrue(template instanceof String);

		trait = _query.getQueryHelper().getTrait(_uri, "commandLink", TagCreationInfo.TRAIT_ID);
		assertNotNull(trait);
		assertTrue(trait.getValue() instanceof TagCreationInfo);
		tci = (TagCreationInfo)trait.getValue();
		assertNotNull(tci.getAttributes());
		assertNotNull(tci.getTemplate());
		assertEquals(0, tci.getAttributes().size());

	}
	
	public void testQuery() throws Exception {
		//tests querying JSF_HTML using new APIs
		IProject project = new MockWorkspaceContext().createProject("test"); 
		IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
		ITaglibDomainMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context); 
		
		Model tagLib = query.findTagLibraryModel(_uri);
		assertNotNull(tagLib);
		
		Entity tag = query.findTagEntity(tagLib, "commandButton");
		assertNotNull(tag);
		
		Entity tagAttr = query.findTagAttributeEntity(tag, "value");
		assertNotNull(tagAttr);
		
		Trait trait1 = query.findTrait(tagLib, "paletteInfos");
		assertNotNull(trait1);
		
		Trait trait2 = query.findTrait(tag, "small-icon");
		assertNotNull(trait2);
		
		Trait trait3 = query.findTrait(tagAttr, "attribute-value-runtime-type");
		assertNotNull(trait3);
		
		//All tags beginning with c (case-insensitve)
		ITaglibDomainPredicateMatcherFactory factory = MetaDataQueryContextFactory.getInstance().getTaglibDomainPredicateMatcherFactory();
		ITaglibDomainEntityPredicateMatcher entityMatcher = factory.createTagEntityMatcher(_uri, "^[cC].*");		
		IResultSet<Entity> tags = query.findEntities(entityMatcher);
		assertNotNull(tags.getResults());
		assertEquals(3, tags.getResults().size());
		dumpEntities(tags.getResults(), "All tags beginning with c (case-insensitive)");
		
		
		//DISABLING BELOW SINCE IT MAY BE TOUGH TO MAINTAIN
//		//All traits with attribute-value-runtime-types for tag attrs beginning with l, m, n,..., or p (case-sensitive)		
//		entityMatcher = factory.createTagAttributeEntityMatcher(_uri,"^[d].*", "^[a-c].*");		
//		IPredicateMatcher<Trait> traitMatcher = factory.createTraitMatcher("attribute-value-runtime-type");
//		IResultSet<Trait> traits = query.findTraits(entityMatcher, traitMatcher);
//		assertNotNull(traits.getResults());
////		assertEquals(3, traits.getResults().size());
//		dumpTraits(traits.getResults(), "All traits with attribute-value-runtime-types for tag attrs on tags beginning beginning with a, b, or c (case-sensitive)");
		

	}
	private void dumpEntities(List<Entity> tags, String title) throws Exception {
		if (DEBUG ) {
			printHeader(title);
			for (final Entity tag: tags) {
				System.out.println(tag.getModel().getId()+"/"+tag.getId());
			}
			printFooter(tags.size());
		}
	}
	
//	private void dumpTraits(List<Trait> traits, String title) throws Exception {
//		if (DEBUG ) {
//			printHeader(title);
//			for (final Trait trait: traits) {
//				System.out.println(trait.getId() + "(for: "+trait.eContainer().toString()+")");
//			}
//			printFooter(traits.size());
//		}
//	}

	private void printHeader(String title) {
		System.out.println();
		System.out.println(title);
		printSeparator();	
	}
	
	private void printFooter(int size) {
		printSeparator();	
		System.out.println("Size: "+size);
		System.out.println();
		
	}
	
	private void printSeparator() {
		System.out.println("--------------------------------------------------------");	
	}
}
