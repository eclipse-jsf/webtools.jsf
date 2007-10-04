/**
 * 
 */
package org.eclipse.jst.pagedesigner.tests.tagcreator.base;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.jst.pagedesigner.commands.CreateItemCommand;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationTool;

public class MockItemCreationTool extends ItemCreationTool
{
    private Command     _cached = null;

    public MockItemCreationTool(TagToolPaletteEntry tagToolPaletteEntryItem) {
        super(tagToolPaletteEntryItem);
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