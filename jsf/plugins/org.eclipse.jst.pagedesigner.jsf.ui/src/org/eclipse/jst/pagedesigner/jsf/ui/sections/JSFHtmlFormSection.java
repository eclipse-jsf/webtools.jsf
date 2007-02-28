/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http:// www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.sections;

import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.IJSFConstants;
import org.eclipse.jst.pagedesigner.properties.attrgroup.AttributeGroupSection;

/**
 * @author mengbo
 */
public class JSFHtmlFormSection extends AttributeGroupSection
{
    public JSFHtmlFormSection()
    {
        super(ITLDConstants.URI_JSF_HTML, IJSFConstants.TAG_FORM, 
                new String[] {
                	IJSFConstants.ATTR_ID,
                	IJSFConstants.ATTR_STYLE
                });
    }
}
