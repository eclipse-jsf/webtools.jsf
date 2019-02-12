/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SimpleLoweredBorder;
import org.eclipse.gef.editparts.ScalableFreeformRootEditPart;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;

/**
 * This RootEditPart can set its own's background, fourground color, and font.
 * All this properties can be changed.
 * 
 * @author Xiao-guang Zhang
 * 
 */
public class ConfigurableRootEditPart extends ScalableFreeformRootEditPart
		implements IFigurePreference {
	/**
	 * 
	 */
	public ConfigurableRootEditPart() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		IFigure fig = super.createFigure();
		fig.setOpaque(true);
		fig.setBorder(new SimpleLoweredBorder());
		return fig;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IFigurePreference#setForegroundColor(org.eclipse.swt.graphics.Color)
	 */
	public void setForegroundColor(Color c) {
		getFigure().setForegroundColor(c);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IFigurePreference#setBackgroundColor(org.eclipse.swt.graphics.Color)
	 */
	public void setBackgroundColor(Color c) {
		getFigure().setBackgroundColor(c);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sybase.stf.jmt.editors.pageflow.editparts.IFigurePreference#setFont(org.eclipse.swt.graphics.Font)
	 */
	public void setFont(Font f) {
		getFigure().setFont(f);
	}

}
