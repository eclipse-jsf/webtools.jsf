package org.eclipse.jst.jsf.core.tests.mock;

import junit.framework.Assert;

import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

public class MockCMNode implements CMNode
{
    private final String _nodeName;
    private final int _nodeType;

    public MockCMNode(final String nodeName, final int nodeType)
    {
        _nodeName = nodeName;
        switch(nodeType)
        {
        case CMNode.ANY_ELEMENT:
        case CMNode.ATTRIBUTE_DECLARATION:
        case CMNode.DATA_TYPE:
        case CMNode.DOCUMENT:
        case CMNode.DOCUMENTATION:
        case CMNode.ELEMENT_DECLARATION:
        case CMNode.ENTITY_DECLARATION:
        case CMNode.GROUP:
        case CMNode.NAME_SPACE:
            _nodeType = nodeType;
        break;
        default:
            _nodeType = -1;
            Assert.fail("Invalid type: "+nodeType);
        }
    }
    public String getNodeName()
    {
        return _nodeName;
    }

    public int getNodeType()
    {
        return _nodeType;
    }

    public boolean supports(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public Object getProperty(String propertyName)
    {
        throw new UnsupportedOperationException();
    }
}
