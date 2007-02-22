package org.eclipse.jst.pagedesigner.dtmanager.internal.provisional;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.DTInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagConvertInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagDecorateInfo;

public class DefaultDTInfo implements IDTInfo {

	private DTInfo dtInfo = null;

	public DefaultDTInfo(DTInfo dtInfo) {
		this.dtInfo = dtInfo;
	}

	public TagConvertInfo getTagConvertInfo() {
		return dtInfo.getTagConvertInfo();
	}

	public List getTagDecorateInfos() {
		return dtInfo.getTagDecorateInfos();
	}

	public TagDecorateInfo getTagDecorateInfo(String id) {
		TagDecorateInfo tdInfo = null;
		EList tdInfos = dtInfo.getTagDecorateInfos();
		Iterator itTDInfos = tdInfos.iterator();
		while (itTDInfos.hasNext()) {
			TagDecorateInfo curTDInfo = (TagDecorateInfo)itTDInfos.next();
			if (curTDInfo.getId().equals(id)) {
				tdInfo = curTDInfo;
				break;
			}
		}
		return tdInfo;
	}

}
