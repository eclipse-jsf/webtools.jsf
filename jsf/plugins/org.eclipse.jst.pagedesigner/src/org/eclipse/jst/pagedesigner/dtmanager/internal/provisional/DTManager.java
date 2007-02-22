package org.eclipse.jst.pagedesigner.dtmanager.internal.provisional;

import org.eclipse.jst.pagedesigner.utils.CMUtil;
import org.w3c.dom.Element;

public class DTManager {

	private IDTInfoFactory dtInfoFactory;

	public IDTInfo getDTInfo(Element element) {
		IDTInfo dtInfo = null;
		String nsURI = CMUtil.getElementNamespaceURI(element);
		IDTInfoFactory dtInfoFactory = getDTInfoFactory(nsURI);
		if (dtInfoFactory != null) {
			dtInfo = dtInfoFactory.getDTInfo(element);
		}
		return dtInfo;
	}

	protected IDTInfoFactory getDTInfoFactory(String nsURI) {
		if (dtInfoFactory == null) {
			dtInfoFactory = new DefaultDTInfoFactory();
		}
		return dtInfoFactory;
	}

}
