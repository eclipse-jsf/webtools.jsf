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
