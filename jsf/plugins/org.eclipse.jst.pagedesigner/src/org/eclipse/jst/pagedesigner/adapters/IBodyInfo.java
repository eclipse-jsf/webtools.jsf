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
package org.eclipse.jst.pagedesigner.adapters;

import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * This is an adapter interface. Will be adapted to all the node in the
 * document. It provides additional information to support designer.
 * 
 * Basicaly it tells the correspoinding node's body related information.
 * 
 * @author mengbo
 */
// XXX: in the future will use adapter mechanism to support dynamic information
public interface IBodyInfo // extends INodeAdapter
{
	// /**
	// * whether this node is runtime visible.
	// *
	// * @return
	// */
	// public boolean isRuntimeVisible(INodeNotifier node);
	//    
	// /**
	// * whether this node is design time visible.
	// * @return
	// */
	// public boolean isDesignTimeVisible(INodeNotifier node);

	// /**
	// * whether is HTML tag.
	// * @param node
	// * @return
	// */
	// public boolean isHTML(IDOMNode node);
	//    
	// /**
	// * whether is JSP tag.
	// * @param node
	// * @return
	// */
	// public boolean isJSP(IDOMNode node);
	//    
	// /**
	// * whether is custom tag.
	// * @param node
	// * @return
	// */
	// public boolean isCustomTag(IDOMNode node);
	//    
	// /**
	// * for custom tag, there may have a corresponding HTML tag name.
	// * This can be used for content model validation.
	// *
	// * @param node
	// * @return
	// */
	// public String getCorrespondingHTMLTag(IDOMNode node);

	/**
	 * whether this node is body node. We treat the document node and certain
	 * element node like "HTML", "BODY", "f:view", "f:subview" as body node. At
	 * design time we may want to move those visual node into the body.
	 * 
	 * @param node 
	 * @return true if node is a body container
	 */
	public boolean isBodyContainer(IDOMNode node);

	/**
	 * this method should only be called isBodyContainer return true; It checks
	 * whether the uri/localname should belong to the head part of this body
	 * container.
	 * 
	 * @param node 
	 * @param uri
	 * @param localname
	 * @return true if node is a header
	 */
	public boolean isBodyHeader(IDOMNode node, String uri, String localname);
}
