/**
 * 
 */
package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.itemcreation.command.ContainerCreationCommand;
import org.eclipse.jst.pagedesigner.itemcreation.command.ElementCustomizationCommand;
import org.eclipse.jst.pagedesigner.itemcreation.command.UserCustomizedContainerCreationCommand;
import org.eclipse.jst.pagedesigner.itemcreation.command.UserCustomizedElementCustomizationCommand;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.ICustomizationData;
import org.eclipse.jst.pagedesigner.itemcreation.customizer.ParentData;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * Tag creation advisor that returns a new ElementCustomizationCommand
 * subclass that will perform customization of attributes, child tags and parent tags 
 * 
 * @author prusev
 * @author Debajit Adhikary
 */
public class UserCustomizedTagCreationAdvisor extends DefaultTagCreationAdvisor {

    /**
     * Default
     * @param creationData
     */
    public UserCustomizedTagCreationAdvisor(CreationData creationData) {
        super(creationData);
    }

    /**
     * @param model
     * @param tagElement
     * @return
     * 
     * Gives back the new command class.
     */
    protected ElementCustomizationCommand getElementCustomizationCommand(
            IDOMModel model, Element tagElement) {
        return new UserCustomizedElementCustomizationCommand(model, tagElement, _creationData);
    }

    
    /** 
     * Performs parent tag customization for the dropped tag. Chains any
     * required container creation commands to ensure that the required parents
     * are created.
     * 
     * (non-Javadoc)
     * 
     * @see org.eclipse.jst.pagedesigner.itemcreation.DefaultTagCreationAdvisor#getContainerCreationCommand (org.eclipse.jst.pagedesigner.dom.IDOMPosition)
     * 
     */
    @Override
    protected ContainerCreationCommand getContainerCreationCommand(IDOMPosition position)
    {
        final IAdaptable adaptable = _creationData.getDropCustomizationData();
        if (adaptable != null)
        {
            final ICustomizationData data = (ICustomizationData) adaptable.getAdapter(ICustomizationData.class);
            ContainerCreationCommand command = null;
            ParentData parentData = data.getParentData();
            
            boolean isFirstParent = true;
            for (final ICustomizationData parentCustomizationData : parentData.getParentCustomizationData())
            {
                TagIdentifier parentTagIdentifier = parentCustomizationData.getTagIdentifier();
                
                if (isFirstParent) 
                {
                    command = new UserCustomizedContainerCreationCommand(position, parentTagIdentifier, _creationData.getTagId(), parentCustomizationData);
                    isFirstParent = false;
                }
                else
                {
                    command.chain(new UserCustomizedContainerCreationCommand(position, parentTagIdentifier, _creationData.getTagId(), parentCustomizationData));
                }
            }
            return command;
        } 
        return null;
    }
}
