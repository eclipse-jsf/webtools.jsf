package org.eclipse.jst.jsf.validation.el.tests;
/*******************************************************************************
 * Copyright (c) 2005 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 

import org.eclipse.jst.jsf.validation.el.tests.jsp.ArithmeticAddTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.ArithmeticDivideTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.ArithmeticMinusTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.ArithmeticModuloTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.ArithmeticMultiplyTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.AssignabilityTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.BadSyntaxTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.BeanPropertyResolutionTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.BeanVariableResolutionTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.BuiltInSymbolsTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.GreaterThanEqTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.GreaterThanTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.JSPFunctionsTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.LessThanEqTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.LessThanTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.LoadBundleResolutionTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.LogicalAndTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.LogicalEqualsTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.LogicalNotEqualsTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.LogicalNotTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.LogicalOrTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.MarkerOffsetsTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.MethodBindingTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.PropertiesOfMapsTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.UnaryEmptyTestCase;
import org.eclipse.jst.jsf.validation.el.tests.jsp.UnaryMinusTestCase;

import junit.framework.Test;
import junit.framework.TestSuite;


/**
 * The full test suite for core.tests
 * @author cbateman
 *
 */
public class AllTests {

    /**
     * @return the test suite
     */
    public static Test suite() 
    {
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.validation.el.tests");
        //$JUnit-BEGIN$
        suite.addTestSuite(BadSyntaxTestCase.class);
        suite.addTestSuite(BeanPropertyResolutionTestCase.class);
        suite.addTestSuite(BeanVariableResolutionTestCase.class);
        suite.addTestSuite(LoadBundleResolutionTestCase.class);
        suite.addTestSuite(BuiltInSymbolsTestCase.class);
        suite.addTestSuite(AssignabilityTestCase.class);
        suite.addTestSuite(JSPFunctionsTestCase.class);
        suite.addTestSuite(MethodBindingTestCase.class);
        suite.addTestSuite(PropertiesOfMapsTestCase.class);
        
        suite.addTestSuite(ArithmeticAddTestCase.class);
        suite.addTestSuite(ArithmeticDivideTestCase.class);
        suite.addTestSuite(ArithmeticMinusTestCase.class);
        suite.addTestSuite(ArithmeticModuloTestCase.class);
        suite.addTestSuite(ArithmeticMultiplyTestCase.class);

        suite.addTestSuite(GreaterThanEqTestCase.class);
        suite.addTestSuite(GreaterThanTestCase.class);
        suite.addTestSuite(LessThanEqTestCase.class);
        suite.addTestSuite(LessThanTestCase.class);

        suite.addTestSuite(LogicalAndTestCase.class);
        suite.addTestSuite(LogicalOrTestCase.class);
        suite.addTestSuite(LogicalEqualsTestCase.class);
        suite.addTestSuite(LogicalNotEqualsTestCase.class);
        suite.addTestSuite(LogicalNotTestCase.class);

        suite.addTestSuite(UnaryEmptyTestCase.class);
        suite.addTestSuite(UnaryMinusTestCase.class);
        
        suite.addTestSuite(MarkerOffsetsTestCase.class);

        //$JUnit-END$
        return suite;
    }
    
}

