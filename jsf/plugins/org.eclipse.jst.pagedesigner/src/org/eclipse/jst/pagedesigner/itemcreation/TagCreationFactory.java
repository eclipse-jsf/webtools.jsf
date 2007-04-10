package org.eclipse.jst.pagedesigner.itemcreation;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.MetaDataQueryHelper;
import org.eclipse.jst.pagedesigner.editors.palette.DesignerPaletteRoot;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.itemcreation.internal.DefaultTagCreator;

/**
 * Creates instances of {@link ITagCreator}s for a the given {@link TagToolPaletteEntry}
 * Will use TagCreatorFactories registered using org.eclipse.jst.jsf.pagedesigner.tagcreationfactories ext-pt.  
 * If none located, will use {@link org.eclipse.jst.pagedesigner.itemcreation.internal.TagCreatorFromMetaData} by default.
 */
public class TagCreationFactory {
	private static TagCreationFactory INSTANCE = null;
	
	public static TagCreationFactory getInstance(){
		if (INSTANCE == null){
			INSTANCE = new TagCreationFactory();
		}
		return INSTANCE;
	}

	public ITagCreator createTagCreator(TagToolPaletteEntry tagToolPaletteEntry) {
		tagToolPaletteEntry.getURI();

		//eventually we will look for additional TagCreatorFactories from ext-pt..
		//for now return default metadata-enabled factory
		
		DesignerPaletteRoot root = (DesignerPaletteRoot)  tagToolPaletteEntry.getParent().getParent();
		IProject project = root.getPaletteManager().getProject();
		IMetaDataModelContext modelContext = MetaDataQueryHelper.createMetaDataModelContext(project, MetaDataQueryHelper.TAGLIB_DOMAIN, tagToolPaletteEntry.getURI());
		
		return new DefaultTagCreator(modelContext);	
		
//		//do we have paletteInfo?
//		Model model = MetaDataQueryHelper.getModel(modelContext);
//		if (model != null){
//			Trait trait = MetaDataQueryHelper.getTrait(model, PaletteInfos.TRAIT_ID);
//			if (trait != null){
//				PaletteInfos pis = (PaletteInfos)trait.getValue();
//				PaletteInfo pi = pis.findPaletteInfoById(tagToolPaletteEntry.getId());
//				if (pi != null){
//					TagCreationInfo info = pi.getTagCreation();
//					if (info != null)
//						return new TagCreatorFromMetaData(modelContext, tagToolPaletteEntry, info);	
//				}
//			}
//			//tag-creation trait on entity directly?
//			Entity tag = MetaDataQueryHelper.getEntity(modelContext, tagToolPaletteEntry.getTagName());
//			if (tag != null){//metadata exists
//				trait = MetaDataQueryHelper.getTrait(tag, "tag-create");
//				if (trait != null && trait.getValue() != null){
//					return new TagCreatorFromMetaData(modelContext, tagToolPaletteEntry, (TagCreationInfo)trait.getValue());
//				}
//				else {//are there creation constraints of any kind?
////					trait = MetaDataQueryHelper.getTrait(model, "is-jsf-component-library");
////					if (trait != null && trait.getValue() != null){
////						return new TagCreatorFromMetaData(modelContext, tagToolPaletteEntry, wrapTraitInCreationInfo(trait.getValue()));
////					}
////					trait = MetaDataQueryHelper.getTrait(tag, "requires-html-form");
////					if (trait != null && trait.getValue() != null){
////						return new TagCreatorFromMetaData(modelContext, tagToolPaletteEntry, (TagCreationInfo)trait.getValue());
////					}
//					
//				}
//			}
//		}
//		return new DefaultTagCreator(tagToolPaletteEntry);
	}

}
