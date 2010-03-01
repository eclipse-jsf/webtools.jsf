package org.eclipse.jst.jsf.core.tests.strategy;

import java.util.regex.Pattern;

import org.eclipse.jst.jsf.common.internal.strategy.IIdentifiableStrategy;

class ConcatStrategy implements IIdentifiableStrategy<String, String, String>
{
    private final Pattern _pattern;
    private final String _concat;
    private final String _id;

    public ConcatStrategy(final Pattern pattern, final String toConcat, final String id)
    {
        _pattern = pattern;
        _concat = toConcat;
        _id = id;
    }

    public String perform(String input) throws Exception
    {
        if (_pattern.matcher(input).matches())
        {
            return input.concat(_concat);
        }
        return getNoResult();
    }

    public String getNoResult()
    {
        return null;
    }

    public String getId()
    {
        return _id;
    }

    public String getDisplayName()
    {
        return "Concat strategy, id="+getId();
    }
    
}