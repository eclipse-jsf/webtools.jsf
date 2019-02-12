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
package org.eclipse.jst.jsf.facesconfig.ui;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.ui.PlatformUI;

/**
 * Centralizes logic to load the faces config model off of the UI thread for
 * the FacesConfig Editor.
 * 
 * Also encapsulates the lifecycle for the instance of the faces artifact 
 * created for its editor.  All creation, access and destruction of the artifact
 * should be centralized through this class
 * 
 * @author cbateman
 *
 */
class ModelLoader 
{
    private FacesConfigArtifactEdit     _edit;
    private Job                         _loadModelJob;
    private CountDownLatch              _modelLoaded = new CountDownLatch(1);    
    
    /**
     * @return  the artifact edit or null if not loaded.  Should only be called
     * after load() is called and has executed its callback 
     */
    public synchronized FacesConfigArtifactEdit getEdit() {
        return _edit;
    }

    private synchronized void setEdit(FacesConfigArtifactEdit edit)
    {
        _edit = edit;
    }
    
    void waitForLoad(long timeoutMs) throws InterruptedException
    {
        _modelLoaded.await(timeoutMs, TimeUnit.MILLISECONDS);
    }
    
    /**
     * Dispose of the model and any unfinished loading operations
     * 
     * Must be run on the UI thread.
     */
    public synchronized void dispose()
    {
        assertOnDisplayThread();
       
        // if the load model job has not completed, cancel it
        if (_loadModelJob != null
                && _loadModelJob.getResult() == null)
        {
            _loadModelJob.cancel();
        }
        
        if (_edit != null)
        {
            _edit.dispose();
            //System.out.println("FacesConfigEditor.dispose(): isDisposed == "+_edit.isDisposed());
        }
    }
    
    /**
     * Load the model file located by path in project. Must be called from the UI thread.
     * 
     * Method does not block.
     * 
     * @param project
     * @param path
     * @param isWebProject 
     * @param signalComplete to be asyncExec'd on the UI thread when the model is loaded
     */
    public void load(final IProject project, final IPath path, final boolean isWebProject, final ModelLoaderComplete signalComplete)
    {
        assertOnDisplayThread();
        _loadModelJob = new ModelLoaderJob(project, path, isWebProject, signalComplete);
        _loadModelJob.schedule();
    }
    
    private class ModelLoaderJob extends Job
    {
        private final IProject                  _project;
        private final IPath                     _path;
        private final ModelLoaderComplete       _runnable;
        private final boolean                   _isWebProject;

        ModelLoaderJob(final IProject project, final IPath path, final boolean isWebProject, final ModelLoaderComplete signalComplete)
        {
            super(EditorMessages.ModelLoader_LoadingModelJobName);
            _project = project;
            _path = path;
            _runnable = signalComplete;
            _isWebProject = isWebProject;
        }
        
        @Override
        protected IStatus run(IProgressMonitor monitor) 
        {
            FacesConfigArtifactEdit artifactEdit = loadModel(_project, _path);
            
            // synchrnoize on the ModelLoader.  Ensure that any call to dispose()
            // that occurs before we set the edit is done atomically.
            synchronized(ModelLoader.this)
            {
                // only bother with this if the task hasn't been signalled for cancel
                if (!monitor.isCanceled())
                {
                    setEdit(artifactEdit);
                    
                    _runnable.setFacesConfigArtifactEdit(artifactEdit);
                    
                    // finish as quickly possible; we are holding the ModelLoader
                    // lock so we must ensure that we don't block.
                    // NEVER USE syncExec here.
                    PlatformUI.getWorkbench().getDisplay().asyncExec(_runnable);
                }
                // if we were cancelled, then dispose of the artifact edit
                else
                {
                    if (artifactEdit != null)
                    {
                        artifactEdit.dispose();
                    }
                }
            }
            
            // signal that we are done loading
            _modelLoaded.countDown();
            return Status.OK_STATUS;
        }
        
        /**
         * Loads the configuration model from the given path.
         * 
         */
        private FacesConfigArtifactEdit loadModel(IProject project, IPath modelPath) 
        {
        	FacesConfigArtifactEdit edit = null;
            if (_isWebProject) 
            {
                IContainer[] webContentContainers = WebrootUtil.getWebContentContainers(project);
                if (webContentContainers != null)
                {
	                for (IContainer webContentContainer: webContentContainers) {
	                    Assert.isTrue(webContentContainer != null && webContentContainer.exists());
	                    if (webContentContainer.getFullPath().isPrefixOf(modelPath))
	                    {
	                        IPath relativePath = modelPath;
	                        relativePath = modelPath.removeFirstSegments(
	                        		webContentContainer.getFullPath().segmentCount());
	                        edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(
	                        		project, relativePath.toString());
	                        break;
	                    }
	                }
                }
            }
            return edit;
        }
    }
    
    abstract static class ModelLoaderComplete implements Runnable
    {
        private FacesConfigArtifactEdit  _edit;
        
        private void setFacesConfigArtifactEdit(FacesConfigArtifactEdit  edit)
        {
            _edit = edit;
        }
        
        public final void run() 
        {
            assertOnDisplayThread();
            doRun(_edit);
        }
        
        /**
         * Called by the runnable.  Implementer should _not_ cache the edit variable
         * edit may be null
         * 
         * @param edit
         */
        protected abstract void doRun(FacesConfigArtifactEdit edit);
    }
    
    private static void assertOnDisplayThread()
    {
        if (Thread.currentThread() != PlatformUI.getWorkbench().getDisplay().getThread())
        {
            throw new IllegalStateException("ModelLoaderComplete must be called on the UI thread"); //$NON-NLS-1$
        }
    }
}
