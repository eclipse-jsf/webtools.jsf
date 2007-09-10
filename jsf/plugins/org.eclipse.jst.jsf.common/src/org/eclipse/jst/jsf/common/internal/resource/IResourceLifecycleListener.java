package org.eclipse.jst.jsf.common.internal.resource;

/**
 * Listener can register for events ona particular LifecycleListener
 * 
 * @author cbateman
 *
 */
public interface IResourceLifecycleListener 
{
    /**
     * Listener accepts the resource lifecycle event
     * 
     * @param event
     * @return the result of accepting the event
     */
    EventResult acceptEvent(ResourceLifecycleEvent event);

    /**
     * @author cbateman
     *
     */
    public static class EventResult
    {
        /**
         * set to true if after the current event is finished firing, the source
         * should be disposed. If self-disposal is not applicable, the flag is ignored
         */
        protected boolean _disposeAfterEvent;

        /**
         * @return the remove listener flag
         */
        public boolean getDisposeAfterEvent()
        {
            return _disposeAfterEvent;
        }

        /**
         * @param disposeAfterEvent
         */
        public void setDisposeAfterEvent(boolean disposeAfterEvent) {
            _disposeAfterEvent = disposeAfterEvent;
        }
    }
}
