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
 * <p>
 * A generic adapter used to adapt arbitrary view definitions to framework
 * objects.
 * </p>
 * 
 * @author cbateman
 * @param <VIEW_DEFN_BASE_TYPE>
 *            the base type of all view definition objects
 * @param <VIEW_CONTAINER_TYPE>
 *            the type of the container that is expeccted to hold the view defn
 * 
 */
public interface IViewDefnAdapter<VIEW_DEFN_BASE_TYPE, VIEW_CONTAINER_TYPE>
{
    /**
     * @param viewDefnObject
     * @param constructionData
     * @param viewContainer
     * @return a view object corresponding to the viewDefnBaseType object or
     *         null if none.
     */
    ViewObject mapToViewObject(
            VIEW_DEFN_BASE_TYPE viewDefnObject,
            ViewObjectConstructionStrategy<? extends VIEW_DEFN_BASE_TYPE> constructionData,
                    VIEW_CONTAINER_TYPE viewContainer);

    /**
     * This method may be expensive.
     * 
     * @param viewDefnObject
     * @param root
     * @return the view object corresponding to viewDefnObject in the
     * component sub-tree rooted at root.
     * <b>May return null if isn't found or can't be found</b>
     */
    ViewObject findViewObject(VIEW_DEFN_BASE_TYPE viewDefnObject, ComponentInfo root);

    /**
     * @param viewObject
     * @param root
     * @return the view definition object that viewObject was derived from
     * using root as the component sub-tree root to search in.
     * <b>May return null if isn't found or can't be found</b>
     */
    VIEW_DEFN_BASE_TYPE findViewDefn(ViewObject viewObject, ComponentInfo root);

    /**
     * @param viewDefnObject
     * @return the id for the viewDefnObject or null if none. Generally, null
     *         should indicate that no id is present but could be. This is
     *         distinct from the case where viewDefnObject can never define an
     *         id, in which case IllegalArgumentException should be thrown.
     * 
     * @throws IllegalArgumentException
     *             may be thrown to indicate that viewDefnObject does not
     *             correspond to a ViewObject type that has an id.
     * 
     */
    String getId(VIEW_DEFN_BASE_TYPE viewDefnObject)
    throws IllegalArgumentException;

    /**
     * Normally this is a workspace resource (IFile) or higher level document
     * type like an IDocument.
     * 
     * @param context
     * @param viewId
     * @return the container resource for the viewId in context.
     */
    VIEW_CONTAINER_TYPE getContainer(DTFacesContext context, String viewId);

    /**
     * @param container
     * @return the view roots for the definition in container or empty if none.
     *         MUST NOT BE NULL.
     */
    List<VIEW_DEFN_BASE_TYPE> getViewDefnRoots(VIEW_CONTAINER_TYPE container);

    /**
     * <p>
     * The view definition adapter must be able to extract all EL expressions
     * from a view definition. Given a model context that points into the view
     * definition it must return the EL expression in that context.
     * </p>
     * 
     * <p>
     * If the model context provided does not refer to a valid view definition
     * for this handler, then ViewHandlerException(EL_NOT_FOUND) should be
     * thrown. If an exception occurs while trying to extract the EL expression
     * at context, then ViewHandlerException(caughtException,
     * EL_EXCEPTION_CAUGHT_WHILE_PARSING) should be thrown.
     * </p>
     * 
     * <p>
     * Note that any reference to parsing here means extraction from the
     * document and not building an AST for the expression itself.</p>
     * 
     * @param context
     * @return the text (stripped of any escaping such as #{} in JSP) of the EL
     *         expression referred to by context or null if there is no valid EL
     *         expression at context.
     * @throws ViewHandlerException
     */
    ELExpression getELExpression(IModelContext context) throws ViewHandlerException;

}
