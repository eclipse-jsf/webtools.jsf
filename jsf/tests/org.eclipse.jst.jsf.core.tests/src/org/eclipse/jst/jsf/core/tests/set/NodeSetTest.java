/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.set;

import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.set.mapping.ElementToTagIdentifierMapping;
import org.eclipse.jst.jsf.core.tagmatcher.XPathMatchingAlgorithm;
import org.eclipse.jst.jsf.core.tests.tagmatcher.BaseTagMatcherTestCase;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Document;

public class NodeSetTest extends BaseTagMatcherTestCase 
{
	private AxiomaticSet  _ancestorsOfInputText;
	private AxiomaticSet  _ancestorsOfCommandButton;
	private AxiomaticSet  _ancestorsOfOutputText;
	private AxiomaticSet  _inputTextSingletonSet;
	
	@Override
	protected void setUp() throws Exception 
	{
        _srcFileName = "/testfiles/jsps/testdata1.jsp.data";
        _destFileName = "/testdata1.jsp";
        
		super.setUp();
		
		_ancestorsOfInputText = getAncestorsOf("/view/html/body/form/panelGrid/inputText", 5);
		_ancestorsOfCommandButton = getAncestorsOf("/view/html/body/form/commandButton", 4);
		_ancestorsOfOutputText = getAncestorsOf("/view/html/body/form/h1/outputText", 5);
		
		Document doc = ((IDOMModel)_structuredModel).getDocument();
        XPathMatchingAlgorithm matcher = new XPathMatchingAlgorithm("/view/html/body/form/panelGrid/inputText");
        _inputTextSingletonSet = matcher.evaluate(doc);
        assertEquals(1, _inputTextSingletonSet.size());
	}

	@Override
	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

	public void testIntersect() 
	{
		AxiomaticSet set1 = 
			_ancestorsOfInputText.intersect(_ancestorsOfCommandButton);
		set1 = new ElementToTagIdentifierMapping().map(set1);
		assertEquals(4, set1.size());
		assertTrue(set1.contains(IJSFConstants.TAG_IDENTIFIER_FORM));
		assertTrue(set1.contains(IJSFConstants.TAG_IDENTIFIER_VIEW));
		// should not contain panelgrid because command button not in it
		assertFalse(set1.contains(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID));
		
		AxiomaticSet set2 =
			_ancestorsOfOutputText.intersect(_ancestorsOfCommandButton);
		set2 = new ElementToTagIdentifierMapping().map(set2);
		assertEquals(4, set2.size());
		assertTrue(set2.contains(IJSFConstants.TAG_IDENTIFIER_FORM));
		assertTrue(set2.contains(IJSFConstants.TAG_IDENTIFIER_VIEW));
		
		// result is same as command button's ancestors, but output text
		// has an additional h1 ancestor
		assertFalse(set2.equals(new ElementToTagIdentifierMapping().map(_ancestorsOfOutputText)));
		assertTrue(set2.equals(new ElementToTagIdentifierMapping().map(_ancestorsOfCommandButton)));
	}

	public void testIsEquivalent() throws Exception
	{
		// this == compareTo
		assertTrue(_ancestorsOfInputText.isEquivalent(_ancestorsOfInputText));
		
		// this._data == compareTo._data
		//assertTrue(_ancestorsOfInputText.isEquivalent((AxiomaticSet) ((NodeSet)_ancestorsOfInputText).clone()));
		
		// this.size() != compareTo.size();
		assertFalse(_ancestorsOfInputText.isEquivalent(_ancestorsOfCommandButton));
		
		// this.size() == compareTo.size() but contents not same
		assertFalse(_ancestorsOfInputText.isEquivalent(_ancestorsOfOutputText));
		
		// this.size() == compareTo.size() AND contents same
		assertTrue(_ancestorsOfInputText.isEquivalent(getAncestorsOf("/view/html/body/form/panelGrid/inputText", -1)));
	}

	public void testUnion() 
	{
		AxiomaticSet set1 = 
			_ancestorsOfInputText.union(_ancestorsOfCommandButton);
		set1 = new ElementToTagIdentifierMapping().map(set1);
		assertEquals(5, set1.size());
		assertTrue(set1.contains(IJSFConstants.TAG_IDENTIFIER_FORM));
		assertTrue(set1.contains(IJSFConstants.TAG_IDENTIFIER_VIEW));
		assertTrue(set1.contains(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID));
		
		AxiomaticSet set2 =
			_ancestorsOfOutputText.union(_ancestorsOfCommandButton);
		set2 = new ElementToTagIdentifierMapping().map(set2);
		assertEquals(5, set2.size());
		assertTrue(set2.contains(IJSFConstants.TAG_IDENTIFIER_FORM));
		assertTrue(set2.contains(IJSFConstants.TAG_IDENTIFIER_VIEW));
		assertFalse(set2.contains(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID));
	}

	public void testIsDisjoint() 
	{
		assertFalse(_ancestorsOfInputText.isDisjoint(_ancestorsOfCommandButton));
		assertFalse(_ancestorsOfInputText.isDisjoint(_ancestorsOfOutputText));
		assertFalse(_ancestorsOfInputText.isDisjoint(_ancestorsOfInputText));
		
		assertTrue(_ancestorsOfInputText.isDisjoint(_inputTextSingletonSet));
		assertTrue(_ancestorsOfOutputText.isDisjoint(_inputTextSingletonSet));
		assertTrue(_ancestorsOfCommandButton.isDisjoint(_inputTextSingletonSet));
	}

	public void testSubtract() 
	{
		// the only non-common parent is the panel grid
		AxiomaticSet  set1 = _ancestorsOfInputText.subtract(_ancestorsOfCommandButton);
		set1 = new ElementToTagIdentifierMapping().map(set1);
		assertEquals(1, set1.size());
		assertTrue(set1.contains(IJSFConstants.TAG_IDENTIFIER_PANEL_GRID));
		
		// all of command button's parents are also parents of inputText
		set1 = _ancestorsOfCommandButton.subtract(_ancestorsOfInputText);
		set1 = new ElementToTagIdentifierMapping().map(set1);
		assertEquals(0, set1.size());
		
		// no common elements
		set1 = _ancestorsOfCommandButton.subtract(_inputTextSingletonSet);
		assertEquals(_ancestorsOfCommandButton, set1);
	}
}
