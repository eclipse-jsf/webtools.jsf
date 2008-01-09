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
    private Map/* <Class, List<Decorator>> */_decorators;
    private Map/* <Class, Object */_adapters;
    /**
     * 
     */
    private static final long serialVersionUID = 1592205691642453075L;

    /**
     * This call may be create a new data structure and should be considered of
     * much higher cost than most calls.
     * 
     * @return all decorators of this object. List should be assumed by clients
     *         to be unmodifiable and may throw mutation exceptions
     */
    public List getAllDecorators() {
        final int size = getDecoratorMap().size();

        if (size == 0) {
            return Collections.EMPTY_LIST;
        }

        final List allDecorators = new ArrayList();
        for (final Iterator entryIt = getDecoratorMap().entrySet().iterator(); entryIt
                .hasNext();) {
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
    public List getDecorators(final Class decoratorType) {
        final List decorators = (List) getDecoratorMap().get(decoratorType);

        if (decorators == null) {
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
    public void addDecorator(final Decorator decorator) {
        if (decorator == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }
        final Class associationType = decorator.getClass();
        addDecorator(decorator, associationType);
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
            final Class associatedType) {
        if (decorator == null || associatedType == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }

        List decoratorsByType = (List) getDecoratorMap().get(associatedType);

        if (decoratorsByType == null) {
            decoratorsByType = new ArrayList(2);
            getDecoratorMap().put(associatedType, decoratorsByType);
        }

        decoratorsByType.add(decorator);
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
            final Class associatedType) {
        if (decorator == null || associatedType == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }

        final List decoratorsByType = (List) getDecoratorMap().get(
                associatedType);

        if (decoratorsByType != null) {
            return decoratorsByType.remove(decorator);
        }

        return false;
    }

    /**
     * <p>Get the adapter associated the class key.</p>  
     * 
     * <p>If addAdapter has been called with adapterType, then this
     * adapter object should always be called.  In the default implementation,
     * if there is no explicit adapter, "this" is returned if (this instanceof adapterType).
     * 
     * @param adapterType
     * @return the interface adapter associated with the class key or null if
     *         not found
     */
    public Object getAdapter(final Class adapterType) {
        if (adapterType == null) {
            return null;
        }

        Object adapter = getAdapterMap().get(adapterType);
        
        if (adapter == null)
        {
            if (adapterType.isInstance(this)) {
                adapter = this;
            }
        }
        
        return adapter;
    }

    /**
     * Adds the interface adapter object under adapterType key. There can be at
     * most one adapter registered for each class key.
     * 
     * @param adapterType
     * @param adapter
     * @throws IllegalArgumentException
     *             if adapterType or adapter is null or if casting adapter to
     *             adapterType would * cause a ClassCastException (i.e. if
     *             !(adapter instanceof adapterType))
     */
    public void addAdapter(final Class adapterType, final Object adapter) {
        if (adapterType == null || adapter == null) {
            throw new IllegalArgumentException("Arguments must not be null");
        }

        if (!adapterType.isInstance(adapter)) {
            throw new IllegalArgumentException("adapter: " + adapter
                    + " must be cast compatible to class: " + adapterType);
        }
        getAdapterMap().put(adapterType, adapter);
    }

    /**
     * Note that {@link #getAdapter(Class)} may still return non-null after
     * this is called if (this instanceof adapterType).
     * 
     * @param adapterType
     * @return the adapter for adapterType that was just removed or null if not
     *         found
     */
    public Object removeAdapter(final Class adapterType) {
        return getAdapterMap().remove(adapterType);
    }

    /**
     * Note that this only returns those adapters added using
     * {@link #addAdapter(Class, Object)}.  It does not return any
     * implicit adapters resulting from (this instanceof type).
     * 
     * @return the map of all adapters.  Maps is immutable and may throw
     * exceptions on attempts to mutate.
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
    protected Map getDecoratorMap() {
        if (_decorators == null) {
            _decorators = new HashMap(4);
        }
        return _decorators;
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
    protected Map getAdapterMap() {
        if (_adapters == null) {
            _adapters = new HashMap(4);
        }

        return _adapters;
    }
}
