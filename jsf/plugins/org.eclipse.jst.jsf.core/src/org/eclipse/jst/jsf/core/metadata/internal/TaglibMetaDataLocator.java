/*******************************************************************************
 * Copyright (c) 2007, 2009 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.core.metadata.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.AbstractMetaDataLocator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IPathSensitiveMetaDataLocator;
import org.eclipse.jst.jsf.core.internal.tld.CMUtil;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLCMDocumentFactory;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;

/**
 * Locator of tag library meta data
 *
 */
public class TaglibMetaDataLocator extends AbstractMetaDataLocator implements IPathSensitiveMetaDataLocator{
	//project must be set to the current project context during locate only...  should not be used when noifying observers
	private IProject _project;
	private TaglibMetaDataSource _source;
	
//	private boolean _notificationEventOccuring;
	
	/**
	 * Constructor
	 */
	public TaglibMetaDataLocator(){
		super();
	}
	
	public List/*<IMetaDataModelProvider>*/ locateMetaDataModelProviders(String uri) {
		List ret = new ArrayList();
		CMDocument doc = null;

		if (uri == null){
			return ret;
		}
		else if (uri.equalsIgnoreCase(CMDocType.HTML_DOC_TYPE)){
			doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.HTML_DOC_TYPE);
		}
		else if (uri.equalsIgnoreCase(CMDocType.JSP11_DOC_TYPE)){
			doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.JSP11_DOC_TYPE);
		}
		else if (uri.equalsIgnoreCase(CMDocType.JSP12_DOC_TYPE)){
			doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.JSP12_DOC_TYPE);
		}
		else if (uri.equalsIgnoreCase(CMDocType.JSP20_DOC_TYPE)){
			doc = HTMLCMDocumentFactory.getCMDocument(CMDocType.JSP20_DOC_TYPE);
		}
		else if (_project != null ){//TLD
			CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
			ITaglibRecord[] tldRecs = TaglibIndex.getAvailableTaglibRecords(_project.getFullPath());
			ITaglibRecord tldRec = findTLD(tldRecs, uri);
			if (tldRec != null)
				doc = factory.createCMDocument(tldRec);
		}
		
		if (doc != null){
			_source = new TaglibMetaDataSource(doc);
			ret.add(_source);
		}
				
		return ret;
	}

	private ITaglibRecord findTLD(ITaglibRecord[] tldRecs, String uri) {
		for (int i=0;i<tldRecs.length;i++){
			ITaglibRecord tldRec = tldRecs[i];
			String tldRecURI = CMUtil.getURIFromTaglibRecord(tldRec, _project);
			if (uri.equals(tldRecURI))
				return tldRec;
		}
		
		return null;
	}

	public void startLocating() {
//		TaglibIndex.addTaglibIndexListener(this);
	}
	public void stopLocating() {		
//		TaglibIndex.removeTaglibIndexListener(this);//non-api call.... danger
	}
	
	//not currently listening, so will not be called
//	public void indexChanged(ITaglibIndexDelta delta) {
////		System.out.println("-----------------------"); //$NON-NLS-1$
//		if (delta.getProject() == _project) {
//			for (ITaglibIndexDelta d : delta.getAffectedChildren()) {
//				System.out.println(">>delta: "+d.getTaglibRecord()+"\n"+d.getKind()); //$NON-NLS-1$ //$NON-NLS-2$
//				String eventURI = CMUtil.createURIFromTaglibRecord(d.getTaglibRecord(), _project);
//				if (eventURI != null){
////					System.out.println(">>>eventURI: "+eventURI); //$NON-NLS-1$
//					if (!_notificationEventOccuring){
//						try {
//							_notificationEventOccuring = true;
//							int type = adaptTagLibEvent(delta);
//							IMetaDataChangeNotificationEvent mdEvent = new MetaDataChangeNotificationEvent(this, eventURI, type);
//							fireEvent(mdEvent);
//						} finally {
//							_notificationEventOccuring = false;
//						}
//					}
//				}
//			}
//		}
//	}
	
//	private int adaptTagLibEvent(ITaglibIndexDelta event) {
//		switch (event.getKind()){
//		case ITaglibIndexDelta.ADDED:
//			return IMetaDataChangeNotificationEvent.ADDED;
//		case ITaglibIndexDelta.REMOVED:
//			return IMetaDataChangeNotificationEvent.REMOVED;
//		default:
//			return IMetaDataChangeNotificationEvent.CHANGED;
//		}		
//	}
//
//	private void fireEvent(final IMetaDataChangeNotificationEvent event) {
//		SafeRunnable.run(new ISafeRunnable(){
//
//			public void handleException(Throwable exception) {
//				// TODO Auto-generated method stub				
//			}
//
//			public void run() throws Exception {
//				Iterator it = getObservers().iterator();
//				while (it.hasNext()){
//					IMetaDataObserver observer = (IMetaDataObserver)it.next();
//					observer.notifyMetadataChanged(event);
//				}
//			}
//
//		});
//		
//	}

	public void setProjectContext(IProject project) {
		this._project = project;		
	}
	
	private class TaglibMetaDataSource implements IMetaDataSourceModelProvider{

		private CMDocument doc;
		private IMetaDataLocator locator;

		TaglibMetaDataSource(CMDocument doc){
			super();
			this.doc = doc;
		}
		
		public Object getSourceModel() {
			return doc;
		}

		public IMetaDataLocator getLocator() {
			return locator;
		}

		public void setLocator(IMetaDataLocator locator) {
			this.locator = locator;			
		}

		public Object getAdapter(Class klass) {
			return null;
		}
	}

}
