/**
 * 
 */
package org.eclipse.jst.pagedesigner.tests.tagcreator.base;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.commands.Command;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.itemcreation.ItemCreationTool;

public class MockItemCreationTool extends ItemCreationTool
{
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
}