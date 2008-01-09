package org.eclipse.jst.jsf.common.runtime.internal.model.component;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.INamingContainerInfo;

/**
 * A design-time analog for the UIForm.
 * 
 * @author cbateman
 */
public class UIFormInfo extends ComponentInfo implements INamingContainerInfo 
{
    /**
     * serializable id
     */
    private static final long serialVersionUID = 6961034911873576644L;

    private final boolean _prependId;
    private final boolean _submitted;
    
    /**
     * @param id
     * @param parent
     * @param componentTypeInfo
     * @param isRendered
     * @param prependId
     * @param submitted
     */
    protected UIFormInfo(final String id, final ComponentInfo parent,
            final ComponentTypeInfo componentTypeInfo, final boolean isRendered
            , final boolean prependId, final boolean submitted) {
        super(id, parent, componentTypeInfo, isRendered);
        _prependId = prependId;
        _submitted = submitted;
    }
    
    /**
     * @return true if the form allows its id to be prepended to its 
     * descendent's ids.
     */
    public final boolean isPrependId()
    {
        return _prependId;
    }

    /**
     * @return true if the form is submitted.
     */
    public final boolean isSubmitted() {
        return _submitted;
    }
}
