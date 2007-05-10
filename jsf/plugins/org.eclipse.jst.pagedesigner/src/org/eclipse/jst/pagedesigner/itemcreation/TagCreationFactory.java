/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.query.MetaDataQueryHelper;
import org.eclipse.jst.pagedesigner.editors.palette.DesignerPaletteRoot;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.itemcreation.internal.DefaultTagCreator;

/**
 * Creates instances of {@link ITagCreator}s for a the given {@link TagToolPaletteEntry}
 * (Eventually) Will use TagCreavtorFactories registered using org.eclipse.jst.jsf.pagedesigner.tagcreationfactories ext-pt.  
 * Currently only using DefaultTagCreator.
 */
public class TagCreationFactory {
	private static TagCreationFactory INSTANCE = null;
	
	/**
	 * @return singleton instance
	 */
	public static TagCreationFactory getInstance(){
		if (INSTANCE == null){
			INSTANCE = new TagCreationFactory();
		}
		return INSTANCE;
	}

	/**
	 * Using the TagToolPaletteEntry, locate the factory to use for tag creation
	 * 
	 * @param tagToolPaletteEntry
	 * @return ITagCreator
	 */
	public ITagCreator createTagCreator(TagToolPaletteEntry tagToolPaletteEntry) {
		tagToolPaletteEntry.getURI();

		//eventually we will look for additional TagCreatorFactories from ext-pt..
		//for now return default metadata-enabled factory
		
		DesignerPaletteRoot root = (DesignerPaletteRoot)  tagToolPaletteEntry.getParent().getParent();
		IProject project = root.getPaletteManager().getProject();
		IMetaDataModelContext modelContext = MetaDataQueryHelper.createMetaDataModelContext(project, MetaDataQueryHelper.TAGLIB_DOMAIN, tagToolPaletteEntry.getURI());
		
		return new DefaultTagCreator(modelContext);	

	}

}
