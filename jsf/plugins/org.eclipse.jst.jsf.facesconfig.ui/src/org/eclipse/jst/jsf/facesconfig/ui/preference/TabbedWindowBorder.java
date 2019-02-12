/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
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

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.FrameBorder;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.SchemeBorder;
import org.eclipse.draw2d.geometry.Insets;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;

/**
 * A frame border for a tabbed window
 * 
 */
/* package */final class TabbedWindowBorder extends FrameBorder
{
    private boolean isVisible = true;

    // CR392586: resource leaks
    private static MySchemeBorder normalBorder = new MySchemeBorder(
            new MySchemeBorder.MyScheme(new Color[]
                                                  { ColorConstants.button, ColorConstants.buttonLightest,
                    ColorConstants.button }, new Color[]
                                                       { ColorConstants.buttonDarkest, ColorConstants.buttonDarker,
                    ColorConstants.button }));

    // CR392586: resource leaks
    private static MySchemeBorder highlightBorder = new MySchemeBorder(
            new MySchemeBorder.MyScheme(new Color[]
                                                  { FigureUtilities.lighter(ColorConstants.titleBackground),
                    ColorConstants.titleBackground,
                    FigureUtilities.darker(ColorConstants.titleBackground) },
                    new Color[]
                              {
                    FigureUtilities
                    .darker(ColorConstants.titleGradient),
                    ColorConstants.titleGradient,
                    FigureUtilities
                    .lighter(ColorConstants.titleGradient) }));

    /**
     * A border scheme
     * 
     */
    private static class MySchemeBorder extends SchemeBorder
    {
        private static class MyScheme extends SchemeBorder.Scheme
        {
            /**
             * @param highlight
             * @param shadow
             */
            public MyScheme(final Color[] highlight, final Color[] shadow)
            {
                super(highlight, shadow);
            }

            @Override
            public Color[] getHighlight()
            {
                return super.getHighlight();
            }

            @Override
            public Color[] getShadow()
            {
                return super.getHighlight();
            }
        }

        MySchemeBorder(final MyScheme scheme)
        {
            super(scheme);
        }

        /**
         * @return the scheme
         */
        public MyScheme getMyScheme()
        {
            return (MyScheme) super.getScheme();
        }

        /**
         * @see org.eclipse.draw2d.Border#paint(IFigure, Graphics, Insets)
         * @param comp
         * @param gc
         * @param insets
         */
        public void paint(final Composite comp, final GC gc, final Insets insets)
        {
            final Color[] tl = getMyScheme().getHighlight();
            final Color[] br = getMyScheme().getShadow();
            paint(comp, gc, insets, tl, br);
        }

        /**
         * Paints the border using the information in the set Scheme and the
         * inputs given. Side widths are determined by the number of colors in
         * the Scheme for each side.
         * 
         * @param comp
         *            the composit whose rect should be used for the inset
         *            bounds
         * @param gc
         *            the graphics object
         * @param insets
         *            the insets
         * @param tl
         *            the highlight (top/left) colors
         * @param br
         *            the shadow (bottom/right) colors
         */
        protected void paint(final Composite comp, final GC gc, final Insets insets, final Color[] tl,
                final Color[] br)
        {
            final org.eclipse.swt.graphics.Rectangle rect = comp.getBounds();

            gc.setLineWidth(1);
            gc.setLineStyle(SWT.LINE_SOLID);

            final int top = rect.y - insets.top;
            final int left = rect.x - insets.left;
            int bottom = rect.y + rect.height + insets.bottom;
            int right = rect.x + rect.width + insets.right;
            gc.setClipping(new org.eclipse.swt.graphics.Rectangle(left, top,
                    right - left, bottom - top));

            final Color color = ColorConstants.red;
            gc.setForeground(color);
            gc.drawLine(left, top, right, bottom);

            for (int i = 0; i < br.length; i++)
            {
                // color = br[i];
                gc.setForeground(color);
                gc.drawLine(right - i, bottom - i, right - i, top + i);
                gc.drawLine(right - i, bottom - i, left + i, bottom - i);
            }

            right--;
            bottom--;

            for (int i = 0; i < tl.length; i++)
            {
                // color = tl[i];
                gc.setForeground(color);
                gc.drawLine(left + i, top + i, right - i, top + i);
                gc.drawLine(left + i, top + i, left + i, bottom - i);
            }
            color.dispose();
        }
    }

    /**
     * @param parent
     */
    public TabbedWindowBorder(final IFigure parent)
    {
        // apparently paint() gets called before createBorders() has had
        // a chance to create the borders, so we just create them here
        inner = new TabbedTitleBarBorder(parent);
        outer = normalBorder;
    }

    /**
     * @param flag
     */
    public void setVisible(final boolean flag)
    {
        if (isVisible != flag)
        {
            isVisible = flag;
            ((TabbedTitleBarBorder) inner).setVisible(flag);
            if (flag)
            {
                ((TabbedTitleBarBorder) inner).getParent().repaint();
            }
        }
    }

    /**
     * @param flag
     */
    public void setHighlight(final boolean flag)
    {
        if (flag)
        {
            outer = highlightBorder;
        }
        else
        {
            outer = normalBorder;
        }
        ((TabbedTitleBarBorder) inner).getParent().repaint();
    }

    @Override
    protected void createBorders()
    {
        // TODO: NOTE: this is overriding default border creation
    }

    @Override
    public void paint(final IFigure figure, final Graphics g, final Insets insets)
    {
        if (isVisible)
        {
            if (comp != null)
            {
                final GC gc = new GC(comp);
                paint(comp, gc, insets);
                gc.dispose();
            }
            else
            {
                super.paint(figure, g, insets);
            }
        }
    }

    private Composite comp;

    /**
     * @param comp_
     * @param gc
     * @param insets
     */
    public void paint(final Composite comp_, final GC gc, final Insets insets)
    {
        this.comp = comp_;
        if (isVisible)
        {
            ((MySchemeBorder) outer).paint(comp_, gc, insets);
        }
    }
}