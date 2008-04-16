package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.jst.jsf.ui.internal.JSFUiPlugin;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class TagRegistryView extends ViewPart
{
    private FormToolkit _toolkit;
    private Form _form;
    private TagRegistryMasterDetailBlock _masterDetailBlock;

    /**
     * The constructor.
     */
    public TagRegistryView()
    {
        // do nothing
    }

    @Override
    public void init(IViewSite site) throws PartInitException
    {
        super.init(site);
        setTitleImage(JSFUiPlugin.getDefault().getImage("obj16/library_obj.gif"));
    }

    /**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
    @Override
    public void createPartControl(final Composite parent)
    {
        final GridLayout  gridLayout= new GridLayout(1,true);
        parent.setLayout(gridLayout);
        _toolkit = new FormToolkit(parent.getDisplay());

        //_form = createScrolledForm(parent);//_toolkit.createScrolledForm(parent);
        _form = _toolkit.createForm(parent);
        _form.setLayoutData(new  GridData(SWT.FILL, SWT.FILL, true,true));
        //_form.getBody().setLayout(new GridLayout(2,true));
        //final ManagedForm managedForm = new ManagedForm(_toolkit, _form);
        _masterDetailBlock =
            new TagRegistryMasterDetailBlock();
        _masterDetailBlock.createContent(_toolkit,_form);
    }

    

    @Override
    public void dispose()
    {
        if (_masterDetailBlock != null)
        {
            _masterDetailBlock.dispose();
            _masterDetailBlock = null;
        }
        super.dispose();
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    @Override
    public void setFocus()
    {
        _form.setFocus();
    }
}