/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.tests;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.TaglibDomainMetaDataModelContextImpl;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;

/**
 * Tests loading of models when an emf model extension is missing.
 * <p/>
 * Expected behavior is for model to load all entities and traits even if extension model is not
 * loaded.   The trait values will be missing where the trait value model is missing.  
 * In these cases, an error should be logged to the JSFCommonPlugin.
 * <p>
 * 
 * <p/>
 * Note: if xsi:type prefix is bad, or no uri to model was provided, then model will NOT load.   
 * This JUnit does not test for the model not loading in this case.
 *
 */
public class MissingMDExtensionModelTests extends AbstractBaseMetaDataTestCase {
	protected ITaglibDomainMetaDataModelContext baseContext;
	Model model;
	Trait trait;
	
	public void setUp() throws Exception {
		super.setUp();
		String uri = "http://org.eclipse.jsf/missingMDModelTest";
				
		LogListener logListener = new LogListener();
		try {

			JSFCommonPlugin.getPlugin().getLog().addLogListener(logListener);
			baseContext = new TaglibDomainMetaDataModelContextImpl(domain, project, uri);
			model = TaglibDomainMetaDataQueryHelper.getModel(baseContext);
			assertNotNull(model);
			if (JSFCommonPlugin.getPlugin().isDebugging() && 
					Boolean.valueOf(Platform.getDebugOption(JSFCommonPlugin.PLUGIN_ID+"/debug/metadataload")).booleanValue()){
				//assert log entries
				assertTrue(logListener.getMessages().size() > 2);
			}
		} finally {
			JSFCommonPlugin.getPlugin().getLog().removeLogListener(logListener);
		}
	}

	public void testModelLoadWithMissingECoreExtensionModel() {
		Entity entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "Tag/Attr1");
		assertNotNull(entity);
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "attribute-value-runtime-type");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertEquals("org.eclipse.jst.jsf.core.attributevalues.StringType", TraitValueHelper.getValueAsString(trait));		
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "bogusTrait");
		//trait ain't bogus, but value will be
		assertNotNull(trait);
		//value should be null
		assertNull(trait.getValue());
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "reallybogusTrait");
		//trait ain't bogus, but value will be
		assertNotNull(trait);
		//value should be null
		assertNull(trait.getValue());

		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "valid-values");
		assertNotNull(trait);
		assertNotNull(trait.getValue());

		
		entity = TaglibDomainMetaDataQueryHelper.getEntity(baseContext, "Tag/DefaultAttr");
		assertNotNull(entity);
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "attribute-value-runtime-type");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertEquals("org.eclipse.jst.jsf.core.attributevalues.StringType", TraitValueHelper.getValueAsString(trait));
		
		trait = TaglibDomainMetaDataQueryHelper.getTrait(entity, "bogusTrait");
		//trait ain't bogus, but value will be
		assertNotNull(trait);
		//value should be null
		assertNull(trait.getValue());
	}
	
	class LogListener implements ILogListener {

		private List<IStatus> statusMsgs;
		public LogListener(){
			this.statusMsgs = new ArrayList<IStatus>();
		}
		public void logging(IStatus status, String plugin) {
			statusMsgs.add(status);			
		}
		
		public List<IStatus> getMessages(){
			return statusMsgs;
		}
		
	}

}
