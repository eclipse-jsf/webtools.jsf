package org.eclipse.jst.jsf.common.internal.strategy;

import org.eclipse.jst.jsf.common.internal.policy.IIdentifiable;


/**
 * A simple strategy that is identifiable
 * 
 * @author cbateman
 *
 * @param <INPUT>
 * @param <OUTPUT>
 * @param <IDTYPE>
 */
public interface IIdentifiableStrategy<INPUT, OUTPUT, IDTYPE> 
    extends ISimpleStrategy<INPUT, OUTPUT>, IIdentifiable<IDTYPE>
{
    // nothing added.
}
