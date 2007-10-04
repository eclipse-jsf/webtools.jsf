/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.dialog;

import java.util.Iterator;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jst.jsf.facesconfig.emf.ComponentType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.swt.widgets.Shell;

/**
 * @author Bryan Yang
 *
 */
public class ComponentListDialog extends CommonListDialog {

	public void selectionChanged(SelectionChangedEvent event) {
		IStructuredSelection ssel = StructuredSelection.EMPTY;
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			ssel = (IStructuredSelection) selection;
		}

		if (!ssel.isEmpty()) {
			for (Iterator iter = ssel.iterator(); iter.hasNext();) {
				ComponentType element = (ComponentType) iter.next();
				if (element.getComponentType() != null)
					setValue(element.getComponentType().getTextContent());
				else
					setValue("");
			}

		}
	}

	/**
	 * @param parentShell
	 * @param page
	 * @param input
	 * @param caption
	 * @param label
	 */
	protected ComponentListDialog(Shell parentShell, IFacesConfigPage page,
			Object input, String caption, String label) {
		super(parentShell, page, input, caption, label);
	}

	protected void configViewer(StructuredViewer structuredViewer) {
		super.configViewer(structuredViewer);
		structuredViewer.addFilter(new ViewerFilter() {
			public boolean select(Viewer viewer, Object parentElement,
					Object element) {
				return FacesConfigPackage.eINSTANCE.getComponentType()
						.isInstance(element);
			}
		});

	}

}
