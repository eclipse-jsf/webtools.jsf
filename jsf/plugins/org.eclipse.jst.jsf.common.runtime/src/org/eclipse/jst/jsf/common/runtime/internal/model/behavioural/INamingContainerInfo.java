package org.eclipse.jst.jsf.common.runtime.internal.model.behavioural;

import java.io.Serializable;

import org.eclipse.jst.jsf.common.runtime.internal.model.IDesigntimeAdapter;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;

/**
 * Design time analog for the NamingContainer interface.
 * 
 * @author cbateman
 * 
 */
public interface INamingContainerInfo extends Serializable
{
    // tagging interface

    /**
     * Used as an adapter impl
     */
    public final static INamingContainerInfo ADAPTER = new NamingContainerInfo();
    
    /**
     * A default naming container info
     * @author cbateman
     *
     */
    public static class NamingContainerInfo implements INamingContainerInfo, IDesigntimeAdapter
    {
        /**
         * 
         */
        private static final long serialVersionUID = 7214529928361444126L;

        public String[] getInterfaces()
        {
            return new String[] {ComponentFactory.INTERFACE_NAMINGCONTAINER};
        }
        
    }
}
