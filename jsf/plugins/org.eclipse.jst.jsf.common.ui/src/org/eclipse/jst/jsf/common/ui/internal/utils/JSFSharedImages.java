/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.common.ui.internal.utils;

/**
 * Constants that can be passed to JSFUICommonPlugin.getImageDescriptor and
 * getImage to obtain common images.
 * 
 * @author cbateman
 *
 */
public interface JSFSharedImages
{
    /**
     * The icon use for default/unknown tags.  Consists of blue open/close
     * angled braces on a neutral background. 
     * 
     * Size: 16x16
     */
    public final static String DEFAULT_PALETTE_TAG_IMG = "PD_Palette_Default.gif"; //$NON-NLS-1$
    
    /**
     * A yellow cube on a white background.  Suitable for representing 
     * generic objects and components.
     * 
     * Size: 16x16
     */
    public final static String GENERIC_OBJECT_IMG = "object.gif"; //$NON-NLS-1$
    
    /**
     * A generic image suitable for representing JSF validators.  Consists of
     * a white box with a blue check mark.
     * 
     * Size: 16x16
     */
    public final static String GENERIC_VALIDATOR_IMG = "jsf_validator.gif"; //$NON-NLS-1$

    /**
     * A generic image suitable for representing JSF converter.  Consists of
     * yellow blob being "converted".
     * 
     * Size: 16x16
     */
    public final static String GENERIC_CONVERTER_IMG = "jsf_converter.gif"; //$NON-NLS-1$

    /**
     * A generic image suitable for representing a view root.  Same as the
     * default image generally associated with f:view.
     * 
     * Size: 16x16.
     */
    public static final String GENERIC_VIEWROOT_IMG = "jsf_view.gif"; //$NON-NLS-1$

}
