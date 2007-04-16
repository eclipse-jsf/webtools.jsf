package org.eclipse.jst.jsf.common.metadata.tests;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.internal.MetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;

import junit.framework.TestCase;

public class IncludeEntityGroupImplTests extends AbstractBaseMetaDataTestCase {
	protected IMetaDataModelContext baseContext;
	IncludeEntityGroup group;
	
	public void setUp() throws Exception {
		super.setUp();
		
		baseContext = new MetaDataModelContextImpl(project, domain, baseTestUri);
		Entity entity = MetaDataQueryHelper.getEntity(baseContext, "loaded");
		assertNotNull(entity);
		group = (IncludeEntityGroup)entity.getIncludeGroups().get(0);
	}
	public void testGetId() {
		assertEquals("eg2", group.getId());
	}

	public void testSetId() {
		String id = group.getId();
		group.setId("new");
		assertEquals("new",group.getId());
		group.setId(id);
	}

	public void testGetModelUri() {
		assertNull(group.getModelUri());		
	}

	public void testSetModelUri() {
		String uri = null;
		group.setModelUri("new");
		assertEquals("new",group.getModelUri());
		group.setModelUri(uri);
	}

}
