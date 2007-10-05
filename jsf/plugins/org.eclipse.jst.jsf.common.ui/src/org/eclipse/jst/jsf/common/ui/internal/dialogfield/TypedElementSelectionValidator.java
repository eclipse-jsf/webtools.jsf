/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.common.ui.internal.dialogfield;

import java.util.Collection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.ui.dialogs.ISelectionStatusValidator;

/**
 * Implementation of a <code>ISelectionValidator</code> to validate the type
 * of an element. Empty selections are not accepted.
 * 
 * @author mengbo
 * @version 1.5
 */
/*package*/ class TypedElementSelectionValidator implements
		ISelectionStatusValidator {

	private IStatus _fgErrorStatus = new StatusInfo(IStatus.ERROR, ""); //$NON-NLS-1$

	private IStatus _fgOKStatus = new StatusInfo();

	private Class[] _fAcceptedTypes;

	private boolean _fAllowMultipleSelection;

	private Collection _fRejectedElements;

	/**
	 * @param acceptedTypes
	 *            The types accepted by the validator
	 * @param allowMultipleSelection
	 *            If set to <code>true</code>, the validator allows multiple
	 *            selection.
	 */
	public TypedElementSelectionValidator(Class[] acceptedTypes,
			boolean allowMultipleSelection) {
		this(acceptedTypes, allowMultipleSelection, null);
	}

	/**
	 * @param acceptedTypes
	 *            The types accepted by the validator
	 * @param allowMultipleSelection
	 *            If set to <code>true</code>, the validator allows multiple
	 *            selection.
	 * @param rejectedElements
	 *            A list of elements that are not accepted
	 */
	public TypedElementSelectionValidator(Class[] acceptedTypes,
			boolean allowMultipleSelection, Collection rejectedElements) {
		Assert.isNotNull(acceptedTypes);
		_fAcceptedTypes = acceptedTypes;
		_fAllowMultipleSelection = allowMultipleSelection;
		_fRejectedElements = rejectedElements;
	}

	/*
	 * @see org.eclipse.ui.dialogs.ISelectionValidator#isValid(java.lang.Object)
	 */
	public IStatus validate(Object[] elements) {
		if (isValid(elements)) {
			return _fgOKStatus;
		}
		return _fgErrorStatus;
	}

	private boolean isOfAcceptedType(Object o) {
		for (int i = 0; i < _fAcceptedTypes.length; i++) {
			if (_fAcceptedTypes[i].isInstance(o)) {
				return true;
			}
		}
		return false;
	}

	private boolean isRejectedElement(Object elem) {
		return (_fRejectedElements != null)
				&& _fRejectedElements.contains(elem);
	}

	private boolean isValid(Object[] selection) {
		if (selection.length == 0) {
			return false;
		}

		if (!_fAllowMultipleSelection && selection.length != 1) {
			return false;
		}

		for (int i = 0; i < selection.length; i++) {
			Object o = selection[i];
			if (!isOfAcceptedType(o) || isRejectedElement(o)) {
				return false;
			}
		}
		return true;
	}
}
