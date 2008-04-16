package org.eclipse.jst.jsf.designtime.internal.view;

import java.io.Serializable;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;

/**
 * The default view root implementation. Assumes an XML view definition.
 * 
 * @author cbateman
 * 
 */
public class DefaultDTUIViewRoot extends DTUIViewRoot
{
    /**
     * serializable
     */
    private static final long                    serialVersionUID = -6948413077931237435L;
    private final DefaultServices                _defaultServices;

    /**
     */
    public DefaultDTUIViewRoot()
    {
        // TODO: refactor constants
        super(null, null, new ComponentTypeInfo("javax.faces.ViewRoot",
                "javax.faces.component.UIViewRoot", "javax.faces.ViewRoot",
                null));
        _defaultServices = new DefaultServices();
    }

    @Override
    public IAdaptable getServices()
    {
        return _defaultServices;
    }

    private class DefaultServices implements IAdaptable, Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = -5220371377352799212L;
        private final XMLViewObjectMappingService   _xmlViewObjectMappingService;

        private DefaultServices()
        {
            _xmlViewObjectMappingService = new XMLViewObjectMappingService();
        }

        public Object getAdapter(final Class adapter)
        {
            if (adapter.equals(XMLViewObjectMappingService.class))
            {
                return _xmlViewObjectMappingService;
            }
            return null;
        }
    }
}
