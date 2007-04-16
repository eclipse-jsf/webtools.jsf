package org.eclipse.jst.jsf.validation.internal.appconfig;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.core.jsfappconfig.JSFAppConfigUtils;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.jst.jsp.core.internal.Logger;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;
import org.eclipse.wst.validation.internal.provisional.core.IValidatorJob;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.DocumentType;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/** 
 * General build-time validator for the JSF application configuration file  (faces-config.xml)b
 *
 * @author cbateman
 *
 */
public class AppConfigValidator implements IValidatorJob {

    public ISchedulingRule getSchedulingRule(IValidationContext helper) {
        // no scheduling rule
        return null;
    }

    public IStatus validateInJob(IValidationContext helper, IReporter reporter)
            throws ValidationException {
        IStatus status = Status.OK_STATUS;
        try {
            validate(helper, reporter);
        }
        catch (ValidationException e) {
            Logger.logException(e);
            status = new Status(IStatus.ERROR, JSFCorePlugin.getDefault().getPluginID(), IStatus.ERROR, e.getLocalizedMessage(), e);
        }
        return status;
    }

    public void cleanup(IReporter reporter) {
        // no cleanup

    }

    public void validate(IValidationContext helper, IReporter reporter)
            throws ValidationException 
    {
        String[] uris = helper.getURIs();
        IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
        if (uris.length > 0) {
            IFile currentFile = null;

            for (int i = 0; i < uris.length && !reporter.isCancelled(); i++) {
                currentFile = wsRoot.getFile(new Path(uris[i]));
                if (currentFile != null && currentFile.exists()) {
//                    if (shouldValidate(currentFile) && fragmentCheck(currentFile)) {

//                        int percent = (i * 100) / uris.length + 1;
//Message message = new LocalizedMessage(IMessage.LOW_SEVERITY, percent + "% " + uris[i]);
  //                      reporter.displaySubtask(this, message);

                        validateFile(currentFile, reporter);
                }
            }
        }
        
        // copied from JSPValidator TODO: perhaps just use app config locator?
//        else {
//
//            // if uris[] length 0 -> validate() gets called for each project
//            if (helper instanceof IWorkbenchContext) {
//
//                IProject project = ((IWorkbenchContext) helper).getProject();
//                JSPFileVisitor visitor = new JSPFileVisitor(reporter);
//                try {
//                    // collect all jsp files for the project
//                    project.accept(visitor, IResource.DEPTH_INFINITE);
//                }
//                catch (CoreException e) {
//                    if (DEBUG)
//                        e.printStackTrace();
//                }
//                IFile[] files = visitor.getFiles();
//                for (int i = 0; i < files.length && !reporter.isCancelled(); i++) {
//                    if (shouldValidate(files[i]) && fragmentCheck(files[i])) {
//                        int percent = (i * 100) / files.length + 1;
//                        Message message = new LocalizedMessage(IMessage.LOW_SEVERITY, percent + "% " + files[i].getFullPath().toString());
//                        reporter.displaySubtask(this, message);
//
//                        validateFile(files[i], reporter);
//                    }
//                    if (DEBUG)
//                        System.out.println("validating: [" + files[i] + "]"); //$NON-NLS-1$ //$NON-NLS-2$
//                }
//            }
//        }
    }

    private void validateFile(IFile file, IReporter reporter)
    {
        FacesConfigArtifactEdit  facesConfigEdit = null;
        
        try
        {
            IPath path = JSFAppConfigUtils.getWebContentFolderRelativePath(file);
            facesConfigEdit = FacesConfigArtifactEdit.
                getFacesConfigArtifactEditForRead(file.getProject(), path.toString());
            
            if (facesConfigEdit != null
                    && facesConfigEdit.getFacesConfig()!=null)
            {
                String version = validateVersioning(file, facesConfigEdit, reporter);
                validateModel(file, facesConfigEdit,reporter, version);
            }
        }
        finally
        {
            if (facesConfigEdit != null)
            {
                facesConfigEdit.dispose();
            }
        }
    }
    
    /**
     * Ensure that the expected project version (facet) jives with what is in
     * the faces-config.  Generally this means:
     * 
     * if (version == 1.1) then no 1.2 artifacts (error)
     * if (version == 1.2) then warn if using old artifacts (warning)
     */
    private String validateVersioning(IFile file, FacesConfigArtifactEdit facesConfigEdit, IReporter reporter)
    {
        final String appConfigFileVersion = getAppConfigFileVersion(facesConfigEdit);
        
        if (appConfigFileVersion != null)
        {
            final String projectVersion = getJSFVersion(file.getProject());
            
            if (IJSFCoreConstants.FACET_VERSION_1_1.equals(projectVersion)
                    || IJSFCoreConstants.FACET_VERSION_1_0.equals(projectVersion))
            {
                if (IJSFCoreConstants.FACET_VERSION_1_2.equals(appConfigFileVersion))
                {
                    reporter.addMessage(this, 
                        DiagnosticFactory
                            .create_APP_CONFIG_IS_NEWER_THAN_JSF_VERSION(file));
                }
            }
            else if (IJSFCoreConstants.FACET_VERSION_1_2.equals(projectVersion))
            {
                if (IJSFCoreConstants.FACET_VERSION_1_1.equals(appConfigFileVersion) 
                        || IJSFCoreConstants.FACET_VERSION_1_0.equals(appConfigFileVersion))
                {
                    reporter.addMessage(this, 
                        DiagnosticFactory
                            .create_APP_CONFIG_IS_OLDER_THAN_JSF_VERSION(file
                                    , appConfigFileVersion, projectVersion));
                }
            }
            // if no exact match, don't make any assumptions
        }
        return appConfigFileVersion;
    }
    
