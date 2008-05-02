/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation;

import java.util.Collection;

import org.eclipse.jst.pagedesigner.dom.DOMPositionHelper;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.itemcreation.command.ContainerCreationCommand;
import org.eclipse.jst.pagedesigner.itemcreation.command.ElementCustomizationCommand;
import org.eclipse.jst.pagedesigner.utils.BodyHelper;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;

/**
 * Clients should sub-class in order to create a new ITagCreationAdvisor type,
 * however, it is preferable to extend DefaultTagCreationAdvisor where possible.
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author cbateman
 *
 */
public abstract class AbstractTagCreationAdvisor implements ITagCreationAdvisor 
{
    /**
     * Information passed by the framework about the tag to be created
     * {@link CreationData}
     */
    protected final CreationData  _creationData;

    /**
     * @param creationData
     */
    public AbstractTagCreationAdvisor(CreationData  creationData)
    {
        this._creationData = creationData;
    }

    /**
     * @param model
     * @param tagElement
     */
    public final void applyCustomization(IDOMModel model, Element tagElement)
    {
        ElementCustomizationCommand command = 
            getElementCustomizationCommand(model, tagElement);
        
        if (command != null && command.canExecute())
        {
            command.execute();
        }
    }

    /**
     * @param model
     * @param tagElement
     * @return the customization command
     */
    protected abstract ElementCustomizationCommand getElementCustomizationCommand(IDOMModel model, Element tagElement);

    /**
     * Return position for tag after all necessary containers are created.  
     * If necessary containers cannot be created and do not exist for any reason,
     * implementer should return null
     * to signal that the tag creation should not occur.
     * 
     * Clients may override this method to provide custom container positioning
     * 
     * @param model
     * @param domPosition
     * @return position after necessary containers are created
     */
    public final IDOMPosition checkAndApplyNecessaryContainers(
            IDOMModel model, IDOMPosition domPosition) 
    {
        IDOMPosition position = DOMPositionHelper.splitText(domPosition);

        position = BodyHelper.adjustInsertPosition
            (_creationData.getUri(), _creationData.getTagName(), position);
        
        //position = getContainerCreationCommands(position);
        ContainerCreationCommand command = getContainerCreationCommand(position);
        
        if (command != null)
        {
            command.execute();
            Collection<IDOMPosition>  collection = command.getResult();
            
            if (collection.size()==1)
            {
                position = collection.iterator().next(); 
            }
            else
            {
                position = null;
            }
        }

        if (position == null) {
            // user cancelled
            return null;
        }

        // because the next call of getPrefix() may insert new taglib node
        // into the document, if we use the normal
        // DOMPositin which use index, maybe it will be invalidated by the
        // new taglib node. So use RefPosition here.
        return DOMPositionHelper.toDOMRefPosition(position);
    }

    /**
     * @param position
     * @return a command (could be CompoundCommand or chained command) that when executed
     * will add any necessary containers.
     */
    protected abstract ContainerCreationCommand getContainerCreationCommand(IDOMPosition position);
}
