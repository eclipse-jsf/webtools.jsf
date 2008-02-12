package org.eclipse.jst.jsf.common.runtime.internal.model.decorator;

import org.eclipse.jst.jsf.common.runtime.internal.model.types.ClassTypeInfo;

/**
 * Type information about a converter.
 * 
 * @author cbateman
 * 
 */
public class ConverterTypeInfo extends ClassTypeInfo
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = -7238952424045449907L;

    private final String _converterId;

    /**
     * For unknown converters, use the UNKNOWN constant.
     * 
     * @param className
     * @param converterId
     * @throws java.lang.IllegalArgumentException
     *             if both className and converterId are null.
     * 
     */
    public ConverterTypeInfo(String className, String converterId)
    {
        super(className, new String[0], new String[0]);
        if (className == null && converterId == null)
        {
            throw new IllegalArgumentException(
                    "converterClass and converterId must not both be null.  For unknown validator use the UNKNOWN constant");
        }

        _converterId = converterId;
    }

    /**
     * For unknown converters, use the UNKNOWN constant.
     * 
     * @param className
     * @param superClasses 
     * @param interfaces 
     * @param converterId
     * @throws java.lang.IllegalArgumentException
     *             if both className and converterId are null.
     * 
     */
    public ConverterTypeInfo(String className, String[] superClasses,
            String[] interfaces, String converterId)
    {
        super(className, superClasses, interfaces);
        if (className == null && converterId == null)
        {
            throw new IllegalArgumentException(
                    "converterClass and converterId must not both be null.  For unknown validator use the UNKNOWN constant");
        }

        _converterId = converterId;
    }

    /**
     * Use when the converter's information unknown such as occurs when using
     * the f:converter tag.
     */
    public static final ConverterTypeInfo UNKNOWN = new ConverterTypeInfo(
            (Object) null);

    /**
     * A private constructor used to create the UNRESOLVED constant. We use an
     * Object arg here (which is discarded) rather than using the zero-arg
     * constructor so as not mess up anything like Serializable that may depend
     * on how zero-arg constructors are defined.
     * 
     * @param unresolved
     */
    private ConverterTypeInfo(Object unresolved)
    {
        super(null, new String[0], new String[0]);
        _converterId = null;
    }

    /**
     * @return the converter's id.
     */
    public final String getConverterId()
    {
        return _converterId;
    }

    public String toString()
    {
        return "Converter Type Info: type = " + _converterId + ", "+super.toString();
    }
}
