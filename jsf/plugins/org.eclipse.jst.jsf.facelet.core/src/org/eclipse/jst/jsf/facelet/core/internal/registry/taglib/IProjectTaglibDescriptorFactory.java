package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import org.eclipse.core.resources.IProject;

/**
 * Implemented by factories that create new IProjectTaglibDescriptor objects.
 * 
 * @author cbateman
 *
 */
public interface IProjectTaglibDescriptorFactory
{
    /**
     * @param project
     * @param factory
     * @param jarProvider
     * @param webAppProvider
     * @param vcQuery
     * @return a new taglib descriptor.
     */
    IProjectTaglibDescriptor create(final IProject project, final TagRecordFactory factory);
}
