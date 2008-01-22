package org.eclipse.jst.jsf.common.runtime.tests.model;

import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.Decorator;

public class TestViewObject extends TestCase
{

    private MockAdapter _adapter1;
    private MockAdapter _adapter2;
    private MockDecorator _decorator1;
    private MockDecorator _decorator2;
    private ViewObject _viewObject1;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        _decorator1 = new MockDecorator();
        _decorator2 = new MockDecorator();
        _adapter1 = new MockAdapter();
        _adapter2 = new MockAdapter();
        _viewObject1 = new MockViewObject();
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    @SuppressWarnings("unchecked")
    public void testGetAllDecorators()
    {
        // we have not added anything so we should get an empty
        // immutable list
        List allDecorators = _viewObject1.getAllDecorators();
        assertSizeAndImmutable(allDecorators, 0);

        // add the decorators; use different keys
        _viewObject1.addDecorator(_decorator1, Decorator.class);
        _viewObject1.addDecorator(_decorator2, String.class);

        allDecorators = _viewObject1.getAllDecorators();

        assertSizeAndImmutable(allDecorators, 2);

        assertTrue(allDecorators.contains(_decorator1));
        assertTrue(allDecorators.contains(_decorator2));
    }

    @SuppressWarnings("unchecked")
    public void testGetAllAdapters()
    {
        // we have not added anything so we should get an empty
        // immutable list
        Map allAdapters = _viewObject1.getAllAdapters();
        assertSizeAndImmutable(allAdapters, 0);

        // add the decorators; use different keys
        // _viewObject1.addAdapter(Object.class, _adapter1);
        _viewObject1.addAdapter(MockAdapter.class, _adapter2);

        allAdapters = _viewObject1.getAllAdapters();

        assertSizeAndImmutable(allAdapters, 1);

        //assertEquals(_adapter1, allAdapters.get(Object.class));
        assertEquals(_adapter2, allAdapters.get(MockAdapter.class));
    }

    @SuppressWarnings("unchecked")
    public void testGetDecorators()
    {
        // haven't added anything yet, so all should be empty immutable
        assertSizeAndImmutable(_viewObject1.getDecorators(Decorator.class), 0);
        assertSizeAndImmutable(_viewObject1.getDecorators(MockDecorator.class),
                0);
        assertSizeAndImmutable(_viewObject1.getDecorators(String.class), 0);

        // just add using self class key
        _viewObject1.addDecorator(_decorator1);
        _viewObject1.addDecorator(_decorator2);

        List decorators = _viewObject1.getDecorators(_decorator1.getClass());
        assertSizeAndImmutable(decorators, 2);
        assertTrue(decorators.contains(_decorator1));
        assertTrue(decorators.contains(_decorator2));

        // wrong key
        assertFalse(_viewObject1.removeDecorator(_decorator1, Decorator.class));
        assertSizeAndImmutable(decorators, 2);

        // right keys
        assertTrue(_viewObject1.removeDecorator(_decorator1, _decorator1
                .getClass()));
        assertTrue(_viewObject1.removeDecorator(_decorator2, _decorator1
                .getClass()));
        assertSizeAndImmutable(decorators, 0);

        // reset view Object and do the same with direct class keys
        _viewObject1 = new MockViewObject();

        _viewObject1.addDecorator(_decorator1, MockDecorator.class);
        _viewObject1.addDecorator(_decorator2, MockDecorator.class);

        decorators = _viewObject1.getDecorators(_decorator1.getClass());
        assertSizeAndImmutable(decorators, 2);
        assertTrue(decorators.contains(_decorator1));
        assertTrue(decorators.contains(_decorator2));

        assertTrue(_viewObject1.removeDecorator(_decorator1, _decorator1
                .getClass()));
        assertTrue(_viewObject1.removeDecorator(_decorator2, _decorator1
                .getClass()));
        assertSizeAndImmutable(decorators, 0);

        // store under different keys
        _viewObject1 = new MockViewObject();

        _viewObject1.addDecorator(_decorator1, Decorator.class);
        _viewObject1.addDecorator(_decorator2);

        // should only be on since _decorator2 was added under its own class
        decorators = _viewObject1.getDecorators(Decorator.class);
        assertSizeAndImmutable(decorators, 1);
        assertTrue(decorators.contains(_decorator1));

        decorators = _viewObject1.getDecorators(MockDecorator.class);
        assertSizeAndImmutable(decorators, 1);
        assertTrue(decorators.contains(_decorator2));
    }

