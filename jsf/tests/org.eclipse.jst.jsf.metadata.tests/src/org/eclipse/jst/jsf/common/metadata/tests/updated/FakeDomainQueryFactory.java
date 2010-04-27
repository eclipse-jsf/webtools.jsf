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
