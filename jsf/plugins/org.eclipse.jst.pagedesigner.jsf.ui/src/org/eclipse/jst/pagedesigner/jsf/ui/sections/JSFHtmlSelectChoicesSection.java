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
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.single.AddSubNodeCommand;
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
public class JSFHtmlSelectChoicesSection extends BaseCustomSection
{
    final private static String[] CHOICETYPES = { IJSFConstants.TAG_SELECTITEM, IJSFConstants.TAG_SELECTITEMS};
    private Table                 _choiceTable;
    private TableViewer           _choiceViewer;
    private CCombo                _choiceTypeCombo;
    private Button                _choiceAddButton, _choiceRemoveButton, _choiceMoveUpButton, _choiceMoveDownButton;

    private class ChoiceCotentLabelProvider implements IStructuredContentProvider, ITableLabelProvider
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
            // do nothing
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

    public JSFHtmlSelectChoicesSection()
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

        createChoicePart(factory, top);
    }

    /**
     * @param factory
     * @param other
     */
    private void createChoicePart(TabbedPropertySheetWidgetFactory factory, Composite other)
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

        TableColumn choiceColumn = new TableColumn(_choiceTable, SWT.NONE);
        choiceColumn.setText(SectionResources.getString("JSFHtmlSelectManyCheckboxSection.Choices")); //$NON-NLS-1$
        choiceColumn.setWidth(100);

        _choiceViewer = new TableViewer(_choiceTable);
        _choiceViewer.setContentProvider(new ChoiceCotentLabelProvider());
        _choiceViewer.setLabelProvider(new ChoiceCotentLabelProvider());
        _choiceViewer.addDoubleClickListener(new IDoubleClickListener()
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
        _choiceViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
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
            public void widgetSelected(SelectionEvent e)
            {
                Map attributes = new HashMap();
                AddSubNodeCommand c = new AddSubNodeCommand(
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
            public void widgetSelected(SelectionEvent e)
            {
                IStructuredSelection selection = (IStructuredSelection) _choiceViewer.getSelection();
                if (selection != null)
                {
                    for (Iterator i = selection.iterator(); i.hasNext();)
                    {
                        IDOMElement node = (IDOMElement) i.next();
                        RemoveSubNodeCommand c = new RemoveSubNodeCommand(
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
            public void widgetSelected(SelectionEvent e)
            {
                int index = _choiceTable.getSelectionIndex();
                IDOMElement child = (IDOMElement) _choiceTable.getItem(index).getData();
                IDOMElement refchild = (IDOMElement) _choiceTable.getItem(index - 1).getData();
                RemoveSubNodeCommand remove = new RemoveSubNodeCommand(SectionResources
                        .getString("JSFHtmlSelectManyCheckboxSection.CommandLabel.RemoveSubTag"), _element, child); //$NON-NLS-1$
                remove.execute();
                InsertSubNodeCommand insert = new InsertSubNodeCommand(
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
            public void widgetSelected(SelectionEvent e)
            {
                int index = _choiceTable.getSelectionIndex();
                IDOMElement child = (IDOMElement) _choiceTable.getItem(index).getData();
                IDOMElement refchild = (IDOMElement) _choiceTable.getItem(index + 1).getData();
                RemoveSubNodeCommand remove = new RemoveSubNodeCommand(SectionResources
                        .getString("JSFHtmlSelectManyCheckboxSection.CommandLabel.RemoveSubTag"), _element, refchild); //$NON-NLS-1$
                remove.execute();
                InsertSubNodeCommand insert = new InsertSubNodeCommand(
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
        ISelection selection = _choiceViewer.getSelection();
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

    public void setInput(IWorkbenchPart part, ISelection selection)
    {
        super.setInput(part, selection);

        _choiceViewer.setInput(_element);
        updateChoiceButtonStatus();
    }

    protected void notifyChanged(INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue,
            Object newValue, int pos)
    {
        if (_choiceViewer != null && !_choiceViewer.getControl().isDisposed())
        {
            _choiceViewer.refresh();
        }

    }
}
