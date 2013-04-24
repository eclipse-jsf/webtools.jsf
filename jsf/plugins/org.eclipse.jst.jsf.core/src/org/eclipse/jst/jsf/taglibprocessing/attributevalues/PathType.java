/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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