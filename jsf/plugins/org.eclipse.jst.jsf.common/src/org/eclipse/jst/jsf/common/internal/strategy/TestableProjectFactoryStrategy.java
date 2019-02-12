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
package org.eclipse.jst.jsf.common.internal.strategy;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.QualifiedName;


/**
 * Abstract class that, when given a project session key, 
 * will provide the instance of OUTPUT to use, or no result
 * <p>
 * Users need only set the project session property with the key and OUTPUT instance
 * @param <OUTPUT>
 */
public abstract class TestableProjectFactoryStrategy<OUTPUT> implements ISimpleStrategy<IProject, OUTPUT> {
	private QualifiedName _key;

	/**
	 * @param testableFactorySessionKey - project property session key for property value holding testable instance 
	 */
	public TestableProjectFactoryStrategy(final QualifiedName testableFactorySessionKey) {
		_key = testableFactorySessionKey;
	}
	
	public OUTPUT perform(final IProject project) throws Exception {
		if (_key != null && project != null) {
			final Object factory = project.getSessionProperties().get(_key);
			if (factory != null)
				return (OUTPUT)factory;
		}			
		return getNoResult();
	}

	public OUTPUT getNoResult() {
		return null;
	}
		
}
