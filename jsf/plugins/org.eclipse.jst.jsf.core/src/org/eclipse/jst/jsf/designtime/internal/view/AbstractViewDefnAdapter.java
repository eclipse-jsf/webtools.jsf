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
package org.eclipse.jst.jsf.designtime.internal.view;

import java.util.List;

import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.datatypes.ELExpression;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.IDTViewHandler.ViewHandlerException;

/**
 * All view definition adapters must sub-class this abstract class.
 * 
 * @author cbateman
 * @param <VIEW_DEFN_BASE_TYPE>
 * @param <VIEW_CONTAINER_TYPE>
 * 
 */
public abstract class AbstractViewDefnAdapter<VIEW_DEFN_BASE_TYPE, VIEW_CONTAINER_TYPE>
        implements IViewDefnAdapter<VIEW_DEFN_BASE_TYPE, VIEW_CONTAINER_TYPE>
{
    public abstract ViewObject mapToViewObject(
            VIEW_DEFN_BASE_TYPE viewDefnObject,
            ViewObjectConstructionStrategy<? extends VIEW_DEFN_BASE_TYPE> constructionData,
            VIEW_CONTAINER_TYPE viewContainer);

    public abstract String getId(VIEW_DEFN_BASE_TYPE viewDefnObject)
            throws IllegalArgumentException;

    public abstract VIEW_CONTAINER_TYPE getContainer(DTFacesContext context,
            String viewId);

    public abstract List<VIEW_DEFN_BASE_TYPE> getViewDefnRoots(
            VIEW_CONTAINER_TYPE container);

    public abstract ELExpression getELExpression(final IModelContext context)
            throws ViewHandlerException;

    public abstract ViewObject findViewObject(VIEW_DEFN_BASE_TYPE viewDefnObject,
            ComponentInfo root);

    public abstract VIEW_DEFN_BASE_TYPE findViewDefn(ViewObject viewObject,
            ComponentInfo root);
}
