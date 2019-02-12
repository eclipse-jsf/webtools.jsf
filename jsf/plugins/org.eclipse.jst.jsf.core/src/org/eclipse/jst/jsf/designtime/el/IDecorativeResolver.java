/*******************************************************************************
 * Copyright (c) 2008 Oracle Corporation.
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
