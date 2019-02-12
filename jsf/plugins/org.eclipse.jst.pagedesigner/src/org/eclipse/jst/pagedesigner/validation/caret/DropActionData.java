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
package org.eclipse.jst.pagedesigner.validation.caret;

import java.util.Collections;
import java.util.List;

/**
 * An action data for object drops
 * @author cbateman
 *
 */
public class DropActionData extends ActionData
{
    /**
     * @param action
     * @param data
     */
    public DropActionData(int action, DropData data ) 
    {
        super(action, data);
    }

    /**
     * @return the drop data or  null if none
     */
    public DropData getDropData()
    {
        return (DropData) getData();
    }
    
    /**
     * Encapsulates the tags to be dropped
     *
     */
    public static class DropData
    {
        private final List    _tagIds;
        
        /**
         * @param tagIds
         */
        public DropData(List tagIds)
        {
            _tagIds = Collections.unmodifiableList(tagIds);
        }
        
        /**
         * @return the list of tag ids being dropped.  List
         * is unmodifiable
         */
        public List getTagIdentifiers()
        {
            return _tagIds;
        }
    }
}
