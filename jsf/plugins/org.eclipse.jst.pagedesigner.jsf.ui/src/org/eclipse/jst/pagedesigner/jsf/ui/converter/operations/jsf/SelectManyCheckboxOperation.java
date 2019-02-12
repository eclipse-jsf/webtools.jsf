/*******************************************************************************
 * Copyright (c) 2005, 2008 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.jsf;

/**
 * ITransformOperation implementation specifically for the "selectManyCheckbox"
 * JSF (HTML) Element. 
 * 
 * <br><b>Note:</b> requires ITransformOperation.setTagConverterContext(...) to
 * have been called to provide a valid ITagConverterContext instance prior to
 * a call to the transform(...) method.
 * 
 * @author Ian Trimble - Oracle
 */
public class SelectManyCheckboxOperation extends TableBasedOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.jsf.ui.converter.operations.jsf.TableBasedOperation#getInputType()
	 */
	protected String getInputType() {
		return "checkbox"; //$NON-NLS-1$
	}

}
