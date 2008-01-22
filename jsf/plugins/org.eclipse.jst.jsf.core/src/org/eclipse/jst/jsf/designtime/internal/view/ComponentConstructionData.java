package org.eclipse.jst.jsf.designtime.internal.view;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

/**
 * Encapsulates information required to construct a component
 * 
 * @author cbateman
 * 
 */
public class ComponentConstructionData
{
    private ComponentInfo _parent;
    private int _idCounter;
    private final IProject _project;

    /**
     * @param idCounter
     * @param parent
     * @param project
     */
    public ComponentConstructionData(final int idCounter,
            final ComponentInfo parent, final IProject project)
    {
        _parent = parent;
        _idCounter = idCounter;
        _project = project;
    }

    /**
     * @return the parent id
     */
    public final ComponentInfo getParent()
    {
        return _parent;
    }

    /**
     * @return the id counter
     */
    public final int getIdCounter()
    {
        return _idCounter;
    }

    /**
     * Increment the counter by 1 and return the old value
     * 
     * @return the old value
     */
    public final int increment()
    {
        return _idCounter++;
    }

    /**
     * @param parent
     */
    public final void setParent(final ComponentInfo parent)
    {
        _parent = parent;
    }

    /**
     * @param idCounter
     */
    public final void setIdCounter(final int idCounter)
    {
        _idCounter = idCounter;
    }

    /**
     * @return the project that this component construction is associated with
     */
    public final IProject getProject()
    {
        return _project;
    }
}
