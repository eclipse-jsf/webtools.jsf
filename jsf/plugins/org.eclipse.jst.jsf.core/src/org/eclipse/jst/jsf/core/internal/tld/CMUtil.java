/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.tld;

import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsp.core.internal.contentmodel.TaglibController;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDocumentFactoryTLD;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TLDCMDocumentManager;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.TaglibTracker;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;
import org.eclipse.jst.jsp.core.taglib.ITaglibRecord;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.html.core.internal.contentmodel.HTMLElementDeclaration;
import org.eclipse.wst.html.core.internal.provisional.HTMLCMProperties;
import org.eclipse.wst.sse.core.internal.provisional.INodeNotifier;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMElementDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;
import org.eclipse.wst.xml.core.internal.provisional.contentmodel.CMNodeWrapper;
import org.eclipse.wst.xml.core.internal.ssemodelquery.ModelQueryAdapter;
import org.w3c.dom.Element;

/**
 * Utility class to content model related information.
 * 
 * @author mengbo
 */
public final class CMUtil {
	/**
	 * If the element is a custom tag, get the URI of it. If the element is a
	 * standard JSP tag, return null. If is not jsp tag, then return null
	 * @param decl
     *  
	 * @return the tag uri as a string
	 */
	public static String getTagURI(CMElementDeclaration decl) {
		if (decl instanceof CMNodeWrapper) {
			decl = (CMElementDeclaration) ((CMNodeWrapper) decl)
					.getOriginNode();
		}
		if (decl instanceof TLDElementDeclaration) {
			CMDocument doc = ((TLDElementDeclaration) decl).getOwnerDocument();
			if (doc instanceof TLDDocument) {
				TLDDocument tldDoc = (TLDDocument)doc;
				return getURIFromDoc(tldDoc , null);
			}
		}
		return null;
	}
 
	/**
	 * @param doc
	 * @param project - may be null in which case it is calculated as necessary from the doc baseLocation
	 * @return valid string to use for the uri when given a TLD doc
	 * 			Must <ul>not</ul> be called with HTML or JSP documents.
	 * 			As there is no API on the doc for standalone or tagDir doc, it is possible that this could return an invalid string.  
	 * 				However, if the code is consistent in it's usage, all should be well.
	 *         or null if not found.
	 */
	public static String  getURIFromDoc(final TLDDocument doc, final IProject project) {
		String uri = doc.getUri();
		IProject proj = project;
		if (uri == null) {//
			Path baseLoc = new Path(doc.getBaseLocation());
			if (proj == null) {
				proj = getProjectFor(baseLoc);
				if (proj == null) {//log error
					return null;
				}
			}
			
			if (isTagDirDocument(doc, proj)) {
				uri = getTagDirURI(doc, proj);
			} else {
				uri = getStandaloneTLDURI(doc, proj);
			}
		}
		return uri;
	}

	/**
	 * @param tldRec
	 * @param project
	 * @return valid string to use for the uri when given a ITaglibRecord
	 * or null.
	 */
	public static String getURIFromTaglibRecord(ITaglibRecord tldRec, IProject project) {		
		//similar code in PaletteHelper and above		
		String uri = tldRec.getDescriptor().getURI();
		if (uri == null || uri.trim().equals("")) {		 //$NON-NLS-1$
			//need to construct valid string representing taglib identifier
			CMDocumentFactoryTLD factory = new CMDocumentFactoryTLD();
			TLDDocument doc = (TLDDocument)factory.createCMDocument(tldRec);
			if (tldRec.getRecordType() == ITaglibRecord.TLD) {		
				uri = getStandaloneTLDURI(doc, project);			
			}
			else if (tldRec.getRecordType() == ITaglibRecord.TAGDIR) {	
				uri =  getTagDirURI(doc, project);				
			}
			
		}
		return uri;
	}
	
	private static String getStandaloneTLDURI(TLDDocument doc, IProject project) {
		Path p = new Path(doc.getBaseLocation());
		IPath webContentPath = ComponentCore.createComponent(project).getRootFolder().getUnderlyingFolder().getLocation();
		return getURIFromPath(p.makeAbsolute().makeRelativeTo(webContentPath.makeAbsolute()));		
	}

