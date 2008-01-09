package org.eclipse.jst.jsf.common.runtime.internal.debug;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RenderNode extends JSFMonitorMessage 
{
    /**
     * 
     */
    private static final long serialVersionUID = -2843929405836710015L;

    private final String      _componentId;       // the id of the component id that caused the node to render
    private final String      _renderedNodeName;  // the node
    private final List/*<RenderedAttribute>*/        _renderedAttributes; // attributes
    private final List        _children;          // the rendered children of renderedNode based
                                                  // on the output document hiearchy
    public RenderNode(String componentId, String renderedNodeName) {
        super();
        _componentId = componentId;
        _renderedNodeName = renderedNodeName;
        
        _children = new ArrayList();
        _renderedAttributes = new ArrayList();
    }
    public final String getComponentId() {
        return _componentId;
    }
    public final List getChildren() {
        return _children;
    }
    public final String getRenderedNodeName() {
        return _renderedNodeName;
    }
    public final List getRenderedAttributes() {
        return _renderedAttributes;
    }
    public String toString()
    {
        String toString = "Root node: "+_renderedNodeName+", attributes: [";
        
        for (Iterator it = _renderedAttributes.iterator(); it.hasNext();)
        {
            final RenderAttribute attribute = (RenderAttribute) it.next();
            toString+=attribute.getName() + "=" + attribute.getValue();
            
            toString+=it.hasNext() ? ", " : "]";
        }
        
        return toString;
    }
}