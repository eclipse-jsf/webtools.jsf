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
package org.eclipse.jst.jsf.facelet.core.internal.facet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/*package*/ class FaceletUninstallModel extends FacetChangeModel
{
    // default bean listeners
    private final PropertyChangeSupport _changeSupport = new PropertyChangeSupport(
                                                              this);

    public void addPropertyChangeListener(final String propertyName,
            final PropertyChangeListener listener)
    {
        _changeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(final String propertyName,
            final PropertyChangeListener listener)
    {
        _changeSupport.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public ChangeActionType getChangeActionType()
    {
        return ChangeActionType.REMOVE;
    }
}