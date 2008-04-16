package org.eclipse.jst.jsf.common.runtime.internal.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.Decorator;

/**
 * The base type of all objects that participate in the lifecycle of a view.
 * This may be used to hold either design time or runtime information.
 * 
 * @author cbateman
 * 
 */
public abstract class ViewObject implements Serializable /* IAdaptable? */
{
    private final ViewObjectData    _data;

    /**
     * 
     */
    private static final long serialVersionUID = 1592205691642453075L;

    /**
     * 
     */
    protected ViewObject()
    {
        this(new ViewObjectData(false));
    }

    /**
     * @param delegate
     */
    protected ViewObject(final ViewObjectData delegate)
    {
        _data = delegate;
        _data.setOwner(this);
    }

    /**
     * @return the object containing all this view object's data
     */
    protected ViewObjectData getData()
    {
        return _data;
    }

    /**
     * IMPORTANT: if this method returns false, then calling a mutator method
     * (basically anything that is not a get/isX() will throw any
     * IllegalStateException).
     * 
     * @return if the object can still be modified.
     */
    public final boolean isModifiable()
    {
        return !getData().isProtected();
    }

    /**
     * Calling this method sets the state to not modifiable
     */
    public final void setProtected()
    {
        getData().setLocked();
    }

    /**
     * This call may be create a new data structure and should be considered of
     * much higher cost than most calls.
     * 
     * @return all decorators of this object. List should be assumed by clients
     *         to be unmodifiable and may throw mutation exceptions
     */
    public List getAllDecorators()
    {
        final int size = getDecoratorMap().size();

        if (size == 0)
        {
            return Collections.EMPTY_LIST;
        }

        final List allDecorators = new ArrayList();
        for (final Iterator entryIt = getDecoratorMap().entrySet().iterator(); entryIt
        .hasNext();)
        {
            final Map.Entry entry = (Map.Entry) entryIt.next();
            final List decorators = (List) entry.getValue();
            allDecorators.addAll(decorators);
        }

        return Collections.unmodifiableList(allDecorators);
    }

    /**
     * @param decoratorType
     * @return all decorators of this object associated with the class type. The
     *         returned list should be assumed to be unmodifiable. Returns an
     *         empty list if no decorators are associated with decoratorType
     */
    public List getDecorators(final Class decoratorType)
    {
        final List decorators = (List) getDecoratorMap().get(decoratorType);

        if (decorators == null)
        {
            return Collections.EMPTY_LIST;
        }
        return Collections.unmodifiableList(decorators);
    }

    /**
     * Add the decorator using decorator.getClass to associate the type. Must be
     * equivalent to addDecorator(decorator, decorator.getClass());
     * 
     * @param decorator
     *            the decorator to add. <b>Must NOT be null.</b>
     * @throws IllegalArgumentException
     *             if decorator is null.
     */
    public void addDecorator(final Decorator decorator)
    {
        _data.addDecorator(decorator);
    }

    /**
     * Add the decorator using the associatedType. Normally you should call
     * {@link #addDecorator(Decorator)} however in some cases you may wish to
     * associated a decorator with a class type other than its own. For example,
     * sub-classes of ValidatorDecorator will want to associated with
     * ValidatorDecorator.getClass() in most cases so that it is included in any
     * common examination of component validators.
     * 
     * @param decorator
     *            to add. <b>Must NOT be null.</b>
     * @param associatedType
     *            the key to store decorator <b>Must NOT be null</b>
     * @throws IllegalArgumentException
     *             if decorator or associatedType is null
     */
    public void addDecorator(final Decorator decorator,
            final Class associatedType)
    {
        _data.addDecorator(decorator, associatedType);
    }

    /**
     * Removes decorator if it is associated to associatedType on this view
     * object.
     * 
     * @param decorator
     * @param associatedType
     * @return true if the decorator was found and removed.
     * @throws IllegalArgumentException
     *             if decorator or associatedType is null
     */
    public boolean removeDecorator(final Decorator decorator,
            final Class associatedType)
    {
        return _data.removeDecorator(decorator, associatedType);
    }

