package org.eclipse.jst.pagedesigner.itemcreation.command;

import javax.xml.namespace.QName;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.dom.ValidatorSupport;

/**
 * @author cbateman
 * 
 *  * <p><b>Provisional API - subject to change</b></p>
 *  
 *  Clients may sub-class
 *
 */
public class TagContainerCreationCommand extends ContainerCreationCommand {

    /**
     * @param domPosition
     * @param containerTag
     * @param tagBeingCreated
     */
    public TagContainerCreationCommand(IDOMPosition domPosition,
            TagIdentifier containerTag, TagIdentifier tagBeingCreated) 
    {
        super(domPosition, containerTag, tagBeingCreated);
    }

    @Override
    protected IDOMPosition doExecute() 
    {
        final IDOMPosition domPosition = getDomPosition();
        final QName  containerQName = getContainerTag().asQName();
        boolean hasform = ValidatorSupport.checkContainer(domPosition, containerQName);
        IDOMPosition newPosition = domPosition;
        if (!hasform) {
            newPosition = ValidatorSupport
                    .insertContainer(domPosition, containerQName, getContainerCustomizationData());
            if (newPosition == null) {
                newPosition = domPosition;
            }
        }
        return newPosition;
    }

    /**
     * Override to inject customization data into the tag creation
     * @return a customization data for the container.
     */
    protected IAdaptable getContainerCustomizationData()
    {
        return null;
    }
    
    @Override
    protected final boolean prepare()
    {
        throw new UnsupportedOperationException("don't use");
    }
}
