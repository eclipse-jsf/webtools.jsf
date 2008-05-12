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
package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.IValidatorTagElement;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

/**
 * A TLD-defined tag (i.e. JSP) that maps one-to-one with a JSF Converter
 * 
 * @author cbateman
 *
 */
public class TLDValidatorTagElement extends TLDJSFTagElement implements IValidatorTagElement
{
    /**
     * 
     */
    private static final long serialVersionUID = 4251223772554969477L;
    private final ValidatorTypeInfo _validator;

    /**
     * @param elementDecl
     * @param validatorTypeInfo
     * @param advisor 
     */
    public TLDValidatorTagElement(final TLDElementDeclaration elementDecl, final ValidatorTypeInfo validatorTypeInfo, final IAttributeAdvisor advisor)
    {
        super(elementDecl, advisor);
        _validator = validatorTypeInfo;
    }

    public final ValidatorTypeInfo getValidator()
    {
        return _validator;
    }

    @Override
    public final TagType getType()
    {
        return TagType.VALIDATOR;
    }

    @Override
    public String toString()
    {
        return _validator.toString();
    }
}
