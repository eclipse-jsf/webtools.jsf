/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
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

package org.eclipse.jst.jsf.common.metadata.query.internal.taglib;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.jst.jsf.common.metadata.Entity;

/**
 * Entity id regex matcher used by the IEntityQueryVisitor
 *
 */
public class TaglibDomainEntityIdRegexPredicateMatcher implements ITaglibDomainEntityPredicateMatcher {
	
	private static final int	LEVEL_UNSET 	= 0;
	private static final int 	LEVEL_MODEL 	= 1;
	private static final int 	LEVEL_TAG 		= 2;
	private static final int 	LEVEL_TAGATTR 	= 3;

	private final Pattern 		_modelKey;
	private final Pattern 		_tagKey;
	private final Pattern 		_tagAttrKey;
	private final int 			_leafLevel;
	private int 				_currentLevel;

	/**
	 * @param spec
	 * @throws PatternSyntaxException 
	 */
	public TaglibDomainEntityIdRegexPredicateMatcher(final TaglibDomainEntityQuerySpec spec) throws PatternSyntaxException {
		_modelKey 		= spec.getKey().getUri() != null ? java.util.regex.Pattern.compile(spec.getKey().getUri()) : null;
		_tagKey 		= spec.getKey().getTagName() != null ? java.util.regex.Pattern.compile(spec.getKey().getTagName()) : null;
		_tagAttrKey 	= spec.getKey().getTagAttributeName() != null ? java.util.regex.Pattern.compile(spec.getKey().getTagAttributeName()) : null;;
		_leafLevel 		= _tagAttrKey != null ? LEVEL_TAGATTR : (_tagKey != null ? LEVEL_TAG : LEVEL_MODEL);
		_currentLevel 	= LEVEL_UNSET;
	}

	public MATCH matches(final Entity entity) {
		if (_currentLevel == LEVEL_MODEL && matches(entity, _modelKey)) {
			return isFullMatch(entity);
		}
		else if (_currentLevel == LEVEL_TAG && matches(entity, _tagKey)){
			return isFullMatch(entity);
		}
		else if (_currentLevel == LEVEL_TAGATTR && matches(entity, _tagAttrKey)){
			return isFullMatch(entity);
		}
		return MATCH.NOT;
	}
	
	private boolean matches(final Entity entity, final Pattern _key) {
		if (_key.matcher(entity.getId()).find())
			return true;
		return false;
	}

	private MATCH isFullMatch(final Entity entity) {
		if (_currentLevel == _leafLevel)
			return MATCH.FULLY;
		return MATCH.PARTIALLY;
	}

	/**
	 * Move up one level in the hierarchy
	 */
	public void popLevel() {
		_currentLevel--;
	}

	/**
	 * Move down one level in the hierarchy
	 */
	public void pushLevel() {
		_currentLevel++;
	}

	public String getUri() {
		return _modelKey.pattern().toString();
	}

	public void reset() {
		_currentLevel = LEVEL_UNSET;
	}

	public int getMaxLevel() {
		return _leafLevel;
	}

	public int getCurrentLevel() {
		return _currentLevel;
	}
	
}
