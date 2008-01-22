package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;


/**
 * A provider of namespaces for a project
 * 
 * @author cbateman
 *
 */
public interface ITagTypeProvider 
{
    /**
     * @param project
     * @return the root elements for the project in the tag type
     * context that this provider services
     */
    Collection<? extends Namespace> getRootElements(IProject project);
}
