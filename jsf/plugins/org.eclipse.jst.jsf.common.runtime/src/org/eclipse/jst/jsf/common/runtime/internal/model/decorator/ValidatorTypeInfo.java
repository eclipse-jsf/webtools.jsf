package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.types.ClassTypeInfo;

/**
 * Type information about validator decorator.
 * @author cbateman
 *
 */
public class ValidatorTypeInfo extends ClassTypeInfo
{
    /**
     * 
     */
    private static final long serialVersionUID = 7512992316792276160L;
    private final String    _validatorId;

    /**
     * For unknown validators, use the UNKNOWN constant.
     * 
     * @param validatorClass
     * @param validatorId
     * @throws java.lang.IllegalArgumentException if both className and
     * converterId are null.
     * 
     */
    public ValidatorTypeInfo(String validatorClass, String validatorId)
    {
        super(validatorClass, new String[0], new String[0]);
        
        if (validatorClass == null && validatorId == null)
        {
            throw new IllegalArgumentException("validatorClass and validatorId must not both be null.  For unknown validator use the UNKNOWN constant"); //$NON-NLS-1$
        }
        _validatorId = validatorId;
    }

    /**
     * For unknown validators, use the UNKNOWN constant.
     * 
     * @param validatorClass
     * @param superClasses 
     * @param interfaces 
     * @param validatorId
     * @throws java.lang.IllegalArgumentException if both className and
     * converterId are null.
     * 
     */
    public ValidatorTypeInfo(String validatorClass, String[] superClasses, String[] interfaces, String validatorId)
    {
        super(validatorClass, superClasses, interfaces);
        
        if (validatorClass == null && validatorId == null)
        {
            throw new IllegalArgumentException("validatorClass and validatorId must not both be null.  For unknown validator use the UNKNOWN constant"); //$NON-NLS-1$
        }
        _validatorId = validatorId;
    }

    /**
     * Use when a validator's type info information is unknown.
     */
    public static final ValidatorTypeInfo  UNKNOWN = 
        new ValidatorTypeInfo((Object)null);
    
    /**
     * A private constructor used to create the UNRESOLVED constant.
     * We use an Object arg here (which is discarded) rather than using
     * the zero-arg constructor so as not mess up anything like Serializable
     * that may depend on how zero-arg constructors are defined.
     * 
     * @param unresolved
     */
    private ValidatorTypeInfo(Object unresolved)
    {
        super(null, new String[0], new String[0]);
        _validatorId = null;
    }

    /**
     * @return the validator id (may be null if unknown).
     */
    public final String getValidatorId()
    {
        return _validatorId;
    }
    
    public String toString()
    {
        return "Validator Type Info: type = " + _validatorId + ", "+super.toString(); //$NON-NLS-1$ //$NON-NLS-2$
    }

}
