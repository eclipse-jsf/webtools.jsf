/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.internal;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.core.runtime.jobs.JobChangeAdapter;
import org.eclipse.jst.jsf.common.JSFCommonPlugin;

/**
 * A pattern that runs a Job and either joins on the job's completion
 * or runs some completion behaviour.
 * 
 * @author cbateman
 *
 */
public class RunOnCompletionPattern implements Runnable
{
    private final Job  _job;
    private final Runnable _runAfter;
    
    /**
     * @param job
     * @param runAfter
     */
    public RunOnCompletionPattern(Job  job, Runnable runAfter)
    {
        _job = job;
        _runAfter = runAfter;
    }

    public void run()
    {
        if (_runAfter != null)
        {
            _job.addJobChangeListener(new JobChangeAdapter()
            {

                @Override
                public void done(IJobChangeEvent event)
                {
                    if (event.getResult().getSeverity() != IStatus.ERROR)
                    {
                        _runAfter.run();
                    }
                }
                
            });
        }

        _job.schedule();
        
        if (_runAfter == null)
        {
            try
            {
                _job.join();
            }
            catch (InterruptedException e)
            {
                JSFCommonPlugin.log(e, "Joining on job"); //$NON-NLS-1$
            }
        }
    }
    
    
}
