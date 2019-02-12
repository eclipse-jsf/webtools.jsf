/*******************************************************************************
 * Copyright (c) 2001, 2011 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.component;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.IInputProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.Decorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractMasterForm;
import org.eclipse.jst.jsf.common.ui.internal.utils.JSFSharedImages;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewObjectMappingService;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewObjectMappingService.ElementData;
import org.eclipse.jst.jsf.ui.internal.common.MetadataTagImageManager;
import org.eclipse.jst.jsf.ui.internal.component.ComponentTreeViewProvider.TreePlaceHolder;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;

/*package*/class ComponentMasterForm extends AbstractMasterForm
{
    private final DTJSFViewModel _model;
    private TreeViewer           _treeViewer;
    private Action               _refreshAction;

    protected ComponentMasterForm(final FormToolkit toolkit,
            final DTJSFViewModel model)
    {
        super(toolkit);
        _model = model;
    }

    @Override
    public Control createClientArea(final Composite parent)
    {
        final Tree tree = getToolkit().createTree(parent,
                SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
        tree.setLayoutData(gridData);

        _treeViewer = new TreeViewer(tree);
        _treeViewer.getTree().setLayoutData(gridData);
        _treeViewer.setContentProvider(new ComponentTreeViewProvider());
        _treeViewer.setLabelProvider(new TreeViewLabelProvider(_treeViewer));
        _treeViewer.setSorter(new ComponentTreeSorter());
        _treeViewer.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(final SelectionChangedEvent event)
            {
                getListener().selectionChanged(event);
            }

        });

        _model.init(new Runnable()
        {
            public void run()
            {
                PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable()
                {
                    public void run()
                    {
                        final Object[] expanded = _treeViewer
                                .getExpandedElements();
                        _treeViewer.refresh();
                        _treeViewer.setExpandedElements(expanded);
                    }
                });
            }
        });

        _treeViewer.setInput(_model);

        makeActions();
        return tree;
    }

    @Override
    protected void contributeActions(final IToolBarManager formManager,
            final IToolBarManager localManager)
    {
        // contribute to local tool bar
        localManager.add(_refreshAction);
        localManager.update(false);
    }

    private void makeActions()
    {
        _refreshAction = new Action()
        {
            @Override
            public void run()
            {
                _model.update();
            }
        };
        _refreshAction.setText(Messages.ComponentMasterForm_RefreshView);
        _refreshAction.setToolTipText(Messages.ComponentMasterForm_RefreshView);
        _refreshAction.setImageDescriptor(JSFUICommonPlugin.getDefault()
                .getImageDescriptor("refresh_nav_16.gif")); //$NON-NLS-1$
    }

    @Override
    protected void contributeToHeadArea(final FormToolkit toolkit,
            final Composite container)
    {
        final Text label = new Text(container
                , SWT.READ_ONLY | SWT.SINGLE | SWT.LEFT);

        String viewId = _model.getViewId();
        viewId = viewId == null ? Messages.ComponentMasterForm_Unknown : viewId;        
        label.setText(NLS.bind(Messages.ComponentMasterForm_HeadAreaText,  viewId,
                _model.getProject().getName())); 
    }

    private static class TreeViewLabelProvider extends LabelProvider
    {
        private final IInputProvider          _inputProvider;
        private final MetadataTagImageManager _metadataIconManager;

        /**
         * @param inputProvider
         */
        private TreeViewLabelProvider(final IInputProvider inputProvider)
        {
            super();
            _inputProvider = inputProvider;
            _metadataIconManager = new MetadataTagImageManager();
        }

        @Override
        public String getText(final Object obj)
        {
            if (obj instanceof ComponentInfo)
            {
                String text = ""; //$NON-NLS-1$
                final ComponentInfo compInfo = (ComponentInfo) obj;
                String className = compInfo.getComponentTypeInfo()
                        .getClassName();
                final int dotIdx = className.lastIndexOf('.');
                if (dotIdx > -1 && dotIdx + 1 < className.length())
                {
                    className = className.substring(dotIdx + 1);
                }
                text = className;

                final String id = compInfo.getId();

                if (id != null)
                {
                    text += " (id=" + id + ")"; //$NON-NLS-1$ //$NON-NLS-2$
                }
                return text;
            }
            else if (obj instanceof TreePlaceHolder)
            {
                return Messages.ComponentMasterForm_CalculatingView;
            }
            return obj.toString();
        }

        @Override
        public Image getImage(final Object obj)
        {
            Image image = null;
            if (obj instanceof ViewObject)
            {
                final XMLViewObjectMappingService mappingService = getMappingService();

                if (mappingService != null)
                {
                    final ElementData elementData = mappingService
                            .findElementData((ViewObject) obj);

                    if (elementData != null)
                    {
                        final TagIdentifier tagId = elementData.getTagId();
                        final DTJSFViewModel model = getModel();
                        if (model != null)
                        {
                            image = _metadataIconManager.getSmallIconImage(
                                    model.getFile(), tagId);
                        }
                    }
                }
                if (image == null)
                {
                    image = getDefaultImage((ViewObject) obj);
                }
            }
            else if (obj instanceof TreePlaceHolder)
            {
                return JSFUICommonPlugin.getDefault().getImage("configs.gif"); //$NON-NLS-1$
            }
            if (image == null)
            {
                final String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
                image = PlatformUI.getWorkbench().getSharedImages().getImage(
                        imageKey);
            }

            return image;
        }

        private XMLViewObjectMappingService getMappingService()
        {
            final DTJSFViewModel model = getModel();

            if (model != null)
            {
                final DTUIViewRoot root = model.getRoot();

                if (root != null)
                {
                    return (XMLViewObjectMappingService) root.getServices()
                            .getAdapter(XMLViewObjectMappingService.class);
                }
            }
            return null;
        }

        private DTJSFViewModel getModel()
        {
            final Object input = _inputProvider.getInput();

            if (input instanceof DTJSFViewModel)
            {
                return (DTJSFViewModel) input;
            }
            return null;
        }

        private Image getDefaultImage(final ViewObject obj)
        {
            if (obj instanceof DTUIViewRoot)
            {
                return JSFUICommonPlugin.getDefault().getImage(
                        JSFSharedImages.GENERIC_VIEWROOT_IMG);
            }
            if (obj instanceof ComponentInfo)
            {
                return JSFUICommonPlugin.getDefault().getImage(
                        JSFSharedImages.GENERIC_OBJECT_IMG);
            }
            else if (obj instanceof ConverterDecorator)
            {
                return JSFUICommonPlugin.getDefault().getImage(
                        JSFSharedImages.GENERIC_CONVERTER_IMG);
            }
            else if (obj instanceof ValidatorDecorator)
            {
                return JSFUICommonPlugin.getDefault().getImage(
                        JSFSharedImages.GENERIC_VALIDATOR_IMG);
            }
            return JSFUICommonPlugin.getDefault().getImage(
                    JSFSharedImages.DEFAULT_PALETTE_TAG_IMG);
        }
    }

    private static class ComponentTreeSorter extends ViewerSorter
    {
        @Override
        public int compare(final Viewer viewer, final Object e1, final Object e2)
        {
            return 0;
        }

        @Override
        public int category(final Object element)
        {
            // sort decorators first into their own category
            if (element instanceof Decorator)
            {
                return 0;
            }
            return 1;
        }
    }
}
