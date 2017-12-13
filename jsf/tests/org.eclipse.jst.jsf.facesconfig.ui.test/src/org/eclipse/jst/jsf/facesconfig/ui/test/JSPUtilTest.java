/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.facesconfig.ui.test;

import java.util.List;

import org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.JSPUtil;
import org.eclipse.jst.jsf.facesconfig.ui.test.util.TestUtil;

/**
 * @author sfshi
 *
 */
public class JSPUtilTest extends FacesConfigEditorTest {

	/**
	 * Test method for {@link org.eclipse.jst.jsf.facesconfig.ui.pageflow.util.JSPUtil#getActionListInJSPFile(java.lang.String)}.
	 * @throws Exception 
	 */
	public void testGetActionListInJSPFile() throws Exception {
		TestUtil.copyFile(project, "WebContent", "page1.jsp");
		
		List<?> actionNodes = JSPUtil.getActionListInJSPFile("/emptyjsfproject/WebContent/page1.jsp");
		assertEquals(3, actionNodes.size());
	}

}
