/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.editpolicies;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.PositionConstants;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.graphics.Image;

public class BasicLabelToolTip extends Label
{
    public BasicLabelToolTip(Image i) {
        super(i);
        initialize();
    }

    public BasicLabelToolTip(String s, Image i) {
        super(s, i);
        initialize();
    }

    public BasicLabelToolTip(String s) {
        super(s);
        initialize();
    }

    protected void initialize()
    {
        setOpaque(true);
        setBackgroundColor(ColorConstants.tooltipBackground);
        setBorder(
                new LineBorder(ColorConstants.tooltipForeground, 1)
                {
                    // add an extra pixel of inset to make sure the text
                    // isn't pressed against the border
                    public Insets getInsets(IFigure figure) {
                        return new Insets(getWidth()+1);
                    }
                }
        );
        setTextAlignment(PositionConstants.CENTER); 
        setForegroundColor(ColorConstants.tooltipForeground);
    }
}
