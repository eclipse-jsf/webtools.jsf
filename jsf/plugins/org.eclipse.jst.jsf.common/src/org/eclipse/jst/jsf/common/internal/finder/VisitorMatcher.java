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
package org.eclipse.jst.jsf.common.internal.finder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A matcher finder that uses a visitor to traverse its INPUT and match values
 * using its matchers. An optional value resolver can be used to get the
 * matching value used.
 * 
 * INPUT need not implement it's own visitation interface. Rather, an instance
 * of MatchingVisitor must be provided to handle this.
 * 
 * @author cbateman
 * @param <INPUT>
 * @param <VISITTYPE>
 * @param <IDTYPE>
 * 
 */
public class VisitorMatcher<INPUT, VISITTYPE, IDTYPE> extends
        AbstractMatcher<INPUT, Collection<? extends VISITTYPE>, IDTYPE>
{
    private final MatchingAcceptor _acceptor;

    /**
     * @param id
     * @param displayName
     * @param acceptor
     * @param matchers 
     */
    public VisitorMatcher(IDTYPE id, String displayName,
            final MatchingAcceptor<INPUT, VISITTYPE> acceptor,
            final List<? extends IMatcher> matchers)
    {
        super(id, displayName, Collections.EMPTY_LIST, matchers);
        _acceptor = acceptor;
    }

    @Override
    public Collection<? extends VISITTYPE> perform(final INPUT input) throws Exception
    {
        MatchingVisitor visitor = new MatchingVisitor(getMatchers());
        _acceptor.accept(input, visitor);
        return visitor.getFoundMatches();
    }

    /**
     * Call visit on each VISITTYPE. Sub-classes must provide implementations of
     * getInputChildren and getVisitableChildren to control what gets visited
     * from the INPUT root.
     * 
     * @param <INPUT>
     * @param <VISITTYPE>
     */
    public abstract static class MatchingAcceptor<INPUT, VISITTYPE>
    {
        private void accept(final INPUT input,
                final MatchingVisitor<VISITTYPE> visitor)
        {
            final Collection<? extends VISITTYPE> inputChildren = getInputChildren(input);
            accept(visitor, inputChildren);
        }

        private void accept(final MatchingVisitor<VISITTYPE> visitor,
                final Collection<? extends VISITTYPE> inputChildren)
        {
            for (final VISITTYPE visitable : inputChildren)
            {
                visitor.visit(visitable);
                accept(visitor, getVisitableChildren(visitable));
            }
        }

        /**
         * @param inputType
         * @return the first level children of INPUT to be visited.
         */
        protected abstract Collection<? extends VISITTYPE> getInputChildren(
                INPUT inputType);

        /**
         * @param visitType
         * @return the visitable children of visitType.
         */
        protected abstract Collection<? extends VISITTYPE> getVisitableChildren(
                VISITTYPE visitType);
    }

    private static final class MatchingVisitor<VISITTYPE>
    {
        private final List<IMatcher> _matchers;
        private final List<VISITTYPE>  _foundMatches = new ArrayList<VISITTYPE>();
        public MatchingVisitor(final List<IMatcher> matcher)
        {
            _matchers = matcher;
        }

        public void visit(final VISITTYPE visitable)
        {
            MATCH_LOOP: for (final IMatcher matcher : _matchers)
            {
                if (matcher.matches(visitable))
                {
                    _foundMatches.add(visitable);
                    break MATCH_LOOP;
                }
            }
        }

        protected final List<VISITTYPE> getFoundMatches()
        {
            return _foundMatches;
        }
    }
}
