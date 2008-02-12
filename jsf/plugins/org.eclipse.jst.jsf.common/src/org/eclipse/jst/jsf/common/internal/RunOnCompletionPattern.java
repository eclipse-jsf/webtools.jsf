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
                JSFCommonPlugin.log(e, "Joining on job");
            }
        }
    }
    
    
}
