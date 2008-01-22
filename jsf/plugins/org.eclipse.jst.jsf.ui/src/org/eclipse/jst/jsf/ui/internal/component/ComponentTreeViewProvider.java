package org.eclipse.jst.jsf.ui.internal.component;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * A tree view adapter for a design time component tree rooted at a design time
 * view root.
 * 
 * @author cbateman
 *
 */
public class ComponentTreeViewProvider implements IStructuredContentProvider,
        ITreeContentProvider 
{
    private final static Object[]  NO_CHILDREN = new Object[0];
    
    public Object[] getElements(Object inputElement) 
    {
        if  (inputElement instanceof DTJSFViewModel)
        {
            return new Object[] {((DTJSFViewModel)inputElement).getRoot()};
        }
        return NO_CHILDREN;
    }

    public void dispose() 
    {
        // nothing to dispose
    }

    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) 
    {
        // do nothing
    }

    public Object[] getChildren(Object parentElement) 
    {
        if (parentElement instanceof ComponentInfo)
        {
            List<Object>  children = new ArrayList<Object>();
            children.addAll(((ComponentInfo)parentElement).getChildren());
            children.addAll(((ComponentInfo)parentElement).getAllDecorators());
            return children.toArray();
        }
        return NO_CHILDREN;
    }

    public Object getParent(Object element) 
    {
        // no parent
        return null;
    }

    public boolean hasChildren(Object element) 
    {
        return getChildren(element).length > 0;
    }
}
