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
package org.eclipse.jst.jsf.common.internal;

/**
 * An injection interface that allows classes to selectively report test progress.
 * 
 * @author cbateman
 *
 */
public interface ITestTracker 
{
    /**
     * Event types
     *
     */
    public enum Event
    {
        /**
         * Signals that tracking should begin on the eventLabel
         * The seqId must be repeated on the STOP_TRACKING event
         * for the same event.
         */
        START_TRACKING,
        /**
         * Signals that tracking should stop on the named event
         * for the seqId that was passed first in the START_TRACKING.
         * 
         */
        STOP_TRACKING
    }
    
    /**
     * Fires the event of type event, a unique instance tracking seqId
     * and a label called eventLabel.
     * 
     * @param event
     * @param seqId
     * @param eventLabel
     */
    void fireEvent(Event event, long seqId, String eventLabel);
}
