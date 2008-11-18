/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation.command;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.itemcreation.internal.AbstractCreationCommand;

/**
 * Command for creating containers.
 * 
 *  * <p><b>Provisional API - subject to change</b></p>
 *  
 *  Clients may sub-class, but is preferable to use {@link SingletonContainerCreationCommand}
 *  or {@link TagContainerCreationCommand} instead.
 *  
 * @author cbateman
 *
 */
public abstract class ContainerCreationCommand extends AbstractCreationCommand 
{
    private final IDOMPosition          _originalDomPosition;
    private final TagIdentifier         _containerTag;
    private final TagIdentifier         _tagBeingCreated;
    private IDOMPosition                _domPosition;
    private ContainerCreationCommand    _nextCommand = null;
    
    /**
     * @param domPosition
     * @param containerTag
     */
    /*package*/ ContainerCreationCommand(IDOMPosition domPosition, TagIdentifier containerTag, TagIdentifier tagBeingCreated)
    {
        _domPosition = domPosition;
        _originalDomPosition = domPosition;
        _containerTag = containerTag;
        _tagBeingCreated = tagBeingCreated;
    }
    
    @Override
    public final void execute() 
    {
        _domPosition = doExecute();
        
        if (_nextCommand != null)
        {
            _nextCommand.setDomPosition(_domPosition);
            _nextCommand.execute();
            // update the dom position
            _domPosition = _nextCommand.getDomPosition();
        }
    }

    @Override
    public final Collection<IDOMPosition> getResult() 
    {
        return Collections.singletonList(_domPosition);
    }

    /**
     * @return the new dom position after the container insert
     */
    protected abstract IDOMPosition doExecute();

    @Override
    public final ContainerCreationCommand chain(Command command) 
    {
        if (!(command instanceof ContainerCreationCommand))
        {
            throw new IllegalArgumentException("Can only chain ContainerCreationCommand's"); //$NON-NLS-1$
        }
        
        if (_nextCommand == null)
        {
            _nextCommand = (ContainerCreationCommand) command;
        }
        else
        {
            _nextCommand.chain(command);
        }
        
        return this;
    }

    /**
     * Update the domPosition
     * 
     * @param domPosition
     */
    public final void setDomPosition(final IDOMPosition domPosition)
    {
        if (_domPosition.getContainerNode().getOwnerDocument() == 
                domPosition.getContainerNode().getOwnerDocument())
        {
            _domPosition = domPosition;
        }
        else
        {
            // if not in same document, then error
            throw new IllegalArgumentException("New domPosition must be in same document as old domPosition"); //$NON-NLS-1$
        }
    }

    /**
     * @return the dom position
     */
    protected final IDOMPosition getDomPosition() {
        return _domPosition;
    }

    /**
     * @return the original dom position that this container creation command
     * was created with.
     */
    protected final IDOMPosition getOriginalDomPosition() {
        return _originalDomPosition;
    }

    /**
     * @return the container tag to be created
     */
    protected final TagIdentifier getContainerTag() {
        return _containerTag;
    }

    /**
     * @return the tag that is being created for which we are adding the container
     */
    protected final TagIdentifier getTagBeingCreated() {
        return _tagBeingCreated;
    }
}
