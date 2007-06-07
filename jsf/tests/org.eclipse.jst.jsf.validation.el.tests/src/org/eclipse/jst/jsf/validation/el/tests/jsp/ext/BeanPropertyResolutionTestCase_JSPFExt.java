package org.eclipse.jst.jsf.validation.el.tests.jsp.ext;

import org.eclipse.jst.jsf.validation.el.tests.jsp.BeanPropertyResolutionTestCase;

/**
 * Test cases for bean property resolution
 * 
 * @author cbateman
 */
public class BeanPropertyResolutionTestCase_JSPFExt extends BeanPropertyResolutionTestCase 
{
    public BeanPropertyResolutionTestCase_JSPFExt() {
        super("/testdata/jsps/beanPropertyResolution.jsp.data", "/beanPropertyResolution.jspf");
    }
}
