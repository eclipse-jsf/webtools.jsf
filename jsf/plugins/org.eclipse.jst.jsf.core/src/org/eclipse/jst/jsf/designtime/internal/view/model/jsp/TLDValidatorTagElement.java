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
    private final ValidatorTypeInfo _validator;
    
    /**
     * @param elementDecl
     * @param validatorTypeInfo 
     */
    public TLDValidatorTagElement(TLDElementDeclaration elementDecl, ValidatorTypeInfo validatorTypeInfo)
    {
        super(elementDecl);
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
