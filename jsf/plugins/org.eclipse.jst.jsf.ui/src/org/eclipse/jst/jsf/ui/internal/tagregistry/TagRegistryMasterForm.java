package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IComponentTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IConverterTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.common.ui.JSFUICommonPlugin;
import org.eclipse.jst.jsf.common.ui.internal.utils.JSFSharedImages;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.ui.internal.tagregistry.ProjectTracker.ProjectAdvisor;
import org.eclipse.jst.jsf.ui.internal.tagregistry.ProjectTracker.ProjectTrackingListener;
import org.eclipse.jst.jsf.ui.internal.tagregistry.TaglibContentProvider.TagRegistryInstance;
import org.eclipse.jst.jsf.ui.internal.tagregistry.TaglibContentProvider.TreePlaceholder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.internal.forms.widgets.FormsResources;
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
public class TagRegistryMasterForm
{
    private TreeViewer                _registryTreeViewer;
//    private Action                    _selectProjectAction;
    private Action                    _refreshAction;
    private final FormToolkit         _toolkit;
    private ISelectionChangedListener _listener;
    private final ProjectTracker      _projectTracker;
    private final ProjectAdvisor      _advisor;
    private ToolBarManager _toolBarManager;

    /**
     * @param toolkit
     */
    public TagRegistryMasterForm(final FormToolkit toolkit)
    {
        _toolkit = toolkit;
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
    public Control createContents(final Composite parent)
    {
        final Tree tree = _toolkit.createTree(parent, SWT.SINGLE | SWT.H_SCROLL
                | SWT.V_SCROLL);

        final GridData gridData = new GridData(SWT.FILL, SWT.CENTER, true, true);
        tree.setLayoutData(gridData);
        _registryTreeViewer = new TreeViewer(tree);
        // _drillDownAdapter = new DrillDownAdapter(_viewer);
        _registryTreeViewer.setContentProvider(new TaglibContentProvider());
        _registryTreeViewer.setLabelProvider(new CommonLabelProvider());
        _registryTreeViewer.setSorter(new NameSorter());

        PlatformUI.getWorkbench().getHelpSystem()
                .setHelp(_registryTreeViewer.getControl(),
                        "ViewHandlerPrototype.viewer");

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
                        _listener.selectionChanged(event);
                    }
                });
        makeActions();
        return tree;
    }

    /**
     * dispose of the master form
     */
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

    /**
     * @param manager
     */
    public void contributeActions(final IToolBarManager manager)
    {
        // do nothing to the manager; we have our own toolbar
        
        // contribute tour tool bar
        _toolBarManager.add(_refreshAction);
        _toolBarManager.update(false);
    }

    /**
     * @param form
     */
    public void contributeToHead(final Form form)
    {
        final Composite head = form.getHead();
        final Composite container = _toolkit.createComposite(head);
        container.setLayout(new RowLayout());
        final Label label = new Label(container, SWT.NONE);
        label.setText("Project: ");
        final ComboViewer combo = new ComboViewer(container, SWT.FLAT
                | SWT.READ_ONLY);
        _toolkit.adapt(combo.getControl(), true, false);
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
        
        _toolBarManager = new ToolBarManager(SWT.FLAT);
        ToolBar toolbar = _toolBarManager.createControl(container);
        //_toolkit.adapt(toolbar, false, false);
        
        toolbar.setBackground(form.getHead().getBackground());
        toolbar.setForeground(form.getHead().getForeground());
        toolbar.setCursor(FormsResources.getHandCursor());
        container.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(DisposeEvent e) {
                if (_toolBarManager != null) {
                    _toolBarManager.dispose();
                    _toolBarManager = null;
                }
            }
        });

        form.setHeadClient(container);
    }

    /**
     * @param listener
     */
    public void initialize(final ISelectionChangedListener listener)
    {
        _listener = listener;
        _projectTracker.startTracking();
    }

    private void makeActions()
    {
        _refreshAction = new Action()
        {
            @Override
            public void run()
            {
                final Object input = _registryTreeViewer.getInput();

                if (input instanceof IProject)
                {
                    _registryTreeViewer.setInput(input);
                }
            }
        };
        _refreshAction.setText("Refresh Registry");
        _refreshAction.setToolTipText("Refresh Registry");
        _refreshAction.setImageDescriptor(JSFUICommonPlugin.getDefault()
                .getImageDescriptor("refresh_nav_16.gif"));
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
            else if (obj instanceof TreePlaceholder)
            {
                return ((TreePlaceholder) obj).getText();
            }
            else if (obj instanceof IProject)
            {
                return ((IProject)obj).getName();
            }
            return obj.toString();
        }

        @Override
        public Image getImage(final Object obj)
        {
            if (obj instanceof Namespace)
            {
                final String imageKey = ISharedImages.IMG_OBJ_FOLDER;
                return PlatformUI.getWorkbench().getSharedImages().getImage(
                        imageKey);
            }
            if (obj instanceof ITagElement)
            {
                // ITagElement tagElement = (ITagElement) obj;

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
