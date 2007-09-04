/**
 * 
 */
package org.eclipse.jst.pagedesigner.tests.tagcreator.base;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.pagedesigner.commands.CreateItemCommand;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Node;

public class MockCreateItemCommand extends CreateItemCommand
{
    public MockCreateItemCommand(String label, IDOMModel model,
            IDOMPosition position, TagToolPaletteEntry tagItem) {
        super(label, model, position, tagItem);
    }

    @Override
    public void formatNode(Node node) 
    {
        // override to NOOP: we don't want to format the node
    }
    
    public IAdaptable getCustomizationDataTesting()
    {
        return super.getCustomizationData();
    }
}