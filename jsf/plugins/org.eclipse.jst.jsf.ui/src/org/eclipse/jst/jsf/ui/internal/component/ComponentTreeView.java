package org.eclipse.jst.jsf.ui.internal.component;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.Decorator;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ResolverUtil;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.ui.internal.component.ComponentDetailTableProvider.ComponentProperty;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.IPage;
import org.eclipse.ui.part.MessagePage;
import org.eclipse.ui.part.Page;
import org.eclipse.ui.part.PageBook;
import org.eclipse.ui.part.PageBookView;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/**
 * A basic page-based view, similar to outline, that shows the design-time
 * component tree for a JSF view definition.
 */

public class ComponentTreeView extends PageBookView
{

    /**
     * This is a callback that will allow us to create the viewer and initialize
     * it.
     */
    @Override
    public void createPartControl(final Composite parent)
    {
        super.createPartControl(parent);

        // Create the help context id for the viewer's control
        // PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(),
        // "ComponentTreeViewerPrototype.viewer");
        // makeActions();
        // hookContextMenu();
        // hookDoubleClickAction();
        // contributeToActionBars();
    }

    // private void hookContextMenu() {
    // MenuManager menuMgr = new MenuManager("#PopupMenu");
    // menuMgr.setRemoveAllWhenShown(true);
    // menuMgr.addMenuListener(new IMenuListener() {
    // public void menuAboutToShow(IMenuManager manager) {
    // SampleView.this.fillContextMenu(manager);
    // }
    // });
    // Menu menu = menuMgr.createContextMenu(viewer.getControl());
    // viewer.getControl().setMenu(menu);
    // getSite().registerContextMenu(menuMgr, viewer);
    // }

    // private void contributeToActionBars() {
    // IActionBars bars = getViewSite().getActionBars();
    // fillLocalPullDown(bars.getMenuManager());
    // fillLocalToolBar(bars.getToolBarManager());
    // }

    // private void fillLocalPullDown(IMenuManager manager) {
    // manager.add(action1);
    // manager.add(new Separator());
    // manager.add(action2);
    //

    // private void fillContextMenu(IMenuManager manager) {
    // manager.add(action1);
    // manager.add(action2);
    // manager.add(new Separator());
    // drillDownAdapter.addNavigationActions(manager);
    // // Other plug-ins can contribute there actions here
    // manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
    // }
    //
    // private void fillLocalToolBar(IToolBarManager manager) {
    // manager.add(action1);
    // manager.add(action2);
    // manager.add(new Separator());
    // drillDownAdapter.addNavigationActions(manager);
    // }

    // private void makeActions() {
    // action1 = new Action() {
    // public void run() {
    // showMessage("Action 1 executed");
    // }
    // };
    // action1.setText("Action 1");
    // action1.setToolTipText("Action 1 tooltip");
    // action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
    // getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
    //
    // action2 = new Action() {
    // public void run() {
    // showMessage("Action 2 executed");
    // }
    // };
    // action2.setText("Action 2");
    // action2.setToolTipText("Action 2 tooltip");
    // action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages().
    // getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
    // doubleClickAction = new Action() {
    // public void run() {
    // ISelection selection = viewer.getSelection();
    // Object obj = ((IStructuredSelection)selection).getFirstElement();
    // showMessage("Double-click detected on "+obj.toString());
    // }
    // };
    // }

    // private void hookDoubleClickAction() {
    // viewer.addDoubleClickListener(new IDoubleClickListener() {
    // public void doubleClick(DoubleClickEvent event) {
    // doubleClickAction.run();
    // }
    // });
    // }
    // private void showMessage(String message) {
    // MessageDialog.openInformation(
    // viewer.getControl().getShell(),
    // "Sample View",
    // message);
    // }
    //
    // /**
    // * Passing the focus request to the viewer's control.
    // */
    // public void setFocus() {
    // viewer.getControl().setFocus();
    //

    @Override
    protected IPage createDefaultPage(final PageBook book)
    {
        final MessagePage page = new MessagePage();
        initPage(page);
        page.createControl(book);
        page.setMessage("Nothing to display");
        return page;
    }

    @Override
    protected PageRec doCreatePage(final IWorkbenchPart part)
    {
        final ComponentPage page = new ComponentPage(getDocumentFromPart(part));
        initPage(page);
        page.createControl(getPageBook());
        return new PageRec(part, page);
    }

    @Override
    protected void doDestroyPage(final IWorkbenchPart part,
            final PageRec pageRecord)
    {
        pageRecord.page.dispose();
        pageRecord.dispose();
    }

    private IDocument getDocumentFromPart(final IWorkbenchPart part)
    {
        return (IDocument) part.getAdapter(IDocument.class);
    }

