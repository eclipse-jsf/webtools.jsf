/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.actions.link;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jst.pagedesigner.viewer.DesignRange;

/**
 * @author mengbo
 * @version 1.5
 */
public abstract class AbstractLinkCreator implements ILinkCreator,
		IExecutableExtension {
	private String _identifier;
    /**
     * the link identifier attribute name
     */
    private static final String LINK_IDENTIFIER = "linkIdentifier"; //$NON-NLS-1$

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.actions.link.ILinkCreator#canExcute(org.eclipse.jst.pagedesigner.viewer.DesignRange)
	 */
	public boolean canExecute(DesignRange range) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.actions.link.ILinkCreator#getLinkIdentifier()
	 */
	public String getLinkIdentifier() {
		return this._identifier;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.runtime.IExecutableExtension#setInitializationData(org.eclipse.core.runtime.IConfigurationElement,
	 *      java.lang.String, java.lang.Object)
	 */
	public void setInitializationData(IConfigurationElement config,
			String propertyName, Object data) throws CoreException {
		this._identifier = config.getAttribute(LINK_IDENTIFIER);
	}
}
