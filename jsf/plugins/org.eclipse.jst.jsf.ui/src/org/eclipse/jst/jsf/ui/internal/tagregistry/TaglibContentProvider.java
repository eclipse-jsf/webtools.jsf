package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;

/**
 * Structured content provider for tag libraries.
 * 
 * @author cbateman
 *
 */
public class TaglibContentProvider implements IStructuredContentProvider, 
	ITreeContentProvider
{
    private final static Object[]       NO_CHILDREN = new Object[0];
    
    private IProject  _currentProject;
    
    public Object[] getElements(Object inputElement) 
    {
        return new Object[] {
                new LibraryContextRoot("JSP Tag Library", new JSPTagTypeProvider())
                //, new LibraryContextRoot("Facelet Tag Library", new FaceletTagTypeProvider()
                
        };
    }

    public void dispose() {
        // nothing to do
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
    {
        if (newInput instanceof IProject)
        {
            _currentProject = (IProject) newInput;
            return;
        }

        if (newInput != null)
        {
            throw new IllegalArgumentException("newInput must be instance of IProject but instead got: "+newInput);
        }
    }

    public Object[] getChildren(Object parentElement) 
    {
        if (parentElement instanceof LibraryContextRoot)
        {
            Collection<? extends Namespace> namespaces = 
                ((LibraryContextRoot)parentElement)._typeProvider.getRootElements(_currentProject);
            return namespaces.toArray();
        }
        else if (parentElement instanceof Namespace)
        {
            return ((Namespace)parentElement).getViewElements().toArray();
        }
        else if (parentElement instanceof IComponentTagElement)
        {
            return new Object[] {((IComponentTagElement)parentElement).getComponent()};
        }

        return NO_CHILDREN;
    }

    public Object getParent(Object element) {
        // no support for parent traversal right now
        return null;
    }

    public boolean hasChildren(Object element) 
    {
        return getChildren(element).length > 0;
    }
    
    private static class LibraryContextRoot
    {
        private final String _contextName;
        private final ITagTypeProvider _typeProvider;
        
        LibraryContextRoot(final String contextName, ITagTypeProvider typeProvider)
        {
            _contextName = contextName;
            _typeProvider = typeProvider;
        }

        public String getContextName() {
            return _contextName;
        }

        public ITagTypeProvider getTypeProvider() {
            return _typeProvider;
        }
        
        public String toString()
        {
            return _contextName;
        }
    }
}
