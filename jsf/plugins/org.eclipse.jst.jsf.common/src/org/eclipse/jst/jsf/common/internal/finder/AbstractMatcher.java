package org.eclipse.jst.jsf.common.internal.finder;

import java.util.Collections;
import java.util.List;

/**
 * A finder that finds it's OUTPUT in INPUT by using a matching strategy.
 * @author cbateman
 * @param <INPUT> 
 * @param <OUTPUT> 
 * @param <IDTYPE> 
 *
 */
public abstract class AbstractMatcher<INPUT, OUTPUT, IDTYPE> extends AbstractFinder<INPUT, OUTPUT, IDTYPE>
{
    private final List<? extends IMatcher> _matchers;

    /**
     * An interface that defines a match.
     *
     */
    public interface IMatcher
    {
        /**
         * @param matchThis
         * @return true if T matches the expected critieria
         */
        boolean matches(Object matchThis);
    }
    
    /**
     * A matcher that matches always.
     * @author cbateman
     *
     */
    public static final class AlwaysMatcher implements IMatcher
    {
        public boolean matches(Object matchThis)
        {
            return true;
        }
    }

    /**
     * @param id
     * @param displayName
     * @param noResultValue
     * @param matchers
     */
    public AbstractMatcher(final IDTYPE id, final String displayName, final OUTPUT noResultValue, final List<? extends IMatcher>  matchers)
    {
        super(id, displayName, noResultValue);
        _matchers = matchers;
    }

    @Override
    public abstract OUTPUT perform(INPUT input) throws Exception;

    /**
     * @return the matchers
     */
    protected final List<IMatcher> getMatchers()
    {
        return Collections.unmodifiableList(_matchers);
    }
}
