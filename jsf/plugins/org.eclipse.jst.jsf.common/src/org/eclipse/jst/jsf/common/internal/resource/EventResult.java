/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.internal.resource;

/**
 * @author cbateman
 *
 */
public class EventResult
{
    private static EventResult DEFAULT;

    /**
     * @return an event result with defaults initialized
     */
    public synchronized static EventResult getDefaultEventResult()
    {
        if (DEFAULT == null)
        {
            DEFAULT = new EventResult()
            {
                @Override
                protected void setDisposeAfterEvent(final boolean disposeAfterEvent)
                {
                    throw new UnsupportedOperationException();
                }
            };
        }
        return DEFAULT;
    }

    private static EventResult DISPOSE_AFTER_EVENT;

    /**
     * @return an event result with default except dispose after
     * is set
     */
    public synchronized static EventResult getDisposeAfterEventResult()
    {
        if (DISPOSE_AFTER_EVENT == null)
        {
            DISPOSE_AFTER_EVENT = new EventResult()
            {
                /**
                 * @return the remove listener flag
                 */
                @Override
                public boolean getDisposeAfterEvent()
                {
                    return true;
                }

                /**
                 * @param disposeAfterEvent
                 */
                @Override
                protected void setDisposeAfterEvent(final boolean disposeAfterEvent) {
                    _disposeAfterEvent = disposeAfterEvent;
                }

            };
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
    protected void setDisposeAfterEvent(final boolean disposeAfterEvent) {
        _disposeAfterEvent = disposeAfterEvent;
    }
}