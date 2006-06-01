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

import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.ui.util.ManagedBeanUtil;

/**
 * @author sfshi
 * 
 */
public class ManagedBeanUtilTest extends FacesConfigEditorTest {

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.facesconfig.ui.util.ManagedBeanUtil#isBeanDuplicate(org.eclipse.core.resources.IProject, java.lang.String)}.
	 */
	public void testIsBeanDuplicate() {
		String mbeanName = "testBean";
		assertFalse(ManagedBeanUtil.isBeanDuplicate(project, mbeanName));

		FacesConfigType facesConfig = editor.getFacesConfig();
		ManagedBeanType bean1 = FacesConfigFactory.eINSTANCE
				.createManagedBeanType();
		ManagedBeanNameType bean1Name = FacesConfigFactory.eINSTANCE
				.createManagedBeanNameType();
		bean1Name.setTextContent(mbeanName);
		bean1.setManagedBeanName(bean1Name);
		facesConfig.getManagedBean().add(bean1);

		assertTrue(ManagedBeanUtil.isBeanDuplicate(project, mbeanName));
	}

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.facesconfig.ui.util.ManagedBeanUtil#getDefaultManagedBeanName(org.eclipse.core.resources.IProject, java.lang.String)}.
	 */
	public void testGetDefaultManagedBeanName() {
		String mbeanName = "testBean";
		assertEquals(mbeanName, ManagedBeanUtil.getDefaultManagedBeanName(
				project, mbeanName));
		
		FacesConfigType facesConfig = editor.getFacesConfig();
		ManagedBeanType bean1 = FacesConfigFactory.eINSTANCE
				.createManagedBeanType();
		ManagedBeanNameType bean1Name = FacesConfigFactory.eINSTANCE
				.createManagedBeanNameType();
		bean1Name.setTextContent(mbeanName);
		bean1.setManagedBeanName(bean1Name);
		facesConfig.getManagedBean().add(bean1);
		
		assertEquals("testBean1", ManagedBeanUtil.getDefaultManagedBeanName(
				project, mbeanName));
		
		
	}

}
