package org.eclipse.jst.pagedesigner.actions.single;

import org.w3c.dom.Node;

/**
 * An edit part selection action that corresponds to a Node selection
 */
public abstract class SelectNodeAction extends SelectEditPartAction 
{
    protected final Node _node;
    
    
    /**
     * @param text
     * @param curNode
     */
    protected SelectNodeAction(String text, Node curNode) {
        super(text);
        _node = curNode;
    }
   
}
