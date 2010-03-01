/******************************************************************************
 * Copyright (c) 2008 BEA Systems, Inc. and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Konstantin Komissarchik
 ******************************************************************************/

package org.eclipse.jst.jsf.core.internal.project.facet;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetUtils;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.internal.Messages;
import org.eclipse.wst.common.project.facet.core.IDynamicPreset;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectBase;
import org.eclipse.wst.common.project.facet.core.IPresetFactory;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.PresetDefinition;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

/**
 * @author <a href="mailto:kosta@bea.com">Konstantin Komissarchik</a>
 */

public final class JSFConfigurationPresetFactory11

    implements IPresetFactory
    
{
    public PresetDefinition createPreset( final String presetId,
                                          final Map<String,Object> context ) 
    
        throws CoreException
        
    {
        final IFacetedProjectBase fproj
            = (IFacetedProjectBase) context.get( IDynamicPreset.CONTEXT_KEY_FACETED_PROJECT );
        
        final IProjectFacetVersion webFacetVersion
            = fproj.getProjectFacetVersion( WebFacetUtils.WEB_FACET );
        
        if( webFacetVersion != null && webFacetVersion.compareTo( WebFacetUtils.WEB_23 ) >= 0 )
        {
            final Set<IProjectFacetVersion> facets = new HashSet<IProjectFacetVersion>();
            
            final IProjectFacet jsfFacet
                = ProjectFacetsManager.getProjectFacet( IJSFCoreConstants.JSF_CORE_FACET_ID );
            
            final IProjectFacetVersion jsfFacetVersion11
                = jsfFacet.getVersion( IJSFCoreConstants.JSF_VERSION_1_1 );
            
            facets.add( jsfFacetVersion11 );
            facets.add( webFacetVersion );
            
            if( webFacetVersion == WebFacetUtils.WEB_23 )
            {
                facets.add( JavaFacet.VERSION_1_3 );
            }
            else if( webFacetVersion == WebFacetUtils.WEB_24 )
            {
                facets.add( JavaFacet.VERSION_1_4 );
            }
            else
            {
                facets.add( JavaFacet.VERSION_1_5 );
            }
            
            return new PresetDefinition( Messages.JSFFacet11_presetLabel, Messages.JSFFacet11_presetDescription, facets );
        }
        
        return null;
    }
    
}
