package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
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

    //private DrillDownAdapter _drillDownAdapter;
    //    private Action           doubleClickAction;

    /**
     * The constructor.
     */
    public TagRegistryView()
    {
        // do nothing
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

        // Create the help context id for the viewer's control

        //        makeActions();
        //hookContextMenu();
        //hookDoubleClickAction();
        //        contributeToActionBars();
    }

    
//    private ScrolledForm  createScrolledForm(final Composite parent)
//    {
//        final int orientation = Window.getDefaultOrientation();
//        final ScrolledForm form = new ScrolledForm(parent, SWT.H_SCROLL | SWT.V_SCROLL|orientation);
//        form.setExpandHorizontal(true);
//        form.setExpandVertical(true);
//        form.setBackground(_toolkit.getColors().getBackground());
//        form.setForeground(_toolkit.getColors().getColor(IFormColors.TITLE));
//        form.setFont(JFaceResources.getHeaderFont());
//        return form;
//    }

    //    private void contributeToActionBars()
    //    {
    //        final IActionBars bars = getViewSite().getActionBars();
    //        fillLocalPullDown(bars.getMenuManager());
    //        fillLocalToolBar(bars.getToolBarManager());
    //    }

    //    private void fillLocalPullDown(final IMenuManager manager)
    //    {
    //        manager.add(_selectProjectAction);
    //        manager.add(new Separator());
    //        manager.add(action2);
    //    }
    //    private void hookContextMenu()
    //    {
    //        final MenuManager menuMgr = new MenuManager("#PopupMenu");
    //        menuMgr.setRemoveAllWhenShown(true);
    //        menuMgr.addMenuListener(new IMenuListener()
    //        {
    //            public void menuAboutToShow(final IMenuManager manager)
    //            {
    //                TagRegistryView.this.fillContextMenu(manager);
    //            }
    //        });
    //    }

    //    private void fillContextMenu(final IMenuManager manager)
    //    {
    //        manager.add(_selectProjectAction);
    //        manager.add(action2);
    //        manager.add(new Separator());
    //        //_drillDownAdapter.addNavigationActions(manager);
    //        // Other plug-ins can contribute there actions here
    //        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    //    }

    //    private void fillLocalToolBar(final IToolBarManager manager)
    //    {
    //        manager.add(_selectProjectAction);
    //        manager.add(action2);
    //        manager.add(new Separator());
    //        //_drillDownAdapter.addNavigationActions(manager);
    //    }



    //    private void hookDoubleClickAction()
    //    {
    //        _viewer.addDoubleClickListener(new IDoubleClickListener()
    //        {
    //            public void doubleClick(final DoubleClickEvent event)
    //            {
    //                doubleClickAction.run();
    //            }
    //        });
    //    }

    //    private void showMessage(final String message)
    //    {
    //        MessageDialog.openInformation(_viewer.getControl().getShell(),
    //                "Sample View", message);
    //    }

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