    /**
     * The view shows the palette associated with the active editor.
     * 
     * @see PageBookView#getBootstrapPart()
     */
    @Override
    protected IWorkbenchPart getBootstrapPart()
    {
        final IWorkbenchPage page = getSite().getPage();
        if (page != null)
        {
            return page.getActiveEditor();
        }
        return null;
    }

    @Override
    protected boolean isImportant(final IWorkbenchPart part)
    {
        final IDocument  document = getDocumentFromPart(part);
        
        if (document != null)
        {
            IFile file = ResolverUtil.getFileForDocument(document);
            
            if (file != null)
            {
                DesignTimeApplicationManager manager =
                    DesignTimeApplicationManager.getInstance(file.getProject());
                
                if (manager != null)
                {
                    return manager.hasDTFacesContext(file);
                }
            }
        }
        
        // fall through, then no, not important.
        return false;
    }

    private static class ComponentPage extends Page
    {
        private TreeViewer _treeViewer;
        private final IDocument _document;
        private TableViewer _detailsViewer;
        private Composite _splitThePane;

        public ComponentPage(final IDocument document)
        {
            _document = document;
        }

        @Override
        public void createControl(final Composite parent)
        {
            final GridData gridData = new GridData(SWT.FILL, SWT.FILL, true,
                    true);

            _splitThePane = new Composite(parent, SWT.NONE);
            _splitThePane.setLayoutData(gridData);
            GridLayout layout = new GridLayout(2, true);
            _splitThePane.setLayout(layout);

            _treeViewer = new TreeViewer(_splitThePane, SWT.MULTI
                    | SWT.H_SCROLL | SWT.V_SCROLL |SWT.BORDER);
            _treeViewer.getTree().setLayoutData(gridData);
            _treeViewer.setContentProvider(new ComponentTreeViewProvider());
            _treeViewer.setLabelProvider(new TreeViewLabelProvider());
            _treeViewer.setSorter(new ComponentTreeSorter());

            final DTJSFViewModel model = new DTJSFViewModel(
                    (IStructuredDocument) _document);
            model.init(new Runnable()
            {
                public void run()
                {
                    PlatformUI.getWorkbench().getDisplay().asyncExec(
                            new Runnable()
                            {
                                public void run()
                                {
                                    _treeViewer.setInput(model);
                                }
                            });
                }

            });

            _detailsViewer = new TableViewer(_splitThePane,SWT.MULTI
                    | SWT.H_SCROLL | SWT.V_SCROLL |SWT.BORDER);
            _detailsViewer.getTable().setLayoutData(gridData);
            TableColumn column = new TableColumn(_detailsViewer.getTable(),
                    SWT.NONE);
            column.setWidth(100);
            column = new TableColumn(_detailsViewer.getTable(), SWT.NONE);
            column.setWidth(100);
            _detailsViewer
                    .setContentProvider(new ComponentDetailTableProvider());
            _detailsViewer.setLabelProvider(new ComponentDetailLabelProvider());

            // hook selection in the tree to setting the input on table
            _treeViewer
                    .addSelectionChangedListener(new ISelectionChangedListener()
                    {
                        public void selectionChanged(SelectionChangedEvent event)
                        {
                            IStructuredSelection selection = (IStructuredSelection) event
                                    .getSelection();
                            
                            if (selection != null && selection.size()>0)
                            _detailsViewer
                                    .setInput(selection.getFirstElement());
                        }

                    });
        }

        @Override
        public Control getControl()
        {
            return _splitThePane;
        }

        @Override
        public void setFocus()
        {
            // do nothing
        }

        static class TreeViewLabelProvider extends LabelProvider
        {
            @Override
            public String getText(final Object obj)
            {
                return obj.toString();
            }

            @Override
            public Image getImage(final Object obj)
            {
                final String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
                return PlatformUI.getWorkbench().getSharedImages().getImage(
                        imageKey);
            }
        }

        static class ComponentDetailLabelProvider extends LabelProvider
                implements ITableLabelProvider
        {
            public Image getColumnImage(final Object element,
                    final int columnIndex)
            {
                // no image
                return null;
            }

            public String getColumnText(final Object element,
                    final int columnIndex)
            {
                String columnText = getText(element);

                if (element instanceof ComponentProperty)
                {
                    switch (columnIndex)
                    {
                        case 0:
                            columnText = ((ComponentProperty) element)
                                    .getName();
                            break;

                        case 1:
                            final Object val = ((ComponentProperty) element)
                                    .getValue();

                            columnText = val != null ? val.toString() : "null";
                    }
                }
                return columnText;
            }

        }
    }

    private static class ComponentTreeSorter extends ViewerSorter
    {
        
        @Override
        public int compare(Viewer viewer, Object e1, Object e2)
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