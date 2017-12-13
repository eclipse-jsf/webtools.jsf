package org.eclipse.jst.jsf.core.tests.mock;

import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDataTypeImpl;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDataType;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;

public class MockTLDAttributeDeclaration extends MockCMAttributeDeclaration
        implements TLDAttributeDeclaration
{

    private final String _description;
    private final String _id;
    private final boolean _required;

    public MockTLDAttributeDeclaration(String nodeName, CMDataType cmType, String description, String id, boolean required)
    {
        super(nodeName, cmType);
        _description = description;
        _id = id;
        _required = required;
    }

    public MockTLDAttributeDeclaration(final String nodeName, String description, String id, boolean required)
    {
        this(nodeName, new CMDataTypeImpl("foo data type name"), description, id, required);
    }
    public String getDescription()
    {
        return _description;
    }

    public String getId()
    {
        return _id;
    }

    public CMDocument getOwnerDocument()
    {
        throw new UnsupportedOperationException();
    }

    public String getRtexprvalue()
    {
        throw new UnsupportedOperationException();
    }

    public String getType()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isFragment()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isRequired()
    {
        return _required;
    }

}
