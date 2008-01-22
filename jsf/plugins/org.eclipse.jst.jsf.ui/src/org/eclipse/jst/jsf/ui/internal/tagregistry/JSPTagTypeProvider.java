package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDTagRegistry;


/**
 * A provider of JSP namespaces for project.
 * @author cbateman
 *
 */
public class JSPTagTypeProvider implements ITagTypeProvider 
{
    public Collection<? extends Namespace> getRootElements(IProject project) 
    {
        TLDTagRegistry registry = TLDTagRegistry.getRegistry(project);
        return registry.getAllTagLibraries();
    }
}
