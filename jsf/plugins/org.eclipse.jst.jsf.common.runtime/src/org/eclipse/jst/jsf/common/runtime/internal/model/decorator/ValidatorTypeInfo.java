package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.types.IClassTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.TypeInfo;

/**
 * Type information about validator decorator.
 * @author cbateman
 *
 */
public class ValidatorTypeInfo extends TypeInfo implements IClassTypeInfo
{
    /**
     * 
     */
    private static final long serialVersionUID = 7512992316792276160L;
    private final String    _validatorId;
    private final String    _className;


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
        super();
        
        if (validatorClass == null && validatorId == null)
        {
            throw new IllegalArgumentException("validatorClass and validatorId must not both be null.  For unknown validator use the UNKNOWN constant");
        }
        _className = validatorClass;
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
        _className = _validatorId = null;
    }

    /**
     * @return the validator id (may be null if unknown).
     */
    public final String getValidatorId()
    {
        return _validatorId;
    }

    public String getClassName()
    {
        return _className;
    }

}
