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

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.IEditableValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValueChangeListenerDecorator;

/**
 * A design-time analog of the standard UIInput.
 * 
 * @author cbateman
 * 
 */
public class UIInputInfo extends UIOutputInfo implements
        IEditableValueHolderInfo
{
    /**
     * serializable uid
     */
    private static final long serialVersionUID = -6055473902554910848L;
    private final boolean _isValid;
    private final boolean _isImmediate;
    private final boolean _isRequired;
    private final Object _submittedValue;
    private final String _validator;
    private final String _valueChangeListener;
    private final boolean _localSetValue;

    /**
     * @param id
     * @param parent
     * @param typeInfo
     * @param editableValueHolderInfo
     * @param isRendered
     */
    protected UIInputInfo(final String id, final ComponentInfo parent,
            final ComponentTypeInfo typeInfo,
            final IEditableValueHolderInfo editableValueHolderInfo,
            final boolean isRendered)
    {
        super(id, parent, typeInfo, editableValueHolderInfo, isRendered);

        if (editableValueHolderInfo == null)
        {
            _isValid = true;
            _isImmediate = false;
            _isRequired = false;
            _localSetValue = false;
            _submittedValue = null;
            _validator = null;
            _valueChangeListener = null;
        }
        else
        {
            _isValid = editableValueHolderInfo.isValid();
            _isImmediate = editableValueHolderInfo.isImmediate();
            _isRequired = editableValueHolderInfo.isRequired();
            _localSetValue = editableValueHolderInfo.isLocalSetValue();
            _submittedValue = editableValueHolderInfo.getSubmittedValue();
            _validator = editableValueHolderInfo.getValidator();
            _valueChangeListener = editableValueHolderInfo
                    .getValueChangeListener();

            for (final Iterator it = editableValueHolderInfo.getValidators()
                    .iterator(); it.hasNext();)
            {
                final ValidatorDecorator validator = (ValidatorDecorator) it
                        .next();
                addValidator(validator);
            }

            for (final Iterator it = editableValueHolderInfo
                    .getValueChangeListeners().iterator(); it.hasNext();)
            {
                final ValueChangeListenerDecorator valueChangeListener = (ValueChangeListenerDecorator) it
                        .next();
                addValueChangeListener(valueChangeListener);
            }
        }
    }

    /**
     * @param parent
     * @param typeInfo
     * @param attributes
     */
    protected UIInputInfo(final ComponentInfo parent,
            final ComponentTypeInfo typeInfo, Map attributes)
    {
        this(getStringProperty("id", attributes, true), parent, typeInfo, //$NON-NLS-1$
                getEditableValueHolderInfo("$editableValueHolderInfo", //$NON-NLS-1$
                        attributes), getBooleanProperty("rendered", attributes)); //$NON-NLS-1$
    }

    private static IEditableValueHolderInfo getEditableValueHolderInfo(
            String key, Map attributes)
    {
        return (IEditableValueHolderInfo) attributes.get(key);
    }

    // public String toString() {
    // final String toString = super.toString();
    // return toString + ", isValid=" + _isValid + ", isImmediate="
    // + _isImmediate + ", isRequired=" + _isRequired;
    // }

    // @Override
    protected String getMostSpecificComponentName()
    {
        return "UIInput"; //$NON-NLS-1$
    }

    public final boolean isValid()
    {
        return _isValid;
    }

    public final boolean isImmediate()
    {
        return _isImmediate;
    }

    public final boolean isRequired()
    {
        return _isRequired;
    }

    public final Object getSubmittedValue()
    {
        return _submittedValue;
    }

    public final String getValidator()
    {
        return _validator;
    }

    public final String getValueChangeListener()
    {
        return _valueChangeListener;
    }

    public final boolean isLocalSetValue()
    {
        return _localSetValue;
    }

    public final void addValidator(final ValidatorDecorator validator)
    {
        addDecorator(validator, ComponentFactory.VALIDATOR);
    }

    public final void addValueChangeListener(
            final ValueChangeListenerDecorator valueChangeListenerInfo)
    {
        addDecorator(valueChangeListenerInfo,
                ComponentFactory.VALUE_CHANGE_LISTENER);
    }

    public final List getValidators()
    {
        return getDecorators(ComponentFactory.VALIDATOR);
    }

    public final List getValueChangeListeners()
    {
        return getDecorators(ComponentFactory.VALUE_CHANGE_LISTENER);
    }
}
