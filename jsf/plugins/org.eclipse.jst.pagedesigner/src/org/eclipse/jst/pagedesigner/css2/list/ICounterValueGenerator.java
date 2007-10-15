/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.css2.list;

/**
 * @author mengbo
 */
public interface ICounterValueGenerator {
	/**
	 * @return the identifier
	 */
	public String getIdentifier();

	/**
	 * @param initial
	 * @return the reset count
	 */
	public ICounterValueGenerator resetCount(int initial);

	/**
	 * @return the generator
	 */
	public ICounterValueGenerator resetCount();

	/**
	 * Set the counter based on value.
	 * @param value 
	 */
	public void setCount(Integer value);

	// public void regist(Object caller);
	// public void unregist(Object caller);

	/**
	 * Increase counter based on declared increment number
	 * @param increment 
	 */
	public void increase(int increment);

	/**
	 * Increase counter with default increment number(1)
	 */
	public void increase();

	/**
	 * @param caller
	 * @return the count
	 */
	public Integer getCount(Object caller);

	/**
	 * @return the current count
	 */
	public int getCurrentCount();
}
