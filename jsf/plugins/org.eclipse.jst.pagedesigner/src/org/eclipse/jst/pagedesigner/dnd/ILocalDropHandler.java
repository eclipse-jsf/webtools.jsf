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
package org.eclipse.jst.pagedesigner.dnd;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public interface ILocalDropHandler {
	/**
	 * whether this handler will use wizard for user to config detail
	 * 
	 * @return true if should use wizard
	 */
	public boolean useWizard();

	/**
	 * @param localData
	 * @param viewer
	 * @return true if should use wizard
	 */
	public boolean useWizard(Object localData, IHTMLGraphicalViewer viewer);

	/**
	 * 
	 * @param localData
	 * @param widget
	 * @return the feed back info
	 */
	public FeedBackInfo supportUpdateWidget(Object localData, Node widget);

	/**
	 * This method will only be called when <code>supportUpdateWidget</code>
	 * return true and <code>useWizard</code> return true.
	 * 
	 * @param localData
	 * @param widget
	 * @param viewer
	 * @return the wizard
	 */
	public IWizard getWizard(Object localData, Node widget,
			IHTMLGraphicalViewer viewer);

	/**
	 * this method is called when the handler don't support wizard.
	 * 
	 * @param localData
	 * @param widget
	 * @param viewer
	 */
	public void doUpdateWidget(Object localData, Node widget,
			IHTMLGraphicalViewer viewer);

	/**
	 * 
	 * @param localData
	 * @param position
	 * @return the feedback info
	 */
	public FeedBackInfo supportInsertElements(Object localData,
			IDOMPosition position);

	/**
	 * 
	 * @param localData
	 * @param position
	 * @param viewer
	 * @return the wizard
	 */
	public IWizard getWizard(Object localData, IDOMPosition position,
			IHTMLGraphicalViewer viewer);

	/**
	 * @param localData
	 * @param position
	 * @param viewer
	 */
	public void doInsertElements(Object localData, IDOMPosition position,
			IHTMLGraphicalViewer viewer);
}
