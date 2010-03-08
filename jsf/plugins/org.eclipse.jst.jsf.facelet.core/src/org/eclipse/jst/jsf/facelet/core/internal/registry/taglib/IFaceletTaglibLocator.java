package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.ILocator;

/**
 * A locator that returns a name-keyed map of facelet tag records for a particular
 * project.
 * 
 * @author cbateman
 *
 */
public interface IFaceletTaglibLocator extends ILocator<Map<String, ? extends IFaceletTagRecord>, IProject, String>
{
    // do nothing
}