package org.eclipse.jst.jsf.core.tests.serialization;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.eclipse.jst.jsf.core.tests.mock.MockTLDAttributeDeclaration;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDTagAttribute;
import org.eclipse.jst.jsf.test.util.junit4.NoPluginEnvironment;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

@Category(NoPluginEnvironment.class)
public class TLDAttributeSerializationTests
{
    private MockTLDAttributeDeclaration _decl;
    private TLDTagAttribute _attribute;

    @Before
    public void setUp() throws Exception
    {
        
        _decl = new MockTLDAttributeDeclaration("mockAttr", "mockDesc", "mockId", true);
        _attribute = new TLDTagAttribute(_decl);
    }
    
    @Test
    public void testWriteRead() throws Exception
    {
        assertFalse(_attribute.hasBeenDeserialized());
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        ObjectOutputStream serializationStream = new ObjectOutputStream(stream);
        serializationStream.writeObject(_attribute);
        ByteArrayInputStream inStream = new ByteArrayInputStream(stream.toByteArray());
        ObjectInputStream deserializeStream = new ObjectInputStream(inStream);
        Object readObject = deserializeStream.readObject();
        assertNotNull(readObject);
        assertTrue(readObject instanceof TLDTagAttribute);
        TLDTagAttribute tagAttribute = (TLDTagAttribute) readObject;
        assertEquals("mockAttr", tagAttribute.getName());
        assertEquals("mockDesc", tagAttribute.getDescription());
        assertEquals(true, tagAttribute.isRequired());
        assertTrue(tagAttribute.hasBeenDeserialized());
    }
}