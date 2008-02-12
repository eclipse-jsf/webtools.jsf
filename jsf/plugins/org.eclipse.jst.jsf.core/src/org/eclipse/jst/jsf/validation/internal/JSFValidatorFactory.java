package org.eclipse.jst.jsf.validation.internal;

/**
 * A factory that is to construct JSF validators
 * 
 * @author cbateman
 *
 */
public class JSFValidatorFactory
{
    /**
     * @return a default validator for XML-defined views.
     */
    public static IJSFViewValidator createDefaultXMLValidator()
    {
        return new XMLViewDefnValidator();
    }
}
