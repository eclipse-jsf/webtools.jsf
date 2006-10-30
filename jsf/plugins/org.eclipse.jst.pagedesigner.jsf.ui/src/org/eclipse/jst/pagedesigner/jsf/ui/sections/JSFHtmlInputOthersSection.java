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
import org.eclipse.jst.pagedesigner.IJMTConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.commands.single.AddSubNodeCommand;
import org.eclipse.jst.pagedesigner.commands.single.ChangeAttributeCommand;
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
public class JSFHtmlInputOthersSection extends BaseCustomSection
{
    private Table                 _validatorsTable, _convertorsTable, _listenersTable;
    private TableViewer           _validatorsViewer, _convertorsViewer, _listenersViewer;

    private Button                _validateAddButton, _validateRemoveButton;
    private Button                _convertAddButton, _convertRemoveButton;
    private Button                _listenAddButton, _listenRemoveButton;
    private CCombo                _validateTypeCombo, _convertTypeCombo, _listenTypeCombo;
    final private static String[] VALIDATETYPES           = { "DoubleRange", "Length", "LongRange" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                                                          };
    final private static String[] CONVERTTYPES            = { "DateTime", "Number" //$NON-NLS-1$ //$NON-NLS-2$
                                                          };
    final private static String[] LISTENTYPES             = { "ValueChange" //$NON-NLS-1$
                                                          };
    final private static String[] VALIDATORS_COLUMN_NAMES = { IJSFConstants.TAG_VALIDATOR, IJSFConstants.ATTR_MINIMUM,
            IJSFConstants.ATTR_MAXIMUM                    };

    private class ValidateCotentLabelProvider implements IStructuredContentProvider, ITableLabelProvider
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
                    if (nodeName.indexOf("validat") != -1) //$NON-NLS-1$
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
                    case 1:
                        result = node.getAttribute(IJSFConstants.ATTR_MINIMUM);
                        break;
                    case 2:
                        result = node.getAttribute(IJSFConstants.ATTR_MAXIMUM);
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

