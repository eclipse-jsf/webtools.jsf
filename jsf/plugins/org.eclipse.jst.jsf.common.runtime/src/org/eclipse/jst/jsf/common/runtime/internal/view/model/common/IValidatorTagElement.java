package org.eclipse.jst.jsf.common.runtime.internal.view.model.common;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;

/**
 * Represents a tag element for a JSF validator.
 * 
 * @author cbateman
 *
 */
public interface IValidatorTagElement extends IJSFTagElement
{
    /**
     * @return the type info about the validator.
     */
    ValidatorTypeInfo getValidator();
}
