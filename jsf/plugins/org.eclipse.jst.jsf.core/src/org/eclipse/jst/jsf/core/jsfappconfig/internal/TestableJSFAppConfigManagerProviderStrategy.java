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
package org.eclipse.jst.jsf.core.jsfappconfig.internal;

import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.jst.jsf.common.internal.strategy.TestableProjectFactoryStrategy;

/**
 * Injectable test factory provider strategy
 * @param <IJSFAppConfigManagerFactory> 
 *
 */
public class TestableJSFAppConfigManagerProviderStrategy<IJSFAppConfigManagerFactory>
	extends TestableProjectFactoryStrategy<IJSFAppConfigManagerFactory> {

	/**
	 * @param testableFactorySessionKey - project property session key for property value holding testable instance 
	 */
	public TestableJSFAppConfigManagerProviderStrategy(final QualifiedName testableFactorySessionKey) {
		super(testableFactorySessionKey);
	}	

}
