package org.eclipse.jst.jsf.core.internal;

import java.util.Set;

/**
 * Sub-classed by all tag registry factories
 * @author cbateman
 *
 */
public abstract class AbstractTagRegistryFactoryProvider implements ITagRegistryFactoryProvider
{
    public abstract Set<ITagRegistryFactoryInfo> getTagRegistryFactories();

}
