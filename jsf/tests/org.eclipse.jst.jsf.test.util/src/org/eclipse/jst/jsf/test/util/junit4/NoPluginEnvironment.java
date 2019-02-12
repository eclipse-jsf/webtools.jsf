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
 * @author cbateman
 * 
 * This class should never be used directly.  It is used to mark JUnit test
 * classes that don't require the Eclipse plugin environment to run.
 *
 */
public interface NoPluginEnvironment extends FastTest
{
    // do nothing
}
