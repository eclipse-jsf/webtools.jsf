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
