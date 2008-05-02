/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.JSPTagResolvingStrategy;
import org.eclipse.jst.jsf.designtime.tests.views.model.jsp.VerifyRegistryUtil.CompositeVerifier;
import org.eclipse.jst.jsf.designtime.tests.views.model.jsp.VerifyRegistryUtil.Verifier;

public abstract class BaseStrategyTestClass extends BaseTestClass
{
    protected JSPTagResolvingStrategy _strategy;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        _strategy = createStrategy();
    }

    public void testResolveTLDElementDeclaration()
    {
        for (final String uri : getTestUris())
        {
            final Map<String, ITagElement> tags = TestUtil
            .constructTagElements(TestUtil.getTags(_tagRecords
                    .get(uri)), _strategy);

            final List<Verifier> verifiers = createVerifiers(tags, uri);

            final CompositeVerifier compVerifier =
                new CompositeVerifier(verifiers,
                        getExpectedTagCount(uri).getMin(),
                        getExpectedTagCount(uri).getMax());

            compVerifier.verify(tags);
        }
    }

    protected abstract JSPTagResolvingStrategy createStrategy();

    protected abstract List<String> getTestUris();

    protected ExpectedTagCount getExpectedTagCount(final String uri)
    {
        if (ITLDConstants.URI_JSF_CORE.equals(uri))
        {
            switch(_jsfVersion)
            {
                case V1_0:
                case V1_1:
                    return new ExpectedTagCount(18,18);

                case V1_2:
                    return new ExpectedTagCount(20, 20);

                default:
                    throw new IllegalStateException("Unknown version: "+_jsfVersion);
            }
        }
        else if (ITLDConstants.URI_JSF_HTML.equals(uri))
        {
            return new ExpectedTagCount(25,25);
        }

        return null;
    }

    static class ExpectedTagCount
    {
        private final int _min;
        private final int _max;
        public ExpectedTagCount(final int min, final int max)
        {
            super();
            assertTrue(min <= max);
            this._max = max;
            this._min = min;
        }
        public final int getMin()
        {
            return _min;
        }
        public final int getMax()
        {
            return _max;
        }
    }

    @SuppressWarnings("unchecked")
    protected List<Verifier> createVerifiers(
            final Map<String, ITagElement> tagElements, final String uri)
            {
        if (ITLDConstants.URI_JSF_CORE.equals(uri))
        {
            switch (_jsfVersion)
            {
                case V1_0:
                case V1_1:
                    return VerifyRegistryUtil.CORE_VERIFIERS_11;

                case V1_2:
                    return VerifyRegistryUtil.CORE_VERIFIERS_12;

                default:
                    return Collections.EMPTY_LIST;
            }
        }
        else if (ITLDConstants.URI_JSF_HTML.equals(uri))
        {
            return VerifyRegistryUtil.HTML_VERIFIERS;
        }

        return Collections.EMPTY_LIST;
            }
}