    public void testNullDecoratorArgs()
    {
        boolean isOk = false;
        // try to add a null decorator
        try
        {
            _viewObject1.addDecorator(null);
        }
        catch (final IllegalArgumentException iae)
        {
            isOk = true;
        }
        assertTrue(isOk);
        assertTrue(_viewObject1.getDecorators(null).isEmpty());

        // try to add a decorator with a null class
        isOk = false;
        try
        {
            _viewObject1.addDecorator(_decorator1, null);
        }
        catch (final IllegalArgumentException iae)
        {
            isOk = true;
        }
        assertTrue(isOk);
        assertTrue(_viewObject1.getDecorators(null).isEmpty());

        // try to remove null decorator
        isOk = false;
        try
        {
            _viewObject1.removeDecorator(null, Decorator.class);
        }
        catch (final IllegalArgumentException iae)
        {
            isOk = true;
        }
        assertTrue(isOk);
        // try to remove decorator with null key
        isOk = false;
        try
        {
            _viewObject1.removeDecorator(_decorator1, null);
        }
        catch (final IllegalArgumentException iae)
        {
            isOk = true;
        }
        assertTrue(isOk);
    }

    public void testGetAdapter()
    {
        // should have no adapters
        assertNull(_viewObject1.getAdapter(MockAdapter.class));
        assertNull(_viewObject1.getAdapter(String.class));
        assertNull(_viewObject1.getAdapter(MockDecorator.class));

        // now add the adapters under the same keys
        _viewObject1.addAdapter(MockAdapter.class, _adapter1);
        assertEquals(_adapter1, _viewObject1.getAdapter(MockAdapter.class));
        _viewObject1.addAdapter(MockAdapter.class, _adapter2);
        assertEquals(_adapter2, _viewObject1.getAdapter(MockAdapter.class));

        // now try class and sub-class
        _viewObject1.addAdapter(MockDecorator.class, _decorator1);
        assertEquals(_decorator1, _viewObject1.getAdapter(MockDecorator.class));
        assertNull(_viewObject1.getAdapter(Decorator.class));
        assertEquals(_decorator1, _viewObject1
                .removeAdapter(MockDecorator.class));
        // ok to add as a decorator because MockDecorator -> Decorator
        _viewObject1.addAdapter(Decorator.class, _decorator1);
        assertEquals(_decorator1, _viewObject1.getAdapter(Decorator.class));
        // this was removed above and should have been readded
        assertNull(_viewObject1.getAdapter(MockDecorator.class));
        assertEquals(_decorator1, _viewObject1.removeAdapter(Decorator.class));

        // test assertion conditions
        boolean assertionOk = false;
        try
        {
            // try to use a null class
            _viewObject1.addAdapter(null, _decorator1);
        }
        catch (final IllegalArgumentException iae)
        {
            assertionOk = true;
        }

        assertTrue(assertionOk);
        assertNull(_viewObject1.getAdapter(null));

        assertionOk = false;
        // try to use null object
        try
        {
            _viewObject1.addAdapter(Decorator.class, null);
        }
        catch (final IllegalArgumentException iae)
        {
            assertionOk = true;
        }
        assertTrue(assertionOk);

        assertionOk = false;
        // try adding a class that is not an instance of the interface key
        try
        {
            _viewObject1.addAdapter(Decorator.class, _adapter1);
        }
        catch (final IllegalArgumentException iae)
        {
            assertionOk = true;
        }

        assertTrue(assertionOk);
    }

    @SuppressWarnings("unchecked")
    public void testDefaultAdapterBehaviour()
    {
        // by default, the view object will return its "this" object
        // if it is an instance of a requested class, even if it has
        // this in its adapter list.
        final ViewObject viewObject1 = new MockViewObject();
        final ViewObject listViewObject1 = new MockViewObjectImplementsList();

        final List listAdapter = new ArrayList();

        // with the non-list, get adapter will return something only if it is
        // added
        assertNull(viewObject1.getAdapter(List.class));
        viewObject1.addAdapter(List.class, listAdapter);
        assertEquals(listAdapter, viewObject1.getAdapter(List.class));
        assertEquals(listAdapter, viewObject1.removeAdapter(List.class));
        assertNull(viewObject1.getAdapter(List.class));

        // however, with the viewObject that is a list...
        assertEquals(listViewObject1, listViewObject1.getAdapter(List.class));

        boolean caughtException = false;
        // can't add an interface to an object already of that type
        try
        {
            listViewObject1.addAdapter(List.class, listAdapter);
        }
        catch (final IllegalArgumentException iae)
        {
            caughtException = true;
        }

        assertTrue(caughtException);
        // should be unaffected
        assertEquals(listViewObject1, listViewObject1.getAdapter(List.class));
    }

    @SuppressWarnings("unchecked")
    public void testGetDecoratorMap()
    {
        // check contract: getDecoratorMap should never return null
        // and should always return the same map
        final Map map = ((MockViewObject) _viewObject1).getDecoratorMap();
        assertNotNull(map);
        assertEquals(map, ((MockViewObject) _viewObject1).getDecoratorMap());
        assertEquals(map, ((MockViewObject) _viewObject1).getDecoratorMap());
        assertEquals(map, ((MockViewObject) _viewObject1).getDecoratorMap());
    }

