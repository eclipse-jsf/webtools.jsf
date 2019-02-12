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
package org.eclipse.jst.jsf.core.metadata.internal;

import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;

/**
 * Provider of {@link Namespace} metadata
 *
 */
public interface INamespaceModelProvider extends IMetaDataSourceModelProvider {
	/**
	 * @return the namespace provided.   May be null.
	 */
	public Namespace getNamespace();
}
