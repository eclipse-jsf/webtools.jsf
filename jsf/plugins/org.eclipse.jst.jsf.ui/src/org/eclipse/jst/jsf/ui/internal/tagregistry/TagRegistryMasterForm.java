/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.form.AbstractMasterForm;
import org.eclipse.jst.jsf.common.ui.internal.utils.JSFSharedImages;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.ui.internal.JSFUITraceOptions;
import org.eclipse.jst.jsf.ui.internal.tagregistry.ProjectTracker.ProjectAdvisor;
import org.eclipse.jst.jsf.ui.internal.tagregistry.ProjectTracker.ProjectTrackingListener;
import org.eclipse.jst.jsf.ui.internal.tagregistry.TaglibContentProvider.TagRegistryInstance;
import org.eclipse.jst.jsf.ui.internal.tagregistry.TaglibContentProvider.TreePlaceholder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.model.WorkbenchAdapter;
import org.eclipse.ui.model.WorkbenchViewerComparator;

/**
 * The master form in the master/detail block for tag registry. Used to display
 * a tree view of each tag registy on the currently selected project and its
 * contents.
 * 
 * @author cbateman
 * 
 */
public class TagRegistryMasterForm extends AbstractMasterForm
{
    private static final String REFRESH_NAV_IMAGE_FILE = "refresh_nav_16.gif"; //$NON-NLS-1$
    
	private TreeViewer           _registryTreeViewer;
    // private Action _selectProjectAction;
    private Action               _refreshAction;

    private final ProjectTracker _projectTracker;
    private final ProjectAdvisor _advisor;
    private GenerateMetadataAction _generateMetadataAction;

    /**
     * @param toolkit
     */
    public TagRegistryMasterForm(final FormToolkit toolkit)
    {
        super(toolkit);
        _advisor = new ProjectAdvisor()
        {
            @Override
            public boolean shouldTrack(final IProject project)
            {
                return JSFAppConfigUtils.isValidJSFProject(project);
            }
        };

        _projectTracker = new ProjectTracker(ResourcesPlugin.getWorkspace()
                .getRoot(), _advisor);
    }

