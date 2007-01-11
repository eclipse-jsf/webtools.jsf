package org.eclipse.jst.pagedesigner.actions.single;

import org.eclipse.gef.EditPart;
import org.w3c.dom.Node;

/**
 * Given a target node/edit part change selection to its parent
 * 
 * @author cbateman
 *
 */
public class SelectParentAction extends SelectNodeAction {

    private final EditPart _editPart;
    
    /**
     * @param node
     * @param editPart
     * @return an action that will change focus selection to the parent
     * part of node/editPart or a disabled one if there is no parent
     */
    public static SelectParentAction create(Node node, EditPart editPart)
    {
        Node parent = node.getParentNode();
        
        if (parent != null)
        {
            final String text = "Parent ("+parent.getNodeName()+")";
            return new SelectParentAction(text, node, editPart);
        }
        SelectParentAction action = new SelectParentAction("Parent", node, editPart);
        action.setEnabled(false);
        return action;
    }
    
    private SelectParentAction(String text, Node node, EditPart editPart)
    {
        super(text, node);
        _editPart = editPart;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.actions.single.SelectEditPartAction#getNewSelection()
     */
    protected EditPart getNewSelection() 
    {
        return _editPart.getParent();
    }
}
