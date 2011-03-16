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

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.internal.IPredicateMatcher;

/**
 * 
 *
 */
public class TaglibDomainTraitIdRegexPredicateMatcher implements IPredicateMatcher<Trait> {

	/**
	 * id 
	 */
	protected final Pattern _idPattern;

	/**
	 * Constructor
	 * @param traitSpec
	 * @throws PatternSyntaxException
	 */
	public TaglibDomainTraitIdRegexPredicateMatcher(final TaglibDomainTraitQuerySpec traitSpec) throws PatternSyntaxException {
		_idPattern = Pattern.compile(traitSpec.getKey().getTraitId());
	}

	public MATCH matches(final Trait trait) {
		if (_idPattern.matcher(trait.getId()).find())
			return MATCH.FULLY;
		return MATCH.NOT;
	}

	public void reset() {
		//nothing to do
	}

}
