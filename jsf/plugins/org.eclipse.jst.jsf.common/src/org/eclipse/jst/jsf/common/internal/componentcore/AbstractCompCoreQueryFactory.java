package org.eclipse.jst.jsf.common.internal.componentcore;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractJEEModelProviderQuery.DefaultJEEModelProviderQuery;
import org.eclipse.jst.jsf.common.internal.componentcore.AbstractVirtualComponentQuery.DefaultVirtualComponentQuery;

/**
 * Factory for creating queries that decouple the caller from comp core/facets
 *
 */
public abstract class AbstractCompCoreQueryFactory {
    /**
     * @param project
     * @return the query for the project or null if one can't be found
     */
    public abstract AbstractVirtualComponentQuery createVirtualComponentQuery(final IProject project);
    /**
     * @param project
     * @return the query for the project or null if one can't be found
     */
    public abstract AbstractJEEModelProviderQuery createJEEModelProviderQuery(final IProject project);
   
    /**
     * The default factory
     *
     */
    public static class DefaultCompCoreQueryFactory extends AbstractCompCoreQueryFactory
    {
        @Override
        public AbstractVirtualComponentQuery createVirtualComponentQuery(IProject project) {
            return new DefaultVirtualComponentQuery();
        }

        @Override
        public AbstractJEEModelProviderQuery createJEEModelProviderQuery(IProject project) {
            if (ModelProviderManager.hasModelProvider(project))
            {
                IModelProvider modelProvider = ModelProviderManager.getModelProvider(project);
                return new DefaultJEEModelProviderQuery(modelProvider);
            }
            return null;
        }
        
    }
}
