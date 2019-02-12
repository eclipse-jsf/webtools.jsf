/*******************************************************************************
 * Copyright (c) 2011, 2015 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.contenttype;

import java.util.regex.Pattern;

/**
 * Content Describer for a "facelet composite component" (document element has an xmlns attribute
 * with a value that matches "http://java.sun.com/jsf/composite" or
 * "http://xmlns.jcp.org/jsf/composite").
 * 
 * @author ian.trimble@oracle.com
 */
public class ContentDescriberForFaceletCompositeComponent extends AbstractContentDescriberForFacelets {

	@Override
	protected Pattern[] getNSValuePatterns() {
		return new Pattern[] {
			Pattern.compile("http://java.sun.com/jsf/composite"), //$NON-NLS-1$
			Pattern.compile("http://xmlns.jcp.org/jsf/composite") //$NON-NLS-1$
		};
	}

}
