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
 * Implements a tag trait query spec where the trait id's are matched.
 * <p>
 * The PredicateMatcher will use this class to do an regex match of the id to the value
 *
 */
public final class TaglibDomainTraitQuerySpec implements IQuerySpec {

	private final String _traidId;
	
	/**
	 * Construct a query spec for a tag library model
	 * @param traidId - may not be null
	 */
	public TaglibDomainTraitQuerySpec(final String traidId) {
		_traidId = traidId;
	}
	
	public TagDomainTraitKey getKey() {
		return new TagDomainTraitKey();
	}
	
	class TagDomainTraitKey extends Key {
		
		/**
		 * @return model traidId
		 */
		public String getTraitId() {
			return _traidId;
		}

		public Object getAdapter(Class adapter) {			
			return null;
		}
		
	}

}
