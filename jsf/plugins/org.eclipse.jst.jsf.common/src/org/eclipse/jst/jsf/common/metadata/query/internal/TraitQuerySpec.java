/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;


/**
 * Implements a query spec for querying traits matching using equality for the id 
 */
public class TraitQuerySpec implements IQuerySpec {

	private final String _traitId;
	/**
	 * @param traitId
	 */
	public TraitQuerySpec(final String traitId) {
		_traitId = traitId; 
	}
	public TraitKey getKey() {
		return null;
	}

	class TraitKey extends Key {

		public Object getAdapter(Class adapter) {
			// TODO Auto-generated method stub
			return null;
		}
		
		public String getId() {
			return _traitId;
		}
	}
}
