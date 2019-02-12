/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.INamingContainerInfo;

/**
 * A design-time analog for the UIForm.
 * 
 * @author cbateman
 */
public class UIFormInfo extends ComponentInfo implements INamingContainerInfo 
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 6961034911873576644L;

    private final boolean _prependId;
    private final boolean _submitted;
    
    /**
     * @param id
     * @param parent
     * @param componentTypeInfo
     * @param isRendered
     * @param prependId
     * @param submitted
     */
    protected UIFormInfo(final String id, final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo, final boolean isRendered
            , final boolean prependId, final boolean submitted) {
        super(id, parent, componentTypeInfo, isRendered);
        _prependId = prependId;
        _submitted = submitted;
    }
    
    /**
     * @param parent
     * @param componentTypeInfo
     * @param attributes
     */
    protected UIFormInfo(final ComponentInfo parent, ComponentTypeInfo componentTypeInfo,
            Map attributes)
    {
        this(getStringProperty("id", attributes, true), //$NON-NLS-1$
                parent,
                componentTypeInfo,
                getBooleanProperty("rendered", attributes, false), //$NON-NLS-1$
                getBooleanProperty("prependId", attributes, false), //$NON-NLS-1$
                getBooleanProperty("submitted", attributes, false)); //$NON-NLS-1$
    }
    
    /**
     * JSF 1.2 only
     * 
     * @return true if the form allows its id to be prepended to its 
     * descendent's ids.
     */
    public final boolean isPrependId()
    {
        return _prependId;
    }

    /**
     * @return true if the form is submitted.
     */
    public final boolean isSubmitted() {
        return _submitted;
    }

    protected String getMostSpecificComponentName()
    {
        return "UIForm"; //$NON-NLS-1$
    } 
}
