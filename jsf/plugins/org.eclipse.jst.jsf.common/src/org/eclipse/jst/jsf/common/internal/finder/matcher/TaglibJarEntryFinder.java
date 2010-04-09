package org.eclipse.jst.jsf.common.internal.finder.matcher;

import java.util.jar.JarEntry;
import java.util.regex.Pattern;

import org.eclipse.jst.jsf.common.JSFCommonPlugin;
import org.eclipse.jst.jsf.common.internal.finder.AbstractMatcher.IMatcher;


/**
 * @author cbateman
 *
 */
public class TaglibJarEntryFinder extends TaglibFinder<JarEntry, JarEntry> implements IMatcher
{
    /**
     * Unique id of this strategy.
     */
    public static final String ID = TaglibJarEntryFinder.class.getCanonicalName();
    private static final String DISPLAY_NAME = "Display Name"; //$NON-NLS-1$
    private final Pattern _pattern;
    
    /**
     * @param pattern
     */
    public TaglibJarEntryFinder(final Pattern pattern)
    {
        super(ID, DISPLAY_NAME, null);
        _pattern = pattern;
    }

    @Override
    public JarEntry perform(JarEntry input) throws Exception
    {
        if (_pattern.matcher(input.getName()).matches())
        {
            return input;
        }
        return getNoResult();
    }

    public boolean matches(Object matchThis)
    {
        if (matchThis instanceof JarEntry)
        {
            try
            {
                return perform((JarEntry) matchThis) != getNoResult();
            } catch (Exception e)
            {
                JSFCommonPlugin.log(e, "While matching jar entry: "+matchThis); //$NON-NLS-1$
            }
        }
        return false;
    }
}