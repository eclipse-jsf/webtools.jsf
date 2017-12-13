package org.eclipse.jst.pagedesigner.tests.tagcreator;

import org.eclipse.jst.jsf.common.dom.TagIdentifier;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolCreationAdapter;
import org.eclipse.jst.pagedesigner.editors.palette.TagToolPaletteEntry;
import org.eclipse.jst.pagedesigner.editors.palette.impl.PaletteItemManager;
import org.eclipse.jst.pagedesigner.editors.palette.impl.TaglibPaletteDrawer;
import org.eclipse.jst.pagedesigner.itemcreation.ITagCreator;
import org.eclipse.jst.pagedesigner.itemcreation.internal.UserCustomizedTagCreator;
import org.eclipse.jst.pagedesigner.tests.tagcreator.base.BaseTagCreatorTestCase;
import org.eclipse.jst.pagedesigner.tests.tagcreatorPlugin.UserCustomizedElementEditFactory;

/**
 * Base test case for a UserCustomizedTagCreator.
 * 
 * @author Debajit Adhikary
 *
 */
public class BaseUserCustomizedTagCreatorTestCase extends BaseTagCreatorTestCase 
{
    /** Label for mock category to be added to the JSF tools palette (for testing only) */
	private static final String CATEGORY_LABEL = "Category label (for testing only)";

    /** Label for mock tag to be added to the JSF tools palette (for testing only) */
	private static final String TAG_LABEL = "User Customized Tag";
	
	/** Label for description for mock tag added to the JSF tools palette (for testing only) */
	private static final String TAG_DESCRIPTION_SHORT = "User Customized Tag (for testing only)";


	public BaseUserCustomizedTagCreatorTestCase() 
	{
		 super("h", "jsf");
	}

	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	
	@Override
	protected void initializePaletteItemManager(PaletteItemManager pim) {
		
		// Setup mock (user-customized) tags.
		// In the current implementation, this requires setting up a tool palette
		// category, and adding the tag(s) to the tool palette. Only then can the
		// tag be inserted.
		
		final TaglibPaletteDrawer category = pim
			.findOrCreateCategory(UserCustomizedElementEditFactory.TAGCREATOR_URI_1, CATEGORY_LABEL);
		assertNotNull(category);
		addItemsToCategory(category);
		
	}


	private void addItemsToCategory(TaglibPaletteDrawer category) {
		if (category.getChildren().size() == 0) {
			addTagToPaletteCategory(UserCustomizedElementEditFactory.TAG1, category);
			addTagToPaletteCategory(UserCustomizedElementEditFactory.TAG2, category);
			addTagToPaletteCategory(UserCustomizedElementEditFactory.TAG3, category);
			addTagToPaletteCategory(UserCustomizedElementEditFactory.TAG4, category);
			addTagToPaletteCategory(UserCustomizedElementEditFactory.TAG5, category);
			addTagToPaletteCategory(UserCustomizedElementEditFactory.TAG6, category);
			addTagToPaletteCategory(UserCustomizedElementEditFactory.TAG7, category);
			addTagToPaletteCategory(UserCustomizedElementEditFactory.TAG8, category);
			addTagToPaletteCategory(UserCustomizedElementEditFactory.TAG9, category);
		}
	}

	@Override
	protected ITagCreator getTagCreator(TagIdentifier tagId) 
	{
		return new UserCustomizedTagCreator();
	}


    /**
     * Adds a tag to a section in the tool palette.
     * 
     * @param tagId
     *            TagIdentifier of tag to add
     * @param category
     *            Category in the tool palette
     * 
     */
    @SuppressWarnings("unchecked")
    private final void addTagToPaletteCategory(final TagIdentifier tagId,
            final TaglibPaletteDrawer category)
    {
        final ITagDropSourceData tagDropSourceData = new TagToolCreationAdapter(
                tagId.getUri(), tagId.getTagName(),
                category.getDefaultPrefix(), tagId.getTagName());
        final TagToolPaletteEntry paletteItem = new TagToolPaletteEntry(
                tagDropSourceData, TAG_LABEL, TAG_DESCRIPTION_SHORT, null, null);
        category.getChildren().add(paletteItem);
        paletteItem.setParent(category);
    }
}
