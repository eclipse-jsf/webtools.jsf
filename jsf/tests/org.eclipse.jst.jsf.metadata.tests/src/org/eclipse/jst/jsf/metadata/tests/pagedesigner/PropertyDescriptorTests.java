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

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.metadata.tests.util.SingleJSPTestCase;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;
import org.eclipse.jst.pagedesigner.properties.ITabbedPropertiesConstants;

//TODO:   can use beefing up
public class PropertyDescriptorTests extends SingleJSPTestCase {
	
	// unused private static QualifiedName qn = new QualifiedName("test","model");
	private String uri = "http://org.eclipse.jsf/propertyDescriptorTest";
	private Model _model;
	private int _offset = 423;
	
	
	public PropertyDescriptorTests() {
		super("/testfiles/jsps/propertyDescTests.jsp",
			"/propertyDescTests.jsp",
				JSFVersion.V1_1,
			"/testfiles/web/faces-config_1_1.xml.data");
	}
	
	public void setUp() throws Exception {
		if (_model == null) {
			super.setUp();
			getModel();			
		}
	}
	
	private Model getModel() {
		if (_model == null) {
			_model = getQuery().findTagLibraryModel(uri);
//			ITaglibDomainMetaDataModelContext modelContext = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(null, uri);
//			_model = TaglibDomainMetaDataQueryHelper.getModel(modelContext);
		}

		return _model;
	}
	
	private ITaglibDomainMetaDataQuery getQuery() {
		final IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext((IProject)null);
		return MetaDataQueryFactory.getInstance().createQuery(context);
	}
	
	public void testBasics(){
		assertNotNull(getModel());
		assertEquals(1, getModel().getChildEntities().size());
		
		Entity tag = getQuery().findTagEntity(getModel(), "Tag");
		assertNotNull(tag);
		assertTrue(tag.getChildEntities().size() == 3);

		testAttr1(tag);
		testDefaultAttr(tag);
			
	}


	private void testAttr1(Entity tag){	
		Entity attr1 = getQuery().findTagAttributeEntity(tag, "Attr1");
		assertNotNull(attr1);
		assertTrue(attr1.getTraits().size() > 2);
		
		List<IMetaDataEnabledFeature> pds = MetaDataEnabledProcessingFactory.getInstance().getAttributeValueRuntimeTypeFeatureProcessors(IPropertyPageDescriptor.class, getStructuredDocumentContext(_structuredDocument, _offset), attr1);
		assertNotNull(pds);
		IPropertyPageDescriptor pd = (IPropertyPageDescriptor)pds.get(0);
		assertNotNull(pd);
		assertEquals("CategoryName", pd.getCategory());
		assertEquals("This is a description.", pd.getDescription());
		assertTrue(pd.isRequired());
		assertEquals("MyAttr1:", pd.getLabel());
		
	}
	
	private void testDefaultAttr(Entity tag){	
		Entity attr1 = getQuery().findTagAttributeEntity(tag, "DefaultAttr");
		assertNotNull(attr1);
		
		List<IMetaDataEnabledFeature> pds = MetaDataEnabledProcessingFactory.getInstance().getAttributeValueRuntimeTypeFeatureProcessors(IPropertyPageDescriptor.class, getStructuredDocumentContext(_structuredDocument, _offset), attr1);
		assertNotNull(pds);
		IPropertyPageDescriptor pd = (IPropertyPageDescriptor)pds.get(0);
		assertNotNull(pd);
		assertEquals(ITabbedPropertiesConstants.OTHER_CATEGORY, pd.getCategory());
		assertNull(pd.getDescription());
		assertFalse(pd.isRequired());
		assertEquals("DefaultAttr:", pd.getLabel());
		
	}
	
	
	public void testLocatePropertyPageDescForBooleanTagAttr() {
		Entity boolAttr = getQuery().getQueryHelper().getEntity(getModel(), "Tag/boolAttr");
		assertNotNull(boolAttr);
		
		List<IMetaDataEnabledFeature> pds = MetaDataEnabledProcessingFactory.getInstance().getAttributeValueRuntimeTypeFeatureProcessors(IPropertyPageDescriptor.class, getStructuredDocumentContext(_structuredDocument, _offset), boolAttr);
		assertNotNull(pds);
		IPropertyPageDescriptor pd = (IPropertyPageDescriptor)pds.get(0);
		assertNotNull(pd);

	}

}
