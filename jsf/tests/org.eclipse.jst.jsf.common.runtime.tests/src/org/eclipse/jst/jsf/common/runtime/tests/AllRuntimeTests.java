package org.eclipse.jst.jsf.common.runtime.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.eclipse.jst.jsf.common.runtime.tests.model.TestViewObject;
import org.eclipse.jst.jsf.common.runtime.tests.model.bean.TestDataModelInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.behavioural.TestActionSource2Info;
import org.eclipse.jst.jsf.common.runtime.tests.model.behavioural.TestValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.component.TestComponentInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.component.TestComponentTypeInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.component.TestUICommandInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.component.TestUIDataInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.component.TestUIFormInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.component.TestUIInputInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.component.TestUIOutputInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.decorator.TestComponentDecorator;

public class AllRuntimeTests {
    /**
     * @return the test suite
     */
    public static Test suite() 
    {
        TestSuite suite = new TestSuite("Test for org.eclipse.jst.jsf.common.runtime");
        //$JUnit-BEGIN$
        
        suite.addTestSuite(TestValueHolderInfo.class);
        suite.addTestSuite(TestComponentDecorator.class);
        suite.addTestSuite(TestDataModelInfo.class);
        suite.addTestSuite(TestComponentTypeInfo.class);
        suite.addTestSuite(TestActionSource2Info.class);
        
        // test the main objects
        suite.addTestSuite(TestViewObject.class);
        suite.addTestSuite(TestComponentInfo.class);
        suite.addTestSuite(TestUIOutputInfo.class);
        suite.addTestSuite(TestUIInputInfo.class);
        suite.addTestSuite(TestUICommandInfo.class);
        suite.addTestSuite(TestUIFormInfo.class);
        suite.addTestSuite(TestUIDataInfo.class);
        
        //$JUnit-END$
        return suite;
    }

}
