package org.eclipse.jst.jsf.metadata.tests.metadataprocessing;

import java.util.List;

import org.eclipse.jst.jsf.contentmodel.annotation.internal.provisional.CMAnnotationHelper;
import org.eclipse.jst.jsf.metadata.tests.Activator;
import org.eclipse.jst.jsf.metadata.tests.metadataprocessing.features.IBarker;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.AbstractMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;

/**
 * Test class implementing existing and new fetaure type for
 * testing the MetaDataEnabledFeature Extension
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class MetaDataEnabledBarkAdapter extends AbstractMetaDataEnabledFeature
	implements
		IBarker, IPossibleValues{

	public MetaDataEnabledBarkAdapter() {
		super();
	}

	public boolean canBark() {
		return true;
	}

	public List getBarks() {
		//notice that we want to use the bundle id of the extender
		return CMAnnotationHelper.getCMAttributePropertyValues(Activator.ID_BUNDLE, getCMAnnotationContext().getUri(),
					getCMAnnotationContext().getElementName(), getCMAnnotationContext().getAttributeName(), 
					"barks");
	}

	public List getPossibleValues() {
		return getBarks();
	}


}
