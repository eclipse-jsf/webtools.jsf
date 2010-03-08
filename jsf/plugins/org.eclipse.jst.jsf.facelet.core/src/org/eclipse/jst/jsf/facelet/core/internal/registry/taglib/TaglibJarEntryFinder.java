package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.jar.JarEntry;
import java.util.regex.Pattern;


/**
 * @author cbateman
 *
 */
public class TaglibJarEntryFinder extends TaglibFinder<JarEntry, JarEntry>
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
}