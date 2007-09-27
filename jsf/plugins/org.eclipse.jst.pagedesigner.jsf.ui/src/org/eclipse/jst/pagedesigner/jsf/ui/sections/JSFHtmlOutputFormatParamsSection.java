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
import java.util.Arrays;
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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
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
public class JSFHtmlOutputFormatParamsSection extends BaseCustomSection
{
    private Table                  _paramTable;
    private TableViewer            _paramViewer;
    private Button                 _paramAddButton, _paramRemoveButton;

    private String[]               _columnNames = 
    {
        "name", "value" //$NON-NLS-1$ //$NON-NLS-2$
    }
    ;

    private class ParamCotentLabelProvider implements IStructuredContentProvider, ITableLabelProvider
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
                    if (nodeName.indexOf("param") != -1) //$NON-NLS-1$
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
            // do nothing
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
                        result = node.getAttribute(IJSFConstants.ATTR_NAME);
                        break;
                    case 1:
                        result = node.getAttribute(IJSFConstants.ATTR_VALUE);
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

    private class ParamCellModifier implements ICellModifier
    {
        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
         */
        public boolean canModify(Object element, String property)
        {
            return true;
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.ICellModifier#getValue(java.lang.Object, java.lang.String)
         */
        public Object getValue(Object element, String property)
        {
            int columnIndex = getColumnNames().indexOf(property);

            Object result = null;
            IDOMElement node = (IDOMElement) element;
            switch (columnIndex)
            {
                case 0: // Name
                    result = node.getAttribute(IJSFConstants.ATTR_NAME);
                    break;
                case 1: // Value
                    result = node.getAttribute(IJSFConstants.ATTR_VALUE);
                    break;
                default:
                    result = ""; //$NON-NLS-1$
            }
            return result != null ? result : ""; //$NON-NLS-1$
        }

        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.ICellModifier#modify(java.lang.Object, java.lang.String, java.lang.Object)
         */
        public void modify(Object element, String property, Object value)
        {
            int columnIndex = getColumnNames().indexOf(property);

            TableItem item = (TableItem) element;
            IDOMElement node = (IDOMElement) item.getData();
            String valueString;
            ChangeAttributeCommand c;
            switch (columnIndex)
            {
                case 0: // Name 
                    valueString = ((String) value).trim();
                    c = new ChangeAttributeCommand(SectionResources.getString("JSFHtmlOutputFormatSection.CommandLabel.ChangeAttribute"), node, IJSFConstants.ATTR_NAME, valueString); //$NON-NLS-1$
                    c.execute();
                    break;
                case 1: // Value 
                    valueString = ((String) value).trim();
                    c = new ChangeAttributeCommand(SectionResources.getString("JSFHtmlOutputFormatSection.CommandLabel.ChangeAttribute"), node, IJSFConstants.ATTR_VALUE, valueString); //$NON-NLS-1$
                    c.execute();
                    break;
                default:
                    break;
            }
            _paramViewer.setInput(_element);
            // TODO: does nothing updateParamButtonStatus();
        }
    }

    /**
     * Default constructor
     */
    public JSFHtmlOutputFormatParamsSection()
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

        createParamPart(factory, top);
    }

    /**
     * @param factory
     * @param other
     */
    private void createParamPart(TabbedPropertySheetWidgetFactory factory, Composite other)
    {
        GridData data;

        Label label = factory.createLabel(other, SectionResources.getString("JSFHtmlOutputFormatSection.Parameters")); //$NON-NLS-1$
        data = new GridData(GridData.FILL);
        data.horizontalSpan = 4;
        label.setLayoutData(data);

        _paramTable = factory.createTable(other, SWT.FULL_SELECTION | SWT.MULTI);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 3;
        data.verticalSpan = 3;
        data.heightHint = 50;
        _paramTable.setHeaderVisible(true);
        _paramTable.setLinesVisible(true);
        _paramTable.setLayoutData(data);

        TableColumn column = new TableColumn(_paramTable, SWT.NONE);
        column.setText(SectionResources.getString("JSFHtmlOutputFormatSection.ColName")); //$NON-NLS-1$
        column.setWidth(100);

        column = new TableColumn(_paramTable, SWT.NONE);
        column.setText(SectionResources.getString("JSFHtmlOutputFormatSection.ColValue")); //$NON-NLS-1$
        column.setWidth(100);

        _paramViewer = new TableViewer(_paramTable);
        _paramViewer.setColumnProperties(_columnNames);

        CellEditor[] editors = new CellEditor[_columnNames.length];
        TextCellEditor textEditor = new TextCellEditor(_paramTable);
        editors[0] = textEditor;
        textEditor = new TextCellEditor(_paramTable);
        editors[1] = textEditor;

        _paramViewer.setCellEditors(editors);
        _paramViewer.setCellModifier(new ParamCellModifier());
        _paramViewer.setContentProvider(new ParamCotentLabelProvider());
        _paramViewer.setLabelProvider(new ParamCotentLabelProvider());
        _paramViewer.addDoubleClickListener(new IDoubleClickListener()
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
        _paramViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
//              TODO: does nothing updateParamButtonStatus();
            }
        }
        );

        _paramAddButton = factory.createButton(other, SectionResources.getString("JSFHtmlOutputTextSection.Add"), //$NON-NLS-1$
        SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _paramAddButton.setLayoutData(data);
        _paramAddButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                Map attributes = new HashMap();
                attributes.put(IJSFConstants.ATTR_NAME, "name"); //$NON-NLS-1$
                attributes.put(IJSFConstants.ATTR_VALUE, "value"); //$NON-NLS-1$
                AddSubNodeCommand c = new AddSubNodeCommand(SectionResources.getString("JSFHtmlOutputFormatSection.CommandLabel.AddSubTag"), _element, "param", //$NON-NLS-1$ //$NON-NLS-2$
                ITLDConstants.URI_JSF_CORE, attributes);
                c.execute();
                _paramViewer.refresh();
//              TODO: does nothing updateParamButtonStatus();
            }
        }
        );
        _paramRemoveButton = factory.createButton(other, SectionResources.getString("JSFHtmlOutputTextSection.Remove"), //$NON-NLS-1$
        SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _paramRemoveButton.setLayoutData(data);
        _paramRemoveButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                IStructuredSelection selection = (IStructuredSelection) _paramViewer.getSelection();
                if (selection != null)
                {
                    for (Iterator i = selection.iterator(); i.hasNext();)
                    {
                        IDOMElement node = (IDOMElement) i.next();
                        RemoveSubNodeCommand c = new RemoveSubNodeCommand(SectionResources.getString("JSFHtmlOutputFormatSection.CommandLabel.RemoveSubTag"), _element, node); //$NON-NLS-1$
                        c.execute();
                    }
                    _paramViewer.refresh();
//                  TODO: does nothing updateParamButtonStatus();
                }
            }
        }
        );
    }
    
    // TODO: does nothing
//    private void updateParamButtonStatus()
//    {
//    }
    
    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);

        _paramViewer.setInput(_element);
//      TODO: does nothing updateParamButtonStatus();
    }
    
    private List getColumnNames()
    {
        return Arrays.asList(_columnNames);
    }

    protected void notifyChanged(INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue, Object newValue, int pos)
    {
        if(_paramViewer != null && !_paramViewer.getControl().isDisposed())
        {
            _paramViewer.refresh();
        }
        
    }
}
