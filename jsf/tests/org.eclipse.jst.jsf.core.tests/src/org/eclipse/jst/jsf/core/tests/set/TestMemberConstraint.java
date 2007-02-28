package org.eclipse.jst.jsf.core.tests.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.sets.internal.provisional.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.internal.provisional.ConcreteAxiomaticSet;
import org.eclipse.jst.jsf.core.internal.provisional.set.constraint.MemberConstraint;

public class TestMemberConstraint extends TestCase 
{
    private AxiomaticSet            _set1;
    private AxiomaticSet            _set2;
    private AxiomaticSet            _set3;

    private AxiomaticSet            _constraintSet1;
    private AxiomaticSet            _constraintSet2;
    private AxiomaticSet            _constraintSet3;
    
    public void testMemberConstraint()
    {
        // A is always a subset of itself
        assertTrue(new MemberConstraint(_set1).passesConstraint(_set1));
        assertTrue(new MemberConstraint(_set2).passesConstraint(_set2));
        assertTrue(new MemberConstraint(_set3).passesConstraint(_set3));
        
        // constraint 1
        assertTrue(new MemberConstraint(_constraintSet1).passesConstraint(_set1));
        Diagnostic fail = new MemberConstraint(_constraintSet1).isSatisfied(_set2);
        assertEquals(Diagnostic.ERROR, fail.getSeverity());
        assertEquals(1, fail.getData().size());
        assertTrue(fail.getData().contains("element2"));
        fail = new MemberConstraint(_constraintSet1).isSatisfied(_set3);
        assertEquals(Diagnostic.ERROR, fail.getSeverity());
        assertEquals(1, fail.getData().size());
        assertTrue(fail.getData().contains("element2"));
        
        // constraint 2
        fail = new MemberConstraint(_constraintSet2).isSatisfied(_set1);
        assertEquals(Diagnostic.ERROR, fail.getSeverity());
        assertTrue(fail.getData().contains("element7"));
        assertEquals(1, fail.getData().size());
        assertTrue(new MemberConstraint(_constraintSet2).passesConstraint(_set2));
        fail = new MemberConstraint(_constraintSet2).isSatisfied(_set3);
        assertEquals(Diagnostic.ERROR, fail.getSeverity());
        assertEquals(1, fail.getData().size());
        assertTrue(fail.getData().contains("element7"));
        
        // constraint 3
        fail = new MemberConstraint(_constraintSet3).isSatisfied(_set1);
        assertEquals(Diagnostic.ERROR, fail.getSeverity());
        assertEquals(1, fail.getData().size());
        assertTrue(fail.getData().contains("element7"));
        fail = new MemberConstraint(_constraintSet3).isSatisfied(_set2);
        assertEquals(Diagnostic.ERROR, fail.getSeverity());
        assertEquals(2, fail.getData().size());
        assertTrue(fail.getData().contains("element1"));
        assertTrue(fail.getData().contains("element2"));
        fail = new MemberConstraint(_constraintSet3).isSatisfied(_set3);
        assertEquals(Diagnostic.ERROR, fail.getSeverity());
        assertEquals(2, fail.getData().size());
        assertTrue(fail.getData().contains("element2"));
        assertTrue(fail.getData().contains("element7"));
        
    }
    
    public void setUp() throws Exception
    {
        super.setUp();
        
        List items = new ArrayList();
        items.add("element1");
        items.add("element2");
        items.add("element3");
        items.add("element4");
        _set1 = createSet(items);
        
        items.clear();
        items.add("element5");
        items.add("element6");
        items.add("element7");
        items.add("element8");
        _set2 = createSet(items);
        
        items.clear();
        items.add("element1");
        items.add("element5");
        _set3 = createSet(items);
        
        // setup a constraint set that will pass for set1
        // fail for set2 (they are disjoint)  and fail for set3
        items.clear();
        items.add("element2");
        _constraintSet1 = createSet(items);
        
        // setup a constraint set that will pass for set2
        // fail for set1 and fail for set3
        items.clear();
        items.add("element7");
        _constraintSet2 = createSet(items);
        
        // setup a constraint set that will fail on all three
        // even though it has common element with all sets
        items.clear();
        items.add("element1");  // match 1 and 3
        items.add("element2");  // match 1
        items.add("element7");  // match 2
        _constraintSet3 = createSet(items);
    }

    protected AxiomaticSet createSet(List items) 
    {
        ConcreteAxiomaticSet  set = new ConcreteAxiomaticSet();
        set = new ConcreteAxiomaticSet();
        for (Iterator it = items.iterator(); it.hasNext();)
        {
            set.add(it.next());
        }
        return set;
    }
}
