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

public @interface BugRegressionTest
{
    // can be used to specify what bug system the bug was reported in.
    // default == Eclipse
    String bugSystem() default "Eclipse";

    /**
     * @return the bug number that that annotated test method covers
     * regression testing for.
     */
    long bugNumber();
}
