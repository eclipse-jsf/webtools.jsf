package org.eclipse.jst.jsf.common.runtime.internal.debug;

/**
 * Information about a rendered XML attribute
 * 
 * @author cbateman
 *
 */
public class RenderAttribute extends JSFMonitorMessage {

    /**
     * 
     */
    private static final long serialVersionUID = -4601065346261130623L;

    private final String  _name;        // the name of the attribute.  TODO: local vs. namespace?
    private final String  _value;       // the rendered value of the  node
    private final String  _propertyName;  // the property name on the corresponding component
                                          // may be null
    /**
     * @param name
     * @param value
     * @param propertyName
     */
    public RenderAttribute(String name, String value, String propertyName) {
        super();
        _name = name;
        _propertyName = propertyName;
        _value = value;
    }
    /**
     * @return the name of the attribute
     */
    public final String getName() {
        return _name;
    }
    /**
     * @return the attribute's value
     */
    public final String getValue() {
        return _value;
    }
    /**
     * @return the name of the corresponding component property if any.
     */
    public final String getPropertyName() {
        return _propertyName;
    }
}
