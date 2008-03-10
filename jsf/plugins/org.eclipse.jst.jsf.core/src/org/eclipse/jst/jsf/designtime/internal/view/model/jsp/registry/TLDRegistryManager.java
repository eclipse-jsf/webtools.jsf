package org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager;
import org.eclipse.jst.jsf.core.internal.JSFCoreTraceOptions;
import org.eclipse.jst.jsf.designtime.internal.view.model.ITagRegistry;
import org.eclipse.jst.jsf.designtime.internal.view.model.TagRegistryFactory;

/**
 * A per-resource singleton manager for TLDTagRegistry's.
 * 
 * @author cbateman
 * 
 */
public final class TLDRegistryManager extends
                        ResourceSingletonObjectManager<TLDTagRegistry, IProject>
{
    // STATIC
    private static TLDRegistryManager                  INSTANCE;

    /**
     * @return the singleton instance
     */
    private static TLDRegistryManager getGlobalManager()
    {
        if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
        {
            JSFCoreTraceOptions
                    .log("TLDRegistryManager: Initializing TLDRegistryManager singleton");
        }

        synchronized(TLDRegistryManager.class)
        {
            if (INSTANCE == null)
            {
                INSTANCE = new TLDRegistryManager();
            }
            
            return INSTANCE;
        }
    }

    private TLDRegistryManager()
    {
        // do nothing
    }

    @Override
    protected TLDTagRegistry createNewInstance(final IProject project)
    {
        if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
        {
            JSFCoreTraceOptions.log("TLDRegistryManager: creating new instance for "
                    + project.toString());
        }

        return new TLDTagRegistry(project);
    }

    @Override
    protected void runAfterGetInstance(final IProject resource)
    {
        if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
        {
            JSFCoreTraceOptions.log("TLDRegistryManager: Acquired instance for "
                    + resource.toString());
        }
    }

    @Override
    protected void runBeforeGetInstance(final IProject resource)
    {
        if (JSFCoreTraceOptions.TRACE_TLDREGISTRYMANAGER)
        {
            JSFCoreTraceOptions.log("TLDRegistryManager: Getting registry for "
                    + resource.toString());
        }
    }
    
    /**
     * Adapter used to allow creation by extension point.
     * 
     * @author cbateman
     *
     */
    public static class MyRegistryFactory extends TagRegistryFactory
    {
        @Override
        public ITagRegistry createTagRegistry(IProject project) throws TagRegistryFactoryException
        {
            try
            {
                return getGlobalManager().getInstance(project);
            }
            catch (ManagedObjectException e)
            {
                throw new TagRegistryFactoryException(e);
            }
        }

        public String getDisplayName()
        {
            return "JSP Registry Factory";
        }
        
    }
}
