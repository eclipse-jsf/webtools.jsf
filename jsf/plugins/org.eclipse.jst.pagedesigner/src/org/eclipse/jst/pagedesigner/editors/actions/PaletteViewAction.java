/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editors.actions;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.ui.views.palette.PaletteView;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class PaletteViewAction extends ShowViewAction {
	/**
	 * the action id
	 */
	public final static String ID = "org.eclipse.jst.pagedesigner.editors.actions.PaletteViewAction"; //$NON-NLS-1$
    
	/**
	 * @param element
	 * @param editDomain
	 */
	public PaletteViewAction(Element element, EditDomain editDomain) {
        super(ActionsMessages.getString("PaletteViewAction.Menu.PaletteView") //$NON-NLS-1$
                , PaletteView.ID);
        //_element = element;
        //_editDomain = editDomain;
	}
    
    /** 
     * Add to default behavior because pallete view doesn't automatically track
     * selection to currently selected edit part
     */
    public void run()
    {
        super.run();
        
        //TODO: for some reason getting a palette item to select doesn't work
//        if (_element != null && _editDomain != null)
//        {
//            PaletteItemManager manager = PaletteItemManager
//                                          .getInstance(getProject(_element));
//            if (manager != null) {
//                IPaletteItemCategory category = manager.findOrCreateCategory(CMUtil
//                        .getElementNamespaceURI(_element), null);
//                
//              if (category != null) {
//                  String name = _element.getLocalName();
//                  if (category.getURI().equals(IJMTConstants.URI_JSP)) {
//                      name = _element.getTagName();
//                  }
//                  IPaletteItemDescriptor descriptor = category
//                      .getItemByTagName(name);
//                  if (descriptor != null)
//                  {
//                      PaletteEntry paletteEntry = descriptor.getPaletteEntry();
//                      final RootEditPart editPart = 
//                          _editDomain.getPaletteViewer().getRootEditPart();
//                      EditPart newSelection = findMatchingPart(editPart.getContents(), paletteEntry);
//
//                      if (newSelection != null)
//                      {
//                          _editDomain.getPaletteViewer().setSelection(new StructuredSelection(newSelection));
//                      }
//                   }
//                }
//            }
//        }
    }
    
//    private EditPart findMatchingPart(EditPart curPart, PaletteEntry paletteEntry)
//    {
//        EditPart match = null;
//
//        if (curPart.getModel() == paletteEntry)
//        {
//            return curPart;
//        }
//        
//        for (final Iterator it = curPart.getChildren().iterator(); it.hasNext();)
//        {
//            match = findMatchingPart((EditPart)it.next(), paletteEntry);
//            
//            if (match != null)
//            {
//                break;
//            }
//        }
//        
//        return match;
//    }
//    private IProject getProject(Element element) {
//        if (element instanceof IDOMElement) {
//            IDOMModel model = ((IDOMElement) element).getModel();
//            IFile file = StructuredModelUtil.getFileFor(model);
//            if (file != null) {
//                return file.getProject();
//            }
//        }
//        return null;
//    }
}
