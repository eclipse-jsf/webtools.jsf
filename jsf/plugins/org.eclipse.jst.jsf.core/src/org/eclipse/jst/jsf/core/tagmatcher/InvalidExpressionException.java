package org.eclipse.jst.jsf.core.tagmatcher;

/**
 * Indicates an exception trapped will compiling or evaluating an
 * expression.
 * 
 * @author cbateman
 *
 */
public class InvalidExpressionException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = 1445871263234840885L;

    /**
     * @see RuntimeException
     */
    public InvalidExpressionException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message
     */
    public InvalidExpressionException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public InvalidExpressionException(Throwable cause) {
        super(cause);
    }
    
    
    
    
}
