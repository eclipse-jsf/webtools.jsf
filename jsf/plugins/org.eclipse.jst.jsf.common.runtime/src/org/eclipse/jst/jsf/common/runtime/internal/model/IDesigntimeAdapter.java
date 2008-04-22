package org.eclipse.jst.jsf.common.runtime.internal.model;

/**
 * Implemented by design time adapters that represent runtime interfaces.
 * 
 * @author cbateman
 *
 */
public interface IDesigntimeAdapter
{
    /**
     * @return the list of runtime interfaces that this runtime adapter 
     * represents.
     */
    String[]  getInterfaces();
}
