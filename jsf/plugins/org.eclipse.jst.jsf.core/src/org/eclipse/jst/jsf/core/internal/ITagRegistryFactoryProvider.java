package org.eclipse.jst.jsf.core.internal;

import java.util.Set;

/**
 * The factory provider interface for tag registries.
 *
 */
public interface ITagRegistryFactoryProvider
{
    /**
     * @return the set of tag registry factories to be used to construct
     * tag registries.
     */
    Set<ITagRegistryFactoryInfo>  getTagRegistryFactories();
}
