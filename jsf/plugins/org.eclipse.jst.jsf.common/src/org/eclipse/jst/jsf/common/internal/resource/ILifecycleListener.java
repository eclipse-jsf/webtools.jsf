package org.eclipse.jst.jsf.common.internal.resource;

import java.util.EventObject;

/**
 * @author cbateman
 *
 * @param <EVENTTYPE>
 */
public interface ILifecycleListener<EVENTTYPE extends EventObject>
{
    /**
     * Listener accepts the resource lifecycle event
     * 
     * @param event
     * @return the result of accepting the event
     */
    EventResult acceptEvent(EVENTTYPE event);
}
