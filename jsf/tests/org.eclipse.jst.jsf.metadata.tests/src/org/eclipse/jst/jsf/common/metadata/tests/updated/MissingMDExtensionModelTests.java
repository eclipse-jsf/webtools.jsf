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
package org.eclipse.jst.jsf.common.metadata.tests.updated;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.ILogListener;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.TraitValueHelper;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryContextFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.MetaDataQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.tests.AbstractBaseMetaDataTestCase;

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
	private ITaglibDomainMetaDataQuery _query;
	Model model;
	Trait trait;
	
	public void setUp() throws Exception {
		super.setUp();
		String uri = "http://org.eclipse.jsf/missingMDModelTest";
				
		LogListener logListener = new LogListener();
		try {

			JSFCommonPlugin.getPlugin().getLog().addLogListener(logListener);
			IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainModelContext(project);
			_query = MetaDataQueryFactory.getInstance().createQuery(context);
			model = _query.findTagLibraryModel(uri);
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
		Entity entity = _query.getQueryHelper().getEntity(model, "Tag/Attr1");
		assertNotNull(entity);
		trait = _query.findTrait(entity, "attribute-value-runtime-type");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertEquals("org.eclipse.jst.jsf.core.attributevalues.StringType", TraitValueHelper.getValueAsString(trait));		
		
		trait = _query.findTrait(entity, "bogusTrait");
		//trait ain't bogus, but value will be
		assertNotNull(trait);
		//value should be null
		assertNull(trait.getValue());
		
		trait = _query.findTrait(entity, "reallybogusTrait");
		//trait ain't bogus, but value will be
		assertNotNull(trait);
		//value should be null
		assertNull(trait.getValue());

		trait = _query.findTrait(entity, "valid-values");
		assertNotNull(trait);
		assertNotNull(trait.getValue());

		
		entity = _query.getQueryHelper().getEntity(model, "Tag/DefaultAttr");
		assertNotNull(entity);
		trait = _query.findTrait(entity, "attribute-value-runtime-type");
		assertNotNull(trait);
		assertNotNull(trait.getValue());
		assertEquals("org.eclipse.jst.jsf.core.attributevalues.StringType", TraitValueHelper.getValueAsString(trait));
		
		trait = _query.findTrait(entity, "bogusTrait");
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
