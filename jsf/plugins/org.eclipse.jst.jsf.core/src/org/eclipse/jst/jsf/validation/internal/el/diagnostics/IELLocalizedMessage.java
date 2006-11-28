package org.eclipse.jst.jsf.validation.internal.el.diagnostics;

/**
 * Allows certain elements of the message to be made public without exposing
 * the whole Message class
 * 
 * @author cbateman
 *
 */
public interface IELLocalizedMessage 
{
    /**
     * @return the unique error code for the message
     */
    public int getErrorCode();
    
    /**
     * @return the absolute offset where the problem starts
     */
    public int getOffset();
    
    /**
     * @return the number of characters starting from getOffset()
     * where the mark stretches
     */
    public int getLength();
}
