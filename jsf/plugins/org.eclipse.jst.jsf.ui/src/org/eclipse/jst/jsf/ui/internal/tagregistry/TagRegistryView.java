package org.eclipse.jst.jsf.ui.internal.tagregistry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.TagElement;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDTagRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;


/**
 * This sample class demonstrates how to plug-in a new
 * workbench view. The view shows data obtained from the
 * model. The sample creates a dummy model on the fly,
 * but a real implementation would connect to the model
 * available either in this or another plug-in (e.g. the workspace).
 * The view is connected to the model using a content provider.
 * <p>
 * The view uses a label provider to define how model
 * objects should be presented in the view. Each
 * view can present the same model objects using
 * different labels and icons, if needed. Alternatively,
 * a single label provider can be shared between views
 * in order to ensure that objects of the same type are
 * presented in the same way everywhere.
 * <p>
 */

public class TagRegistryView extends ViewPart
{
    private TreeViewer viewer;
    private DrillDownAdapter drillDownAdapter;
    private Action _selectProjectAction;
    private Action action2;
    private Action doubleClickAction;

    class ViewLabelProvider extends LabelProvider {

        @Override
        public String getText(final Object obj) {
            if (obj instanceof Namespace)
            {
                if (((Namespace)obj).getDisplayName() != null)
                {
                    return ((Namespace)obj).getDisplayName();
                }
                return ((Namespace)obj).getNSUri();
            }
            else if (obj instanceof TagElement)
            {
                return ((ITagElement)obj).getName();
            }
            return obj.toString();
        }
        @Override
        public Image getImage(final Object obj) {
            final String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
            return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
        }
    }
    class NameSorter extends ViewerSorter {
        // do nothing
    }

    /**
     * The constructor.
     */
    public TagRegistryView() {
        // do nothing
    }

    /**
     * This is a callback that will allow us
     * to create the viewer and initialize it.
     */
    @Override
    public void createPartControl(final Composite parent) {
        viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        drillDownAdapter = new DrillDownAdapter(viewer);
        viewer.setContentProvider(new TaglibContentProvider());
        viewer.setLabelProvider(new ViewLabelProvider());
        viewer.setSorter(new NameSorter());

        final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();

        for (final IProject project : projects)
        {
            if (project.isAccessible())
            {
                viewer.setInput(project);
            }
        }

        // Create the help context id for the viewer's control
        PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(), "ViewHandlerPrototype.viewer");
        makeActions();
        hookContextMenu();
        hookDoubleClickAction();
        contributeToActionBars();
    }

    private void hookContextMenu() {
        final MenuManager menuMgr = new MenuManager("#PopupMenu");
        menuMgr.setRemoveAllWhenShown(true);
        menuMgr.addMenuListener(new IMenuListener() {
            public void menuAboutToShow(final IMenuManager manager) {
                TagRegistryView.this.fillContextMenu(manager);
            }
        });
        final Menu menu = menuMgr.createContextMenu(viewer.getControl());
        viewer.getControl().setMenu(menu);
        getSite().registerContextMenu(menuMgr, viewer);
    }

    private void contributeToActionBars() {
        final IActionBars bars = getViewSite().getActionBars();
        fillLocalPullDown(bars.getMenuManager());
        fillLocalToolBar(bars.getToolBarManager());
    }

    private void fillLocalPullDown(final IMenuManager manager) {
        manager.add(_selectProjectAction);
        manager.add(new Separator());
        manager.add(action2);
    }

    private void fillContextMenu(final IMenuManager manager) {
        manager.add(_selectProjectAction);
        manager.add(action2);
        manager.add(new Separator());
        drillDownAdapter.addNavigationActions(manager);
        // Other plug-ins can contribute there actions here
        manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    }

    private void fillLocalToolBar(final IToolBarManager manager) {
        manager.add(_selectProjectAction);
        manager.add(action2);
        manager.add(new Separator());
        drillDownAdapter.addNavigationActions(manager);
    }

    private void makeActions()
    {
        final IMenuCreator selectProjectCreator = new IMenuCreator()
        {
            private MenuManager createMenuMgr()
            {
                // TODO: this is inefficient but means we don't
                // have to do the laborious process of filtering
                // workspace changes.
                final MenuManager dropDownMenuMgr =  new MenuManager();

                final IProject projects[] =
                    ResourcesPlugin.getWorkspace().getRoot().getProjects();

                for (final IProject project : projects)
                {
                    if (JSFAppConfigUtils.isValidJSFProject(project))
                    {
                        dropDownMenuMgr.add(new Action(project.getName())
                        {
                            @Override
                            public void run()
                            {
                                viewer.setInput(project);
                            }
                        });
                    }
                }

                return dropDownMenuMgr;
            }

            public void dispose()
            {
                // do nothing
            }

            public Menu getMenu(Control parent) {
                MenuManager manager = createMenuMgr();
                return manager.createContextMenu(parent);
            }

            public Menu getMenu(Menu parent) {
                final MenuManager manager = createMenuMgr();
                final Menu menu = new Menu(parent);
                final IContributionItem[] items = manager.getItems();
                for (final IContributionItem item : items) {
                    IContributionItem newItem = item;
                    if (item instanceof ActionContributionItem) {
                        newItem = new ActionContributionItem(
                                ((ActionContributionItem) item).getAction());
                    }
                    newItem.fill(menu, -1);
                }
                return menu;
            }

        };

        _selectProjectAction = new Action("Set project"){/*do nothing*/};
        _selectProjectAction.setToolTipText("Action 1 tooltip");
        _selectProjectAction.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
                getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
        _selectProjectAction.setMenuCreator(selectProjectCreator);

        action2 = new Action() {
            @Override
            public void run() {
                final Object input = viewer.getInput();

                if (input instanceof IProject)
                {
                    //FaceletTagRegistry.getRegistry((IProject) input).refresh();
                    TLDTagRegistry.getRegistry((IProject)input).refresh();
                    viewer.refresh();
                }
            }
        };
        action2.setText("Refresh Registry");
        action2.setToolTipText("Refresh Registry");
        action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
                getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
        doubleClickAction = new Action() {
            @Override
            public void run() {
                final ISelection selection = viewer.getSelection();
                final Object obj = ((IStructuredSelection)selection).getFirstElement();
                showMessage("Double-click detected on "+obj.toString());
            }
        };
    }

    private void hookDoubleClickAction() {
        viewer.addDoubleClickListener(new IDoubleClickListener() {
            public void doubleClick(final DoubleClickEvent event) {
                doubleClickAction.run();
            }
        });
    }
    private void showMessage(final String message) {
        MessageDialog.openInformation(
                viewer.getControl().getShell(),
                "Sample View",
                message);
    }

    /**
     * Passing the focus request to the viewer's control.
     */
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }
}