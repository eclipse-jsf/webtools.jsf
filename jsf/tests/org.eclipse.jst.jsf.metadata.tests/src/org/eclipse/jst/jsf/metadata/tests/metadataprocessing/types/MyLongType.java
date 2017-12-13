/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.metadataprocessing.types;

import org.eclipse.jst.jsf.taglibprocessing.attributevalues.LongType;

/**
 * Subclass of boolean type used by tests to prove feature extensions
 *
 */
public class MyLongType extends LongType {

	public boolean isValidValue(String value) {
		System.out.println("MyLongType: isValidValue(value)");
		return super.isValidValue(value);
	}

}
