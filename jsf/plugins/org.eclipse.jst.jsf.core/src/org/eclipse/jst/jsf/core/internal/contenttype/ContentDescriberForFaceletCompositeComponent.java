/*******************************************************************************
 * Copyright (c) 2011 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.contenttype;

import java.util.regex.Pattern;

/**
 * Content Describer for a "facelet composite component" (document element has an xmlns attribute
 * with a value that matches "http://java.sun.com/jsf/composite").
 * 
 * @author ian.trimble@oracle.com
 */
public class ContentDescriberForFaceletCompositeComponent extends AbstractContentDescriberForFacelets {

	@Override
	protected Pattern[] getNSValuePatterns() {
		return new Pattern[] {
			Pattern.compile("http://java.sun.com/jsf/composite") //$NON-NLS-1$
		};
	}

}
