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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.EditDomain;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.pagedesigner.editors.PageDesignerActionConstants;
import org.eclipse.jst.pagedesigner.parts.ElementEditPart;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.actions.ActionContext;
import org.eclipse.ui.actions.ActionGroup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @version 1.5
 */
public class RelatedViewActionGroup extends ActionGroup 
{
    private final EditDomain  _editDomain;
    
    /**
     * @param editDomain
     */
    public RelatedViewActionGroup(EditDomain editDomain)
    {
        _editDomain = editDomain;
    }
    
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.actions.ActionGroup#fillContextMenu(org.eclipse.jface.action.IMenuManager)
	 */
	public void fillContextMenu(IMenuManager menu) {
		super.fillContextMenu(menu);

		final IMenuManager viewMgr = new MenuManager(ActionsMessages
				.getString("RelatedViewActionGroup.Menu.ShowView"),//$NON-NLS-1$
				PageDesignerActionConstants.SHOWVIEW_SUBMENU_ID);
		viewMgr.add(new Action() {
		    // add noop action; TODO: why?
		});
		viewMgr.setRemoveAllWhenShown(true);
        final ActionContext context = getContext();
		viewMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
                // TODO: don't like this at all
				String[] views = { "org.eclipse.ui.views.PropertySheet", //$NON-NLS-1$
						"org.eclipse.gef.ui.palette_view", "org.eclipse.ui.views.ContentOutline"}; //$NON-NLS-1$ //$NON-NLS-2$

                Map icons = getIconForView(views);
                // TODO: is this the best way to do this?
				if (manager.find(PropertiesViewAction.ID) == null) {
					Action action = new PropertiesViewAction();
					action.setId(PropertiesViewAction.ID);
					action.setImageDescriptor((ImageDescriptor) icons
							.get(views[0]));
					manager.add(action);
				}
                    ISelection selection = context.getSelection();
                    if (manager.find(PaletteViewAction.ID) == null) {
                    if (selection instanceof IStructuredSelection)
                    {
                        IStructuredSelection strucSelection = 
                            (IStructuredSelection) selection;
                        Action action = null;
                        
                        if (strucSelection.getFirstElement() instanceof ElementEditPart)
                        {                      
                            Element selectedElem = (Element)
                                ((ElementEditPart) strucSelection.getFirstElement()).getModel();
        					action = new PaletteViewAction(selectedElem, _editDomain);
                        }
                        else
                        {
                            // if can't determine, just default open the palette
                            action = new PaletteViewAction(null, null);
                        }
                        action.setId(PaletteViewAction.ID);
                        action.setImageDescriptor((ImageDescriptor) icons
                                .get(views[1]));
                        manager.add(action);
                    }
				}
                if (manager.find(OutlineViewAction.ID) == null)
                {
                    Action action = new OutlineViewAction();
                    action.setId(OutlineViewAction.ID);
                    action.setImageDescriptor((ImageDescriptor) icons
                            .get(views[2]));
                    manager.add(action);
                }
                
			}
		});
		menu.appendToGroup(IWorkbenchActionConstants.MB_ADDITIONS, viewMgr);
	}

	private Map getIconForView(String[] viewids) {
		List views = Arrays.asList(viewids);
		Map icons = new HashMap();

		IConfigurationElement[] elements = Platform.getExtensionRegistry()
				.getConfigurationElementsFor("org.eclipse.ui.views"); //$NON-NLS-1$
		for (int i = 0; i < elements.length; i++) {
			String name = elements[i].getName();
			String id = elements[i].getAttribute("id"); //$NON-NLS-1$
			if ("view".equals(name) && views.contains(id)) { //$NON-NLS-1$
				String iconPath = elements[i].getAttribute("icon"); //$NON-NLS-1$
				if (iconPath != null) {
					icons.put(id, AbstractUIPlugin.imageDescriptorFromPlugin(
							elements[i].getDeclaringExtension().getContributor().getName(),
							iconPath));
				} else {
					icons.put(id, null);
				}
				if (icons.size() == viewids.length) {
					break;
				}
			}
		}
		return icons;
	}
}
