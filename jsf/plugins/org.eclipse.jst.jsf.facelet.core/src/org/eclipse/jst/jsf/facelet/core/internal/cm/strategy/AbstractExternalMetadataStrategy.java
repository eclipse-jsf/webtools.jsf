/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.cm.strategy;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.jsf.facelet.core.internal.cm.ExternalTagInfo;
import org.eclipse.jst.jsf.facelet.core.internal.cm.TagInfo;

/**
 * Super class of all external meta-data strategy.
 * 
 * @author cbateman
 * 
 */
/* package */abstract class AbstractExternalMetadataStrategy implements
IExternalMetadataStrategy
{
    private final String _displayName;
    private final String _id;

    protected AbstractExternalMetadataStrategy(final String id,
            final String displayName)
    {
        _id = id;
        _displayName = displayName;
    }

    public final TagInfo getNoResult()
    {
        // this value must be "==" comparable
        return ExternalTagInfo.NULL_INSTANCE;
    }

    public abstract TagInfo perform(TagIdentifier input) throws Exception;

    public final String getDisplayName()
    {
        return _displayName;
    }

    public String getId()
    {
        return _id;
    }
}
