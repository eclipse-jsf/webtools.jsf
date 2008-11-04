package org.eclipse.jst.pagedesigner.itemcreation.command;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;

public class UserCustomizedContainerCreationCommand extends
        TagContainerCreationCommand
{
    private final IAdaptable    _data;
    
    public UserCustomizedContainerCreationCommand(IDOMPosition domPosition,
            TagIdentifier containerTag, TagIdentifier tagBeingCreated, IAdaptable data)
    {
        super(domPosition, containerTag, tagBeingCreated);
        _data = data;
    }

    @Override
    protected IAdaptable getContainerCustomizationData()
    {
        return _data;
    }

}
