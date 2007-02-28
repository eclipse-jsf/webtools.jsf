package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.jface.resource.ImageDescriptor;

public interface IImageDescriptorProvider {
	/**
	 * @param imagePath
	 * @return ImageDescriptor - implementers should eat exceptions and return null whenever imageDescriptor cannot be returned
	 */
	public ImageDescriptor getImageDescriptor(String imagePath);
}
