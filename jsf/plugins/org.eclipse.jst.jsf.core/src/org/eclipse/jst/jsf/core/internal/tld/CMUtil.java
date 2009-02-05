/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.tld;

import java.util.Iterator;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.TaglibController;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TLDCMDocumentManager;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TaglibTracker;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLElementDeclaration;
import org.eclipse.wst.html.core.internal.provisional.HTMLCMProperties;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMNodeWrapper;
import org.eclipse.wst.xml.core.internal.ssemodelquery.ModelQueryAdapter;
import org.w3c.dom.Element;

/**
 * Utility class to content model related information.
 * 
 * @author mengbo
 */
public final class CMUtil {
	/**
	 * If the element is a custom tag, get the URI of it. If the element is a
	 * standard JSP tag, return null. If is not jsp tag, then return null
	 * @param decl
     *  
	 * @return the tag uri as a string
	 */
	public static String getTagURI(CMElementDeclaration decl) {
		if (decl instanceof CMNodeWrapper) {
			decl = (CMElementDeclaration) ((CMNodeWrapper) decl)
					.getOriginNode();
		}
		if (decl instanceof TLDElementDeclaration) {
			CMDocument doc = ((TLDElementDeclaration) decl).getOwnerDocument();
			if (doc instanceof TLDDocument) {
				return ((TLDDocument) doc).getUri();
			}
		}
		return null;
	}

	/**
	 * Test whether this is the JSP core tag.
	 * 
	 * @param decl
	 * @return true if decl is a jsp element declaration
	 */
	public static boolean isJSP(CMElementDeclaration decl) {
		if (!decl.supports(HTMLCMProperties.IS_JSP)) {
			return false;
		}
		Boolean b = (Boolean) decl.getProperty(HTMLCMProperties.IS_JSP);
		return b.booleanValue();
	}

	/**
	 * @param decl
	 * @return true if the element declartion is a non-JSP html element
	 */
	public static boolean isHTML(CMElementDeclaration decl) {
		if (!isJSP(decl) && (decl instanceof HTMLElementDeclaration)) {
			return true;
		}
		return false;
	}

	/**
	 * get element declaration of specified element
	 * 
	 * @param element
	 * @return null if can't get it.
	 */
	public static CMElementDeclaration getElementDeclaration(Element element) {
		if (element == null) {
			return null;
		}
		INodeNotifier notifier = (INodeNotifier) element.getOwnerDocument();
		if (notifier == null) {
			return null;
		}
		ModelQueryAdapter mqa = (ModelQueryAdapter) notifier
				.getAdapterFor(ModelQueryAdapter.class);
		if (mqa == null) {
			return null;
		}
		return mqa.getModelQuery().getCMElementDeclaration(element);
	}

	/**
	 * @param element
	 * @return the TLDElementDeclaration for element or null if not found
	 */
	public static TLDElementDeclaration getTLDElementDeclaration(Element element) {
		CMNode decl = getElementDeclaration(element);
		if (decl instanceof CMNodeWrapper) {
			decl = ((CMNodeWrapper) decl).getOriginNode();
		}
		if (decl instanceof TLDElementDeclaration) {
			return (TLDElementDeclaration) decl;
		}
        return null;
	}
	
	/**
	 * !!! NOTE: this function is intended to work around the problem that if your element
	 * has not yet been added to an  IDOMModel, getElementDeclaration won't be able to find
	 * it.  This method does nothing (unlike the ModelQuery-based approach in getElementDeclaration)
	 * to ensure that the namespace "uri" provided is valid in the structured document provided.  It is
	 * therefore only advisable to use this method in cases where your node is not already a member of a 
	 * IDOMModel.
	 * 
	 * @param uri
	 * @param elementName
	 * @param document 
	 * @return the TLDElementDeclaration for this required tag or null if there  is nothing appropriate
	 */
	public static CMElementDeclaration getTLDElementDeclaration(final String uri, final String elementName, IDocument document)
	{
	    TLDCMDocumentManager tldmgr = TaglibController.getTLDCMDocumentManager(document);
	    
	    if (tldmgr != null)
	    {
            for (Iterator it = tldmgr.getTaglibTrackers().iterator();it.hasNext();)
            {
                TaglibTracker  tracker = (TaglibTracker) it.next();
                
                if (tracker.getURI().equals(uri))
                {
                    return (CMElementDeclaration) tracker.getElements().getNamedItem(tracker.getPrefix()+":"+elementName);
                }
            }
	    }
        // fallthrough
        return null;
	}

	/**
	 * give an element, get its namespace URI.
	 * 
	 * @param element
	 * @return the namespace URI
	 */
	public static String getElementNamespaceURI(Element element) {
	    //System.out.printf("uri for %s is %s\n", element.toString(), element.getNamespaceURI());

	    CMElementDeclaration decl = getElementDeclaration(element);
		if (decl == null) {
		    
		    // if the content model has nothing, see if the element 
		    // itself has an xml namespace
		    // TODO: should only apply this if the source document
		    // is a valid XML doc?
		    final String uri = element.getNamespaceURI();
		    
		    // may be null which the default state
			return uri;
		}

		if (isJSP(decl)) {
			return ITLDConstants.URI_JSP;
		} else if (isHTML(decl)) {
			return ITLDConstants.URI_HTML;
		}

		return getTagURI(decl);
	}

	/**
	 * @param element
	 * @return true if the element can have children
	 */
	public static boolean canHaveDirectTextChild(Element element) {
		CMElementDeclaration decl = getElementDeclaration(element);
		if (decl == null) {
			return true;
		}
		int contentType = decl.getContentType();
		return contentType != CMElementDeclaration.ELEMENT
				&& contentType != CMElementDeclaration.EMPTY;

	}
    
    private CMUtil()
    {
        // util class, no external instantiation
    }
}
