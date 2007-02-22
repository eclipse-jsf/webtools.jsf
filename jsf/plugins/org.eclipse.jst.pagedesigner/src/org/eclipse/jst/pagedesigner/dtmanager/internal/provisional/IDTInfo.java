package org.eclipse.jst.pagedesigner.dtmanager.internal.provisional;

import java.util.List;

import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagConvertInfo;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.TagDecorateInfo;

public interface IDTInfo {

	public TagConvertInfo getTagConvertInfo();
	public List getTagDecorateInfos();
	public TagDecorateInfo getTagDecorateInfo(String id);

}
