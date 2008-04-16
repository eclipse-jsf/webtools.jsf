package org.eclipse.jst.jsf.common.ui.internal.form;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractMasterForm
{

    private final FormToolkit         _toolkit;
    private ISelectionChangedListener _listener;
    private ToolBarManager            _toolBarManager;

    /**
     * @param toolkit
     */
    protected AbstractMasterForm(FormToolkit toolkit)
    {
        super();
        _toolkit = toolkit;
    }

    /**
     * @param listener
     *            the selection listener that is signalled to indicate the
     *            selection in the master has changed and the detail should be
     *            updated.
     */
    public final void initialize(final ISelectionChangedListener listener)
    {
        _listener = listener;
        doInitialize();
    }

    /**
     * It is safe to call getListener() and get getToolkit() in this method. All
     * other methods should be considered unavailable.
     */
    protected void doInitialize()
    {
        // do nothing by default; override to do customize init
    }

    /**
     * @param parent
     * @return the client area for the master form, using parent is the parent
     * control.
     */
    public abstract Control createClientArea(final Composite parent);

    /**
     * @param form
     */
    public final void createHead(final Form form)
    {
        final Composite head = form.getHead();
        final Composite container = getToolkit().createComposite(head);
        container.setLayout(new RowLayout());

        // sub-class contribution
        contributeToHeadArea(getToolkit(), container);

        _toolBarManager = new ToolBarManager(SWT.FLAT);
        ToolBar toolbar = _toolBarManager.createControl(container);
        // _toolkit.adapt(toolbar, false, false);

        toolbar.setBackground(form.getHead().getBackground());
        toolbar.setForeground(form.getHead().getForeground());
        //toolbar.setCursor(FormsResources.getHandCursor());
        container.addDisposeListener(new DisposeListener()
        {
            public void widgetDisposed(DisposeEvent e)
            {
                if (_toolBarManager != null)
                {
                    _toolBarManager.dispose();
                    _toolBarManager = null;
                }
            }
        });

        form.setHeadClient(container);

    }

    /**
     * Override to add client area before the toolbar.
     * 
     * @param toolkit
     * @param container
     */
    protected void contributeToHeadArea(FormToolkit toolkit, Composite container)
    {
        // do nothing by default
    }

    /**
     * @param formManager
     */
    public final void contributeActions(final IToolBarManager formManager)
    {
        contributeActions(formManager, _toolBarManager);
    }

    /**
     * @param formManager
     *            adds to the toolkit's toolbar
     * @param localManager
     *            adds to AbstractMasterForm's toolbar.
     */
    protected void contributeActions(final IToolBarManager formManager,
            final IToolBarManager localManager)
    {
        // do nothing by default. Override to add actions.
    }

    /**
     * Should be called at any time after initialize and createContents when the
     * owner is finished with the master-detail form block.
     */
    public void dispose()
    {
        // do nothing by default;
    }

    /**
     * @return the tool kit in use.
     */
    protected final FormToolkit getToolkit()
    {
        return _toolkit;
    }

    /**
     * @return the selection listener
     */
    protected final ISelectionChangedListener getListener()
    {
        return _listener;
    }
}