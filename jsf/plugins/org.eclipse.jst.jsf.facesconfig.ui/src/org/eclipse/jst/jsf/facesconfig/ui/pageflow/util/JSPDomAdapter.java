/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * This class is defined as a simple adapter to JSP DOM model. User can get the
 * taglib prefix, and get the needed element by element tage name, and
 * namespace(prefix) Usage: User should initialize this class instance before
 * using it. Following is a typical example: JSPDomAdapter jspAdapter = new
 * JSPDomAdapter(); IFile jspFile =
 * ResourcesPlugin.getWorkspace().getRoot().getFile(jspPath); if
 * (_jspAdapter.initialize(jspFile)) { //the prefix of JSF HTML TagLib String
 * prefix = _jspAdapter.getTagLibPrefix(JSPDomAdapter.JSF_HTML_TAGLIB); }
 * 
 * @author Xiao-guang Zhang
 */
public class JSPDomAdapter {
    /** log instance */
    private static final Logger log = EditorPlugin
    .getLogger(JSPDomAdapter.class);

    /** JSP Taglib's tag name */
    public static String JSP_TAGLIB_TAG = "jsp:directive.taglib"; //$NON-NLS-1$

    /** JSF HTML TagLib name */
    public static String JSF_HTML_TAGLIB = "http://java.sun.com/jsf/html"; //$NON-NLS-1$

    /** singleton model manager */
    private static IModelManager modelManager;

    /** source jsp file */
    private IFile jspFile;

    /** Structured Model of JSP File */
    private IStructuredModel model;

    /** Root Document (Node) of JSP file */
    private Document document;

    /**
     * 
     */
    public JSPDomAdapter() {
        super();

    }

    /**
     * initialize this adapter to get the StructuredModel for the input file.
     * At last user should call releaseModel() method to release the Structured Model.
     * 
     * @param file -
     *            JSP file
     * @return - True means sucessfully load jsp file.
     */
    public boolean initialize(final IFile file) {
        jspFile = file;

        if (jspFile != null && jspFile.exists()) {
            try {
                model = getModel(jspFile);
            } catch (final IOException e) {
                // PageFlow.JSPDomAdapter.FailToGetStructuredModel = Failed to
                // get the structured model
                log.error("PageFlow.JSPDomAdapter.FailToGetStructuredModel", e); //$NON-NLS-1$
            } catch (final CoreException e) {
                // PageFlow.JSPDomAdapter.FailToGetStructuredModel = Failed to
                // get the structured model
                log.error("PageFlow.JSPDomAdapter.FailToGetStructuredModel", e);//$NON-NLS-1$
            }

            if (model != null && model instanceof IDOMModel) {
                return true;
            }
        }

        return false;
    }

    /**
     * get the prefix for the input taglib URI, e.g.,
     * http://java.sun.com/jsf/html -> "h"
     * 
     * @param taglibURI
     * @return the prefix
     */
    public String getTagLibPrefix(final String taglibURI) {
        String prefix = null;

        final IStructuredDocument sdocument = model.getStructuredDocument();

        if (sdocument != null)
        {
            IStructuredDocumentContext context =
                IStructuredDocumentContextFactory.INSTANCE.getContext(sdocument, -1);
            ITaglibContextResolver resolver =
            IStructuredDocumentContextResolverFactory2.INSTANCE.getTaglibContextResolverFromDelegates(context);
            prefix = resolver.getTagPrefixForURI(taglibURI);
        }

        return prefix;
    }

    /**
     * get the elements by the namespace and its tag name, e.g., h and
     * commandButton.
     * 
     * @param namespace -
     *            namespace for the taglib, e.g., h for
     *            http://java.sun.com/jsf/html
     * @param elementName -
     *            element Tag Name, e.g., h
     * @return - Element Node list.
     */
    public List getElementsByTagNameNS(final String namespace, final String elementName) {
        List nodes = null;

        if (getDocument() != null) {
            NodeList listNodes = null;
            if (namespace != null) {
                listNodes = getDocument().getElementsByTagName(
                        namespace + ":" + elementName); //$NON-NLS-1$
            } else {
                listNodes = getDocument().getElementsByTagName(elementName);
            }

            if (listNodes != null && listNodes.getLength() > 0) {
                nodes = new ArrayList();
                for (int i = 0; i < listNodes.getLength(); i++) {
                    nodes.add(listNodes.item(i));
                }
            }
        }
        return nodes;
    }

    /**
     * get the singleton model manager.
     * 
     * @return
     */
    private IModelManager getModelManager() {
        if (modelManager == null) {
            modelManager = StructuredModelManager.getModelManager();
        }
        return modelManager;
    }

    /**
     * get the structured model for the JSP file
     * 
     * @param file -
     *            JSP File
     * @return - IStructuredModel
     * @throws IOException
     * @throws CoreException
     */
    private IStructuredModel getModel(final IFile file) throws IOException,
    CoreException {
        return getModelManager().getModelForRead(file);
    }

    /**
     * get the root docuement for the StructuredModel
     * 
     * @return
     */
    private Document getDocument() {
        if (document == null) {
            if (model != null && model instanceof IDOMModel) {
                document = ((IDOMModel) model).getDocument();
            }
        }
        return document;
    }


    /**
     * signal we are done with the model
     */
    public void releaseModel() {
        if (model != null) {
            model.releaseFromRead();
        }
    }
}
