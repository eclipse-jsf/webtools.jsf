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
package org.eclipse.jst.jsf.designtime.internal.resources;

import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;

/**
 * 
 * @author cbateman
 *
 */
public abstract class JSFResourceChangeListener implements ILocatorChangeListener
{

   /**
     * Indicates that a tag library has changed
     * @author cbateman
     *
     */
    public static class JSFResourceChangedEvent extends LocatorChangeEvent
    {
        /**
         * 
         */
        private static final long serialVersionUID = -1546380081551499245L;

        /**
         * TODO: what happens if one locator has a namespace collision with
         * another one?
         */
        public enum CHANGE_TYPE
        {
            /**
             * Indicates that the resource is new
             */
            ADDED, 
            
            /**
             * Indicates that the resource was removed.
             */
            REMOVED, 
            
            /**
             * Indicates that the resource is not new, but it's content
             * has changed
             */
            CHANGED
        }

        private final CHANGE_TYPE       _changeType;
        private final IJSFResourceFragment _oldValue;
        private final IJSFResourceFragment _newValue;

        /**
         * @param source
         * @param oldValue 
         * @param newValue 
         * @param changeType
         */
        public JSFResourceChangedEvent(
                final AbstractJSFResourceLocator source,
                final IJSFResourceFragment oldValue, 
                final IJSFResourceFragment newValue,
                final CHANGE_TYPE changeType)
        {
            super(source);
            _changeType = changeType;
            _oldValue = oldValue;
            _newValue = newValue;
        }

        @Override
        public IJSFResourceLocator getSource()
        {
            return (IJSFResourceLocator) super.getSource();
        }

        /**
         * @return the type of the change
         */
        public final CHANGE_TYPE getChangeType()
        {
            return _changeType;
        }

        /**
         * @return the old value. This is null if the event is ADDED
         */
        public final IJSFResourceFragment getOldValue()
        {
            return _oldValue;
        }
        
        /**
         * @return the new value.  This is null if the event is REMOVED
         */
        public final IJSFResourceFragment getNewValue()
        {
            return _newValue;
        }
    }

    public final void changed(final LocatorChangeEvent event)
    {
        changed((JSFResourceChangedEvent)event);
    }

    /**
     * @param event
     */
    public abstract void changed(final JSFResourceChangedEvent event);
}
