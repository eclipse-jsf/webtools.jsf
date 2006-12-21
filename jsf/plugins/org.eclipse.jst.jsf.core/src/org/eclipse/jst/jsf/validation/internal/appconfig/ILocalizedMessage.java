package org.eclipse.jst.jsf.validation.internal.appconfig;

/**
 * Used to expose internal message data to unit tests.  Should not be used by clients
 * @author cbateman
 *
 */
public interface ILocalizedMessage {
    /**
     * @return the error code
     */
    public int getErrorCode();
}
