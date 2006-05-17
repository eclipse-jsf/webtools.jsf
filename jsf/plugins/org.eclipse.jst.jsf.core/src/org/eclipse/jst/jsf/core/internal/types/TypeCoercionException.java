package org.eclipse.jst.jsf.core.internal.types;

/**
 * @author cbateman
 *
 */
public class TypeCoercionException extends Exception 
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * @see java.lang.Exception
     */
    public TypeCoercionException() {
        super();
    }

    /**
     * @param message
     * @param cause
     * @see java.lang.Exception
     */
    public TypeCoercionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     * @see java.lang.Exception
     */
    public TypeCoercionException(String message) {
        super(message);
    }

    /**
     * @param cause
     * @see java.lang.Exception
     */
    public TypeCoercionException(Throwable cause) {
        super(cause);
    }

}
