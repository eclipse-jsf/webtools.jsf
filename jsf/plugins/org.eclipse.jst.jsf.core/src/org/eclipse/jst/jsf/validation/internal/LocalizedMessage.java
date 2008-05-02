/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package org.eclipse.jst.jsf.validation.internal;

import java.util.Locale;

import org.eclipse.core.resources.IResource;
import org.eclipse.wst.validation.internal.core.Message;

/**
 * Localized version of the message.
 * 
 * @author cbateman
 *
 */
public class LocalizedMessage extends Message {

    private String _message = null;

    /**
     * @param severity
     * @param messageText
     */
    public LocalizedMessage(int severity, String messageText) {
        this(severity, messageText, null);
    }

    /**
     * @param severity
     * @param messageText
     * @param targetObject
     */
    public LocalizedMessage(int severity, String messageText, IResource targetObject) {
        this(severity, messageText, (Object) targetObject);
    }

    /**
     * @param severity
     * @param messageText
     * @param targetObject
     */
    public LocalizedMessage(int severity, String messageText, Object targetObject) {
        super(null, severity, null);
        setLocalizedMessage(messageText);
        setTargetObject(targetObject);
    }

    /**
     * @param message
     */
    public void setLocalizedMessage(String message) {
        _message = message;
    }

    /**
     * @return the message
     */
    public String getLocalizedMessage() {
        return _message;
    }

    public String getText() {
        return getLocalizedMessage();
    }

    public String getText(ClassLoader cl) {
        return getLocalizedMessage();
    }

    public String getText(Locale l) {
        return getLocalizedMessage();
    }

    public String getText(Locale l, ClassLoader cl) {
        return getLocalizedMessage();
    }
}