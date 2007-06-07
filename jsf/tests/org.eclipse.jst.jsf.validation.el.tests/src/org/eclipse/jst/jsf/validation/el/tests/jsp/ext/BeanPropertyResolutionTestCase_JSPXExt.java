package org.eclipse.jst.jsf.validation.el.tests.jsp.ext;

import org.eclipse.jst.jsf.validation.el.tests.jsp.BeanPropertyResolutionTestCase;

/**
 * Test cases for bean property resolution
 * 
 * @author cbateman
 */
public class BeanPropertyResolutionTestCase_JSPXExt extends BeanPropertyResolutionTestCase 
{
    public BeanPropertyResolutionTestCase_JSPXExt() {
        super("/testdata/jsps/beanPropertyResolution.jsp.data", "/beanPropertyResolution.jspx");
    }
}
