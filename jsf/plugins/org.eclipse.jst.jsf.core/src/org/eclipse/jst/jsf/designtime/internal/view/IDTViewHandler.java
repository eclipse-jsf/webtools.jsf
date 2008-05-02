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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.jsf.common.internal.resource.ImmutableLifecycleListener;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;

/**
 * <p>
 * Defines the basic interface for all design time view handlers that parallel
 * custom runtime view handlers (extenders of ViewHandler).
 * </p>
 * 
 * @author cbateman
 * 
 */
public interface IDTViewHandler
{
    /**
     * <p>
     * Creates and returns the view root. This method <i>may</i> optionally
     * intialize the view root with any data that need only be done once per
     * instance. Generally, this will include providing the root access to
     * itself or its IViewDefnAdapterFactory so that it can refresh itself.
     * </p>
     * 
     * @param facesContext
     * @param viewId
     * @return a designtime view root instance of viewId under the given faces
     *         context
     * @throws ViewHandlerException
     */
    DTUIViewRoot createView(DTFacesContext facesContext, String viewId)
            throws ViewHandlerException;

    /**
     * Calculate the locale for the current view context. The return string must
     * conform to the standard format proscribed by java.util.Locale.
     * 
     * @param context
     * @return the locale string for the view definition referred to by context
     * @throws ViewHandlerException
     */
    String calculateLocale(DTFacesContext context) throws ViewHandlerException;

    /**
     * Given that resource is the view definition source and requestPath is the
     * current servlet uri for a request relative to the web content root, map
     * it to the actual request uri that would be required at runtime to invoke
     * the view definition in resource. DTFacesContext will contain an external
     * context that may be used to derive servlet mapping configuration.
     * 
     * @param context
     * @param resource
     * @param requestPath
     * @return the IPath representing the newly mapped request path
     * @throws ViewHandlerException
     */
    IPath getActionURL(DTFacesContext context, IResource resource,
            IPath requestPath) throws ViewHandlerException;

    /**
     * TODO: if we already have context, must we already know what the source
     * is?
     * 
     * The IResource need not exist.
     * 
     * @param context
     * @param viewId
     * @return the resource containing the view definition for viewId.
     * @throws ViewHandlerException
     */
    IResource getActionDefinition(DTFacesContext context, String viewId)
            throws ViewHandlerException;

    /**
     * Given that a request is made for uri from within the view named by
     * relativeToViewId at runtime, return the path to the resource containing
     * that view definition. The path need not point to a resource that
     * currently exists.
     * 
     * @param context
     * @param relativeToViewId
     * @param uri
     * @return the path to the resource named by uri relative to the view named
     *         by relativeToViewId
     * @throws ViewHandlerException
     */
    IPath getRelativeActionPath(DTFacesContext context,
            String relativeToViewId, String uri) throws ViewHandlerException;

    /**
     * Translate a workspace resource to the view id it will have at runtime.
     * 
     * @param context
     * @param res
     * @return the view id for res or null if there is no meaningful value
     */
    String getViewId(DTFacesContext context, IResource res);

    /**
     * @param context
     * @return a view definition adapter
     * @throws ViewHandlerException
     */
    IViewDefnAdapterFactory getViewMetadataAdapterFactory(DTFacesContext context)
            throws ViewHandlerException;

    /**
     * @param file
     * @return true if the contents of the file is of a format that this view
     *         handler supports. Generally, this is decided by a combination of
     *         content type and, in the case of XML formats, schema.
     */
    boolean supportsViewDefinition(final IFile file);

    /**
     * Releases the handler's in-memory image.
     */
    void dispose();

    /**
     * Called by design time app manager to set a global resource listener
     * for the view handler to use.  View handlers should generally use this
     * listener to reduce the overhead of extra resouce change listeners.
     * 
     * @param listener
     */
    void setLifecycleListener(final ImmutableLifecycleListener listener);

    /**
     * General exception class for problems that a custom view handler wants to
     * propagate to the framework in a well-defined way.
     * 
     */
    public static final class ViewHandlerException extends Exception
    {
        private final Cause _causeCode;

        /**
         * Enumerates the known failure causes
         * 
         */
        public enum Cause
        {
            /**
             * thrown from getELExpression when the context passed does not
             * refer to a parsable EL expression in the view definition
             */
            EL_NOT_FOUND,
            /**
             * thrown from getELExpression when an exception occurs during EL
             * parsing. The ViewHandlerException should wrap the causing
             * exception in this cause
             */
            EL_EXCEPTION_CAUGHT_WHILE_PARSING,
            /**
             * an undefined exception cause indicating something not foreseen by
             * the framework.
             */
            UNDEFINED,

            /**
             * indicates that a bad or null version stamp was encountered. The
             * wrapped exception may provide tracing information to the cause.
             */
            BAD_VERSION_STAMP,

            /**
             * indicates that a bad or null staleness advisor was encountered.
             * The wrapped exception may provide tracing information to the
             * cause.
             */
            BAD_STALENESS_ADVISOR, 
            
            /**
             * a general failure by the view creation to create a new view root.
             */
            UNABLE_TO_CREATE_VIEW
        }

        /**
         * 
         */
        private static final long serialVersionUID = 2109525340402345111L;

        /**
         * Construct an exception with an undefined cause
         */
        public ViewHandlerException()
        {
            this(Cause.UNDEFINED);
        }

        /**
         * @param cause
         */
        public ViewHandlerException(final Cause cause)
        {
            super();
            _causeCode = cause;
        }

        /**
         * @param message
         * @param caught
         * @param cause
         */
        public ViewHandlerException(final String message,
                final Throwable caught, final Cause cause)
        {
            super(message, caught);
            _causeCode = cause;
        }

        /**
         * @param message
         * @param cause
         */
        public ViewHandlerException(final String message, final Cause cause)
        {
            super(message);
            _causeCode = cause;
        }

        /**
         * @param caught
         * @param cause
         */
        public ViewHandlerException(final Throwable caught, final Cause cause)
        {
            super(caught);
            _causeCode = cause;
        }

        /**
         * @return the cause code
         */
        public final Cause getCauseCode()
        {
            return _causeCode;
        }
    }
}
