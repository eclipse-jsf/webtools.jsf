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
        private static EventResult DEFAULT;

        /**
         * @return an event result with defaults initialized
         */
        public static EventResult getDefaultEventResult()
        {
            if (DEFAULT == null)
            {
                DEFAULT = new EventResult();
            }
            return DEFAULT;
        }

        private static EventResult DISPOSE_AFTER_EVENT;

        /**
         * @return an event result with default except dispose after
         * is set
         */
        public static EventResult getDisposeAfterEventResult()
        {
            if (DISPOSE_AFTER_EVENT == null)
            {
                DISPOSE_AFTER_EVENT = new EventResult();
                DISPOSE_AFTER_EVENT.setDisposeAfterEvent(true);
            }
            return DISPOSE_AFTER_EVENT;
        }

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
        protected void setDisposeAfterEvent(boolean disposeAfterEvent) {
            _disposeAfterEvent = disposeAfterEvent;
        }
    }
}
