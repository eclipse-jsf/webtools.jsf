/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.viewer;

import org.eclipse.jface.viewers.SelectionChangedEvent;

/**
 * @author cbateman
 * 
 * Default adapter class for IHTMLGraphicalViewerListener
 *
 */
public abstract class HTMLGraphicalViewerListenenerAdapter implements
        IHTMLGraphicalViewerListener {

    public void selectionAboutToChange() {
        // do nothing -- override to implement
    }

    public void selectionChangeFinished() {
        // do nothing -- override to implement
    }

    public void selectionChanged(SelectionChangedEvent event) {
        // do nothing == override to implement
    }

}
