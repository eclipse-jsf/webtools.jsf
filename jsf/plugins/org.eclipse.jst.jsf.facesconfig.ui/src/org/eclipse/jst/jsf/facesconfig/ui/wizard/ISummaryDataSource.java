/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.wizard;

import java.util.List;

/**
 * Specifies behavior for a datasource that client wizards use to communicate
 * summary information to summary page.
 * 
 * @author plevin
 * @version 1.0
 */
public interface ISummaryDataSource {
	/**
	 * Returns key-value summary data.
	 * 
	 * @return List - Summary data.
	 */
	public List getSummaryData();
}
