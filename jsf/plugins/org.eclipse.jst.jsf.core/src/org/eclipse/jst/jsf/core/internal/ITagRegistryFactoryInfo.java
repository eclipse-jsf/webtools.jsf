package org.eclipse.jst.jsf.core.internal;

import java.util.Set;

import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory;

/**
 * @author cbateman
 * @noimplement
 */
public interface ITagRegistryFactoryInfo
{
    /**
     * @return the content types that the tag registry supports.
     */
    public abstract Set<IContentType> getContentTypes();

    /**
     * @return the tag registry factory.
     */
    public abstract TagRegistryFactory getTagRegistryFactory();

    /**
     * @return the unique id of the factory.
     */
    public abstract String getId();

    /**
     * @return the description of the tag registry factory.
     */
    public abstract String getDescription();

}
