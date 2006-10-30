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
package org.eclipse.jst.pagedesigner.editors.palette;

import org.eclipse.gef.palette.PaletteEntry;
import org.eclipse.jface.resource.ImageDescriptor;

/**
 * @author mengbo
 */
public interface IPaletteItemEntry {
	/**
	 * @return
	 */
	public String getId();

	/**
	 * @param id
	 */
	public void setId(String id);

	/**
	 * @return
	 */
	public String getLabel();

	/**
	 * @param lable
	 */
	public void setLabel(String lable);

	/**
	 * @return
	 */
	public String getDescription();

	/**
	 * @param desc
	 */
	public void setDescription(String desc);

	/**
	 * @return
	 */
	public boolean isVisible();

	/**
	 * @param visible
	 */
	public void setVisible(boolean visible);

	public PaletteEntry getPaletteEntry();

	/**
	 * @param entry
	 */
	public void setPaletteEntry(PaletteEntry entry);

	public String getSmallIconString();

	public ImageDescriptor getSmallIcon();

	public String getLargeIconString();

	public ImageDescriptor getLargeIcon();

	public void setInitialState(int state);

	public int getInitialState();
}
