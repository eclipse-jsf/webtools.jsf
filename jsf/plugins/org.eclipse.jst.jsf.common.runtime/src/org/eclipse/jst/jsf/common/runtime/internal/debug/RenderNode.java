package org.eclipse.jst.jsf.common.runtime.internal.debug;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A rendered XML node.
 * 
 * @author cbateman
 *
 */
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
    /**
     * @param componentId
     * @param renderedNodeName
     */
    public RenderNode(String componentId, String renderedNodeName) {
        super();
        _componentId = componentId;
        _renderedNodeName = renderedNodeName;
        
        _children = new ArrayList();
        _renderedAttributes = new ArrayList();
    }
    /**
     * @return the corresponding component's id
     */
    public final String getComponentId() {
        return _componentId;
    }
    /**
     * @return the node's children
     */
    public final List getChildren() {
        return _children;
    }
    /**
     * @return the name of the rendered node (element)
     */
    public final String getRenderedNodeName() {
        return _renderedNodeName;
    }
    /**
     * @return the attributes of this node as rendered.
     */
    public final List getRenderedAttributes() {
        return _renderedAttributes;
    }
    public String toString()
    {
        String toString = "Root node: "+_renderedNodeName+", attributes: ["; //$NON-NLS-1$ //$NON-NLS-2$
        
        for (Iterator it = _renderedAttributes.iterator(); it.hasNext();)
        {
            final RenderAttribute attribute = (RenderAttribute) it.next();
            toString+=attribute.getName() + "=" + attribute.getValue(); //$NON-NLS-1$
            
            toString+=it.hasNext() ? ", " : "]"; //$NON-NLS-1$ //$NON-NLS-2$
        }
        
        return toString;
    }
}