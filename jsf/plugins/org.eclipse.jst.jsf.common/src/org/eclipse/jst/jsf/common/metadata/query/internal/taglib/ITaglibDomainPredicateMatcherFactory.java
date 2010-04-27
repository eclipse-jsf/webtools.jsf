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

package org.eclipse.jst.jsf.common.metadata.query.internal.taglib;

import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.internal.IPredicateMatcher;
import org.eclipse.jst.jsf.common.metadata.query.internal.IPredicateMatcherFactory;


/**
 * Interface for creating {@link IPredicateMatcher}s for the tag library domain.
 * <p>
 * @noextend
 */
public interface ITaglibDomainPredicateMatcherFactory extends IPredicateMatcherFactory {

	/**
	 * Returns a matcher that will match a tag library specification
	 * @param uri - may not be null
	 * @return {@link ITaglibDomainEntityPredicateMatcher} 
	 */
	public ITaglibDomainEntityPredicateMatcher createTagLibraryModelMatcher(final String uri);
	/**
	 * Returns a matcher that will match a tag specification
	 * @param uri - may not be null
	 * @param tagName - may not be null
	 * @return {@link ITaglibDomainEntityPredicateMatcher} 
	 */
	public ITaglibDomainEntityPredicateMatcher createTagEntityMatcher(final String uri, final String tagName);
	
	/**
	 * Returns a matcher that will match a tag attribute specification
	 * @param uri - may not be null
	 * @param tagName - may not be null
	 * @param tagAttributeName - may not be null
	 * @return {@link ITaglibDomainEntityPredicateMatcher} 
	 */
	public ITaglibDomainEntityPredicateMatcher createTagAttributeEntityMatcher(final String uri, final String tagName, final String tagAttributeName) ;
	
	/**
	 * Returns a matcher that will match the trait id
	 * @param traitId - may not be null
	 * @return {@link IPredicateMatcher} for {@link Trait} 
	 */
	public IPredicateMatcher<Trait> createTraitMatcher(final String traitId);
}
