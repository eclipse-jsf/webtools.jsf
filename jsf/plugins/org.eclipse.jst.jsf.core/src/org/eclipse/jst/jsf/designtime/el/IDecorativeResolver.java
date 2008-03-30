/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 * 
 ********************************************************************************/

package org.eclipse.jst.jsf.designtime.el;

/**
 * Tagging interface that must be implemented by all resolvers that want to participate
 * in result decoration (i.e. variable and property resolvers) via the 
 * forRuntimeClass extension.
 * 
 * Clients may implement.
 * 
 * @author cbateman
 *
 */
public interface IDecorativeResolver
{
    // purposefully empty
}