    /**
     * @param facesConfigEdit
     * @return the version of the app config file or null if not determinant
     */
    private String getAppConfigFileVersion(FacesConfigArtifactEdit facesConfigEdit)
    {
        String appConfigVersion = null;
        
        final IDOMModel domModel = facesConfigEdit.getIDOMModel();
        final IDOMDocument document = domModel.getDocument();
        if (document == null) {return null;}
        
        final DocumentType docType = domModel.getDocument().getDoctype();
        
        // if we have DTD doctype then we're looking at 1.1 or before
        if (docType != null)
        {
            appConfigVersion = extractVersionFromPublicId(docType);
            // if not found in the public id, try the system id
            if (appConfigVersion == null)
            {
                appConfigVersion = extractVersionFromSystemId(docType);
            }
        }
        else
        {
            NodeList rootNodes = domModel.getDocument().getChildNodes();
            
            for (int i = 0; i < rootNodes.getLength(); i++)
            {
                Node node = rootNodes.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE
                        && "faces-config".equals(node.getLocalName()))
                {
                    NamedNodeMap map = node.getAttributes();
                    // the most accurate thing is the version
                    Node versionAttrib = map.getNamedItem("version");
                    
                    if (versionAttrib != null)
                    {
                        appConfigVersion = versionAttrib.getNodeValue();
                        break;
                    }
                    
                    // TODO: add additional heuristic to parse out
                    // the schema
                }
            }
        }
        
        return appConfigVersion;
    }
    


    private void validateModel(final IFile file, 
                               final FacesConfigArtifactEdit facesConfigEdit, 
                               final IReporter reporter,
                               final String version)
    {
        final FacesConfigType facesConfigType = facesConfigEdit.getFacesConfig();
        FacesConfigValidator validator = new FacesConfigValidator(version);
        List messages = new ArrayList();
        validator.validate(facesConfigType, messages, file);
        
        for (final Iterator it = messages.iterator(); it.hasNext();)
        {
            IMessage message = (IMessage) it.next();
            reporter.addMessage(this, message);
        }
    }
    
    /**
     * @param project
     * @return the version string for the JSF facet on project
     * or null if not found
     */
    private String getJSFVersion(final IProject project)
    {
        try
        {
            final IFacetedProject facetedProject = ProjectFacetsManager.create(project);
            Set facets = facetedProject.getProjectFacets();
            
            for (final Iterator it = facets.iterator(); it.hasNext();)
            {
                IProjectFacetVersion  facetVersion = 
                    (IProjectFacetVersion) it.next();
                
                if (IJSFCoreConstants.JSF_CORE_FACET_ID.equals(facetVersion.getProjectFacet().getId()))
                {
                    return facetVersion.getVersionString();
                }
            }
        }
        catch (CoreException ce)
        {
            JSFCorePlugin.log(ce, "Problem loading faceted project");
            // fall-through and return null
        }
        return null;
    }
    
    private String extractVersionFromPublicId(DocumentType docType)
    {
        final String publicId = docType.getPublicId();
        final String publicIdRegex = "-\\/\\/(.*)\\/\\/(.*)\\/\\/.*";

        if (publicId != null)
        {
            final Pattern pattern = Pattern.compile(publicIdRegex);
            Matcher matcher = pattern.matcher(publicId);

            if (matcher.matches())
            {
                final String classTypeString = matcher.group(2);
                final String[] classTypes = classTypeString.split("\\s+");
                
                // verify that the class type is a DTD
                if (classTypes.length > 0
                        && "DTD".equals(classTypes[0]))
                {
                    // either 1.0 or 1.1; be most conservative
                    String appConfigVersion = IJSFCoreConstants.JSF_VERSION_1_0;
                   
                    // see if the version is in the public id
                    if (IJSFCoreConstants.JSF_VERSION_1_1.equals(classTypes[classTypes.length-1]))
                    {
                        appConfigVersion = IJSFCoreConstants.FACET_VERSION_1_1;
                    }
                    
                    return appConfigVersion;
                }
            }
        }

        return null;
    }

    private String extractVersionFromSystemId(DocumentType docType) 
    {
        final String systemId = docType.getSystemId();
        final String systemIdRegEx = "http:\\/\\/java.sun.com\\/dtd\\/web-facesconfig_(.*)\\.dtd";
        if (systemId != null)
        {
            final Pattern pattern = Pattern.compile(systemIdRegEx);
            Matcher matcher = pattern.matcher(systemId);

            if (matcher.matches())
            {
                final String version = matcher.group(1);
                if ("1_1".equals(version)||"1_0".equals(version))
                {
                    return version.replaceAll("_", ".");
                }
            }
        }
        return null;
    }
}
