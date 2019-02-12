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
package org.eclipse.jst.jsf.facelet.core.internal.facet;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/*package*/ class FacetInstallModel extends FacetChangeModel
{
    // default bean listeners
    private final PropertyChangeSupport changeSupport = new PropertyChangeSupport(
                                                              this);

    public void addPropertyChangeListener(final String propertyName,
            final PropertyChangeListener listener)
    {
        changeSupport.addPropertyChangeListener(propertyName, listener);
    }

    public void removePropertyChangeListener(final String propertyName,
            final PropertyChangeListener listener)
    {
        changeSupport.removePropertyChangeListener(propertyName, listener);
    }

    @Override
    public ChangeActionType getChangeActionType()
    {
        return ChangeActionType.ADD;
    }
}
