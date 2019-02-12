/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.metadata.tests.updated;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.IResultSet;
import org.eclipse.jst.jsf.common.metadata.query.internal.AbstractMetaDataDomainQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.AbstractMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.IEntityPredicateMatcher;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;
import org.eclipse.jst.jsf.common.metadata.query.internal.ITraitPredicateMatcher;

public class FakeDomainQueryFactory extends AbstractMetaDataDomainQueryFactory {

	public static final String FAKE_MD_DOMAIN = "MyFakeDomain";
	
	public FakeDomainQueryFactory() {
		super(FAKE_MD_DOMAIN);
	}

	public IMetaDataQuery createQuery(IMetaDataDomainContext context) {
		return new AbstractMetaDataQuery(null, context) {

			public IResultSet<Entity> findEntities(
					IEntityPredicateMatcher entityMatcher) {
				// TODO Auto-generated method stub
				return null;
			}

			public IResultSet<Trait> findTraits(
					IEntityPredicateMatcher entityMatcher,
					ITraitPredicateMatcher traitMatcher)
					 {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
