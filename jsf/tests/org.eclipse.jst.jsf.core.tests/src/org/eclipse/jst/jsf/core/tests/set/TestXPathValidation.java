package org.eclipse.jst.jsf.core.tests.set;

import org.eclipse.jst.jsf.common.sets.AxiomaticSet;
import org.eclipse.jst.jsf.common.sets.ConcreteAxiomaticSet;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.core.internal.tld.TagIdentifierFactory;
import org.eclipse.jst.jsf.core.set.constraint.MemberConstraint;
import org.eclipse.jst.jsf.core.set.mapping.ElementToTagIdentifierMapping;
import org.eclipse.jst.jsf.core.tagmatcher.XPathMatchingAlgorithm;
import org.eclipse.jst.jsf.core.tests.tagmatcher.BaseTagMatcherTestCase;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class TestXPathValidation extends BaseTagMatcherTestCase {

    protected void setUp() throws Exception {
        _srcFileName = "/testfiles/jsps/testdata1.jsp.data";
        _destFileName = "/testdata1.jsp";
        super.setUp();
    }

    public void testValidateParentMembership()
    {
        AxiomaticSet inputAncestors = getAncestorsOfInputText();
        
        //map the set to TagIds
        inputAncestors = new ElementToTagIdentifierMapping().map(inputAncestors);
        // create constraint set: form and view must be parents of inputText
        AxiomaticSet constraintSet = new ConcreteAxiomaticSet();
        constraintSet.add(TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, "form"));
        constraintSet.add(TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_CORE, "view"));

        // create an apply member constraint
        MemberConstraint memberConstraint = new MemberConstraint(constraintSet);
        assertTrue(memberConstraint.passesConstraint(inputAncestors));
        
        // test a constraint set that isn't satisfied: no ancestor is inputLabel
        constraintSet.clear();
        constraintSet.add(TagIdentifierFactory.createJSPTagWrapper(ITLDConstants.URI_JSF_HTML, "form"));
        memberConstraint = new MemberConstraint(constraintSet);
        assertTrue(memberConstraint.failsConstraint(inputAncestors));
    }
    
    protected AxiomaticSet getAncestorsOfInputText()
    {
        Document doc = ((IDOMModel)_structuredModel).getDocument();
        System.out.println(System.currentTimeMillis());
        XPathMatchingAlgorithm matcher = new XPathMatchingAlgorithm("/view/html/body/form/panelGrid/inputText");
        AxiomaticSet  set = matcher.evaluate(doc);
        assertEquals(1, set.size());
     
        // get all of the ancestors of the inputText
        final Node inputText = (Node) set.getFirstElement();
        matcher = new XPathMatchingAlgorithm("ancestor::*");
        return matcher.evaluate(inputText);
    }
}
