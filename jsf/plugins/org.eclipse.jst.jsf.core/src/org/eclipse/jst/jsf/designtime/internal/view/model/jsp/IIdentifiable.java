package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

/**
 * An object with an unique identity
 * @author cbateman
 * @param <IDTYPE> 
 *
 */
public interface IIdentifiable<IDTYPE>
{
    /**
     * @return the unique identifier
     */
    IDTYPE getId();
}
