/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.elementedit.html;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit;
import org.eclipse.jst.pagedesigner.validation.caret.IPositionMediator;
import org.eclipse.jst.pagedesigner.viewer.DefaultDropLocationStrategy;
import org.eclipse.jst.pagedesigner.viewer.DesignPosition;
import org.eclipse.jst.pagedesigner.viewer.IDropLocationStrategy;
import org.w3c.dom.Node;

/**
 * Simple ElementEdit implementation to support drag and drop
 * of style sheet link tags into the HTML head tag.
 */
public class StylesheetLinkElementEdit extends AbstractElementEdit
{
    public IDropLocationStrategy getDropRequestorLocationStrategy(TagIdentifier tag, EditPartViewer viewer) {
        return new MyDropLocationStrategy(viewer);
    }

    private static class MyDropLocationStrategy extends DefaultDropLocationStrategy
    {
        /**
         * @param viewer
         */
        public MyDropLocationStrategy(EditPartViewer viewer) {
            super(viewer);
        }

        public DesignPosition calculateDesignPosition(EditPart host,
                Point p, IPositionMediator validator) {
        	Node node = (Node) host.getModel();
        	if (host != null && IHTMLConstants.TAG_HEAD.equalsIgnoreCase(node.getLocalName())) {
        		return new DesignPosition(host, 0);
        	}
        	return super.calculateDesignPosition(host, p, validator);
        }
    }
}
