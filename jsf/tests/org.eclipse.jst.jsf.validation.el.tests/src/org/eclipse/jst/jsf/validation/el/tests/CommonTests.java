package org.eclipse.jst.jsf.validation.el.tests;

import junit.framework.TestSuite;

import org.eclipse.jst.jsf.test.util.ConfigurableTestSuite;
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
import org.eclipse.jst.jsf.validation.el.tests.jsp.DataTableResolutionTestCase;
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

public class CommonTests 
{
    static void getFaces_common_suite(TestSuite suite)
    {
        //$JUnit-BEGIN$
        suite.addTest(new ConfigurableTestSuite(BadSyntaxTestCase.class));
        suite.addTest(new ConfigurableTestSuite(BeanPropertyResolutionTestCase.class));
        suite.addTest(new ConfigurableTestSuite(BeanVariableResolutionTestCase.class));
        suite.addTest(new ConfigurableTestSuite(LoadBundleResolutionTestCase.class));
        suite.addTest(new ConfigurableTestSuite(DataTableResolutionTestCase.class));
        suite.addTest(new ConfigurableTestSuite(BuiltInSymbolsTestCase.class));
        suite.addTest(new ConfigurableTestSuite(AssignabilityTestCase.class));
        suite.addTest(new ConfigurableTestSuite(JSPFunctionsTestCase.class));
        suite.addTest(new ConfigurableTestSuite(MethodBindingTestCase.class));
        suite.addTest(new ConfigurableTestSuite(PropertiesOfMapsTestCase.class));
        
        suite.addTest(new ConfigurableTestSuite(ArithmeticAddTestCase.class));
        suite.addTest(new ConfigurableTestSuite(ArithmeticDivideTestCase.class));
        suite.addTest(new ConfigurableTestSuite(ArithmeticMinusTestCase.class));
        suite.addTest(new ConfigurableTestSuite(ArithmeticModuloTestCase.class));
        suite.addTest(new ConfigurableTestSuite(ArithmeticMultiplyTestCase.class));

        suite.addTest(new ConfigurableTestSuite(GreaterThanEqTestCase.class));
        suite.addTest(new ConfigurableTestSuite(GreaterThanTestCase.class));
        suite.addTest(new ConfigurableTestSuite(LessThanEqTestCase.class));
        suite.addTest(new ConfigurableTestSuite(LessThanTestCase.class));

        suite.addTest(new ConfigurableTestSuite(LogicalAndTestCase.class));
        suite.addTest(new ConfigurableTestSuite(LogicalOrTestCase.class));
        suite.addTest(new ConfigurableTestSuite(LogicalEqualsTestCase.class));
        suite.addTest(new ConfigurableTestSuite(LogicalNotEqualsTestCase.class));
        suite.addTest(new ConfigurableTestSuite(LogicalNotTestCase.class));

        suite.addTest(new ConfigurableTestSuite(UnaryEmptyTestCase.class));
        suite.addTest(new ConfigurableTestSuite(UnaryMinusTestCase.class));
        
        suite.addTest(new ConfigurableTestSuite(MarkerOffsetsTestCase.class));
        //$JUnit-END$
    }
}