	private static String getTagDirURI(TLDDocument doc, IProject project) {
		Path p = new Path(doc.getBaseLocation());
		IVirtualComponent projectComp = ComponentCore.createComponent(project);
		
		if (projectComp != null)
		{
		    IVirtualFolder rootFolder = projectComp.getRootFolder();
		    
		    if (rootFolder != null)
		    {
		        IPath webContentPath = 
		            rootFolder.getUnderlyingFolder().getFullPath();
		        return getURIFromPath(p.makeRelativeTo(webContentPath));
		    }
		}
		return null;
	}

	private static String getURIFromPath(IPath uriPath)
	{
		if (uriPath != null)
			return "/"+uriPath.toString(); //$NON-NLS-1$ - do not remove "/" since is necessary for tagdir attr on taglib directive
		
		return null;
	}

	/**
	 * @param tldDoc - must not be null
	 * @param project - must not be null
	 * @return true if this is a tag dir tldDocument
	 */
	public static boolean isTagDirDocument(final TLDDocument tldDoc, final IProject project) {
		if (tldDoc.getUri() == null || tldDoc.getUri().equals("")) { //$NON-NLS-1$
			IPath p = new Path(tldDoc.getBaseLocation());
			IPath webContentPath = ComponentCore.createComponent(project).getRootFolder().getUnderlyingFolder().getFullPath();
			if (p.matchingFirstSegments(webContentPath) == webContentPath.segmentCount()) {
				p = p.removeFirstSegments(webContentPath.segmentCount());
				if (p.matchingFirstSegments(new Path("WEB-INF/tags")) == 2) { //$NON-NLS-1$)  {
					return true;
				}
			}
		}
		return false;
	}
	
	//Code taken from jsf.common.ui.WorkspaceUtil
	private static IProject getProjectFor(IPath path) {
		String[] segs = path.segments();
		String projectPath = new String();
		IProject[] projects = ResourcesPlugin.getWorkspace().getRoot()
				.getProjects();
		IProject project = null;
		for (int p = 0; p < projects.length; p++) {
			if (projects[p].isOpen()) {
				for (int s = 0; s < segs.length; s++) {
					if (segs[s].equalsIgnoreCase(projects[p].getName())) {
						// Once we have a match on the project name, then
						// the remainder of the segments equals the project path
						for (int s2 = s + 1; s2 < segs.length; s2++) {
							projectPath = projectPath
									+ "/" //$NON-NLS-1$
									+ segs[s2];
						}
						project = projects[p];
						break;
					}
				}
			}
		}
		if (project == null) {
			return null;
		}

		// TODO: still don't understand why this refreshLocal is necessary
		// for now, going to only allow it if this method is called 
		// when the tree isn't locked.  This shouldn't cause a regression, since
		// when the call fails currently things keep on going due to the catch
		if (!project.getWorkspace().isTreeLocked())
		{
    		try {
    			project.refreshLocal(IResource.DEPTH_INFINITE, null);
    		} catch (CoreException e) {
//                 TODO C.B.:pushing this down to a warning because it creates really
//                 spurious output.  Don't know why we are calling refreshLocal at all.
                JSFCorePlugin.log(Status.WARNING, "Error.RefreshingLocal", e); //$NON-NLS-1$
    		}
		}
		
		IResource res = project.findMember(new Path(projectPath));
		if ((res != null) && (res.exists())) {
			return project;
		}
		return null;
	}
	
	/**
	 * Test whether this is the JSP core tag.
	 * 
	 * @param decl
	 * @return true if decl is a jsp element declaration
	 */
	public static boolean isJSP(CMElementDeclaration decl) {
		if (!decl.supports(HTMLCMProperties.IS_JSP)) {
			return false;
		}
		Boolean b = (Boolean) decl.getProperty(HTMLCMProperties.IS_JSP);
		return b.booleanValue();
	}

