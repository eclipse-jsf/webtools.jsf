/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.set;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.ConcreteAxiomaticSet;

public class ConcreteAxiomaticSetTest extends TestCase 
{
	AxiomaticSet  	_primaryColours;
	AxiomaticSet	_secondaryColours;
	AxiomaticSet	_allMainColours;
	
	AxiomaticSet	_palette;
	AxiomaticSet	_overlappingSet;
	AxiomaticSet    _emptySet;
	
	@SuppressWarnings("unchecked")
    @Override
	protected void setUp() throws Exception 
	{
		super.setUp();
		
		// make concreteSet1 disjoint with concreteSet2 and non-disjoint with concrete3
		
		_emptySet = new ConcreteAxiomaticSet();
		
		_primaryColours = new ConcreteAxiomaticSet();
		_primaryColours.add("blue");
		_primaryColours.add("red");
		_primaryColours.add("yellow");

		_secondaryColours = new ConcreteAxiomaticSet();
		_secondaryColours.add("green");
		_secondaryColours.add("purple");
		_secondaryColours.add("orange");
		
		_allMainColours = new ConcreteAxiomaticSet();
		_allMainColours.add("blue");
		_allMainColours.add("red");
		_allMainColours.add("yellow");
		_allMainColours.add("green");
		_allMainColours.add("purple");
		_allMainColours.add("orange");
		
		_palette = new ConcreteAxiomaticSet();
		_palette.add("blue");
		_palette.add("green");
		_palette.add("burgundy");
		
		_overlappingSet = new ConcreteAxiomaticSet();
		_overlappingSet.add("blue");
		_overlappingSet.add("red");
		_overlappingSet.add("yellow");
		_overlappingSet.add("green");
		_overlappingSet.add("purple");
		_overlappingSet.add("burgundy");
	}

	@Override
	protected void tearDown() throws Exception 
	{
		super.tearDown();
		
		_primaryColours = null;
		_secondaryColours = null;
		_palette = null;
		_emptySet = null;
	}

	public void testIntersect() 
	{
		// intersect the empty is set an identity
		assertTrue(_primaryColours.intersect(_secondaryColours).isEmpty());
		
		// non-disjoint intersection not empty
		assertFalse(_primaryColours.intersect(_palette).isEmpty());
		assertEquals(1, _primaryColours.intersect(_palette).size());
		assertEquals("blue", _primaryColours.intersect(_palette).getFirstElement());

		// intersection is commutative
		assertEquals(_primaryColours.intersect(_palette), _palette.intersect(_primaryColours));
		//intersection is associative
		assertEquals(_primaryColours.intersect(_overlappingSet).intersect(_palette)
					, _overlappingSet.intersect(_palette).intersect(_primaryColours));		
		
		// intersection of disjoint sets are empty
		assertTrue(_primaryColours.intersect(_secondaryColours).isEmpty());
		
		// intersection of subset of a set with the set is the subset
		assertEquals(_primaryColours, _allMainColours.intersect(_primaryColours));
		assertEquals(_secondaryColours, _allMainColours.intersect(_secondaryColours));
	}

	public void testIsEquivalent() 
	{
		// identity: a set is equal to itself
		assertTrue(_primaryColours.isEquivalent(_primaryColours));
		
		// two unequal sets are not equivalent
		assertFalse(_primaryColours.isEquivalent(_secondaryColours));
	}

	public void testUnion() 
	{
		// union with empty is identity
		assertEquals(_primaryColours, _primaryColours.union(_emptySet));
		
		// primary and secondary union to allMain
		assertEquals(_allMainColours, _primaryColours.union(_secondaryColours));
		
		// union is commutative
		assertEquals(_primaryColours.union(_secondaryColours)
				    , _secondaryColours.union(_primaryColours));
		// union is associative
		assertEquals(_primaryColours.union(_secondaryColours).union(_overlappingSet), 
						_secondaryColours.union(_overlappingSet).union(_primaryColours));
	}

	public void testIsDisjoint() {
		// primary colours and secondary have no common elements
		assertTrue(_primaryColours.isDisjoint(_secondaryColours));
		
		// all sets are disjoint with the empty set
		assertTrue(_primaryColours.isDisjoint(_emptySet));
		assertTrue(_secondaryColours.isDisjoint(_emptySet));
		assertTrue(_emptySet.isDisjoint(_allMainColours));
		
		// subset is not disjoint with set
		assertFalse(_primaryColours.isDisjoint(_allMainColours));
	}

	public void testGetFirstElement() 
	{
		// TODO:
	}

	public void testSubtract() {
		// A - B = A if A and B are disjoint
		assertEquals(_primaryColours, _primaryColours.subtract(_secondaryColours));
		
		// A - B = C where A = B U C and B and C are disjoint
		assertEquals(_secondaryColours, _allMainColours.subtract(_primaryColours));
		assertEquals(_primaryColours, _allMainColours.subtract(_secondaryColours));
		
		// A - B = A if B is empty
		assertEquals(_secondaryColours, _secondaryColours.subtract(_emptySet));
		
		AxiomaticSet remainder = _allMainColours.subtract(_overlappingSet);
		assertEquals(1, remainder.size());
		assertTrue(remainder.contains("orange"));
	}

}
