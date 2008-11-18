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
package org.eclipse.jst.pagedesigner.itemcreation.internal;

import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;

/**
 * Internal super-class of all tag creation commands.
 * 
 * @author cbateman
 *
 */
public abstract class AbstractCreationCommand extends AbstractCommand implements
        ICreationCommand 
{
    public abstract void execute(); 

    public final void redo() 
    {
        throw new UnsupportedOperationException("don't use"); //$NON-NLS-1$
    }

    @Override
    public final boolean canExecute() 
    {
        return super.canExecute();
    }

    @Override
    public final boolean canUndo() {
        return super.canUndo();
    }

    @Override
    public Command chain(Command command) {
        if (command instanceof ICreationCommand)
        {
            return super.chain(command);
        }
        throw new IllegalArgumentException("command must an ICreationCommand"); //$NON-NLS-1$
    }

    @Override
    public final void dispose() 
    {
        throw new UnsupportedOperationException("don't use"); //$NON-NLS-1$
    }

    @Override
    public final Collection<?> getAffectedObjects() {
        return super.getAffectedObjects();
    }

    @Override
    public final String getDescription() 
    {
        return super.getDescription();
    }

    @Override
    public final String getLabel() 
    {
        return super.getLabel();
    }

    @Override
    public Collection<?> getResult() 
    {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected abstract boolean prepare(); 

    @Override
    public final void setDescription(String description) 
    {
        super.setDescription(description);
    }

    @Override
    public final void setLabel(String label) 
    {
        super.setLabel(label);
    }

    @Override
    public final void undo() 
    {
        // for now, there is no undo
        super.undo();
    }


}
