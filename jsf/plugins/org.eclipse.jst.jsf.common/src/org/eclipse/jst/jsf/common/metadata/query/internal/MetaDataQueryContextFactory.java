/*******************************************************************************
 * Copyright (c) 2010, 2011 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.strategy.AbstractTestableExtensibleDefaultProviderSelectionStrategy;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.ITaglibDomainPredicateMatcherFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.TaglibDomainContextImpl;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.TaglibDomainEntityIdRegexPredicateMatcher;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.TaglibDomainEntityQuerySpec;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.TaglibDomainTraitIdRegexPredicateMatcher;
import org.eclipse.jst.jsf.common.metadata.query.internal.taglib.TaglibDomainTraitQuerySpec;

/**
 * Factory for producing {@link IMetaDataDomainContext}s and {@link IPredicateMatcherFactory}s
 * TODO: Consider using {@link AbstractTestableExtensibleDefaultProviderSelectionStrategy}
 */
public final class MetaDataQueryContextFactory {
	
	private static MetaDataQueryContextFactory INSTANCE = new MetaDataQueryContextFactory();
	//private static DomainModelContextFactoryReader DOMAIN_FACTORY_READER;
	
	private MetaDataQueryContextFactory(){
		//DOMAIN_FACTORY_READER = new DomainModelContextFactoryReader();		
	}
	
	/**
	 * @return singleton instance of {@link MetaDataQueryContextFactory}
	 */
	public static MetaDataQueryContextFactory getInstance() {
		synchronized (INSTANCE) {		
			return INSTANCE;			
		}
	}

	/**
	 * Creates a context for querying the tag library domain
	 * @param project - must not be null
	 * @return ITaglibDomainModelContext
	 */
	public IMetaDataDomainContext createTaglibDomainModelContext(final IProject project) {
		assert project != null;
		return new TaglibDomainContextImpl(project);
	}
	
	/**
	 * Creates a context for querying the tag library domain
	 * @param file - must not be null
	 * @return ITaglibDomainModelContext
	 */
	public IMetaDataDomainContext createTaglibDomainModelContext(final IFile file) {
		assert file != null;
		return new TaglibDomainContextImpl(file);
	}
	
//	/**
//	 * Creates a context for querying the tag library domain
//	 * @param file - must not be null
//	 * @return ITaglibDomainModelContext
//	 */
//	public IMetaDataDomainContext createTaglibDomainModelContext(final IFile file) {
//		assert file != null;
//		IMetaDataDomainContext context = createDomainModelContext(file, IMetaDataDomainContext.TAGLIB_DOMAIN_CONTEXT_ID);
//		if (context == null)
//			context = new TaglibDomainContextImpl(file);
//		
//		return context;		
//	}
//	
//	/**
//	 * @param file
//	 * @param domainId
//	 * @return IMetaDataDomainContext
//	 */
//	public IMetaDataDomainContext createDomainModelContext(final IFile file, final String domainId) {
//		//given domain id, get the query factory
//		final IMetaDataDomainModelContextFactory factory = DOMAIN_FACTORY_READER.getFactoryFor(domainId);
//		if (factory != null) 
//			return factory.createDomainModelContext(file);
//		
////		JSFCommonPlugin.log(new UnsupportedOperationException(), "Unknown domain model context factory for domain id: "+domainId); //$NON-NLS-1$
//		return null;
//	}
//	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// FIXME!!!!!  The 	ITaglibDomainPredicateMatcherFactory does not belong here!
//
	
	/**
	 * @return {@link ITaglibDomainPredicateMatcherFactory}
	 */
	public ITaglibDomainPredicateMatcherFactory getTaglibDomainPredicateMatcherFactory() {
		//currently only produces one kind of factory 
		return new TaglibDomainRegexQueryMatcherFactory();
	}

	private static class TaglibDomainRegexQueryMatcherFactory implements ITaglibDomainPredicateMatcherFactory {

		public TaglibDomainEntityIdRegexPredicateMatcher createTagLibraryModelMatcher(final String uri) {
			return new TaglibDomainEntityIdRegexPredicateMatcher(new TaglibDomainEntityQuerySpec(uri));
		}
		
		public TaglibDomainEntityIdRegexPredicateMatcher createTagEntityMatcher(final String uri,
				final String tagName) {
			return new TaglibDomainEntityIdRegexPredicateMatcher(new TaglibDomainEntityQuerySpec(uri, tagName));
		}

		public TaglibDomainEntityIdRegexPredicateMatcher createTagAttributeEntityMatcher(final String uri,
				final String tagName, final String tagAttributeName) {
			return new TaglibDomainEntityIdRegexPredicateMatcher(new TaglibDomainEntityQuerySpec(uri, tagName, tagAttributeName));
		}
		
		public IPredicateMatcher<Trait> createTraitMatcher(final String traitId) {
			return new TaglibDomainTraitIdRegexPredicateMatcher(new TaglibDomainTraitQuerySpec(traitId));
		}

		public Object getAdapter(final Class adapter) {
			return null;
		}
				
	}
	
//	private static class DomainModelContextFactoryReader
//		extends
//			AbstractSimpleClassExtensionRegistryReader<IMetaDataDomainModelContextFactory> {
//	
//		private static final String EXT_PT_ID 			= "domainModelContextFactory"; //$NON-NLS-1$
//		private static final String EXT_PT_ELEMENT 		= "factory"; //$NON-NLS-1$
//		private static final String EXT_PT_ATTR 		= "class"; //$NON-NLS-1$
//		
//		/**
//		 * Constructor
//		 */
//		protected DomainModelContextFactoryReader() {
//			super(JSFCommonPlugin.PLUGIN_ID, 
//					EXT_PT_ID, EXT_PT_ELEMENT, EXT_PT_ATTR, 
//					new CompareOrgEclipseJstContributorsLastComparator<IMetaDataDomainModelContextFactory>()
//			);
//		}
//		
//		@Override
//		protected void handleLoadFailure(final CoreException ce) {
//			JSFCommonPlugin.log(ce,
//					"Error loading IMetaDataDomainModelContextFactory from extension"); //$NON-NLS-1$
//		
//		}
//		
//		public IMetaDataDomainModelContextFactory getFactoryFor(final String domainId) {
//			for (final IMetaDataDomainModelContextFactory factory : getExtensions()) {
//				if (factory.getDomainIdentifierFor().equals(domainId)){
//					return factory;
//				}
//			}
//			return null;
//		}
//	}
}