    /**
     * <p>
     * Get the adapter associated the class key.
     * </p>
     * 
     * <p>
     * If addAdapter has been called with adapterType, then this adapter object
     * should always be called. In the default implementation, if there is no
     * explicit adapter, "this" is returned if (this instanceof adapterType).
     * 
     * @param adapterType
     * @return the interface adapter associated with the class key or null if
     *         not found
     */
    public Object getAdapter(final Class adapterType)
    {
        if (adapterType == null)
        {
            return null;
        }

        Object adapter = getAdapterMap().get(adapterType);

        if (adapter == null)
        {
            if (adapterType.isInstance(this))
            {
                adapter = this;
            }
        }

        return adapter;
    }

    /**
     * Adds the interface adapter object under adapterType key. There can be at
     * most one adapter registered for each class key.
     * 
     * It is an error (throws exception) to try to add an adapter for
     * adapterType which this is already instance. This restriction is necessary
     * because otherwise local getters/setters would need to be aware of the
     * adapter mechanism and verify inheritance hierarchies on every calls. This
     * mechanism is intended only for adding interfaces to view object impls
     * that don't already have them.
     * 
     * @param adapterType
     * @param adapter
     * @throws IllegalArgumentException
     *             if adapterType or adapter is null or if casting adapter to
     *             adapterType would * cause a ClassCastException (i.e. if
     *             !(adapter instanceof adapterType)) OR if this is already an
     *             instance of adapterType.
     */
    public void addAdapter(final Class adapterType, final Object adapter)
    {
        _data.addAdapter(adapterType, adapter);
    }

    /**
     * Note that {@link #getAdapter(Class)} may still return non-null after this
     * is called if (this instanceof adapterType).
     * 
     * @param adapterType
     * @return the adapter for adapterType that was just removed or null if not
     *         found
     */
    public Object removeAdapter(final Class adapterType)
    {
        return getAdapterMap().remove(adapterType);
    }

    /**
     * Note that this only returns those adapters added using
     * {@link #addAdapter(Class, Object)}. It does not return any implicit
     * adapters resulting from (this instanceof type).
     * 
     * @return the map of all adapters. Maps is immutable and may throw
     *         exceptions on attempts to mutate.
     */
    public Map getAllAdapters()
    {
        if (getAdapterMap().size() == 0)
        {
            return Collections.EMPTY_MAP;
        }
        return Collections.unmodifiableMap(getAdapterMap());
    }

    /**
     * <p>
     * The contract for this method is that it must always return a usable Map
     * and that map must be the same on every call. Lazy construction may be
     * used (as it is by default). The default map size is 4 and load factor is
     * 3 meaning that there should be decent tradeoff between wasted table size
     * and overhead used to increase it should the number of decorators exceed
     * 3. <b>Must never return null.</b>
     * </p>
     * 
     * <p>
     * Generally, the method should not need to be overridden, however it is
     * provided to allow sub-classes to change the way the decorators map is
     * constructed.
     * </p>
     * 
     * @return the map containing lists of decorators keyed by class.
     * 
     */
    protected Map getDecoratorMap()
    {
        return _data.getDecoratorMap();
    }

    /**
     * <p>
     * The contract for this method is that it must always return a usable Map
     * and that map must be the same on every call. Lazy construction may be
     * used (as it is by default). The default map size is 4 and load factor is
     * 3 meaning that there should be decent tradeoff between wasted table size
     * and overhead used to increase it should the number of decorators exceed
     * 3. <b>Must never return null.</b>
     * </p>
     * 
     * <p>
     * Generally, the method should not need to be overridden, however it is
     * provided to allow sub-classes to change the way the decorators map is
     * constructed.
     * </p>
     * 
     * @return the map containing lists of adapters keyed by class.
     * 
     */
    protected Map getAdapterMap()
    {
        return _data.getAdapterMap();
    }

    // ALL ViewObject's must use reference equals
    public final boolean equals(final Object obj)
    {
        return super.equals(obj);
    }

    public final int hashCode()
    {
        return super.hashCode();
    }

    /**
     * The protectable view object data.
     * 
     */
    public static class ViewObjectData extends ProtectedDataObject
    {
        /**
         * 
         */
        private static final long serialVersionUID = -4216980607447926035L;
        private Map _decorators;
        private Map _adapters;
        private Object _owner;

