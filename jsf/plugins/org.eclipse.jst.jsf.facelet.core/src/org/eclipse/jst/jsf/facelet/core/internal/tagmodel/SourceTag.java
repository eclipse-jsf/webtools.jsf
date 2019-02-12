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
 * A facet "source" tag as defined in the dtd.
 * 
 * @author cbateman
 *
 */
public class SourceTag extends FaceletTag
{
    /**
     * 
     */
    private static final long serialVersionUID = 4648054050352065079L;
    private final String _source;

    /**
     * @param uri
     * @param name
     * @param source
     * @param factory
     * @param advisor
     */
    public SourceTag(final String uri, final String name, final String source, final FaceletDocumentFactory factory, 
            final IAttributeAdvisor advisor) {
        super(uri, name, TagType.HANDLER, null, factory, advisor);
        _source = source;
    }

    /**
     * @return the source
     */
    public final String getSource() {
        return _source;
    }
}