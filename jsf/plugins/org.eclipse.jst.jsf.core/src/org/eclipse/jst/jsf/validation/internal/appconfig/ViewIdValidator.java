package org.eclipse.jst.jsf.validation.internal.appconfig;


/**
 * @author cbateman
 *
 */
public abstract class ViewIdValidator extends NodeValidationVisitor 
{
    /**
     * @param nodeType
     * @param nodeName
     */
    protected ViewIdValidator(final short nodeType, final String nodeName) 
    {
        super(nodeType, nodeName);
    }

}
