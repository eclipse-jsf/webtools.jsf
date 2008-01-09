package org.eclipse.jst.jsf.common.runtime.internal.debug;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;

public class MonitorMessageFactory 
{
    public static ComponentTreeMessage createTreeMessage(final String viewId, final ComponentInfo root, final RenderNode renderRoot)
    {
        ComponentTreeMessage message = new ComponentTreeMessage();
        message.setViewId(viewId);
        message.setTreeRoot(root);
        message.setRenderRoot(renderRoot);
        return message;
    }
}
