package org.eclipse.jst.jsf.common.metadata.tests;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xml.type.SimpleAnyType;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;

public class TraitImplTests extends AbstractBaseMetaDataTestCase {
	protected IMetaDataModelContext baseContext;
	Model model;
	Trait trait;
	
	public void setUp() throws Exception {
		super.setUp();
		
		baseContext = new MetaDataModelContextImpl(project, domain, baseTestUri);
		model = MetaDataQueryHelper.getModel(baseContext);
		assertNotNull(model);
		trait = MetaDataQueryHelper.getTrait(baseContext, "loaded", "A");
		assertNotNull(trait);
	}

	public void testGetValue() {
		assertNotNull(trait.getValue());
		assertTrue(trait.getValue() instanceof EObject);
		assertEquals("a", TraitValueHelper.getValueAsString(trait));
	}

	public void testSetValue() {
//		fail("Not yet implemented");
	}

	public void testGetSourceModel() {
		assertNotNull(trait.getSourceModelProvider());
		assertTrue(trait.getSourceModelProvider().getSourceModel() instanceof Model);
		assertEquals(model, trait.getSourceModelProvider().getSourceModel());
	}

	public void testSetSourceModel() {
// Set during model load.   no need to test.
	}

	public void testGetId() {
		//id
		assertNotNull(trait.getId());
		assertEquals("A", trait.getId() );		
	}

	public void testSetId() {
		String id = trait.getId();
		trait.setId("new");
		assertEquals("new",trait.getId());
		trait.setId(id);
	}

	public void testAccept() {
//		Excercised by Query tests
	}

}
