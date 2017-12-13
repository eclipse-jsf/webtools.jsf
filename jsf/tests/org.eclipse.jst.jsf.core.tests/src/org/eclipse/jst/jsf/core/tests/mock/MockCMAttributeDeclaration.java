package org.eclipse.jst.jsf.core.tests.mock;

import java.util.Enumeration;

import org.eclipse.wst.xml.core.internal.contentmodel.CMAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDataType;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;

public class MockCMAttributeDeclaration extends MockCMNode implements
        CMAttributeDeclaration
{

    private CMDataType _cmType;

    public MockCMAttributeDeclaration(String nodeName, CMDataType cmType)
    {
        super(nodeName, CMNode.ATTRIBUTE_DECLARATION);
        _cmType = cmType;
    }

    public String getAttrName()
    {
        return super.getNodeName();
    }

    public CMDataType getAttrType()
    {
        return _cmType;
    }

    public String getDefaultValue()
    {
        throw new UnsupportedOperationException();
    }

    @SuppressWarnings("rawtypes")
    public Enumeration getEnumAttr()
    {
        throw new UnsupportedOperationException();
    }

    public int getUsage()
    {
        throw new UnsupportedOperationException();
    }
}
