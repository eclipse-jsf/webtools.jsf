/*******************************************************************************
 * Copyright (c) 2006 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.core.tests.util;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.FacetProjectCreationOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.IFacetedProject.Action.Type;

/**
 * Augments a ProjectEnvironment with JSF faceting
 * 
 * @author cbateman
 *
 */
public class JSFFacetedTestEnvironment 
{
    private final WebProjectTestEnvironment     _projectTestEnvironment;
    private JSFFacetInstallDataModelProvider    _modelProvider;
    private IDataModel                          _model;

    /**
     * @param projectTestEnvironment
     */
    public JSFFacetedTestEnvironment(final WebProjectTestEnvironment projectTestEnvironment) 
    {
        super();
        _projectTestEnvironment = projectTestEnvironment;
    }
    
    /**
     * Initialize the facet
     * @param version -- the version of the facet.  Valid strings are constant
     * publics on this class starting FACET_VERSION
     */
    public void initialize(final String version)
    {
        try
        {
            final IProject project = 
               _projectTestEnvironment.getTestProject();
            //seed JSFLib registry if not present
            // TODO: is this really necessary for the facet?
            JSFCoreUtilHelper.createJSFLibraryRegistry();

            _modelProvider = new JSFFacetInstallDataModelProvider();
            _model = DataModelFactory.createDataModel(_modelProvider);    
            _model.setStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, project.getName());
            
            Set actions = new HashSet();
            actions.add(new IFacetedProject.Action((Type) _model.getProperty(IFacetDataModelProperties.FACET_TYPE),
                    getJSFFacet(version),
                _model));
            
            IFacetedProject facetedProject = ProjectFacetsManager.create(project);
            facetedProject.modify(actions, null);
            
            try {
                FacetProjectCreationOperation.addDefaultFactets(facetedProject, null);
            } catch (ExecutionException e) {
                Logger.getLogger().logError(e);
            }           
        } catch (CoreException e) {
            Logger.getLogger().logError(e);
        }       
    }
    
    private IProjectFacetVersion getJSFFacet(String version)
    {
        IProjectFacet facet = ProjectFacetsManager.getProjectFacet(IJSFCoreConstants.JSF_CORE_FACET_ID);
        return facet.getVersion(version);
    }
    
    /**
     * Dispose of held resources
     */
    public void dispose()
    {
        if (_model != null)
        {
            _model.dispose();
        }
    }
}
