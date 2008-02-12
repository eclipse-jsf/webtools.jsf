/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.ui.internal.validation;

import java.util.Locale;

import org.eclipse.core.resources.IResource;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.wst.validation.internal.core.Message;

/**
 * EL customized localizable validation message
 * @author cbateman
 *
 */
class MyLocalizedMessage extends Message
{
	private final String _message;
	private final int	 _errorCode;

	/**
	 * @param severity
	 * @param messageText
	 * @param targetObject
	 * @param errorCode 
	 */
	public MyLocalizedMessage(int severity, String messageText, IResource targetObject, int errorCode) {
		this(severity, messageText, (Object) targetObject, errorCode);
	}

	/**
	 * @param severity
	 * @param messageText
	 * @param targetObject
	 * @param errorCode 
	 */
	private MyLocalizedMessage(int severity, String messageText, Object targetObject, int errorCode) {
		super(JSFCorePlugin.getDefault().getBundle().getSymbolicName(), severity, 
                messageText);
		_message = messageText;
		setTargetObject(targetObject);
		_errorCode = errorCode;
	}

	/**
	 * @return the localized message
	 */
	public String getLocalizedMessage() {
		return _message;
	}

	/**
	 * @see org.eclipse.wst.validation.internal.core.Message#getText()
	 */
	public String getText() {
		return getLocalizedMessage();
	}

	/**
	 * @see org.eclipse.wst.validation.internal.core.Message#getText(java.lang.ClassLoader)
	 */
	public String getText(ClassLoader cl) {
		return getLocalizedMessage();
	}

	/**
	 * @see org.eclipse.wst.validation.internal.core.Message#getText(java.util.Locale)
	 */
	public String getText(Locale l) {
		return getLocalizedMessage();
	}

	public String getText(Locale l, ClassLoader cl) {
		return getLocalizedMessage();
	}

	/**
	 * @return the error code related to this message
	 */
	public int getErrorCode() {
		return _errorCode;
	}


	/**
	 * @param offset
	 * @return true if this message applies to document offset
	 */
	public boolean appliesTo(int offset)
	{
		return (offset >= getOffset() && offset < getOffset()+getLength());
	}
}
