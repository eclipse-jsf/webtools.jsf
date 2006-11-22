/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
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

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.single.AddSubNodeCommand;
import org.eclipse.jst.pagedesigner.commands.single.InsertSubNodeCommand;
import org.eclipse.jst.pagedesigner.commands.single.RemoveSubNodeCommand;
import org.eclipse.jst.pagedesigner.properties.BaseCustomSection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetPage;
import org.eclipse.wst.common.ui.properties.internal.provisional.TabbedPropertySheetWidgetFactory;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author mengbo
 * @version 1.5
 */
public class JSFHtmlDataTableColumnsSection extends BaseCustomSection
{
    private Table                  _columnsTable;
    private TableViewer            _columnsViewer;
    private Button                 _addButton, _removeButton, _moveUpButton, _moveDownButton;

    final private String           DEFAULT_COLUMN_NAME = "column"; //$NON-NLS-1$
    final private String           DEFAULT_FACET_NAME  = "header"; //$NON-NLS-1$
    final private String           DEFAULT_TEXT_NAME   = "text"; //$NON-NLS-1$

    class ColumnCotentLabelProvider implements IStructuredContentProvider, ITableLabelProvider
    {

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
         */
        public Object[] getElements(Object inputElement)
        {
            IDOMElement root = _element;
            List result = new ArrayList();

            NodeList children = root.getChildNodes();
            for (int i = 0, n = children.getLength(); i < n; i++)
            {
                Node child = children.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE)
                {
                    IDOMElement element = (IDOMElement) child;
                    String nodeName = element.getNodeName();
                    if (nodeName.indexOf("column") != -1) //$NON-NLS-1$
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
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
        {
            // no support for input changed
        }

        public String getColumnText(Object element, int columnIndex)
        {
            String result = null;
            if (element instanceof IDOMElement)
            {
                IDOMElement node = (IDOMElement) element;
                switch (columnIndex)
                {
                    case 0:
                        result = node.getAttribute(IJSFConstants.ATTR_ID);
                        break;
                    default:
                        break;
                }
            }
            return result != null ? result : ""; //$NON-NLS-1$
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.IContentProvider#dispose()
         */

        public void dispose()
        {
            // nothing to dispose
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
         */
        public Image getColumnImage(Object element, int columnIndex)
        {
            return null;
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void addListener(ILabelProviderListener listener)
        {
            // TODO: no support for listeners?
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
         */
        public boolean isLabelProperty(Object element, String property)
        {
            return false;
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
         */
        public void removeListener(ILabelProviderListener listener)
        {
            // TODO: no support for listeners?
        }

    }

    public JSFHtmlDataTableColumnsSection()
    {
        super();
    }

    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage)
    {
        super.createControls(parent, aTabbedPropertySheetPage);
        TabbedPropertySheetWidgetFactory factory = aTabbedPropertySheetPage.getWidgetFactory();
        Composite top = factory.createFlatFormComposite(parent);

        GridLayout layout = new GridLayout();
        layout.numColumns = 4;
        top.setLayout(layout);

        createColumnPart(factory, top);
    }
    
    private void createColumnPart(TabbedPropertySheetWidgetFactory factory, Composite other)
    {
        GridData data;
        _columnsTable = factory.createTable(other, SWT.FULL_SELECTION | SWT.MULTI);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 3;
        data.verticalSpan = 4;
        data.widthHint = 10;
        data.heightHint = 50;
        _columnsTable.setHeaderVisible(true);
        _columnsTable.setLayoutData(data);
        _columnsTable.setLinesVisible(true);

        TableColumn column = new TableColumn(_columnsTable, SWT.NONE);
        column.setText(SectionResources.getString("JSFHtmlDataTableSection.Columns")); //$NON-NLS-1$
        column.setWidth(200);

        _columnsViewer = new TableViewer(_columnsTable);
        _columnsViewer.setContentProvider(new ColumnCotentLabelProvider());
        _columnsViewer.setLabelProvider(new ColumnCotentLabelProvider());
        _columnsViewer.addDoubleClickListener(new IDoubleClickListener()
        {
            public void doubleClick(DoubleClickEvent event)
            {
                IStructuredSelection selection = (IStructuredSelection) event.getSelection();
                if (selection != null)
                {
                    IDOMElement node = (IDOMElement) selection.getFirstElement();
                    gotoNode(node);
                }
            }
        }
        );
        _columnsViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                updateButtonStatus();
            }
        }
        );

        // add buttons group
        _addButton = factory.createButton(other, SectionResources.getString("JSFHtmlDataTableSection.Add"), SWT.NONE); //$NON-NLS-1$
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _addButton.setLayoutData(data);
        _addButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int count = _columnsTable.getItemCount();

                Map attributes = new HashMap();
                attributes.put(IJSFConstants.ATTR_ID, DEFAULT_COLUMN_NAME + (count + 1));
                AddSubNodeCommand c = new AddSubNodeCommand(SectionResources.getString("JSFHtmlDataTableSection.CommandLabel.AddSubTag"), _element, "column", //$NON-NLS-1$ //$NON-NLS-2$
                IJMTConstants.URI_JSF_HTML, attributes);
                c.execute();

                IDOMElement child = c.getChildNode();
                attributes = new HashMap();
                attributes.put(IJSFConstants.ATTR_NAME, DEFAULT_FACET_NAME);
                c = new AddSubNodeCommand(SectionResources.getString("JSFHtmlDataTableSection.CommandLabel.AddSubTag"), child, "facet", IJMTConstants.URI_JSF_CORE, attributes); //$NON-NLS-1$ //$NON-NLS-2$
                c.execute();

                child = c.getChildNode();
                attributes = new HashMap();
                attributes.put(IJSFConstants.ATTR_ID, DEFAULT_TEXT_NAME + (count + 1));
                attributes.put(IJSFConstants.ATTR_VALUE, DEFAULT_COLUMN_NAME + (count + 1));
                c = new AddSubNodeCommand(SectionResources.getString("JSFHtmlDataTableSection.CommandLabel.AddSubTag"), child, "outputText", IJMTConstants.URI_JSF_HTML, attributes); //$NON-NLS-1$ //$NON-NLS-2$
                c.execute();

                _columnsViewer.refresh();
                //                if (c.getChildNode() != null)
                //                {
                //                    gotoNode(c.getChildNode());
                //                }
                updateButtonStatus();
            }
        }
        );
        _removeButton = factory.createButton(other, SectionResources.getString("JSFHtmlDataTableSection.Remove"), //$NON-NLS-1$
        SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _removeButton.setLayoutData(data);
        _removeButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                IStructuredSelection selection = (IStructuredSelection) _columnsViewer.getSelection();
                if (selection != null)
                {
                    for (Iterator i = selection.iterator(); i.hasNext();)
                    {
                        IDOMElement node = (IDOMElement) i.next();
                        RemoveSubNodeCommand c = new RemoveSubNodeCommand(SectionResources.getString("JSFHtmlDataTableSection.CommandLabel.RemoveSubTag"), _element, node); //$NON-NLS-1$
                        c.execute();
                    }
                    _columnsViewer.refresh();
                    updateButtonStatus();
                }
            }
        }
        );
        _moveUpButton = factory.createButton(other, SectionResources.getString("JSFHtmlDataTableSection.MoveUp"), //$NON-NLS-1$
        SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _moveUpButton.setLayoutData(data);
        _moveUpButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int index = _columnsTable.getSelectionIndex();
                IDOMElement child = (IDOMElement) _columnsTable.getItem(index).getData();
                IDOMElement refchild = (IDOMElement) _columnsTable.getItem(index - 1).getData();
                RemoveSubNodeCommand remove = new RemoveSubNodeCommand(SectionResources.getString("JSFHtmlDataTableSection.CommandLabel.RemoveSubTag"), _element, child); //$NON-NLS-1$
                remove.execute();
                InsertSubNodeCommand insert = new InsertSubNodeCommand(SectionResources.getString("JSFHtmlDataTableSection.CommandLabel.InsertSubTag"), _element, child, refchild); //$NON-NLS-1$
                insert.execute();
                _columnsViewer.refresh();
                updateButtonStatus();
            }
        }
        );
        _moveDownButton = factory.createButton(other, SectionResources.getString("JSFHtmlDataTableSection.MoveDown"), //$NON-NLS-1$
        SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _moveDownButton.setLayoutData(data);
        _moveDownButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                int index = _columnsTable.getSelectionIndex();
                IDOMElement child = (IDOMElement) _columnsTable.getItem(index).getData();
                IDOMElement refchild = (IDOMElement) _columnsTable.getItem(index + 1).getData();
                RemoveSubNodeCommand remove = new RemoveSubNodeCommand(SectionResources.getString("JSFHtmlDataTableSection.CommandLabel.RemoveSubTag"), _element, refchild); //$NON-NLS-1$
                remove.execute();
                InsertSubNodeCommand insert = new InsertSubNodeCommand(SectionResources.getString("JSFHtmlDataTableSection.CommandLabel.InsertSubTag"), _element, refchild, child); //$NON-NLS-1$
                insert.execute();
                _columnsViewer.refresh();
                updateButtonStatus();
            }
        }
        );
    }

    public void updateButtonStatus()
    {
        _removeButton.setEnabled(true);
        _moveUpButton.setEnabled(true);
        _moveDownButton.setEnabled(true);
        ISelection selection = _columnsViewer.getSelection();
        if (selection.isEmpty())
        {
            _removeButton.setEnabled(false);
            _moveUpButton.setEnabled(false);
            _moveDownButton.setEnabled(false);
        }
        if (_columnsTable.getItemCount() == 0)
        {
            _removeButton.setEnabled(false);
            _moveUpButton.setEnabled(false);
            _moveDownButton.setEnabled(false);
        }
        if (_columnsTable.getSelectionCount() > 1)
        {
            _moveUpButton.setEnabled(false);
            _moveDownButton.setEnabled(false);
        }
        if (_columnsTable.getSelectionIndex() == 0)
        {
            _moveUpButton.setEnabled(false);
        }
        if (_columnsTable.getSelectionIndex() == _columnsTable.getItemCount() - 1)
        {
            _moveDownButton.setEnabled(false);
        }
    }

    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);

        _columnsViewer.setInput(_element);

        updateButtonStatus();
    }

    protected void notifyChanged(INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue, Object newValue, int pos)
    {
        if(_columnsViewer != null && !_columnsViewer.getControl().isDisposed())
        {
            _columnsViewer.refresh();
        }
    }
}