    private class ValidateCellModifier implements ICellModifier
    {
        /* (non-Javadoc)
         * @see org.eclipse.jface.viewers.ICellModifier#canModify(java.lang.Object, java.lang.String)
         */
        public boolean canModify(Object element, String property)
        {
            int columnIndex = getColumnNames().indexOf(property);

            if (columnIndex == 0)
            {
                return false;
            }
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
                case 0: // Node Name
                    result = node.getNodeName();
                    break;
                case 1: // Min
                    result = node.getAttribute(IJSFConstants.ATTR_MINIMUM);
                    break;
                case 2: // Max
                    result = node.getAttribute(IJSFConstants.ATTR_MAXIMUM);
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
                case 1: // Min 
                    valueString = ((String) value).trim();
                    c = new ChangeAttributeCommand(
                            SectionResources.getString("JSFHtmlInputTextSection.CommandLabel.ChangeAttribute"), node, IJSFConstants.ATTR_MINIMUM, valueString); //$NON-NLS-1$
                    c.execute();
                    break;
                case 2: // Max 
                    valueString = ((String) value).trim();
                    c = new ChangeAttributeCommand(
                            SectionResources.getString("JSFHtmlInputTextSection.CommandLabel.ChangeAttribute"), node, IJSFConstants.ATTR_MAXIMUM, valueString); //$NON-NLS-1$
                    c.execute();
                    break;
                default:
                    break;
            }
            _validatorsViewer.setInput(_element);
            updateValidateButtonStatus();
        }
    }

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

    public JSFHtmlInputOthersSection()
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

        createValidatePart(factory, top);

        createConvertPart(factory, top);

        createListenPart(factory, top);
    }

    /**
     * @param factory
     * @param other
     */
    private void createConvertPart(TabbedPropertySheetWidgetFactory factory, Composite other)
    {
        GridData data;
        _convertorsTable = factory.createTable(other, SWT.FULL_SELECTION | SWT.MULTI);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 3;
        data.verticalSpan = 3;
        data.heightHint = 50;
        _convertorsTable.setHeaderVisible(true);
        _convertorsTable.setLayoutData(data);
        _convertorsTable.setLinesVisible(true);

        TableColumn convertColumn = new TableColumn(_convertorsTable, SWT.NONE);
        convertColumn.setText(SectionResources.getString("JSFHtmlInputTextSection.Converter")); //$NON-NLS-1$
        convertColumn.setWidth(100);

        _convertorsViewer = new TableViewer(_convertorsTable);
        _convertorsViewer.setContentProvider(new ConvertCotentLabelProvider());
        _convertorsViewer.setLabelProvider(new ConvertCotentLabelProvider());
        _convertorsViewer.addDoubleClickListener(new IDoubleClickListener()
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
        _convertorsViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                updateConvertButtonStatus();
            }
        });

        _convertTypeCombo = factory.createCCombo(other, SWT.READ_ONLY);
        _convertTypeCombo.setItems(CONVERTTYPES);
        _convertTypeCombo.select(0);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _convertTypeCombo.setLayoutData(data);

        _convertAddButton = factory.createButton(other, SectionResources.getString("JSFHtmlInputTextSection.Add"), //$NON-NLS-1$
                SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _convertAddButton.setLayoutData(data);
        _convertAddButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                Map attributes = new HashMap();
                AddSubNodeCommand c = new AddSubNodeCommand(SectionResources
                        .getString("JSFHtmlInputTextSection.CommandLabel.AddSubTag"), _element, "convert" //$NON-NLS-1$ //$NON-NLS-2$
                        + _convertTypeCombo.getText(), IJMTConstants.URI_JSF_CORE, attributes);
                c.execute();
                _convertorsViewer.refresh();
                updateConvertButtonStatus();
            }
        });
        _convertRemoveButton = factory.createButton(other,
                SectionResources.getString("JSFHtmlInputTextSection.Remove"), SWT.NONE); //$NON-NLS-1$
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _convertRemoveButton.setLayoutData(data);
        _convertRemoveButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                IStructuredSelection selection = (IStructuredSelection) _convertorsViewer.getSelection();
                if (selection != null)
                {
                    for (Iterator i = selection.iterator(); i.hasNext();)
                    {
                        IDOMElement node = (IDOMElement) i.next();
                        RemoveSubNodeCommand c = new RemoveSubNodeCommand(SectionResources
                                .getString("JSFHtmlInputTextSection.CommandLabel.RemoveSubTag"), _element, node); //$NON-NLS-1$
                        c.execute();
                    }
                    _convertorsViewer.refresh();
                    updateConvertButtonStatus();
                }
            }
        });
    }

    /**
     * @param factory
     * @param other
     */
    private void createListenPart(TabbedPropertySheetWidgetFactory factory, Composite other)
    {
        GridData data;
        _listenersTable = factory.createTable(other, SWT.FULL_SELECTION | SWT.MULTI);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 3;
        data.verticalSpan = 3;
        data.heightHint = 50;
        _listenersTable.setHeaderVisible(true);
        _listenersTable.setLayoutData(data);
        _listenersTable.setLinesVisible(true);

        TableColumn listenColumn = new TableColumn(_listenersTable, SWT.NONE);
        listenColumn.setText(SectionResources.getString("JSFHtmlInputTextSection.Listeners")); //$NON-NLS-1$
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
                updateListenButtonStatus();
            }
        });

        _listenTypeCombo = factory.createCCombo(other, SWT.READ_ONLY);
        _listenTypeCombo.setItems(LISTENTYPES);
        _listenTypeCombo.select(0);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _listenTypeCombo.setLayoutData(data);

        _listenAddButton = factory.createButton(other, SectionResources.getString("JSFHtmlInputTextSection.Add"), //$NON-NLS-1$
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
                        SectionResources.getString("JSFHtmlInputTextSection.CommandLabel.AddSubTag"), _element, listener, IJMTConstants.URI_JSF_CORE, attributes); //$NON-NLS-1$
                c.execute();
                _listenersViewer.refresh();
                updateListenButtonStatus();
            }
        });
        _listenRemoveButton = factory.createButton(other,
                SectionResources.getString("JSFHtmlInputTextSection.Remove"), SWT.NONE); //$NON-NLS-1$
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
                                .getString("JSFHtmlInputTextSection.CommandLabel.RemoveSubTag"), _element, node); //$NON-NLS-1$
                        c.execute();
                    }
                    _listenersViewer.refresh();
                    updateListenButtonStatus();
                }
            }
        });
    }

    /**
     * @param factory
     * @param other
     */
    private void createValidatePart(TabbedPropertySheetWidgetFactory factory, Composite other)
    {
        GridData data;
        _validatorsTable = factory.createTable(other, SWT.FULL_SELECTION | SWT.MULTI);
        data = new GridData(GridData.FILL_BOTH);
        data.horizontalSpan = 3;
        data.verticalSpan = 3;
        data.heightHint = 50;
        _validatorsTable.setHeaderVisible(true);
        _validatorsTable.setLayoutData(data);
        _validatorsTable.setLinesVisible(true);

        TableColumn validateColumn = new TableColumn(_validatorsTable, SWT.NONE);
        validateColumn.setText(SectionResources.getString("JSFHtmlInputTextSection.Validators")); //$NON-NLS-1$
        validateColumn.setWidth(50);

        TableColumn minColumn = new TableColumn(_validatorsTable, SWT.NONE);
        minColumn.setText(SectionResources.getString("JSFHtmlInputTextSection.Minimum")); //$NON-NLS-1$
        minColumn.setWidth(50);

        TableColumn maxColumn = new TableColumn(_validatorsTable, SWT.NONE);
        maxColumn.setText(SectionResources.getString("JSFHtmlInputTextSection.Maximum")); //$NON-NLS-1$
        maxColumn.setWidth(50);

        _validatorsViewer = new TableViewer(_validatorsTable);
        _validatorsViewer.setColumnProperties(VALIDATORS_COLUMN_NAMES);

        CellEditor[] editors = new CellEditor[VALIDATORS_COLUMN_NAMES.length];
        TextCellEditor textEditor = new TextCellEditor(_validatorsTable);
        editors[0] = textEditor;
        textEditor = new TextCellEditor(_validatorsTable);
        editors[1] = textEditor;
        textEditor = new TextCellEditor(_validatorsTable);
        editors[2] = textEditor;

        _validatorsViewer.setCellEditors(editors);
        _validatorsViewer.setCellModifier(new ValidateCellModifier());
        _validatorsViewer.setContentProvider(new ValidateCotentLabelProvider());
        _validatorsViewer.setLabelProvider(new ValidateCotentLabelProvider());
        _validatorsViewer.addDoubleClickListener(new IDoubleClickListener()
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
        _validatorsViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                updateValidateButtonStatus();
            }
        });

        _validateTypeCombo = factory.createCCombo(other, SWT.READ_ONLY);
        _validateTypeCombo.setItems(VALIDATETYPES);
        _validateTypeCombo.select(0);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _validateTypeCombo.setLayoutData(data);

        _validateAddButton = factory.createButton(other, SectionResources.getString("JSFHtmlInputTextSection.Add"), //$NON-NLS-1$
                SWT.NONE);
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _validateAddButton.setLayoutData(data);
        _validateAddButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                Map attributes = new HashMap();
                AddSubNodeCommand c = new AddSubNodeCommand(SectionResources
                        .getString("JSFHtmlInputTextSection.CommandLabel.AddSubTag"), _element, "validate" //$NON-NLS-1$ //$NON-NLS-2$
                        + _validateTypeCombo.getText(), IJMTConstants.URI_JSF_CORE, attributes);
                c.execute();
                _validatorsViewer.refresh();
                updateValidateButtonStatus();
            }
        });
        _validateRemoveButton = factory.createButton(other, SectionResources
                .getString("JSFHtmlInputTextSection.Remove"), SWT.NONE); //$NON-NLS-1$
        data = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
        _validateRemoveButton.setLayoutData(data);
        _validateRemoveButton.addSelectionListener(new SelectionAdapter()
        {
            public void widgetSelected(SelectionEvent e)
            {
                IStructuredSelection selection = (IStructuredSelection) _validatorsViewer.getSelection();
                if (selection != null)
                {
                    for (Iterator i = selection.iterator(); i.hasNext();)
                    {
                        IDOMElement node = (IDOMElement) i.next();
                        RemoveSubNodeCommand c = new RemoveSubNodeCommand(SectionResources
                                .getString("JSFHtmlInputTextSection.CommandLabel.RemoveSubTag"), _element, node); //$NON-NLS-1$
                        c.execute();
                    }
                    _validatorsViewer.refresh();
                    updateValidateButtonStatus();
                }
            }
        });
    }

    private void updateValidateButtonStatus()
    {
        _validateRemoveButton.setEnabled(true);
        ISelection selection = _validatorsViewer.getSelection();
        if (selection == null || selection.isEmpty())
        {
            _validateRemoveButton.setEnabled(false);
        }
        if (_validatorsTable.getItemCount() == 0)
        {
            _validateRemoveButton.setEnabled(false);
        }
    }

    private void updateConvertButtonStatus()
    {
        _convertAddButton.setEnabled(true);
        _convertRemoveButton.setEnabled(true);
        ISelection selection = _convertorsViewer.getSelection();
        if (selection == null || selection.isEmpty())
        {
            _convertRemoveButton.setEnabled(false);
        }
        if (_convertorsTable.getItemCount() == 0)
        {
            _convertRemoveButton.setEnabled(false);
        }
        if (_convertorsTable.getItemCount() > 0)
        {
            _convertAddButton.setEnabled(false);
        }
    }

    private void updateListenButtonStatus()
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

        _validatorsViewer.setInput(_element);
        updateValidateButtonStatus();

        _convertorsViewer.setInput(_element);
        updateConvertButtonStatus();

        _listenersViewer.setInput(_element);
        updateListenButtonStatus();
    }

    private List getColumnNames()
    {
        return Arrays.asList(VALIDATORS_COLUMN_NAMES);
    }

    protected void notifyChanged(INodeNotifier notifier, int eventType, Object changedFeature, Object oldValue, Object newValue, int pos)
    {
        if(_validatorsViewer != null && !_validatorsViewer.getControl().isDisposed())
        {
            _validatorsViewer.refresh();
            _convertorsViewer.refresh();
            _listenersViewer.refresh();
        }
    }
}
