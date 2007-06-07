package org.eclipse.jst.jsf.validation.el.tests.jsp.ext;

import org.eclipse.jst.jsf.validation.el.tests.jsp.BeanPropertyResolutionTestCase;

/**
 * Test cases for bean property resolution
 * 
 * @author cbateman
 */
public class BeanPropertyResolutionTestCase_JSFExt extends BeanPropertyResolutionTestCase 
{
    public BeanPropertyResolutionTestCase_JSFExt() {
        super("/testdata/jsps/beanPropertyResolution.jsp.data", "/beanPropertyResolution.jsf");
    }
}
