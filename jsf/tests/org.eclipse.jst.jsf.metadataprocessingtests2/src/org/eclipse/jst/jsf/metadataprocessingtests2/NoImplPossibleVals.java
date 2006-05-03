package org.eclipse.jst.jsf.metadataprocessingtests2;


import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.AbstractMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.AbstractRootTypeDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.features.IPossibleValues;
import org.eclipse.wtp.jsf.contentmodel.annotation.internal.provisional.CMAnnotationHelper;

public class NoImplPossibleVals extends AbstractMetaDataEnabledFeature implements
		IPossibleValues {

	public NoImplPossibleVals() {
		super();
	}

	public List getPossibleValues() {
		return getPossibleVals();
	}

	private List getPossibleVals() {
		return CMAnnotationHelper.getCMAttributePropertyValues(getCMAnnotationContext().getBundleId(), getCMAnnotationContext().getUri(),
				getCMAnnotationContext().getElementName(), getCMAnnotationContext().getAttributeName(),
				IPossibleValues.POSSIBLE_VALUES_PROP_NAME);

	}
	
	

}
