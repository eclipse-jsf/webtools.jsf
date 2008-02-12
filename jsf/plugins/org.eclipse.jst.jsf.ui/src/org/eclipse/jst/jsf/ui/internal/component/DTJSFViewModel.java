package org.eclipse.jst.jsf.ui.internal.component;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.DTUIViewRoot;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;


/**
 * The top-level input model for a design time component tree
 * 
 * @author cbateman
 *
 */
public class DTJSFViewModel 
{
    private final IStructuredDocument           _document;
    private final IProject                      _project;
    private final IFile                         _file;
    private DTUIViewRoot                        _treeRoot;
    private final IStructuredDocumentContext    _context;

    /**
     * @param document
     * @throws IllegalArgumentException if document cannot be used to initialize a component tree.
     */
    public DTJSFViewModel(final IStructuredDocument document)
    {
        _document = document;
        _context =
            IStructuredDocumentContextFactory.INSTANCE.getContext(_document, -1);
        
        final IWorkspaceContextResolver resolver =
            IStructuredDocumentContextResolverFactory.INSTANCE.getWorkspaceContextResolver(_context);
        
        if (resolver == null)
        {
            throw new IllegalArgumentException();
        }
        
        _project = resolver.getProject();
        _file = (IFile) resolver.getResource();
        
        if (_project == null || _file == null)
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * @return the structured document that this view model was created for.
     */
    public final IStructuredDocument getDocument() {
        return _document;
    }
    
    /**
     * Acquire and initialize the component tree root for the document
     * @param runnable 
     */
    public void init(final Runnable runnable)
    {
        final DesignTimeApplicationManager manager = 
            DesignTimeApplicationManager.getInstance(_project);
        
        if (manager != null)
        {
            final DTFacesContext facesContext = manager.getFacesContext(_file);
            
            if (facesContext != null)
            {
                _treeRoot = facesContext.getViewRoot();
                _treeRoot.refresh(runnable);
            }
        }
    }
    
    /**
     * @return the design time view root
     */
    public DTUIViewRoot getRoot()
    {
        return _treeRoot;
    }
}
