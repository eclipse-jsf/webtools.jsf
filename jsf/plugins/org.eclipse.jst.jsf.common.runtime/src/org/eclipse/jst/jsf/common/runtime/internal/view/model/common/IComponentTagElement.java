package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;

/**
 * A JSF tag element that maps one-to-one to a JSF UIComponent
 * 
 * @author cbateman
 *
 */
public interface IComponentTagElement extends IJSFTagElement 
{
    ComponentTypeInfo getComponent();
}
