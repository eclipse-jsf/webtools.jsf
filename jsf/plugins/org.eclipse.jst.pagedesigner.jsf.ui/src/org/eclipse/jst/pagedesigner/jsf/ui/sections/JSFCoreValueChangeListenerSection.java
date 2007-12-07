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

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.pagedesigner.properties.attrgroup.OLDAttributeGroupSection;

/**
 * @author mengbo
 * @deprecated
 */
public class JSFCoreValueChangeListenerSection extends OLDAttributeGroupSection
{
    /**
     * The default constructor
     */
    public JSFCoreValueChangeListenerSection()
    {
        super(ITLDConstants.URI_JSF_CORE, IJSFConstants.TAG_VALUECHANGELISTENER, 
                new String[] {
                	IJSFConstants.ATTR_TYPE
                });
    }
}
