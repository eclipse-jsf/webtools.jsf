/*******************************************************************************
 * Copyright (c) 2007, 2013 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidationMessage;

/**
 * EXPERIMENTAL - may change or dissappear
 *
 */
public abstract class PathType extends AbstractRootTypeDescriptor {

	private final List<IValidationMessage> _validationMsgs = new ArrayList<IValidationMessage>(1);

	/**
	 * Constructor
	 */
	public PathType() {
		super();
	}

	/**
	 * @return list of {@link IValidationMessage}
	 */
	public List<IValidationMessage> getValidationMessages() {
		return _validationMsgs;
	}

}