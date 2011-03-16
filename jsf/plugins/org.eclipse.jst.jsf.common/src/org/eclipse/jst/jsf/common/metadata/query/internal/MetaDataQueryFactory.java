/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.pde.AbstractSimpleClassExtensionRegistryReader;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainQueryFactory;

/**
 * Entry point for querying metadata.  Delegates query creation to the domainQueryFactory registered for the domain.
 * <p>
 * Usage Ex.:
 *     IMetaDataDomainContext context = MetaDataQueryContextFactory.getInstance().createTaglibDomainContext(project, uri);
 *     ITaglibMetaDataQuery query = MetaDataQueryFactory.getInstance().createQuery(context);
 */
public final class MetaDataQueryFactory {
	private static MetaDataQueryFactory INSTANCE = new MetaDataQueryFactory();
	private static DomainQueryFactoryReader DOMAIN_QUERY_FACTORY_READER;
	/**
	 * @return the singleton instance of the MetaDataQueryFactory
	 */
	public static MetaDataQueryFactory getInstance() {
		synchronized(INSTANCE) {
			return INSTANCE;
		}
	}
	
	private MetaDataQueryFactory() {
		DOMAIN_QUERY_FACTORY_READER = new DomainQueryFactoryReader();
	}

	/**
	 * Create a query for against a domain of metadata
	 * 
	 * @param <T> 
	 * @param context
	 * @return {@link IMetaDataQuery}
	 */
	public <T extends IMetaDataQuery> T createQuery(final IMetaDataDomainContext context) {
		//given domain id, get the query factory
		final IMetaDataDomainQueryFactory factory = DOMAIN_QUERY_FACTORY_READER.getFactoryFor(context.getDomainId());
		if (factory != null) 
			return (T)factory.createQuery(context);
		
		JSFCommonPlugin.log(new UnsupportedOperationException(), "Unknown query factory for domain id: "+context.getDomainId()); //$NON-NLS-1$
		return null;
	}
	
	private static class DomainQueryFactoryReader
			extends
				AbstractSimpleClassExtensionRegistryReader<IMetaDataDomainQueryFactory> {

		private static final String EXT_PT_ID 		= "domainQueryFactory"; //$NON-NLS-1$
		private static final String EXT_PT_ELEMENT 	= "factory"; //$NON-NLS-1$
		private static final String EXT_PT_ATTR 	= "class"; //$NON-NLS-1$

		/**
		 * Constructor
		 */
		protected DomainQueryFactoryReader() {
			super(JSFCommonPlugin.PLUGIN_ID, 
					EXT_PT_ID, EXT_PT_ELEMENT, EXT_PT_ATTR, 
					new CompareOrgEclipseJstContributorsLastComparator<IMetaDataDomainQueryFactory>()
			);
		}

		@Override
		protected void handleLoadFailure(final CoreException ce) {
			JSFCommonPlugin.log(ce,
					"Error loading IMetaDataDomainQueryFactory from extension"); //$NON-NLS-1$

		}
		
		public IMetaDataDomainQueryFactory getFactoryFor(final String domainId) {
			for (final IMetaDataDomainQueryFactory factory : getExtensions()) {
				if (factory.getDomainIdentifier().equals(domainId)){
					return factory;
				}
			}
			return null;
		}
	}
}
