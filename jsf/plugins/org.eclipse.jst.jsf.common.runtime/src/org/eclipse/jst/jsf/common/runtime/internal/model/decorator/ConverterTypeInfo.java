package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.types.IClassTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.types.TypeInfo;

/**
 * Type information about a converter.
 * 
 * @author cbateman
 *
 */
public class ConverterTypeInfo extends TypeInfo implements IClassTypeInfo
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = -7238952424045449907L;

    private final String    _className;
    private final String    _converterId;

    /**
     * For unknown converters, use the UNKNOWN constant.
     * 
     * @param className
     * @param converterId
     * @throws java.lang.IllegalArgumentException if both className and
     * converterId are null.
     * 
     */
    public ConverterTypeInfo(String className, String converterId)
    {
        super();
        if (className == null && converterId == null)
        {
            throw new IllegalArgumentException("converterClass and converterId must not both be null.  For unknown validator use the UNKNOWN constant");
        }

        _className = className;
        _converterId = converterId;
    }
   
    /**
     * Use when the converter's information unknown such as occurs when
     * using the f:converter tag.
     */
    public static final ConverterTypeInfo  UNKNOWN = 
        new ConverterTypeInfo((Object)null);
    
    /**
     * A private constructor used to create the UNRESOLVED constant.
     * We use an Object arg here (which is discarded) rather than using
     * the zero-arg constructor so as not mess up anything like Serializable
     * that may depend on how zero-arg constructors are defined.
     * 
     * @param unresolved
     */
    private ConverterTypeInfo(Object unresolved)
    {
        _className = _converterId = null;
    }

    public String getClassName()
    {
        return _className;
    }


    /**
     * @return the converter's id.
     */
    public final String getConverterId()
    {
        return _converterId;
    }
}
