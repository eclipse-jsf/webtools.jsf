package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import org.eclipse.core.resources.IProject;

/**
 * The base class for all impls of IProjectTaglibDescriptorFactory.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractProjectTaglibDescriptorFactory implements IProjectTaglibDescriptorFactory 
{

    public abstract IProjectTaglibDescriptor create(IProject project,
            TagRecordFactory factory);
}
