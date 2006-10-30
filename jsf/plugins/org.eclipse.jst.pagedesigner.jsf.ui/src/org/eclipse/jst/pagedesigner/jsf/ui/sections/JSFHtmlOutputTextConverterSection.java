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
import org.eclipse.jst.pagedesigner.commands.single.AddSubNodeCommand;
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
public class JSFHtmlOutputTextConverterSection extends BaseCustomSection
{
    private Table                  _convertTable;
    private TableViewer            _convertViewer;
    private Button                 _convertAddButton, _convertRemoveButton;
    private CCombo                 _convertTypeCombo;
    final private static String[]  CONVERTTYPES = 
    {
        "DateTime", "Number" //$NON-NLS-1$ //$NON-NLS-2$
    }
    ;
    
    private class ConvertCotentLabelProvider implements IStructuredContentProvider, ITableLabelProvider
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
                    if (nodeName.indexOf("convert") != -1) //$NON-NLS-1$
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
        }

        public String getColumnText(Object element, int columnIndex)
        {
            String result = null;
            if (element instanceof IDOMElement)
            {
                IDOMElement node = (IDOMElement) element;
                String nodeName = node.getNodeName();
                switch (columnIndex)
                {
                    case 0:
                        result = nodeName;
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
        }

    }

    public JSFHtmlOutputTextConverterSection()
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

        createConvertPart(factory, top);
    }

    /**
     * @param factory
     * @param other
     */
    private void createConvertPart(TabbedPropertySheetWidgetFactory factory, Composite other)
    {
        GridData data;
        _convertTable = factory.createTable(other, SWT.FULL_SELECTION | SWT.MULTI);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 3;
        data.verticalSpan = 3;
        _convertTable.setHeaderVisible(true);
        _convertTable.setLayoutData(data);
        _convertTable.setLinesVisible(true);

        TableColumn convertColumn = new TableColumn(_convertTable, SWT.NONE);
        convertColumn.setText(SectionResources.getString("JSFHtmlOutputTextSection.Converter")); //$NON-NLS-1$
        convertColumn.setWidth(100);

        _convertViewer = new TableViewer(_convertTable);
        _convertViewer.setContentProvider(new ConvertCotentLabelProvider());
        _convertViewer.setLabelProvider(new ConvertCotentLabelProvider());
        _convertViewer.addDoubleClickListener(new IDoubleClickListener()
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
        _convertViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                updateConvertButtonStatus();
            }
        }
        );

        _convertTypeCombo = factory.createCCombo(other, SWT.READ_ONLY);
        _convertTypeCombo.setItems(CONVERTTYPES);
        _convertTypeCombo.select(0);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _convertTypeCombo.setLayoutData(data);

        _convertAddButton = factory.createButton(other, SectionResources.getString("JSFHtmlOutputTextSection.Add"), //$NON-NLS-1$
        SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _convertAddButton.setLayoutData(data);
        _convertAddButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                Map attributes = new HashMap();
                AddSubNodeCommand c = new AddSubNodeCommand(SectionResources.getString("JSFHtmlOutputTextSection.CommandLabel.AddSubTag"), _element, "convert" //$NON-NLS-1$ //$NON-NLS-2$
                + _convertTypeCombo.getText(), IJMTConstants.URI_JSF_CORE, attributes);
                c.execute();
                _convertViewer.refresh();
                updateConvertButtonStatus();
            }
        }
        );
        _convertRemoveButton = factory.createButton(other, SectionResources
            .getString("JSFHtmlOutputTextSection.Remove"), SWT.NONE); //$NON-NLS-1$
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _convertRemoveButton.setLayoutData(data);
        _convertRemoveButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                IStructuredSelection selection = (IStructuredSelection) _convertViewer.getSelection();
                if (selection != null)
                {
                    for (Iterator i = selection.iterator(); i.hasNext();)
                    {
                        IDOMElement node = (IDOMElement) i.next();
                        RemoveSubNodeCommand c = new RemoveSubNodeCommand(SectionResources.getString("JSFHtmlOutputTextSection.CommandLabel.RemoveSubTag"), _element, node); //$NON-NLS-1$
                        c.execute();
                    }
                    _convertViewer.refresh();
                    updateConvertButtonStatus();
                }
            }
        }
        );
    }

    private void updateConvertButtonStatus()
    {
        _convertAddButton.setEnabled(true);
        _convertRemoveButton.setEnabled(true);
        ISelection selection = _convertViewer.getSelection();
        if (selection == null || selection.isEmpty())
        {
            _convertRemoveButton.setEnabled(false);
        }
        if (_convertTable.getItemCount() == 0)
        {
            _convertRemoveButton.setEnabled(false);
        }
        if (_convertTable.getItemCount() > 0)
        {
            _convertAddButton.setEnabled(false);
        }
    }

    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);

        _convertViewer.setInput(_element);
        updateConvertButtonStatus();
    }

    protected void notifyChanged(INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue, Object newValue, int pos)
    {
        if(_convertViewer != null && !_convertViewer.getControl().isDisposed())
        {
            _convertViewer.refresh();
        }
    }
}
