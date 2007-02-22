package org.eclipse.jst.pagedesigner.dtmanager.internal.provisional;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataModelContext;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.query.MetaDataQueryHelper;
import org.eclipse.jst.pagedesigner.dtmanager.dtinfo.internal.provisional.DTInfo;
import org.eclipse.jst.pagedesigner.utils.CMUtil;
import org.eclipse.wst.xml.core.internal.document.DocumentImpl;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DefaultDTInfoFactory implements IDTInfoFactory {

	public static final String DTINFO_TRAIT_KEY = "dt-info";

	public IDTInfo getDTInfo(Element element) {
		IDTInfo dtInfo = null;
		String nsURI = CMUtil.getElementNamespaceURI(element);
		IProject project = getProject(element);
		if (project != null) {
			IMetaDataModelContext context = MetaDataQueryHelper.createMetaDataModelContext(project, MetaDataQueryHelper.TAGLIB_DOMAIN, nsURI);
			if (context != null) {
				Trait trait = MetaDataQueryHelper.getTrait(context, element.getLocalName(), DTINFO_TRAIT_KEY);
				if (trait != null) {
					DTInfo dtInfoModelObject = (DTInfo)trait.getValue();
					if (dtInfoModelObject != null) {
						dtInfo = new DefaultDTInfo(dtInfoModelObject);
					}
				}
			}
		}
		return dtInfo;
	}

	private IProject getProject(Element element) {
		IProject project = null;
		if (element != null) {
			Document document = element.getOwnerDocument();
			if (document != null && document instanceof DocumentImpl) {
				IDOMModel model = ((DocumentImpl)document).getModel();
				if (model != null) {
					String baseLocation = model.getBaseLocation();
					if (baseLocation != null && baseLocation.length() > 0) {
						IWorkspace workspace = ResourcesPlugin.getWorkspace();
						if (workspace != null) {
							IWorkspaceRoot workspaceRoot = workspace.getRoot();
							if (workspaceRoot != null) {
								IResource resource = workspaceRoot.findMember(baseLocation);
								if (resource != null) {
									project = resource.getProject();
								}
							}
						}
					}
				}
			}
		}
		return project;
	}

}
