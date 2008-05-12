/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSource2Info;
import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IActionSourceInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ActionListenerDecorator;

/**
 * An design time analog of the standard UICommand.
 * @author cbateman
 *
 */
public class UICommandInfo extends ComponentInfo implements IActionSource2Info {
    private final String _actionExpression;
    private final String _actionListener;
    private final boolean _isImmediate;

    /**
     * serialization id
     */
    private static final long serialVersionUID = -9025172832535840949L;

    /**
     * @param id
     * @param parent
     * @param componentTypeInfo
     * @param isRendered
     * @param actionSourceInfo
     */
    protected UICommandInfo(final String id, final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo,
            final boolean isRendered, final IActionSourceInfo actionSourceInfo) {
        super(id, parent, componentTypeInfo, isRendered);

        if (actionSourceInfo == null) {
            _actionExpression = null;
            _actionListener = null;
            _isImmediate = false;
        } else {
            // TODO: having action and actionExpression will come back to
            // to bite us.
            _actionExpression = actionSourceInfo.getAction();
            _actionListener = actionSourceInfo.getActionListener();
            _isImmediate = actionSourceInfo.isImmediate();

            for (final Iterator it = actionSourceInfo.getActionListeners()
                    .iterator(); it.hasNext();) {
                final ActionListenerDecorator actionListener = 
                    (ActionListenerDecorator) it.next();
                addActionListener(actionListener);
            }
        }
    }

    /**
     * @param parent
     * @param componentTypeInfo
     * @param attributes
     */
    protected UICommandInfo(final ComponentInfo parent, final ComponentTypeInfo componentTypeInfo,
            Map attributes)
    {
        this(getStringProperty("id", attributes, true), //$NON-NLS-1$
                parent, componentTypeInfo,
                getBooleanProperty("rendered", attributes, false), //$NON-NLS-1$
                getActionSourceInfo("$actionSourceInfo", attributes) //$NON-NLS-1$
                );
    }
    
    private static IActionSourceInfo getActionSourceInfo(String key,
            Map attributes)
    {
        return (IActionSourceInfo) attributes.get(key);
    }

    public String getActionExpression() {
        return _actionExpression;
    }

    public final void addActionListener(final ActionListenerDecorator actionListener) {
        addDecorator(actionListener, ComponentFactory.ACTION_LISTENER);
    }

    public final String getAction() {
        return _actionExpression;
    }

    public final String getActionListener() {
        return _actionListener;
    }

    public final List getActionListeners() {
        return getDecorators(ComponentFactory.ACTION_LISTENER);
    }

    public final boolean isImmediate() {
        return _isImmediate;
    }

    protected String getMostSpecificComponentName()
    {
        return "UICommand"; //$NON-NLS-1$
    }
}
