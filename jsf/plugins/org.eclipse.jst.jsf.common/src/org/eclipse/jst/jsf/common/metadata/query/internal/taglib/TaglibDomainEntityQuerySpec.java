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

import org.eclipse.jst.jsf.common.metadata.query.internal.IQuerySpec;
import org.eclipse.jst.jsf.common.metadata.query.internal.Key;

/**
 * Implements a tag entity query spec where the entity's id's are matched.
 * <p>
 * The PredicateMatcher will use this class to do an regex match of the id to the value
 *
 */
public final class TaglibDomainEntityQuerySpec implements IQuerySpec {

	private final String _uri;
	private final String _tagName;
	private final String _tagAttrName;
	
	/**
	 * Construct a query spec for a tag library model
	 * @param uri - may not be null
	 */
	public TaglibDomainEntityQuerySpec(final String uri) {
		_uri = uri;
		_tagName = null;
		_tagAttrName = null;
	}
	
	/**
	 * Construct a query spec for a tag
	 * @param uri - may not be null
	 * @param tagName - may not be null
	 */
	public TaglibDomainEntityQuerySpec (final String uri, final String tagName) {
		_uri = uri;
		_tagName = tagName;
		_tagAttrName = null;
	}
	
	/**
	 * Construct a query spec for a tag attribute
	 * @param uri - may not be null
	 * @param tagName - may not be null
	 * @param tagAttributeName - may not be null
	 */
	public TaglibDomainEntityQuerySpec (final String uri, final String tagName, final String tagAttributeName) {
		_uri = uri;
		_tagName = tagName;
		_tagAttrName = tagAttributeName;
	}
	public TagDomainEntityKey getKey() {
		return new TagDomainEntityKey();
	}
	
	class TagDomainEntityKey extends Key {
		
		/**
		 * @return model uri
		 */
		public String getUri() {
			return _uri;
		}

		public String getTagName() {
			return _tagName;
		}
		
		public String getTagAttributeName() {
			return _tagAttrName;
		}
		public Object getAdapter(Class adapter) {			
			return null;
		}
		
	}

}
