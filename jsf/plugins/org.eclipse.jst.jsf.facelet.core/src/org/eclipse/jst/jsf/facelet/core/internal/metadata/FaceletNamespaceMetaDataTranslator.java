/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.metadata;

import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.Namespace;
import org.eclipse.jst.jsf.core.metadata.internal.NamespaceMetaDataTranslator;

/**
 * Translates Facelet {@link Namespace} to standard {@link Entity} and {@link Trait} metadata
 *
 */
public class FaceletNamespaceMetaDataTranslator extends NamespaceMetaDataTranslator {
	private static final String COMPOSITE = "http://java.sun.com/jsf/composite"; //$NON-NLS-1$
	
	@Override
	protected String getNamespaceDisplayLabel() {
		if (getMergedModel().getId().indexOf(COMPOSITE) == 0 
				&& getMergedModel().getId().length() > COMPOSITE.length())
			return getMergedModel().getId().substring(COMPOSITE.length()+1);
		return super.getNamespaceDisplayLabel();
	}

}
