package org.eclipse.jst.jsf.core.tagmatcher;

/**
 * Wraps an exception thrown during algorithm evaluation
 * @author cbateman
 *
 */
public class EvaluationException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = -3250608181004283586L;

    /**
     * 
     */
    public EvaluationException() {
        super();
    }

    /**
     * @param message
     */
    public EvaluationException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public EvaluationException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public EvaluationException(String message, Throwable cause) {
        super(message, cause);
    }

}
