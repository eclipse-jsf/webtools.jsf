/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.jst.pagedesigner.tests.tagcreator.base;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.jst.pagedesigner.commands.CreateItemCommand;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationTool;

public class MockItemCreationTool extends ItemCreationTool
{
    private Command     _cached = null;

    public MockItemCreationTool(ITagDropSourceData tagDropSourceData) {
        super(tagDropSourceData);
    }

    @Override
    public void customizeDropAndMaybeExecute(int button) {
        super.customizeDropAndMaybeExecute(button);
    }

    @Override
    protected void setCurrentCommand(Command c) {
        super.setCurrentCommand(c);
    }

    @Override
    public EditDomain getDomain() {
        return super.getDomain();
    }

    public CreateItemCommand getExecutedCommand()
    {
        return (CreateItemCommand) _cached;
    }
    
    @Override
    protected void executeCurrentCommand() {
        // trap the current command because executeCurrentCommand
        // will null it after execution
        _cached = getCurrentCommand();
        super.executeCurrentCommand();
    }
    
}