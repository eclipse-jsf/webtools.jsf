/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.pagedesigner.jsp.core.internal.metadata;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.metadata.internal.AbstractMetaDataLocator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataLocator;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.internal.IPathSensitiveMetaDataLocator;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.jst.jsp.core.taglib.TaglibIndex;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLCMDocumentFactory;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMDocType;

/**
 * Locator for tag library meta data
 *
 */
public class TaglibLocator extends AbstractMetaDataLocator implements IPathSensitiveMetaDataLocator{
	//project must be set to the current project context during locate only...  should not be used when noifying observers
	private IProject project;
	private TaglibMetaDataSource source;

	// FIXME: unused
	//	private String uri;
//	private boolean _notificationEventOccuring;
	
	/**
	 * Constructor
	 */
	public TaglibLocator(){
		super();
		//we will continue listening for the tag lib uri, even if none are found initially
//		TaglibIndex.addTaglibIndexListener(this);//non-api call.... danger!
	}
	
	public List/*<IMetaDataModelProvider>*/ locateMetaDataModelProviders(String uri) {
		// FIXME: unused this.uri = uri;
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
			//what about JSP 1.2???
		}
		else if (project != null ){//TLD
			CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
			ITaglibRecord[] tldRecs = TaglibIndex.getAvailableTaglibRecords(project.getFullPath());
			ITaglibRecord tldRec = findTLD(tldRecs, uri);
			if (tldRec != null)
				doc = factory.createCMDocument(tldRec);
		}
		
		if (doc != null){
			source = new TaglibMetaDataSource(doc);
			ret.add(source);
		}
				
		return ret;
	}
	
	private ITaglibRecord findTLD(ITaglibRecord[] tldRecs, String uri) {
		for (int i=0;i<tldRecs.length;i++){
			ITaglibRecord tld = tldRecs[i];
			if (uri.equals(tld.getDescriptor().getURI()))
				return tld;
		}
		return null;
	}

	public void stopLocating() {		
//		TaglibIndex.removeTaglibIndexListener(this);//non-api call.... danger
	}
	
	//not currently listening, so will not be called
//	public void indexChanged(ITaglibDescriptor event) {
//		if (event.getURI() != null && event.getURI().equals(uri)){
//			if (!_notificationEventOccuring){
//				_notificationEventOccuring = true;
//				int type = adaptTagLibEvent(event);
//				IMetaDataChangeNotificationEvent mdEvent = new MetaDataChangeNotificationEvent(this, uri, type);
//				fireEvent(mdEvent);						
//				_notificationEventOccuring = false;
//			}
//		}
//	}

//	private int adaptTagLibEvent(ITaglibRecordEvent event) {
//		switch (event.getType()){
//		case ITaglibRecordEvent.ADDED:
//			return IMetaDataChangeNotificationEvent.ADDED;
//		case ITaglibRecordEvent.REMOVED:
//			return IMetaDataChangeNotificationEvent.REMOVED;
//		default:
//			return IMetaDataChangeNotificationEvent.CHANGED;
//		}		
//	}

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
		this.project = project;		
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
			// TODO Auto-generated method stub
			return null;
		}
	}

}
