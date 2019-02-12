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
package org.eclipse.jst.jsf.common.internal.locator;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Default delegatable locator for composing locators.
 * 
 * @author cbateman
 *
 * @param <LOCATORTYPE>
 * @param <COMPOSITETYPE>
 * @param <CONTEXTTYPE>
 * @param <IDTYPE>
 */
public class DefaultComposingLocatorDelegate<LOCATORTYPE, COMPOSITETYPE, CONTEXTTYPE, IDTYPE> extends
        AbstractLocator<COMPOSITETYPE, CONTEXTTYPE, IDTYPE>
{

    /**
     * @param id
     * @param displayName
     */
    public DefaultComposingLocatorDelegate(IDTYPE id, String displayName)
    {
        super(id, displayName);
    }

    
    /**
     * @param id
     * @param displayName
     * @param noResultValue
     * @param mutableListenerList
     */
    public DefaultComposingLocatorDelegate(
            IDTYPE id,
            String displayName,
            COMPOSITETYPE noResultValue,
            CopyOnWriteArrayList<ILocatorChangeListener> mutableListenerList)
    {
        super(id, displayName, noResultValue, mutableListenerList);
    }


    @Override
    protected COMPOSITETYPE doLocate(CONTEXTTYPE context)
    {
        throw new UnsupportedOperationException("This method is abstract and should not be called"); //$NON-NLS-1$
    }
}
