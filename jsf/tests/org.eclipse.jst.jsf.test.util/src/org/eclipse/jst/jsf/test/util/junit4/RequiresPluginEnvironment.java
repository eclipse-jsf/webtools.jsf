/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.test.util.junit4;

/**
 * Marks a JUnit4 test category that must be run using the PDE Plugin Test JUnit
 * Runner and the plugin env that it bootstraps.  These tests cannot accept
 * Workspace mocks.
 * 
 * @author cbateman
 *
 */
public interface RequiresPluginEnvironment extends SlowTest
{

}
