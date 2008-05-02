/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.debug;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;


/**
 * Represents a component tree in message for that can be serialized.
 * 
 * @author cbateman
 *
 */
public class ComponentTreeMessage extends JSFMonitorMessage 
{
    /**
     * 
     */
    private static final long serialVersionUID = -6282344329821994209L;
    private String                              _viewId;
    private ComponentInfo                       _treeRoot;
    private RenderNode                          _renderRoot;
   // private HashMap<String, ComponentInfo>      _idIndex;
    
    /**
     * default constructor
     */
    public ComponentTreeMessage()
    {
        //_idIndex = new HashMap<String, ComponentInfo>();
    }
    
    /**
     * @return the view id
     */
    public final String getViewId() {
        return _viewId;
    }

    /**
     * @return the componentinfo at the root of the component tree
     */
    public final ComponentInfo getTreeRoot() {
        return _treeRoot;
    }

    /**
     * @return the rendered root node
     */
    public final RenderNode getRenderRoot() {
        return _renderRoot;
    }

    final void setRenderRoot(RenderNode renderRoot) {
        _renderRoot = renderRoot;
    }

    final void setTreeRoot(ComponentInfo treeRoot)
    {
        _treeRoot = treeRoot;
    }
    
    final void setViewId(final String viewId)
    {
        _viewId = viewId;
    }
    
//    @Override
//    protected void deserialize(InputStream inStream) throws IOException, ClassNotFoundException
//    {
//        ObjectInputStream  objectInputStream = new ObjectInputStream(inStream);
//        
//        Object obj = objectInputStream.readObject();
//        int bytesRead = super.deserialize(inStream);
//        
//        // first line must be startView
//        final BufferedReader reader = 
//            new BufferedReader(new InputStreamReader(inStream));
//        
//        String line = reader.readLine();
//        
//        if (line != null
//                && line.startsWith(START_VIEW))
//        {
//            _viewId = line.substring(START_VIEW.length()).trim();
//            bytesRead += line.length()+1;
//        }
//        
//        // loop until EOF
//        while ((line = reader.readLine())!=null)
//        {
//            bytesRead += line.length()+1;
//            
//            if (line.startsWith("component:"))
//            {
//                parseComponent(line.substring("component:".length()).trim());
//            }
//            else if (line.startsWith("facet:"))
//            {
//                parseFacet(line.substring("facet:".length()).trim());
//            }
//            else if (line.startsWith("uiInput:"))
//            {
//                parseUIInput(line.substring("uiInput:".length()).trim());
//            }
//            else if (line.startsWith("uiOutput:"))
//            {
//                parseUIOutput(line.substring("uiOutput:".length()).trim());
//            }
//            else if (line.startsWith("endView"))
//            {
//                //
//            }
//        }
//        return bytesRead;
//    }

//    private ComponentInfo  parseComponent(final String line)
//    {
//        final String[]  fields = line.split(",");
//        if (fields.length >= 4)
//        {
//            String parentId = "!".equals(fields[0]) ? null : fields[0];
//            String id = "!".equals(fields[1]) ? null : fields[1];
//            String componentFamily = fields[2];
//            String renderFamily = fields[3];
//            
//            ComponentInfo componentInfo = 
//                ComponentFactory.createComponentInfo
//                    (id, parentId, componentFamily, renderFamily);
//
//            if (id == null)
//            {
//                _treeRoot = componentInfo;
//                _idIndex.put(null, componentInfo);
//            }
//            else
//            {
//                _idIndex.put(id, componentInfo);
//                
//                ComponentInfo parent = 
//                    (ComponentInfo)_idIndex.get(parentId);
//                
//                if (parent != null)
//                {
//                    parent.getChildren().add(componentInfo);
//                }
//                else
//                {
//                    _treeRoot.getChildren().add(componentInfo);
//                }
//            }
//        }
//        return null;
//    }
//    
//    private FacetInfo parseFacet(final String line)
//    {
//        final String[]  fields = line.split(",");
//
//        if (fields.length >= 4)
//        {
//            String parentId = "!".equals(fields[0]) ? null : fields[0];
//            String id = "!".equals(fields[1]) ? null : fields[1];
//            String componentFamily = fields[2];
//            String renderFamily = fields[3];
//
//            FacetInfo facetInfo = 
//                ComponentFactory.createFacetInfo(id, parentId, componentFamily, renderFamily);
//            ComponentInfo  parent = _idIndex.get(parentId);
//            
//            if (parent != null)
//            {
//                parent.getChildren().add(facetInfo);
//            }
//            
//            return facetInfo;
//        }
//        return null;
//    }
//    
//    private UIInputInfo parseUIInput(final String line)
//    {
//        final String[]  fields = line.split(",");
//
//        if (fields.length >= 7)
//        {
//            String parentId = "!".equals(fields[0]) ? null : fields[0];
//            String id = "!".equals(fields[1]) ? null : fields[1];
//            String componentFamily = fields[2];
//            String renderFamily = fields[3];
//            String isValid = fields[4];
//            String isImmediate = fields[5];
//            String isRequired = fields[6];
//            String isRendered = fields[7];
//            
//            UIInputInfo uiInputInfo = 
//                ComponentFactory.createUIInputInfo
//                    (id, parentId, componentFamily, renderFamily, isValid, isImmediate, isRequired, isRendered);
//            ComponentInfo  parent = _idIndex.get(parentId);
//            
//            if (parent != null)
//            {
//                parent.getChildren().add(uiInputInfo);
//            }
//            
//            return uiInputInfo;
//        }            
//        return null;
//    }
//    
//    private UIOutputInfo parseUIOutput(final String line)
//    {
//        final String[]  fields = line.split(",");
//
//        if (fields.length >= 4)
//        {
//            String parentId = "!".equals(fields[0]) ? null : fields[0];
//            String id = "!".equals(fields[1]) ? null : fields[1];
//            String componentFamily = fields[2];
//            String renderFamily = fields[3];
//            String isRendered = fields[4];
//            
//            UIOutputInfo uiOutputInfo = 
//                ComponentFactory.createUIOutputInfo(id, parentId, componentFamily, renderFamily, isRendered);
//            
//            ComponentInfo  parent = _idIndex.get(parentId);
//            
//            if (parent != null)
//            {
//                parent.getChildren().add(uiOutputInfo);
//            }
//            
//            return uiOutputInfo;
//        }            
//        return null;
//    }
}
