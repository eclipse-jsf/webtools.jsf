/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
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
