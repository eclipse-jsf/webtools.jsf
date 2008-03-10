package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * A blank details form.
 * 
 * @author cbateman
 * 
 */
public class BlankDetailsForm extends AbstractDetailsForm
{
    private Composite _emptyPanel;

    @Override
    public void createContents(Composite parent)
    {
        _emptyPanel = getToolkit().createComposite(parent);
    }

    @Override
    public Control getControl()
    {
        return _emptyPanel;
    }

    @Override
    protected void doUpdateSelection(Object newSelection)
    {
        // do nothing
    }

    @Override
    protected String getTitle()
    {
        return "";
    }

}
