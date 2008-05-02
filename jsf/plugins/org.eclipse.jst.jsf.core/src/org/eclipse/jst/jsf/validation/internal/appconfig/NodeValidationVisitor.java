/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

/**
 * Visits an app config DOM tree and calls validators based on the
 * configured validator tree
 * 
 * @author cbateman
 *
 */
public abstract class NodeValidationVisitor 
{
    private final short     _nodeType;
    private final String    _nodeName;
    private final Map       _attributeVisitors;
    private final Map       _elementVisitors;
    
    /**
     * Used to indicate no child nodes
     */
    protected final static NodeValidationVisitor[] EMPTY_CHILDREN = new NodeValidationVisitor[0];
    
    /**
     * @param nodeType
     * @param nodeName
     */
    public NodeValidationVisitor(short nodeType, String nodeName)
    {
        _nodeType = nodeType;
        _nodeName = nodeName;
        _attributeVisitors = new HashMap();
        _elementVisitors = new HashMap();
        
        NodeValidationVisitor[] childVisitors = getChildNodeValidators();
        
        for (int i = 0; i < childVisitors.length; i++)
        {
            final NodeValidationVisitor visitor = childVisitors[i];
            
            switch(visitor.getNodeType())
            {
                case Node.ATTRIBUTE_NODE:
                    _attributeVisitors.put(visitor.getNodeName(), visitor);
                break;
                
                case Node.ELEMENT_NODE:
                    _elementVisitors.put(visitor.getNodeName(), visitor);
                break;
            }
        }
    }
    
    /**
     * @param node
     * @param messages
     * @param file 
     */
    public final void validate(Node node, List messages, IFile file)
    {
        doValidate(node, messages, file);
        
        final NamedNodeMap attributes = node.getAttributes();
        
        for (int i = 0; attributes != null && i < attributes.getLength(); i++)
        {
            final Node attr = attributes.item(i);
            final NodeValidationVisitor  visitor = 
                (NodeValidationVisitor) _attributeVisitors.get(attr.getNodeName());
            if (visitor != null)
            {
                visitor.validate(attr, messages, file);
            }
        }
        
        for (int i = 0; i < node.getChildNodes().getLength(); i++)
        {
            final Node child = node.getChildNodes().item(i);
            final NodeValidationVisitor  visitor = 
                (NodeValidationVisitor) _elementVisitors.get(child.getNodeName());
            if (visitor != null)
            {
                visitor.validate(child, messages, file);
            }
        }
    }

    /**
     * Do the validation for this visitor on this node.  Add any Message's to
     * the messages list
     * 
     * @param node
     * @param messages
     * @param file 
     */
    protected abstract void doValidate(Node node, List messages, IFile file);
    
    /**
     * @return an array of visitors that validate children of the current node
     */
    protected abstract NodeValidationVisitor[] getChildNodeValidators();
    
    /**
     * @return the node type for this validator
     */
    protected short getNodeType()
    {
        return _nodeType;
    }
    
    /**
     * @return the node name for this validator
     */
    protected String getNodeName()
    {
        return _nodeName;
    }
}
