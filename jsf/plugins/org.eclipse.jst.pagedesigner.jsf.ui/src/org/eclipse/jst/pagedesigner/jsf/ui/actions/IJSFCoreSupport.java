/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.actions;

/**
 * This interface is to provide information of how to enable/disable add
 * jsf core sub element.
 * 
 * As other JSF taglibs that we don't know today may also use jsf core tags inside
 * them, so we may also enable the actions for adding jsf core tags to them. But
 * we need check whether individual JSF core tags are supported.
 * 
 * @author mengbo
 * @version 1.5
 */
public interface IJSFCoreSupport
{
    /**
     * actionListener can only be added to those ActionSource component.
     * @return if is an action source
     */
    public boolean isActionSource();

    /**
     * 
     * @return true if is a UI component
     */
    public boolean isUIComponent();

    /**
     * converter (convertXXX) can only be added to those ValueHolder
     * @return true if is a value holder
     */
    public boolean isValueHolder();

    /**
     * validator can only be added to EditableValueHolder
     * @return true if is an editable value holder
     */
    public boolean isEditableValueHolder();

    /**
     * whether support selectItem and selectItems
     * @return true if supports the UISelectItem(s) contract
     */
    public boolean supportSelectItems();
}
