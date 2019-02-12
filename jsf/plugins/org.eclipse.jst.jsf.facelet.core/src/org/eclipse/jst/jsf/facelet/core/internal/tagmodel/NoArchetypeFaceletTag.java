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
package org.eclipse.jst.jsf.facelet.core.internal.tagmodel;

import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IAttributeAdvisor;
import org.eclipse.jst.jsf.facelet.core.internal.cm.FaceletDocumentFactory;


/**
 * A facelet tag with no information about it than its name
 * 
 * @author cbateman
 *
 */
public final class NoArchetypeFaceletTag extends FaceletTag {

    /**
     * 
     */
    private static final long serialVersionUID = 4810723162936027305L;

    /**
     * @param uri
     * @param name
     * @param factory
     * @param advisor
     */
    public NoArchetypeFaceletTag(final String uri, final String name, final FaceletDocumentFactory factory, 
            final IAttributeAdvisor advisor) {
        this(uri, name, null, factory, advisor);
    }

    /**
     * @param uri
     * @param name
     * @param handlerClassName
     * @param factory
     * @param advisor
     */
    public NoArchetypeFaceletTag(final String uri, final String name, final String handlerClassName, final FaceletDocumentFactory factory, 
            final IAttributeAdvisor advisor)
    {
        super(uri, name, TagType.HANDLER, handlerClassName, factory, advisor);
    }
}
