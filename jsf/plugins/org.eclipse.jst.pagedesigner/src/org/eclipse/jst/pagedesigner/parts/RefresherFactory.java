/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.parts;

import org.eclipse.wst.sse.core.internal.provisional.AbstractAdapterFactory;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;

/**
 * @author mengbo
 * @version 1.5
 */
public class RefresherFactory extends AbstractAdapterFactory {
	private final Refresher refresher = new Refresher();

	RefresherFactory() {
		super(Refresher.class, true);
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.eclipse.wst.sse.core.internal.provisional.AbstractAdapterFactory#createAdapter(org.eclipse.wst.sse.core.internal.provisional.INodeNotifier)
	 */
	protected INodeAdapter createAdapter(INodeNotifier target) {
		return refresher;
	}

	static RefresherFactory _instance = new RefresherFactory();

	/**
	 * @return the factory singleton
	 */
	public static RefresherFactory getInstance() {
		return _instance;
	}
}
