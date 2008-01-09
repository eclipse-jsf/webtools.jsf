package org.eclipse.jst.jsf.common.runtime.tests.debug;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.runtime.internal.debug.RenderAttribute;
import org.eclipse.jst.jsf.common.runtime.internal.debug.RenderNode;

public class TestRenderNode extends TestCase {

    private RenderNode      _renderNode;
    
    protected void setUp() throws Exception {
        super.setUp();
        
        _renderNode = new RenderNode("org.eclipse.jsf.jst.CompType", "th");
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetComponentId() 
    {
        assertEquals("org.eclipse.jsf.jst.CompType", _renderNode.getComponentId());
    }

    @SuppressWarnings("unchecked")
    public void testGetChildren() {
        RenderNode  child1 = new RenderNode("org.eclipse.jsf.jst.CompType2", "a");
        RenderNode  child2 = new RenderNode("org.eclipse.jst.jst.CompType3", "br");
        
        _renderNode.getChildren().add(child1);
        _renderNode.getChildren().add(child2);
        
        assertEquals(2, _renderNode.getChildren().size());
        verifySame(child1, (RenderNode) _renderNode.getChildren().get(0));
        verifySame(child2, (RenderNode) _renderNode.getChildren().get(1));
    }

    public void testGetRenderedNodeName() {
        assertEquals("th", _renderNode.getRenderedNodeName());
    }

    @SuppressWarnings("unchecked")
    public void testGetRenderedAttributes() {
        RenderAttribute  attribute1 = new RenderAttribute("id", "blah", null);
        
        _renderNode.getRenderedAttributes().add(attribute1);
        
        RenderAttribute attribute2 = new RenderAttribute("value", "5", "Value");
        _renderNode.getRenderedAttributes().add(attribute2);
        
        verifySame(attribute1, (RenderAttribute)_renderNode.getRenderedAttributes().get(0));
        verifySame(attribute2, (RenderAttribute)_renderNode.getRenderedAttributes().get(1));
    }

    public static void verifySame(RenderNode truth, RenderNode check)
    {
        if (truth == check) return;
        
        assertEquals(truth.getComponentId(), check.getComponentId());
        assertEquals(truth.getRenderedNodeName(), check.getRenderedNodeName());

        assertEquals(truth.getChildren().size(), check.getChildren().size());
        
        for (int i = 0; i < check.getChildren().size(); i++)
        {
            verifySame((RenderNode)truth.getChildren().get(i), (RenderNode)check.getChildren().get(i));
        }
        
        assertEquals(truth.getRenderedAttributes().size(), check.getChildren().size());
        
        for (int i = 0; i < check.getRenderedAttributes().size(); i++)
        {
            verifySame((RenderAttribute)truth.getRenderedAttributes().get(i), 
                       (RenderAttribute)check.getRenderedAttributes().get(i));
        }
    }
    
    public static void verifySame(RenderAttribute truth, RenderAttribute check)
    {
        assertEquals(truth.getName(), check.getName());
        assertEquals(truth.getPropertyName(), check.getPropertyName());
        assertEquals(truth.getValue(), check.getValue());
    }
}
