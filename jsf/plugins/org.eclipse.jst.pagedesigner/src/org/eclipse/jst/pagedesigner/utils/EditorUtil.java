/*******************************************************************************
 * Copyright (c) 2009 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.utils;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.pagedesigner.editors.HTMLEditor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.ResourceUtil;
import org.eclipse.ui.internal.Workbench;

/**
 * Utility class for Editor related information.
 * <br>
 * <p><b>Provisional API - subject to change</b></p>
 */
public class EditorUtil {

	private static final String PROPERTY_QUALIFIER = "org.eclipse.jst.pagedesigner"; //$NON-NLS-1$
    private static final String PERSIST_PROPERTY_NAME_DESIGNER_MODE = "DesignMode";  //$NON-NLS-1$
    private static final QualifiedName PERSIST_PROPERTY_KEY_DESIGNER_MODE = 
    	new QualifiedName(PROPERTY_QUALIFIER, PERSIST_PROPERTY_NAME_DESIGNER_MODE);

	/**
	 * Find the design mode property for the file resource of the
	 * editor input. If found, then return the property value,
	 * otherwise return null. 
	 * 
	 * @param editorInput
	 * @return user selected design mode for the editor input file
	 */
	public static String getEditorInputDesignModeProperty(IEditorInput editorInput) {
		String dmProperty = null;
		if (editorInput != null) {
			IResource res = ResourceUtil.getResource(editorInput);
			if (res != null) {
				try {
					dmProperty = res.getPersistentProperty(PERSIST_PROPERTY_KEY_DESIGNER_MODE);
				} catch (CoreException e) {
					// do nothing;
				}
			}
		}

		return dmProperty;
	}

	/**
	 * Save the design mode property for the file resource of the
	 * editor input. If the supplied mode is <code>null</code>,
	 * the persistent property is removed from this resource. 
	 * 
	 * @param editorInput
	 * @param mode user selected design mode to be saved
	 */
	public static void setEditorInputDesignModeProperty(IEditorInput editorInput, String mode) {
		if (editorInput != null) {
			IResource res = ResourceUtil.getResource(editorInput);
			if (res != null) {
				try {
					res.setPersistentProperty(PERSIST_PROPERTY_KEY_DESIGNER_MODE, mode);
				} catch (CoreException e) {
					// do nothing;
				}
			}
		}
	}

	/**
	 * Calls <code>refreshDesignViewer()</code> on all open HTMLEditor
	 * (Web Page Editor) instances.
	 */
	public static void refreshAllWPEDesignViewers() {
		IWorkbenchWindow window = Workbench.getInstance().getActiveWorkbenchWindow();
		if (window != null) {
			IWorkbenchPage page = window.getActivePage();
			if (page != null) {
				IEditorReference[] editorRefs = page.getEditorReferences();
				for (IEditorReference editorRef: editorRefs) {
					IEditorPart editorPart = editorRef.getEditor(false);
					if (editorPart instanceof HTMLEditor) {
						((HTMLEditor)editorPart).refreshDesignViewer();
					}
				}
			}
		}
	}

}
