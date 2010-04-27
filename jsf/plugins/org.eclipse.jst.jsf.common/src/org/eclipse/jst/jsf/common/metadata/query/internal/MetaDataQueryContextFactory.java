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

import org.eclipse.core.resources.IProject;
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
 *
 */
public final class MetaDataQueryContextFactory {
	
	private static MetaDataQueryContextFactory INSTANCE = new MetaDataQueryContextFactory();
	
	private MetaDataQueryContextFactory(){
		//read registry for query context and matcher factories here...  future  
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
}
