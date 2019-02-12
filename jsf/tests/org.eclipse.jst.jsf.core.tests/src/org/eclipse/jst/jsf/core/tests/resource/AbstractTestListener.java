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
package org.eclipse.jst.jsf.core.tests.resource;

import java.util.EventObject;

import org.eclipse.jst.jsf.common.internal.resource.EventResult;
import org.eclipse.jst.jsf.common.internal.resource.ILifecycleListener;

abstract class AbstractTestListener<EVENTTYPE extends EventObject> implements ILifecycleListener<EVENTTYPE>
{

    public abstract EventResult acceptEvent(final EVENTTYPE event);
}
