/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.tests;

import java.util.ResourceBundle;

import junit.framework.TestCase;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IClassLoaderProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IImageDescriptorProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IResourceBundleProvider;
import org.eclipse.jst.jsf.common.metadata.query.ITaglibDomainMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.TaglibDomainMetaDataQueryHelper;
import org.eclipse.swt.graphics.Image;

public class ModelProviderAdapterTests extends TestCase {
	private final String JSF_HTML_URI	= "http://java.sun.com/jsf/html";
	private final String IMAGES_BASE 	= 	"/icons/palette/JSFHTML/small/";
	
	private Trait _trait;
	
	protected void setUp() throws Exception {
		super.setUp();
		
		ITaglibDomainMetaDataModelContext context = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(null, JSF_HTML_URI);
		Model model = TaglibDomainMetaDataQueryHelper.getModel(context);
		assertNotNull(model);
		//get the trait that was defined by the "palette" md file.  This will establish the correct sourceModelProvider.
		_trait = TaglibDomainMetaDataQueryHelper.getTrait(model, "display-label");
		assertNotNull(_trait);
	}

	public void testImageDescriptorProvider() {	

		IImageDescriptorProvider imageProvider = (IImageDescriptorProvider)_trait.getSourceModelProvider().getAdapter(IImageDescriptorProvider.class);
		assertNotNull(imageProvider);
		ImageDescriptor id = imageProvider.getImageDescriptor(IMAGES_BASE+"JSF_COMMANDBUTTON");
		assertNotNull(id);
		Image image = id.createImage();
		assertNotNull(image);
		image.dispose();
				
	}

//Comment out till resourceBundleHelper issues on Linux is resolved: https://bugs.eclipse.org/bugs/show_bug.cgi?id=202537
	public void testResourceBundlerProvider() {
		
		IResourceBundleProvider bundleProvider = (IResourceBundleProvider)_trait.getSourceModelProvider().getAdapter(IResourceBundleProvider.class);
		assertNotNull(bundleProvider);
		
		ResourceBundle bundle = bundleProvider.getResourceBundle();
		assertNotNull(bundle);
		
		assertNotNull(bundle.getString("JSFHTML.display-label"));
		assertEquals("JSF HTML", bundle.getString("JSFHTML.display-label"));
		assertNotNull(bundle.getString("column.display-label"));
		assertEquals("Column", bundle.getString("column.display-label"));
		
		//
		ITaglibDomainMetaDataModelContext context = TaglibDomainMetaDataQueryHelper.createMetaDataModelContext(null, "RootOfPluginTest");
		Model model = TaglibDomainMetaDataQueryHelper.getModel(context);
		assertNotNull(model);
		_trait = TaglibDomainMetaDataQueryHelper.getTrait(model, "T1");
		assertNotNull(_trait);
		bundleProvider = (IResourceBundleProvider)_trait.getSourceModelProvider().getAdapter(IResourceBundleProvider.class);
		assertNotNull(bundleProvider);
		bundle = bundleProvider.getResourceBundle();
		assertNotNull(bundle.getString("NLS"));
		assertEquals("This is externalized text", bundle.getString("NLS"));		
	}
	
	@SuppressWarnings({ })
	public void testClassloaderProvider() {
		
		IClassLoaderProvider classLoaderProvider = (IClassLoaderProvider)_trait.getSourceModelProvider().getAdapter(IClassLoaderProvider.class);
		assertNotNull(classLoaderProvider);
		Class<?> klass = classLoaderProvider.loadClass("java.lang.String");
		assertNotNull(klass);
		
		// when all MD was moved from WPE to tagsupport plugin, there was no longer a plugin specific class to load here.    FIX ME later.
//		klass = classLoaderProvider.loadClass("org.eclipse.jst.pagedesigner.jsf.ui.util.JSFUIPluginResourcesUtil");
//		assertNotNull(klass);
	}
	
}