        /**
         * @param isProtected
         */
        public ViewObjectData(final boolean isProtected)
        {
            super(isProtected);
        }

        private void setOwner(final ViewObject viewObject)
        {
            _owner = viewObject;
        }

        /**
         * For serialization only.
         */
        //        public ViewObjectData()
        //        {
        //            // for serializability
        //            super(false);
        //        }

        /**
         * @param decorator
         * @param associatedType
         * @return true if the decorator was removed.
         */
        public boolean removeDecorator(final Decorator decorator, final Class associatedType)
        {
            enforceProtection();

            if (decorator == null || associatedType == null)
            {
                throw new IllegalArgumentException("Arguments must not be null");
            }

            final List decoratorsByType = (List) getDecoratorMap().get(
                    associatedType);

            if (decoratorsByType != null)
            {
                return decoratorsByType.remove(decorator);
            }

            return false;
        }

        /**
         * @param adapterType
         * @param adapter
         */
        public void addAdapter(final Class adapterType, final Object adapter)
        {
            enforceProtection();

            if (adapterType == null || adapter == null)
            {
                throw new IllegalArgumentException("Arguments must not be null");
            }

            if (!adapterType.isInstance(adapter))
            {
                throw new IllegalArgumentException("adapter: " + adapter
                        + " must be cast compatible to class: " + adapterType);
            }
            else if (adapterType.isInstance(_owner))
            {
                throw new IllegalArgumentException("this: " + _owner
                        + " must not already be an instance of class: "
                        + adapterType);
            }
            getAdapterMap().put(adapterType, adapter);
        }

        /**
         * @param decorator
         */
        public void addDecorator(final Decorator decorator)
        {
            enforceProtection();

            if (decorator == null)
            {
                throw new IllegalArgumentException("Arguments must not be null");
            }
            final Class associationType = decorator.getClass();
            addDecorator(decorator, associationType);
        }

        /**
         * @param decorator
         * @param associatedType
         */
        public void addDecorator(final Decorator decorator, final Class associatedType)
        {
            enforceProtection();

            if (decorator == null || associatedType == null)
            {
                throw new IllegalArgumentException("Arguments must not be null");
            }

            List decoratorsByType = (List) getDecoratorMap().get(associatedType);

            if (decoratorsByType == null)
            {
                decoratorsByType = new ArrayList(2);
                getDecoratorMap().put(associatedType, decoratorsByType);
            }

            decoratorsByType.add(decorator);
        }

        /**
         * @return the decorator map, creating it if necessary
         */
        protected synchronized Map getDecoratorMap()
        {
            if (_decorators == null)
            {
                if (isProtected())
                {
                    _decorators = Collections.EMPTY_MAP;
                }
                _decorators = new HashMap(4);
            }
            return _decorators;
        }

        /**
         * @return the adapter map, creating if necessary.
         */
        protected synchronized Map getAdapterMap()
        {
            if (_adapters == null)
            {
                _adapters = new HashMap(4);
            }

            return _adapters;
        }
    }

    /**
     * An object that enforces that mutation can only happen up to point where
     * the object is designated protected at which point is it is forever
     * immutable.
     * 
     */
    public static abstract class ProtectedDataObject implements Serializable
    {
        /**
         * 
         */
        private static final long serialVersionUID = 4470279408370430399L;
        private boolean _isProtected;

        /**
         * @param isProtected
         */
        public ProtectedDataObject(final boolean isProtected)
        {
            _isProtected = isProtected;
        }

        /**
         * @throws UnsupportedOperationException
         */
        protected final synchronized void enforceProtection() throws UnsupportedOperationException
        {
            if (isProtected())
            {
                throw new UnsupportedOperationException("Object "+this.toString()+ " is locked for modification");
            }
        }

        /**
         * @return true if this object is protected and irrevocablly immutable.
         */
        public final synchronized boolean isProtected()
        {
            return _isProtected;
        }

        /**
         * Executed right before setProtected irrevocably sets the protection
         * flag. Does nothing by default
         */
        protected void doBeforeProtecting()
        {
            // do nothing by default
        }
        /**
         * Makes this object irrevocably immutable.
         */
        public final synchronized void setLocked()
        {
            doBeforeProtecting();
            _isProtected = true;
        }
    }
}
