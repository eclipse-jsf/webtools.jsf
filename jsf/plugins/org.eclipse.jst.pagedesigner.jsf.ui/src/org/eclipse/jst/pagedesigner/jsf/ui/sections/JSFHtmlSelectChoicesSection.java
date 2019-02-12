/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.sections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.commands.single.AddSubNodeCommand;
import org.eclipse.jst.pagedesigner.commands.single.ChangeAttributeCommand;
import org.eclipse.jst.pagedesigner.commands.single.InsertSubNodeCommand;
import org.eclipse.jst.pagedesigner.commands.single.RemoveSubNodeCommand;
import org.eclipse.jst.pagedesigner.properties.BaseCustomSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFHtmlSelectChoicesSection extends BaseCustomSection
{
    final private static String[] CHOICETYPES = { IJSFConstants.TAG_SELECTITEM, IJSFConstants.TAG_SELECTITEMS};
    private Table                 _choiceTable;
    private TableViewer           _choiceViewer;
    private CCombo                _choiceTypeCombo;
    private Button                _choiceAddButton, _choiceRemoveButton, _choiceMoveUpButton, _choiceMoveDownButton;
    private static final String[] COLUMN_NAMES = new String[] {"choices","itemLabel", "itemValue", "id"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

    private class ChoiceCotentLabelProvider implements IStructuredContentProvider, ITableLabelProvider
    {

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(final Object inputElement)
        {
            final IDOMElement root = _element;
            final List result = new ArrayList();

            final NodeList children = root.getChildNodes();
            for (int i = 0, n = children.getLength(); i < n; i++)
            {
                final Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE)
                {
                    final IDOMElement element = (IDOMElement) child;
                    final String nodeName = element.getNodeName();
                    if (nodeName.indexOf("select") != -1) //$NON-NLS-1$
                    {
                        result.add(child);
                    }
                }
            }

            if (result.isEmpty())
            {
                return new Object[0];
            }

            return result.toArray(new IDOMElement[result.size()]);
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
         */
        public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput)
        {
            // do nothing
        }

        public String getColumnText(final Object element, final int columnIndex)
        {
            String result = null;
            if (element instanceof Node)
            {
                final Node node = (Node) element;
                Node attrNode = null;
                String attrName = null;
                if (columnIndex == 1)
                {
                    attrName = "itemLabel"; //$NON-NLS-1$
                }
                else if (columnIndex == 2)
                {
                    attrName = "itemValue"; //$NON-NLS-1$
                }
                else if (columnIndex == 3)
                {
                    attrName = "id"; //$NON-NLS-1$
                }

                switch (columnIndex)
                {
                    case 0:
                        result = node.getNodeName();
                        break;
                    default:
                    {
                        attrNode = node.getAttributes().getNamedItem(attrName);
                        if (attrNode != null)
                        {
                            result = attrNode.getNodeValue()!=null ? attrNode.getNodeValue() : " - "; //$NON-NLS-1$
                        }
                        else
                        {
                            result = " - "; //$NON-NLS-1$
                        }
                    }
                }
            }
            return result != null ? result : ""; //$NON-NLS-1$
        }

        public void dispose()
        {
            // do nothing
        }

        public Image getColumnImage(final Object element, final int columnIndex)
        {
            return null;
        }

        public void addListener(final ILabelProviderListener listener)
        {
            // TODO: no support for listeners?
        }

        public boolean isLabelProperty(final Object element, final String property)
        {
            return false;
        }

        public void removeListener(final ILabelProviderListener listener)
        {
            // TODO: no support for listeners?
        }
    }

    /**
     * Default constructor
     */
    public JSFHtmlSelectChoicesSection()
    {
        super();
    }

    @Override
    public void createControls(final Composite parent, final TabbedPropertySheetPage aTabbedPropertySheetPage)
    {
        super.createControls(parent, aTabbedPropertySheetPage);
        final TabbedPropertySheetWidgetFactory factory = aTabbedPropertySheetPage.getWidgetFactory();
        final Composite top = factory.createFlatFormComposite(parent);

        final GridLayout layout = new GridLayout();
        layout.numColumns = 4;
        top.setLayout(layout);

        createChoicePart(factory, top);
    }

    /**
     * @param factory
     * @param other
     */
    private void createChoicePart(final TabbedPropertySheetWidgetFactory factory, final Composite other)
    {
        GridData data;
        _choiceTable = factory.createTable(other, SWT.FULL_SELECTION | SWT.MULTI);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 3;
        data.verticalSpan = 5;
        data.heightHint = 50;
        _choiceTable.setHeaderVisible(true);
        _choiceTable.setLayoutData(data);
        _choiceTable.setLinesVisible(true);

        final TableColumn choiceColumn = new TableColumn(_choiceTable, SWT.NONE);
        choiceColumn.setText(SectionResources.getString("JSFHtmlSelectManyCheckboxSection.Choices")); //$NON-NLS-1$
        choiceColumn.setWidth(100);

        final TableColumn labelColumn = new TableColumn(_choiceTable, SWT.NONE);
        labelColumn.setText("itemLabel"); //$NON-NLS-1$
        labelColumn.setWidth(100);

        final TableColumn valueColumn = new TableColumn(_choiceTable, SWT.NONE);
        valueColumn.setText("itemValue"); //$NON-NLS-1$
        valueColumn.setWidth(100);

        final TableColumn idColumn = new TableColumn(_choiceTable, SWT.NONE);
        idColumn.setText("id"); //$NON-NLS-1$
        idColumn.setWidth(100);

        _choiceViewer = new TableViewer(_choiceTable);
        _choiceViewer.setColumnProperties(COLUMN_NAMES );
        final CellEditor[] editors = new CellEditor[4];
        TextCellEditor textEditor = new TextCellEditor(_choiceTable);
        editors[0] = textEditor;
        textEditor = new TextCellEditor(_choiceTable);
        editors[1] = textEditor;
        textEditor = new TextCellEditor(_choiceTable);
        editors[2] = textEditor;
        textEditor = new TextCellEditor(_choiceTable);
        editors[3] = textEditor;

        _choiceViewer.setCellEditors(editors);
        _choiceViewer.setCellModifier(new ICellModifier(){

            public boolean canModify(final Object element, final String property) {
                final IDOMElement node = (IDOMElement)element;
                if (node.getLocalName().equals("selectItem")) //$NON-NLS-1$
                {
                    return ! property.equals("choices"); //$NON-NLS-1$
                }

                return property.equals("id"); //$NON-NLS-1$
            }

            public Object getValue(final Object element, final String property) {
                final IDOMElement node = (IDOMElement) element;
                final String val = node.getAttribute(property) != null ? node.getAttribute(property) : ""; //$NON-NLS-1$
                return val;
            }

            public void modify(final Object element, final String property, final Object value) {

                final TableItem item = (TableItem) element;
                final IDOMElement node = (IDOMElement) item.getData();
                String valueString;
                ChangeAttributeCommand c;

                valueString = ((String) value).trim();
                c = new ChangeAttributeCommand(
                        SectionResources.getString("JSFHtmlInputTextSection.CommandLabel.ChangeAttribute"), node, property, valueString); //$NON-NLS-1$
                c.execute();

                _choiceViewer.refresh();
            }

        });
        _choiceViewer.setContentProvider(new ChoiceCotentLabelProvider());
        _choiceViewer.setLabelProvider(new ChoiceCotentLabelProvider());
        _choiceViewer.addDoubleClickListener(new IDoubleClickListener()
        {
            public void doubleClick(final DoubleClickEvent event)
            {
                final IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                if (selection != null)
                {
                    final IDOMElement node = (IDOMElement) selection.getFirstElement();
                    gotoNode(node);
                }
            }
        });
        _choiceViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(final SelectionChangedEvent event)
            {
                updateChoiceButtonStatus();
            }
        });

        _choiceTypeCombo = factory.createCCombo(other, SWT.READ_ONLY);
        _choiceTypeCombo.setItems(CHOICETYPES);
        _choiceTypeCombo.select(0);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _choiceTypeCombo.setLayoutData(data);

        _choiceAddButton = factory.createButton(other, SectionResources
                .getString("JSFHtmlSelectManyCheckboxSection.Add"), SWT.NONE); //$NON-NLS-1$
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _choiceAddButton.setLayoutData(data);
        _choiceAddButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(final SelectionEvent e)
            {
                final Map attributes = new HashMap();
                final AddSubNodeCommand c = new AddSubNodeCommand(
                        SectionResources.getString("JSFHtmlSelectManyCheckboxSection.CommandLabel.AddSubTag"), _element, _choiceTypeCombo.getText(), //$NON-NLS-1$
                        ITLDConstants.URI_JSF_CORE, attributes);
                c.execute();
                _choiceViewer.refresh();
                updateChoiceButtonStatus();
            }
        });
        _choiceRemoveButton = factory.createButton(other, SectionResources
                .getString("JSFHtmlSelectManyCheckboxSection.Remove"), SWT.NONE); //$NON-NLS-1$
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _choiceRemoveButton.setLayoutData(data);
        _choiceRemoveButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(final SelectionEvent e)
            {
                final IStructuredSelection selection = (IStructuredSelection) _choiceViewer.getSelection();
                if (selection != null)
                {
                    for (final Iterator i = selection.iterator(); i.hasNext();)
                    {
                        final IDOMElement node = (IDOMElement) i.next();
                        final RemoveSubNodeCommand c = new RemoveSubNodeCommand(
                                SectionResources
                                .getString("JSFHtmlSelectManyCheckboxSection.CommandLabel.RemoveSubTag"), _element, node); //$NON-NLS-1$
                        c.execute();
                    }
                    _choiceViewer.refresh();
                    updateChoiceButtonStatus();
                }
            }
        });
        _choiceMoveUpButton = factory.createButton(other, SectionResources
                .getString("JSFHtmlSelectManyCheckboxSection.MoveUp"), SWT.NONE); //$NON-NLS-1$
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _choiceMoveUpButton.setLayoutData(data);
        _choiceMoveUpButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(final SelectionEvent e)
            {
                final int index = _choiceTable.getSelectionIndex();
                final IDOMElement child = (IDOMElement) _choiceTable.getItem(index).getData();
                final IDOMElement refchild = (IDOMElement) _choiceTable.getItem(index - 1).getData();
                final RemoveSubNodeCommand remove = new RemoveSubNodeCommand(SectionResources
                        .getString("JSFHtmlSelectManyCheckboxSection.CommandLabel.RemoveSubTag"), _element, child); //$NON-NLS-1$
                remove.execute();
                final InsertSubNodeCommand insert = new InsertSubNodeCommand(
                        SectionResources.getString("JSFHtmlSelectManyCheckboxSection.CommandLabel.InsertSubTag"), _element, child, refchild); //$NON-NLS-1$
                insert.execute();
                _choiceViewer.refresh();
                _choiceViewer.getTable().select(index - 1);
                updateChoiceButtonStatus();
            }
        });
        _choiceMoveDownButton = factory.createButton(other, SectionResources
                .getString("JSFHtmlSelectManyCheckboxSection.MoveDown"), SWT.NONE); //$NON-NLS-1$
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _choiceMoveDownButton.setLayoutData(data);
        _choiceMoveDownButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(final SelectionEvent e)
            {
                final int index = _choiceTable.getSelectionIndex();
                final IDOMElement child = (IDOMElement) _choiceTable.getItem(index).getData();
                final IDOMElement refchild = (IDOMElement) _choiceTable.getItem(index + 1).getData();
                final RemoveSubNodeCommand remove = new RemoveSubNodeCommand(SectionResources
                        .getString("JSFHtmlSelectManyCheckboxSection.CommandLabel.RemoveSubTag"), _element, refchild); //$NON-NLS-1$
                remove.execute();
                final InsertSubNodeCommand insert = new InsertSubNodeCommand(
                        SectionResources.getString("JSFHtmlSelectManyCheckboxSection.CommandLabel.InsertSubTag"), _element, refchild, child); //$NON-NLS-1$
                insert.execute();
                _choiceViewer.refresh();
                _choiceViewer.getTable().select(index + 1);
                updateChoiceButtonStatus();
            }
        });
    }

    private void updateChoiceButtonStatus()
    {
        _choiceRemoveButton.setEnabled(true);
        _choiceMoveUpButton.setEnabled(true);
        _choiceMoveDownButton.setEnabled(true);
        final ISelection selection = _choiceViewer.getSelection();
        if (selection.isEmpty())
        {
            _choiceRemoveButton.setEnabled(false);
            _choiceMoveUpButton.setEnabled(false);
            _choiceMoveDownButton.setEnabled(false);
        }
        if (_choiceTable.getItemCount() == 0)
        {
            _choiceRemoveButton.setEnabled(false);
            _choiceMoveUpButton.setEnabled(false);
            _choiceMoveDownButton.setEnabled(false);
        }
        if (_choiceTable.getSelectionCount() > 1)
        {
            _choiceMoveUpButton.setEnabled(false);
            _choiceMoveDownButton.setEnabled(false);
        }
        if (_choiceTable.getSelectionIndex() == 0)
        {
            _choiceMoveUpButton.setEnabled(false);
        }
        if (_choiceTable.getSelectionIndex() == _choiceTable.getItemCount() - 1)
        {
            _choiceMoveDownButton.setEnabled(false);
        }
    }

    @Override
    public void setInput(final IWorkbenchPart part, final ISelection selection)
    {
        super.setInput(part, selection);

        _choiceViewer.setInput(_element);
        updateChoiceButtonStatus();
    }

    @Override
    protected void notifyChanged(final INodeNotifier notifier, final int eventType, final Object changedFeature, final Object oldValue,
            final Object newValue, final int pos)
    {
        if (_choiceViewer != null && !_choiceViewer.getControl().isDisposed())
        {
            _choiceViewer.refresh();
        }

    }
}
