package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.jface.resource.ImageDescriptor;

public interface IImageDescriptorProvider extends IMetaDataSourceModelProviderAdapter {
	public ImageDescriptor getImageDescriptor(String inagePath);
}
