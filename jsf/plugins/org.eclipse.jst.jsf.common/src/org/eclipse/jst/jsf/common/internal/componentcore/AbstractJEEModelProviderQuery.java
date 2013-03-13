package org.eclipse.jst.jsf.common.internal.componentcore;

import java.util.Collections;
import java.util.List;

import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.javaee.core.ParamValue;
import org.eclipse.jst.javaee.web.WebApp;

/**
 * Provides a decoupling indirection to decouple JEE model calls from the IModelProvider for testing etc.
 *
 */
public abstract class AbstractJEEModelProviderQuery {

    public abstract List<ParamValue> getWebAppParamValues();
    
    public static class DefaultJEEModelProviderQuery extends AbstractJEEModelProviderQuery
    {
        private IModelProvider modelProvider;
        public DefaultJEEModelProviderQuery(final IModelProvider modelProvider)
        {
            this.modelProvider = modelProvider;
        }
        @Override
        public List<ParamValue> getWebAppParamValues() {
            WebApp webApp = getWebApp();
            if (webApp != null)
            {
                return webApp.getContextParams();
            }
            return Collections.emptyList();
        }
        
        protected WebApp getWebApp()
        {
            final Object webAppObj = modelProvider.getModelObject();
            if (webAppObj instanceof org.eclipse.jst.javaee.web.WebApp)
            {
                return (WebApp) webAppObj;
            }
            return null;
        }
    }



}
