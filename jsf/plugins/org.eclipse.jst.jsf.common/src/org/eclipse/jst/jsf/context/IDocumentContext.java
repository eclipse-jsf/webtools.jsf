/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.context;

/**
 * Encapsulates runtime context in a JFace IDocument model
 * 
 * Clients may NOT implement this interface directly but may sub-class
 * Extend AbstractDocumentContext instead.

 * @author cbateman
 *
 */
public interface IDocumentContext extends IModelContext 
{
	// TODO: might make sense to have a getDocument() accessor here
	// currently empty
}
