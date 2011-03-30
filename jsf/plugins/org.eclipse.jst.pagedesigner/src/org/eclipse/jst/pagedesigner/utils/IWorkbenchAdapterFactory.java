/*******************************************************************************
 * Copyright (c) 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * IAdapterFactory capable of adapting to IWorkbenchAdapter instances.
 * 
 * @author ian.trimble@oracle.com
 */
public class IWorkbenchAdapterFactory implements IAdapterFactory {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		IWorkbenchAdapter adapter = null;
		if (adapterType.equals(IWorkbenchAdapter.class)) {
			if (adaptableObject instanceof NodeEditPart) {
				final IDOMNode node = ((NodeEditPart)adaptableObject).getIDOMNode();
				if (node != null) {
					final IDOMModel model = node.getModel();
					if (model != null) {
						final IFile file = StructuredModelUtil.getFileFor(model);
						if (file != null) {
							adapter = new IWorkbenchAdapter() {
								public Object[] getChildren(Object object) {
									return new Object[0];
								}
								public ImageDescriptor getImageDescriptor(Object object) {
									return null;
								}
								public String getLabel(Object object) {
									return file.getName();
								}
								public Object getParent(Object object) {
									return file.getParent();
								}
							};
						}
					}
				}
			}
		}
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return new Class[]{IWorkbenchAdapter.class};
	}

}
