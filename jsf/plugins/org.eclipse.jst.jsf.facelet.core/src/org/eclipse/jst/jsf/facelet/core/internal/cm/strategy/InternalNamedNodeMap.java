package org.eclipse.jst.jsf.facelet.core.internal.cm.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.wst.xml.core.internal.contentmodel.CMNamedNodeMap;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

/*package*/ class InternalNamedNodeMap implements CMNamedNodeMap
{
    private final List<CMNode> _nodes = new ArrayList<CMNode>();

    /**
     * Add a node to the map.  This is not on the CMNamedNodeMap and is used
     * to populate the map.
     * 
     * @param node
     */
    public void add(final CMNode node)
    {
        _nodes.add(node);
    }

    public int getLength()
    {
        return _nodes.size();
    }

    public CMNode getNamedItem(final String name)
    {
        for (final CMNode foundNode : _nodes)
        {
            if (name.equals(foundNode.getNodeName()))
            {
                return foundNode;
            }
        }
        return null;
    }

    public CMNode item(final int index)
    {
        if (index < _nodes.size())
        {
            return _nodes.get(index);
        }
        return null;
    }

    public Iterator<?> iterator()
    {
        return Collections.unmodifiableList(_nodes).iterator();
    }
    
    /**
     * A null instance object for InternalNamedNodeMap
     *
     */
    public static class NullInternalNamedNodeMap extends InternalNamedNodeMap
    {

        @Override
        public void add(final CMNode node)
        {
            // do nothing
        }

        @Override
        public int getLength()
        {
            // always empty
            return 0;
        }

        @Override
        public CMNode getNamedItem(final String name)
        {
            return null;
        }

        @Override
        public CMNode item(final int index)
        {
            return null;
        }

        @Override
        public Iterator<?> iterator()
        {
            return Collections.EMPTY_LIST.iterator();
        }

    }
}