    /**
     * @param parent
     * @return the contents main control
     */
    @Override
    public Control createClientArea(final Composite parent)
    {
        final Tree tree = getToolkit().createTree(parent,
                SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

        final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
        tree.setLayoutData(gridData);
        _registryTreeViewer = new TreeViewer(tree);
        // _drillDownAdapter = new DrillDownAdapter(_viewer);
        _registryTreeViewer.setContentProvider(new TaglibContentProvider());
        _registryTreeViewer.setLabelProvider(new CommonLabelProvider());
        _registryTreeViewer.setSorter(new NameSorter());

        PlatformUI.getWorkbench().getHelpSystem()
                .setHelp(_registryTreeViewer.getControl(),
                        "ViewHandlerPrototype.viewer"); //$NON-NLS-1$

        _projectTracker.addListener(new ProjectTrackingListener()
        {
            @Override
            protected void projectsChanged(final IProject project,
                    final Reason reason)
            {
                switch (reason)
                {
                    case ADDED:
                    {
                        // nothing to do, since new project can't be selected
                        // yet
                    }
                    case REMOVED:
                    {
                        // if the removed project is the one that is current
                        // update the treeviewer
                        if (project.equals(_registryTreeViewer.getInput()))
                        {
                            updateProjects();
                        }
                    }
                }
            }
        });

        _registryTreeViewer
                .addSelectionChangedListener(new ISelectionChangedListener()
                {

                    public void selectionChanged(
                            final SelectionChangedEvent event)
                    {
                        getListener().selectionChanged(event);
                        updateActions(event.getSelection());
                    }
                });
        makeActions();
        createContextMenuManager(_registryTreeViewer.getControl());
        return tree;
    }

    /**
     * dispose of the master form
     */
    @Override
    public void dispose()
    {
        _projectTracker.dispose();
    }

    private void updateProjects()
    {
        final Set<IProject> projects = _projectTracker.getProjects();

        for (final IProject project : projects)
        {
            new SetInputRunnable(project, _registryTreeViewer).run();
            break;
        }
    }

    @Override
    protected final void contributeActions(IToolBarManager formManager,
            IToolBarManager localManager)
    {
        // do nothing to the manager; we have our own toolbar

        // contribute to local tool bar
        localManager.add(_refreshAction);
        localManager.update(false);
    }

    /**
     */
    @Override
    protected void contributeToHeadArea(final FormToolkit toolkit,
            final Composite container)
    {
        final Label label = new Label(container, SWT.NONE);
        label.setText(Messages.TagRegistryMasterForm_Project);
        final ComboViewer combo = new ComboViewer(container, SWT.FLAT
                | SWT.READ_ONLY);
        getToolkit().adapt(combo.getControl(), true, false);
        combo.addSelectionChangedListener(new ISelectionChangedListener()
        {
            public void selectionChanged(SelectionChangedEvent event)
            {
                IStructuredSelection selection = (IStructuredSelection) event
                        .getSelection();

                final IProject selectedProject = (IProject) selection
                        .getFirstElement();
                _registryTreeViewer.setInput(selectedProject);
            }
        });
        combo.setLabelProvider(new CommonLabelProvider());
        combo.setContentProvider(new ProjectContentProvider());
        combo.setComparator(new WorkbenchViewerComparator());
        combo.setInput(_projectTracker);
        _projectTracker.addListener(new ProjectTrackingListener()
        {
            @Override
            protected void projectsChanged(IProject project, Reason reason)
            {
                combo.getControl().getDisplay().asyncExec(new Runnable()
                {
                    public void run()
                    {
                        combo.refresh();
                    }
                });
            }
        });
    }

    /**
     */
    @Override
    public void doInitialize()
    {
        _projectTracker.startTracking();
    }

    private void makeActions()
    {
        if (_registryTreeViewer == null)
        {
            throw new IllegalStateException(
                    "_registryTreeViewer must be initialized before calling makeActions"); //$NON-NLS-1$
        }
        _refreshAction = new RefreshAction(_registryTreeViewer);
        _refreshAction.setText(Messages.TagRegistryMasterForm_RefreshRegistry);
        _refreshAction.setToolTipText(Messages.TagRegistryMasterForm_RefreshRegistry);
        _refreshAction.setImageDescriptor(JSFUICommonPlugin.getDefault()
                .getImageDescriptor(REFRESH_NAV_IMAGE_FILE));
        
        if (JSFUITraceOptions.TRACE_METADATAGEN)
        {
            _generateMetadataAction = new GenerateMetadataAction();
        }
    }

    private void createContextMenuManager(final Control control)
    {
        // Create menu manager.
        MenuManager menuMgr = new MenuManager();
           menuMgr.setRemoveAllWhenShown(true);
           menuMgr.addMenuListener(new IMenuListener() {
                   public void menuAboutToShow(IMenuManager mgr) {
                           fillContextMenu(mgr);
                   }
           });
           
           // Create menu.
        Menu menu = menuMgr.createContextMenu(control);
           control.setMenu(menu);
           
           // Register menu for extension.
        //getSite().registerContextMenu(menuMgr, viewer);
    }
    
    private void fillContextMenu(IMenuManager mgr)
    {
        if (JSFUITraceOptions.TRACE_METADATAGEN)
        {
            mgr.add(_generateMetadataAction);
        }
    }
    
    private void updateActions(final ISelection selection)
    {
        if (JSFUITraceOptions.TRACE_METADATAGEN)
        {
            updateMetadataGenAction(selection);
        }
    }

    private void updateMetadataGenAction(final ISelection selection)
    {
        if (selection instanceof IStructuredSelection)
        {
            if (((IStructuredSelection)selection).getFirstElement() instanceof Namespace)
            {
                Namespace ns =  (Namespace) ((IStructuredSelection)selection).getFirstElement();
                _generateMetadataAction.setNamespace(ns);
                _generateMetadataAction.setEnabled(true);
                return;
            }
        }
        _generateMetadataAction.setEnabled(false);
    }
    
    private static class RefreshAction extends Action
    {
        private final StructuredViewer _viewer;

        /**
         * @param viewer
         */
        private RefreshAction(final StructuredViewer viewer)
        {
            super();
            _viewer = viewer;
            setEnabled(false);
            _viewer.addSelectionChangedListener(new ISelectionChangedListener()
            {
                public void selectionChanged(SelectionChangedEvent event)
                {
                    boolean enabled = false;
                    final Object selectedObj = getSelected(event.getSelection());
                    if (selectedObj instanceof TagRegistryInstance)
                    {
                        enabled = true;
                    }
                    setEnabled(enabled);
                }
            });
        }

        @Override
        public void run()
        {
            MessageDialog dialog = new MessageDialog(_viewer.getControl().getShell(), Messages.TagRegistryMasterForm_FlushCacheQuestion, null, // accept
                    // the
                    // default
                    // window
                    // icon
                    Messages.TagRegistryMasterForm_FlushCacheMessage
                    , MessageDialog.QUESTION, new String[] { IDialogConstants.YES_LABEL,
                            IDialogConstants.NO_LABEL }, 1); // no is the
                                                                // default
            final boolean flushCaches =  (dialog.open() == 0);
            final Object selectedObj = getSelected(_viewer.getSelection());

            if (selectedObj instanceof TagRegistryInstance)
            {
                final TagRegistryInstance registryInstance = (TagRegistryInstance) selectedObj;
                final ITagRegistry registry = registryInstance.getRegistry();
                if (registry != null)
                {
                    // need a non-null runnable so that refresh won't block,
                    // but don't need to fire events, since the registry will
                    // fire events on change.
                    final Runnable nullRunnable = new Runnable()
                    {
                        public void run()
                        {/* do nothing */
                        }
                    };

                    registry.refresh(nullRunnable, flushCaches);
                }
            }
        }

        private Object getSelected(ISelection selection)
        {
            if (selection instanceof IStructuredSelection)
            {
                final IStructuredSelection structuredSel = (IStructuredSelection) selection;
                if (structuredSel.size() == 1)
                {
                    return structuredSel.getFirstElement();
                }
            }
            return null;
        }
    }

    private static class ProjectContentProvider extends
            BaseWorkbenchContentProvider
    {
        @Override
        protected IWorkbenchAdapter getAdapter(final Object element)
        {
            return new WorkbenchAdapter()
            {
                @Override
                public Object[] getChildren(final Object object)
                {
                    if (object instanceof ProjectTracker)
                    {
                        return ((ProjectTracker) object).getProjects()
                                .toArray();
                    }
                    return new Object[0];
                }
            };
        }
    }

    private static class CommonLabelProvider extends LabelProvider
    {
        private static final String CONFIGS_IMAGE_FILE = "configs.gif"; //$NON-NLS-1$

		@Override
        public String getText(final Object obj)
        {
            if (obj instanceof TagRegistryInstance)
            {
                return ((TagRegistryInstance) obj).getInfo().getDescription();
            }
            else if (obj instanceof Namespace)
            {
                if (((Namespace) obj).getDisplayName() != null)
                {
                    return ((Namespace) obj).getDisplayName();
                }
                return ((Namespace) obj).getNSUri();
            }
            else if (obj instanceof ITagElement)
            {
                return ((ITagElement) obj).getName();
            }
            else if (obj instanceof ITagAttribute)
            {
                return ((ITagAttribute)obj).getName();
            }
            else if (obj instanceof TreePlaceholder)
            {
                return ((TreePlaceholder) obj).getText();
            }
            else if (obj instanceof IProject)
            {
                return ((IProject) obj).getName();
            }
            return obj.toString();
        }

        @Override
        public Image getImage(final Object obj)
        {
            if (obj instanceof Namespace)
            {
                return JavaUI.getSharedImages().getImage(
                        org.eclipse.jdt.ui.ISharedImages.IMG_OBJS_LIBRARY);
            }
            else if (obj instanceof TagRegistryInstance)
            {
                final String imageKey = ISharedImages.IMG_OBJ_FOLDER;
                return PlatformUI.getWorkbench().getSharedImages().getImage(
                        imageKey);
            }
            else if (obj instanceof ITagElement)
            {
                if (obj instanceof IComponentTagElement)
                {
                    return JSFUICommonPlugin.getDefault().getImage(
                            JSFSharedImages.GENERIC_OBJECT_IMG);
                }
                else if (obj instanceof IConverterTagElement)
                {
                    return JSFUICommonPlugin.getDefault().getImage(
                            JSFSharedImages.GENERIC_CONVERTER_IMG);
                }
                else if (obj instanceof IValidatorTagElement)
                {
                    return JSFUICommonPlugin.getDefault().getImage(
                            JSFSharedImages.GENERIC_VALIDATOR_IMG);
                }
                return JSFUICommonPlugin.getDefault().getImage(
                        JSFSharedImages.DEFAULT_PALETTE_TAG_IMG);
            }
            else if (obj instanceof TreePlaceholder)
            {
                return JSFUICommonPlugin.getDefault().getImage(CONFIGS_IMAGE_FILE);
            }

            final String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
            return PlatformUI.getWorkbench().getSharedImages().getImage(
                    imageKey);
        }
    }

    class NameSorter extends ViewerSorter
    {
        // do nothing
    }
}
