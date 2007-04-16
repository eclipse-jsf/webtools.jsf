package org.eclipse.jst.jsf.common.metadata.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;

import junit.framework.TestCase;

public class TinyTestTests extends AbstractBaseMetaDataTestCase {
	protected IMetaDataModelContext baseContext;
	Model model;
	Trait trait;
	
	public void setUp() throws Exception {
		super.setUp();
		String uri = "http://org.eclipse.jsf/tinytest";
		
		baseContext = new MetaDataModelContextImpl(project, domain, uri);
		model = MetaDataQueryHelper.getModel(baseContext);
		assertNotNull(model);
	}

	public void testGetValue() {
		Entity entity = MetaDataQueryHelper.getEntity(baseContext, "A/copy1");
		assertNotNull(entity);
		trait = MetaDataQueryHelper.getTrait(entity, "model-trait");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertTrue(trait.getValue() instanceof AnyType);
		assertEquals("ATrait", TraitValueHelper.getValueAsString(trait));
		
		entity = MetaDataQueryHelper.getEntity(baseContext, "B/copy1");
		assertNotNull(entity);
		trait = MetaDataQueryHelper.getTrait(entity, "model-trait");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertTrue(trait.getValue() instanceof AnyType);
		assertEquals("ATrait", TraitValueHelper.getValueAsString(trait));
	}

}
