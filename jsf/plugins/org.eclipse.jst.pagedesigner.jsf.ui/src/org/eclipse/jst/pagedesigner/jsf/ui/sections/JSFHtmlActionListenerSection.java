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
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.commands.single.AddSubNodeCommand;
import org.eclipse.jst.pagedesigner.commands.single.RemoveSubNodeCommand;
import org.eclipse.jst.pagedesigner.properties.BaseCustomSection;
import org.eclipse.jst.pagedesigner.properties.DesignerPropertyTool;
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
public class JSFHtmlActionListenerSection extends BaseCustomSection
{
    final private static String[] LISTENTYPES = { "Action"};

    private IDOMElement            _element;
    private Table                 _listenersTable;
    private TableViewer           _listenersViewer;
    private CCombo                _listenTypeCombo;
    private Button                _listenAddButton, _listenRemoveButton;

    private class ListenerCotentLabelProvider implements IStructuredContentProvider, ITableLabelProvider
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
                    if (nodeName.indexOf("Listener") != -1) //$NON-NLS-1$
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
            // no support for viewer changes
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
            // TODO: listeners not supported?
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
            // TODO: listeners not supported?
        }
    }

    /**
     * Default constructor
     */
    public JSFHtmlActionListenerSection()
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

        createListenerPart(factory, top);
    }

    /**
     * @param factory
     * @param other
     */
    private void createListenerPart(TabbedPropertySheetWidgetFactory factory, Composite other)
    {
        GridData data;
        _listenersTable = factory.createTable(other, SWT.FULL_SELECTION | SWT.MULTI);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 3;
        data.verticalSpan = 4;
        data.heightHint = 50;
        data.widthHint = 100;
        _listenersTable.setHeaderVisible(true);
        _listenersTable.setLayoutData(data);
        _listenersTable.setLinesVisible(true);

        TableColumn listenColumn = new TableColumn(_listenersTable, SWT.NONE);
        listenColumn.setText(SectionResources.getString("JSFHtmlCommandButtonSection.Listeners")); //$NON-NLS-1$
        listenColumn.setWidth(100);

        _listenersViewer = new TableViewer(_listenersTable);
        _listenersViewer.setContentProvider(new ListenerCotentLabelProvider());
        _listenersViewer.setLabelProvider(new ListenerCotentLabelProvider());
        _listenersViewer.addDoubleClickListener(new IDoubleClickListener()
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
        });
        _listenersViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                updateListenerButtonStatus();
            }
        });

        _listenTypeCombo = factory.createCCombo(other, SWT.READ_ONLY);
        _listenTypeCombo.setItems(LISTENTYPES);
        _listenTypeCombo.select(0);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _listenTypeCombo.setLayoutData(data);

        _listenAddButton = factory.createButton(other, SectionResources.getString("JSFHtmlCommandButtonSection.Add"), //$NON-NLS-1$
                SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _listenAddButton.setLayoutData(data);
        _listenAddButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                Map attributes = new HashMap();
                String listener = _listenTypeCombo.getText();
                listener = listener.substring(0, 1).toLowerCase() + listener.substring(1) + "Listener"; //$NON-NLS-1$
                AddSubNodeCommand c = new AddSubNodeCommand(
                        SectionResources.getString("JSFHtmlCommandButtonSection.CommandLabel.AddSubTag"), _element, listener, ITLDConstants.URI_JSF_CORE, attributes); //$NON-NLS-1$
                c.execute();
                _listenersViewer.refresh();
                updateListenerButtonStatus();
            }
        });
        _listenRemoveButton = factory.createButton(other, SectionResources
                .getString("JSFHtmlCommandButtonSection.Remove"), SWT.NONE); //$NON-NLS-1$
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _listenRemoveButton.setLayoutData(data);
        _listenRemoveButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                IStructuredSelection selection = (IStructuredSelection) _listenersViewer.getSelection();
                if (selection != null)
                {
                    for (Iterator i = selection.iterator(); i.hasNext();)
                    {
                        IDOMElement node = (IDOMElement) i.next();
                        RemoveSubNodeCommand c = new RemoveSubNodeCommand(SectionResources
                                .getString("JSFHtmlCommandButtonSection.CommandLabel.RemoveSubTag"), _element, node); //$NON-NLS-1$
                        c.execute();
                    }
                    _listenersViewer.refresh();
                    updateListenerButtonStatus();
                }
            }
        });
    }

    private void updateListenerButtonStatus()
    {
        _listenRemoveButton.setEnabled(true);
        ISelection selection = _listenersViewer.getSelection();
        if (selection == null || selection.isEmpty())
        {
            _listenRemoveButton.setEnabled(false);
        }
        if (_listenersTable.getItemCount() == 0)
        {
            _listenRemoveButton.setEnabled(false);
        }
    }

    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);
        _element = (IDOMElement) DesignerPropertyTool.getElement(part, selection);
        _listenersViewer.setInput(_element);
    }

    protected void notifyChanged(INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue, Object newValue, int pos)
    {
        if(_listenersViewer != null && !_listenersViewer.getControl().isDisposed())
        {
            _listenersViewer.refresh();
        }
        
    }
}
