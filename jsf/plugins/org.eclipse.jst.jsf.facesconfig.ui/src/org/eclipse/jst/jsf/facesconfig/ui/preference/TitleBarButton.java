/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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

package org.eclipse.jst.jsf.facesconfig.ui.preference;

import java.util.Iterator;

import org.eclipse.draw2d.ActionEvent;
import org.eclipse.draw2d.ActionListener;
import org.eclipse.draw2d.Button;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Cursors;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;

/**
 * A title bar button
 *
 */
/*package*/ final class TitleBarButton extends Button {
	private boolean state = false;

	private Image uncheckedImage;

	private Image checkedImage;

	private Image uncheckedHiliteImage;

	private Image checkedHiliteImage;

	private RectangleList uncheckedRects;

	private RectangleList checkedRects;

	private boolean hilite = false;



	/**
	 * @param unchecked
	 * @param uncheckedHilite
	 * @param checked
	 * @param checkedHilite
	 */
	public TitleBarButton(Image unchecked, Image uncheckedHilite,
			Image checked, Image checkedHilite) {
		super();
		uncheckedImage = unchecked;
		if (uncheckedHilite == null)
			uncheckedHiliteImage = unchecked;
		else
			uncheckedHiliteImage = uncheckedHilite;
		if (checked == null)
			checkedImage = unchecked;
		else
			checkedImage = checked;
		if (checkedHilite == null)
			checkedHiliteImage = unchecked;
		else
			checkedHiliteImage = checkedHilite;
		initialize();
	}

	/**
	 * @param rects
	 */
	public TitleBarButton(RectangleList rects) {
		super();
		uncheckedRects = rects;
		initialize();
	}

	/**
	 * @param unchecked
	 * @param checked
	 */
	public TitleBarButton(RectangleList unchecked, RectangleList checked) {
		super();
		uncheckedRects = unchecked;
		checkedRects = checked;
		initialize();
	}

	/**
	 * @return the state
	 */
	public boolean getState() {
		return state;
	}

	/**
	 * @param newState
	 */
	public void setState(boolean newState) {
		state = newState;
		hilite = false;
	}

	private void initialize() {
		setRequestFocusEnabled(true);
		setFocusTraversable(true);

		if (uncheckedImage != null) {
			org.eclipse.swt.graphics.Rectangle r = uncheckedImage.getBounds();
			setBounds(new Rectangle(0, 0, r.width, r.height));
			prefSize = new Dimension(r.width, r.height);
		} else {
			setForegroundColor(ColorConstants.black);
			setBackgroundColor(ColorConstants.white);
			calculatePreferredSize();
			setBounds(new Rectangle(0, 0, prefSize.width, prefSize.height));
		}
		setCursor(Cursors.ARROW);
		setBorder(null);
		setFont(JFaceResources.getFontRegistry().get(
				JFaceResources.DEFAULT_FONT));

		addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setState(!state);
			}
		});
		this.addMouseMotionListener(new MouseMotionListener() {

			public void mouseDragged(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			public void mouseEntered(MouseEvent me) {
				hilite = true;
				repaint();
			}

			public void mouseExited(MouseEvent me) {
				hilite = false;
				repaint();
			}

			public void mouseHover(MouseEvent me) {
				// TODO Auto-generated method stub

			}

			public void mouseMoved(MouseEvent me) {
				// TODO Auto-generated method stub

			}
		});
	}

	/**
	 * @return the preferred size dimensions
	 */
	public Dimension calculatePreferredSize() {
		if (prefSize == null) {
			Rectangle rect = new Rectangle(0, 0, 0, 0);
			if (uncheckedRects != null) {
				Iterator<Rectangle> iter = uncheckedRects.iterator();
				while (iter.hasNext()) {
					/*Point p =*/ getLocation();
					Rectangle r = iter.next().getCopy();
					rect = rect.getUnion(r);
				}
			}
			if (checkedRects != null) {
				Iterator<Rectangle> iter = checkedRects.iterator();
				while (iter.hasNext()) {
					/*Point p =*/ getLocation();
					Rectangle r = iter.next().getCopy();
					rect = rect.getUnion(r);
				}
			}
			prefSize = rect.getSize();
			if (prefSize.width > prefSize.height)
				prefSize.height = prefSize.width;
			else if (prefSize.height > prefSize.width)
				prefSize.width = prefSize.height;
			prefSize.width += 4;
			prefSize.height += 4;
		}
		return prefSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.draw2d.IFigure#paint(org.eclipse.draw2d.Graphics)
	 */
	public void paint(Graphics graphics) {
		if (!isVisible())
			return;
		if (uncheckedImage != null) {
			if (hilite) {
				if (state == false)
					graphics.drawImage(uncheckedHiliteImage, getLocation());
				else
					graphics.drawImage(checkedHiliteImage, getLocation());
			} else {
				if (state == false)
					graphics.drawImage(uncheckedImage, getLocation());
				else
					graphics.drawImage(checkedImage, getLocation());
			}
		} else {
			Iterator<Rectangle> iter = null;
			if (state == false)
				iter = uncheckedRects.iterator();
			else
				iter = checkedRects.iterator();
			while (iter.hasNext()) {
				Point p = getLocation();
				Rectangle r = iter.next().getCopy();
				r = r.translate(p.x, p.y);
				graphics.setForegroundColor(getForegroundColor());
				if (hilite)
					graphics.setBackgroundColor(ColorConstants.lightBlue);
				else
					graphics.setBackgroundColor(ColorConstants.white);
				graphics.fillRectangle(r);
				graphics.drawRectangle(r);
			}
		}
	}
}
