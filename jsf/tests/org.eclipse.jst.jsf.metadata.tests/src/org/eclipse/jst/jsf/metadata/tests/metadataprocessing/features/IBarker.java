package org.eclipse.jst.jsf.metadata.tests.metadataprocessing.features;

import java.util.List;

import org.eclipse.jst.jsf.metadataprocessors.internal.provisional.IMetaDataEnabledFeature;

public interface IBarker extends IMetaDataEnabledFeature {

	public boolean canBark();
	public List getBarks();
}