	/**
	 * @param decl
	 * @return true if the element declartion is a non-JSP html element
	 */
	public static boolean isHTML(CMElementDeclaration decl) {
		if (!isJSP(decl) && (decl instanceof HTMLElementDeclaration)) {
			return true;
		}
		return false;
	}

	/**
	 * get element declaration of specified element
	 * 
	 * @param element
	 * @return null if can't get it.
	 */
	public static CMElementDeclaration getElementDeclaration(Element element) {
		if (element == null) {
			return null;
		}
		INodeNotifier notifier = (INodeNotifier) element.getOwnerDocument();
		if (notifier == null) {
			return null;
		}
		ModelQueryAdapter mqa = (ModelQueryAdapter) notifier
				.getAdapterFor(ModelQueryAdapter.class);
		if (mqa == null) {
			return null;
		}
		return mqa.getModelQuery().getCMElementDeclaration(element);
	}

	/**
	 * @param element
	 * @return the TLDElementDeclaration for element or null if not found
	 */
	public static TLDElementDeclaration getTLDElementDeclaration(Element element) {
		CMNode decl = getElementDeclaration(element);
		if (decl instanceof CMNodeWrapper) {
			decl = ((CMNodeWrapper) decl).getOriginNode();
		}
		if (decl instanceof TLDElementDeclaration) {
			return (TLDElementDeclaration) decl;
		}
        return null;
	}
	
	/**
	 * !!! NOTE: this function is intended to work around the problem that if your element
	 * has not yet been added to an  IDOMModel, getElementDeclaration won't be able to find
	 * it.  This method does nothing (unlike the ModelQuery-based approach in getElementDeclaration)
	 * to ensure that the namespace "uri" provided is valid in the structured document provided.  It is
	 * therefore only advisable to use this method in cases where your node is not already a member of a 
	 * IDOMModel.
	 * 
	 * @param uri
	 * @param elementName
	 * @param document 
	 * @return the TLDElementDeclaration for this required tag or null if there  is nothing appropriate
	 */
	public static CMElementDeclaration getTLDElementDeclaration(final String uri, final String elementName, IDocument document)
	{
	    TLDCMDocumentManager tldmgr = TaglibController.getTLDCMDocumentManager(document);
	    
	    if (tldmgr != null)
	    {
            for (Iterator it = tldmgr.getTaglibTrackers().iterator();it.hasNext();)
            {
                TaglibTracker  tracker = (TaglibTracker) it.next();
                
                if (tracker.getURI().equals(uri))
                {
                    return (CMElementDeclaration) tracker.getElements().getNamedItem(tracker.getPrefix()+":"+elementName); //$NON-NLS-1$
                }
            }
	    }
        // fallthrough
        return null;
	}

	/**
	 * give an element, get its namespace URI.
	 * 
	 * @param element
	 * @return the namespace URI
	 */
	public static String getElementNamespaceURI(Element element) {
	    //System.out.printf("uri for %s is %s\n", element.toString(), element.getNamespaceURI());

	    CMElementDeclaration decl = getElementDeclaration(element);
		if (decl == null) {
		    
		    // if the content model has nothing, see if the element 
		    // itself has an xml namespace
		    // TODO: should only apply this if the source document
		    // is a valid XML doc?
		    final String uri = element.getNamespaceURI();
		    
		    // may be null which the default state
			return uri;
		}

		if (isJSP(decl)) {
			return ITLDConstants.URI_JSP;
		} else if (isHTML(decl)) {
			return ITLDConstants.URI_HTML;
		}

		return getTagURI(decl);
	}

	/**
	 * @param element
	 * @return true if the element can have children
	 */
	public static boolean canHaveDirectTextChild(Element element) {
		CMElementDeclaration decl = getElementDeclaration(element);
		if (decl == null) {
			return true;
		}
		int contentType = decl.getContentType();
		return contentType != CMElementDeclaration.ELEMENT
				&& contentType != CMElementDeclaration.EMPTY;

	}
    
    private CMUtil()
    {
        // util class, no external instantiation
    }

}
