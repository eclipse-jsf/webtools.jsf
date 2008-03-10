package org.eclipse.jst.jsf.common.ui.internal.preferences;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.common.internal.policy.OrderedListProvider;
import org.eclipse.jst.jsf.common.internal.policy.OrderedListProvider.OrderableObject;
import org.eclipse.jst.jsf.common.ui.internal.utils.PixelConverter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * Creates a composite with a checked table viewer and up/down buttons that
 * allow ordering and enablement of a list of items in the table.
 * 
 * Largely derived from
 * org.eclipse.jdt.internal.ui.preferences.CodeAssistAdvancedConfigurationBlock
 * 
 * @author cbateman
 * 
 */
public class StrategyOrderingPanel
{
    private CheckboxTableViewer       _viewer;
    private Button                    fUpButton;
    private Button                    fDownButton;

    private final OrderedListProvider _provider;
    private final ITableLabelProvider _labelProvider;
    private final String              _title;

    /**
     * @param provider
     * @param labelProvider
     * @param title 
     */
    public StrategyOrderingPanel(final OrderedListProvider provider,
            final ITableLabelProvider labelProvider, final String title)
    {
        _provider = provider;
        _labelProvider = labelProvider;
        _title = title;
    }

    /**
     * @param parent
     * @return the control
     */
    public Control createContents(final Composite parent)
    {
        final Composite panel = new Composite(parent, SWT.NONE);
        final GridLayout layout = new GridLayout(2, false);
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        panel.setLayout(layout);

        final Label titleLabel = new Label(panel, SWT.NONE);
        titleLabel.setText(_title);
        final GridData gridData = new GridData(GridData.FILL,
                GridData.BEGINNING, true, false, 2, 1);
        titleLabel.setLayoutData(gridData);

        createTableViewer(panel);
        createButtonList(panel);

        return panel;
    }

    /**
     * Refresh the UI from model. Must not be called before createContents
     */
    public void refresh()
    {
        _provider.resetOrderedObjects();
        _viewer.refresh();
        for (final OrderableObject object : _provider.getOrderedObjects())
        {
            _viewer.setChecked(object, object.isEnabled());
        }
    }

    private void createTableViewer(final Composite parent)
    {
        _viewer = CheckboxTableViewer.newCheckList(parent, SWT.SINGLE
                | SWT.BORDER);
        final Table table = _viewer.getTable();
        table.setHeaderVisible(false);
        table.setLinesVisible(false);
        table.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING,
                true, false, 1, 1));

        final TableColumn nameColumn = new TableColumn(table, SWT.NONE);
        // nameColumn
        // .setText(PreferencesMessages.CodeAssistAdvancedConfigurationBlock_separate_table_category_column_title);
        nameColumn.setResizable(false);

        _viewer.setContentProvider(new ContentProvider());

        _viewer.setLabelProvider(_labelProvider);
        _viewer.setInput(_provider);

        final int ICON_AND_CHECKBOX_WITH = 50;
        final int HEADER_MARGIN = 20;
        int minNameWidth = computeWidth(table, nameColumn.getText())
                + HEADER_MARGIN;
        for (int i = 0; i < _provider.getOrderedObjects().size(); i++)
        {
            minNameWidth = Math.max(minNameWidth, computeWidth(table,
                    _labelProvider.getColumnText(_provider.getOrderedObjects()
                            .get(i), 0))
                    + ICON_AND_CHECKBOX_WITH);
        }

        nameColumn.setWidth(minNameWidth);

        _viewer.addCheckStateListener(new ICheckStateListener()
        {
            public void checkStateChanged(final CheckStateChangedEvent event)
            {
                final boolean checked = event.getChecked();
                final OrderableObject element = (OrderableObject) event
                        .getElement();
                element.setEnabled(checked);
            }
        });

        table.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(final SelectionEvent e)
            {
                handleTableSelection();
            }
        });

    }

    private void createButtonList(final Composite parent)
    {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING,
                false, false));

        final GridLayout layout = new GridLayout();
        layout.marginWidth = 0;
        layout.marginHeight = 0;
        composite.setLayout(layout);

        fUpButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        fUpButton.setText(OrderingMessages.Ordering_Up);
        fUpButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(final SelectionEvent e)
            {
                final int index = getSelectionIndex();
                if (index != -1)
                {
                    final OrderableObject object = _provider
                            .getOrderedObjects().get(index);
                    _provider.moveUp(object);
                    _viewer.refresh();
                    handleTableSelection();
                }
            }
        });
        fUpButton.setLayoutData(new GridData());
        setButtonDimensionHint(fUpButton);

        fDownButton = new Button(composite, SWT.PUSH | SWT.CENTER);
        fDownButton.setText(OrderingMessages.Ordering_Down);
        fDownButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(final SelectionEvent e)
            {
                final int index = getSelectionIndex();
                if (index != -1)
                {
                    final OrderableObject object = _provider
                            .getOrderedObjects().get(index);
                    _provider.moveDown(object);
                    _viewer.refresh();
                    handleTableSelection();
                }
            }
        });
        fDownButton.setLayoutData(new GridData());
        setButtonDimensionHint(fDownButton);
    }

    private int computeWidth(final Control control, final String name)
    {
        if (name == null)
        {
            return 0;
        }
        final GC gc = new GC(control);
        try
        {
            gc.setFont(JFaceResources.getDialogFont());
            return gc.stringExtent(name).x + 10;
        }
        finally
        {
            gc.dispose();
        }
    }

    private void handleTableSelection()
    {
        final OrderableObject item = getSelectedItem();
        if (item != null)
        {
            final int index = getSelectionIndex();
            fUpButton.setEnabled(index > 0);
            fDownButton
                    .setEnabled(index < _provider.getOrderedObjects().size() - 1);
        }
        else
        {
            fUpButton.setEnabled(false);
            fDownButton.setEnabled(false);
        }
    }

    private OrderableObject getSelectedItem()
    {
        return (OrderableObject) ((IStructuredSelection) _viewer.getSelection())
                .getFirstElement();
    }

    private int getSelectionIndex()
    {
        return _viewer.getTable().getSelectionIndex();
    }

    /**
     * Returns a width hint for a button control.
     */
    private static int getButtonWidthHint(final Button button)
    {
        button.setFont(JFaceResources.getDialogFont());
        PixelConverter converter = new PixelConverter(button);
        final int widthHint = converter
                .convertHorizontalDLUsToPixels(IDialogConstants.BUTTON_WIDTH);
        return Math.max(widthHint, button.computeSize(SWT.DEFAULT, SWT.DEFAULT,
                true).x);
    }

    /**
     * Sets width and height hint for the button control. <b>Note:</b> This is
     * a NOP if the button's layout data is not an instance of
     * <code>GridData</code>.
     * 
     * @param button
     *            the button for which to set the dimension hint
     */
    private static void setButtonDimensionHint(final Button button)
    {
        Assert.isNotNull(button);
        final Object gd = button.getLayoutData();
        if (gd instanceof GridData)
        {
            ((GridData) gd).widthHint = getButtonWidthHint(button);
            ((GridData) gd).horizontalAlignment = GridData.FILL;
        }
    }

    private static class ContentProvider implements IStructuredContentProvider
    {

        public Object[] getElements(final Object inputElement)
        {
            if (inputElement instanceof OrderedListProvider)
            {
                return ((OrderedListProvider) inputElement).getOrderedObjects()
                        .toArray();
            }
            return new Object[0];
        }

        public void dispose()
        {
            // do nothing
        }

        public void inputChanged(final Viewer viewer, final Object oldInput,
                final Object newInput)
        {
            // do nothing
        }

    }
}
