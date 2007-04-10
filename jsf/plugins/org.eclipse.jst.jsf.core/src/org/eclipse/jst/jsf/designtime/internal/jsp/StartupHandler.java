/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.internal.jsp;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.core.runtime.content.IContentTypeManager;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.internal.provisional.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWindowListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

/**
 * On workbench startup, registers a part listener that triggers when
 * a JSP editor opens.
 * 
 * @author cbateman
 *
 */
public class StartupHandler implements IStartup 
{
    private final JSPEditorListener    _partListener = new JSPEditorListener();
    
	public void earlyStartup() 
    {
        PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable()
        {
            public void run()
            {
                IWorkbenchWindow  windows[] = 
                    PlatformUI.getWorkbench().getWorkbenchWindows();
                
                for (int i = 0; i < windows.length; i++)
                {
                    IWorkbenchPage pages[] = windows[i].getPages();
                    for (int j = 0; j < pages.length; j++)
                    {
                        IEditorReference[]  editorReferences = 
                            pages[j].getEditorReferences();
                        
                        for (int k = 0; k < editorReferences.length; k++)
                        {
                            if (_partListener.isValidJSPEditor(editorReferences[k]))
                            {
                                _partListener.setJSPModelListener(editorReferences[k]);
                            }
                        }
                    }
                    windows[i].getPartService().addPartListener(_partListener);
                }
                
                // TODO: register with all windows?
                PlatformUI.getWorkbench().addWindowListener(new IWindowListener()
                {
        
                    public void windowActivated(IWorkbenchWindow window) {
                        // do nothing
                    }
        
                    public void windowDeactivated(IWorkbenchWindow window) {
                        // do nothing
                    }
        
                    public void windowClosed(IWorkbenchWindow window) {
                        window.getPartService().removePartListener(_partListener);
                    }
        
                    public void windowOpened(IWorkbenchWindow window) {
                        window.getPartService().addPartListener(_partListener);
                    }
                });    
            }
        });
	}

	private static class JSPEditorListener implements IPartListener2
	{
		public void partActivated(IWorkbenchPartReference partRef) {
			// do nothing
			
		}

		public void partBroughtToTop(IWorkbenchPartReference partRef) {
			// do nothing
		}

        public void partClosed(IWorkbenchPartReference partRef) {
            if (isValidJSPEditor(partRef))
            {
                releaseJSPModelListener((IEditorReference) partRef);
            }
		}

		public void partDeactivated(IWorkbenchPartReference partRef) {
			// do nothing
		}

		public void partOpened(IWorkbenchPartReference partRef) {
            if (isValidJSPEditor(partRef))
            {
                setJSPModelListener((IEditorReference)partRef);
            }
		}

		public void partHidden(IWorkbenchPartReference partRef) {
			// do nothing
		}

		public void partVisible(IWorkbenchPartReference partRef) {
			// do nothing
		}

		public void partInputChanged(IWorkbenchPartReference partRef) {
			// do nothing
			
		}
       
        private boolean isJSPEditor(IEditorReference editorRef)
        {
            IFile file = getIFile(editorRef);

            if (file != null)
            {
                IContentTypeManager typeManager = Platform.getContentTypeManager();
                IContentType jspContentType = 
                    typeManager.getContentType("org.eclipse.jst.jsp.core.jspsource");
                if (jspContentType != null
                        && jspContentType.isAssociatedWith(file.getName()))
                {
                    return true;
                }
            }

            return false;
        }
        
        /**
         * @param editorRef
         * @return true if the editor is editing the JSP content type and
         * the owning project is a JSF project
         */
        boolean isValidJSPEditor(IEditorReference editorRef)
        {
            final IFile file = getIFile(editorRef);
            
            return file != null && 
                    JSFAppConfigUtils.isValidJSFProject(file.getProject()) &&
                        isJSPEditor(editorRef);
        }
        
        
        boolean isValidJSPEditor(IWorkbenchPartReference partRef)
        {
            if (partRef instanceof IEditorReference)
            {
                return isValidJSPEditor((IEditorReference)partRef);
            }
            
            return false;
        }
        
        void setJSPModelListener(IEditorReference editorRef)
        {
            IFile file = getIFile(editorRef);
            
            if (file != null)
            {
                try
                {
                    // implicitly creates if not present
                    JSPModelProcessor processor = JSPModelProcessor.get(file);
                    processor.refresh(false);
                }
                catch (Exception e)
                {
                    JSFCorePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID, 0, "Error acquiring model processor",e));
                }
            }
        }
        
        void releaseJSPModelListener(IEditorReference editorRef)
        {
            IFile file = getIFile(editorRef);
            
            if (file != null)
            {
                JSPModelProcessor.dispose(file);
            }
        }
        
        IFile getIFile(IEditorReference editorRef)
        {
            try
            {
                IEditorInput editorInput = editorRef.getEditorInput();
                Object adapt = editorInput.getAdapter(IFile.class);
                
                if (adapt instanceof IFile)
                {
                    return (IFile) adapt;
                }
            }
            catch (PartInitException excp)
            {
                JSFCorePlugin.getDefault().getLog().log(new Status(IStatus.ERROR, JSFCorePlugin.PLUGIN_ID, 0, "Error acquiring editor input",excp));
            }
            
            return null;
        }
	}
}
