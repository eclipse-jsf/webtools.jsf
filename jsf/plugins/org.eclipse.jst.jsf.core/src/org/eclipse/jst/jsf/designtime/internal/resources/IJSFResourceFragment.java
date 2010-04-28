package org.eclipse.jst.jsf.designtime.internal.resources;

/**
 * An object that backs a fragment of a JSF resource. A fragment may be a full
 * fledged JSF resource or a may be another interesting related object such as
 * the library folder that holds a resource.
 * 
 * @author cbateman
 * 
 */
public interface IJSFResourceFragment
{

    /**
     * @return true if the fragment is accessible
     */
    public abstract boolean isAccessible();

    /**
     * @return the type of this fragment.
     */
    public abstract Type getType();

    /**
     * @return the id of the fragment
     */
    public ResourceFragmentIdentifier getId();

    /**
     * The type of a fragment
     * 
     */
    public enum Type
    {
        /**
         * A fragment of this type is a full-fledged JSF Resource. A fragment of
         * this type can always be cast to IJSFResource.
         */
        RESOURCE,
        /**
         * A fragment of this type is a container for actual RESOURCE's or other
         * fragments (or both). A fragment of this type can always be cast to
         * IJSFResourceContainer.
         */
        CONTAINER;
    }

}