    @SuppressWarnings("unchecked")
    public void testGetAdapterMap()
    {
        // check contract: getAdapterMap should never return null
        // and should always return the same map
        final Map map = ((MockViewObject) _viewObject1).getAdapterMap();
        assertNotNull(map);
        assertEquals(map, ((MockViewObject) _viewObject1).getAdapterMap());
        assertEquals(map, ((MockViewObject) _viewObject1).getAdapterMap());
        assertEquals(map, ((MockViewObject) _viewObject1).getAdapterMap());

    }

    public void testSerializable() throws Exception
    {
        _viewObject1.addDecorator(_decorator1);
        _viewObject1.addDecorator(_decorator2, Decorator.class);
        _viewObject1.addAdapter(MockAdapter.class, _adapter1);

        final ViewObject deserialized = RuntimeTestUtil
        .serializeDeserialize(_viewObject1);

        RuntimeTestUtil.verifySame(_viewObject1, deserialized);
    }

    @SuppressWarnings("unchecked")
    private void assertSizeAndImmutable(final List list, final int size)
    {
        assertEquals(size, list.size());
        // in the default case, the list should throw an exception
        // on modification
        boolean isListImmutable = false;
        try
        {
            list.add(new MockDecorator());
        }
        catch (final Exception e)
        {
            // success
            isListImmutable = true;
        }
        assertTrue(isListImmutable);
        // since immutable, should not have changed
        assertEquals(size, list.size());
    }

    @SuppressWarnings("unchecked")
    private void assertSizeAndImmutable(final Map map, final int size)
    {
        assertEquals(size, map.size());
        // in the default case, the list should throw an exception
        // on modification
        boolean isMapImmutable = false;
        try
        {
            map.put(new Object(), new MockDecorator());
        }
        catch (final Exception e)
        {
            // success
            isMapImmutable = true;
        }
        assertTrue(isMapImmutable);
        // since immutable, should not have changed
        assertEquals(size, map.size());
    }

    public static class MockViewObject extends ViewObject
    {
        /**
         * 
         */
        private static final long serialVersionUID = 5839704536769825171L;

        @SuppressWarnings("unchecked")
        @Override
        public Map getAdapterMap()
        {
            return super.getAdapterMap();
        }

        @SuppressWarnings("unchecked")
        @Override
        public Map getDecoratorMap()
        {
            return super.getDecoratorMap();
        }
    }

    @SuppressWarnings("unchecked")
    public static class MockViewObjectImplementsList extends ViewObject
    implements List
    {
        /**
         * 
         */
        private static final long serialVersionUID = -6379202834709723049L;

        public boolean add(final Object o)
        {
            return false;
        }

        public void add(final int index, final Object element)
        {

        }

        public boolean addAll(final Collection c)
        {
            return false;
        }

        public boolean addAll(final int index, final Collection c)
        {
            return false;
        }

        public void clear()
        {

        }

        public boolean contains(final Object o)
        {
            return false;
        }

        public boolean containsAll(final Collection c)
        {
            return false;
        }

        public Object get(final int index)
        {
            return null;
        }

        public int indexOf(final Object o)
        {
            return 0;
        }

        public boolean isEmpty()
        {
            return false;
        }

        public Iterator iterator()
        {
            return null;
        }

        public int lastIndexOf(final Object o)
        {
            return 0;
        }

        public ListIterator listIterator()
        {
            return null;
        }

        public ListIterator listIterator(final int index)
        {
            return null;
        }

        public boolean remove(final Object o)
        {
            return false;
        }

        public Object remove(final int index)
        {
            return null;
        }

        public boolean removeAll(final Collection c)
        {
            return false;
        }

        public boolean retainAll(final Collection c)
        {
            return false;
        }

        public Object set(final int index, final Object element)
        {
            return null;
        }

        public int size()
        {
            return 0;
        }

        public List subList(final int fromIndex, final int toIndex)
        {
            return null;
        }

        public Object[] toArray()
        {
            return null;
        }

        public Object[] toArray(final Object[] a)
        {
            return null;
        }

    }

    public static class MockDecorator extends Decorator
    {

        /**
         * 
         */
        private static final long serialVersionUID = 456684865942628607L;

    }

    public static class MockAdapter implements Serializable
    {

        /**
         * 
         */
        private static final long serialVersionUID = 424297135847238931L;
    }

    @SuppressWarnings("unchecked")
    public static class MockAdapterImplementsList extends AbstractList
    {

        @Override
        public Object get(final int index)
        {
            return null;
        }

        @Override
        public int size()
        {
            return 0;
        }

    }
}
