/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;

/**
 * Thrown when query specification does not meet the criteria for the domain or the query
 *
 */
public class InvalidQuerySpecificationException extends Exception {
	/**
	 * serializaion id
	 */
	private static final long serialVersionUID = -2835106570357619405L;

	/**
	 * @param message
	 */
	public InvalidQuerySpecificationException(final String message) {
		super(message);
	}
}
