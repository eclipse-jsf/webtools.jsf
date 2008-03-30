package org.eclipse.jst.jsf.context.symbol.source;


/**
 * Passed to AbstractContextSymbolFactory's to provide additional information.
 * All information is optional and need not be used if not needed.
 * 
 * Clients may use but NOT IMPLEMENT. New methods may be added without notice.
 * 
 * @author cbateman
 * @since 3.0
 *
 */
public interface IAdditionalContextSymbolInfo
{
    /**
     * @return a suggested symbol type signature.  May be null.
     */
    String getSymbolTypeSignature();

    /**
     * @return the name of the attribute on the current element context that
     * contains a value expression that may be used to find type information.
     * May be null.
     * 
     */
    String getValueExpressionAttributeName();
}