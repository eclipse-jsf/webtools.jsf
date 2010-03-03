package org.eclipse.jst.jsf.test.util.mock;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class MockDataModel implements IDataModel
{
    private final String _id;
    private final Map<String, MockPropertyHolder> _properties;

    public MockDataModel(final String id, final Map<String, MockPropertyHolder>  properties)
    {
        _id = id;
        _properties = properties;
    }
    public String getID()
    {
        return _id;
    }

    public IDataModelOperation getDefaultOperation()
    {
        throw new UnsupportedOperationException();
    }

    public List<?> getExtendedContext()
    {
        throw new UnsupportedOperationException();
    }

    public Object getProperty(String propertyName)
    {
        MockPropertyHolder property = _properties.get(propertyName);
        if (property != null)
        {
            return property.getValue();
        }
        return null;
    }

    public Object getDefaultProperty(String propertyName)
    {
        MockPropertyHolder property = _properties.get(propertyName);
        if (property != null)
        {
            return property.getDefaultValue();
        }
        return null;
    }

    public int getIntProperty(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public boolean getBooleanProperty(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public String getStringProperty(String propertyName)
    {
        return (String) getProperty(propertyName);
    }

    public void setProperty(String propertyName, Object propertyValue)
    {
        MockPropertyHolder property = _properties.get(propertyName);
        if (property == null)
        {
            property = new MockPropertyHolder();
            _properties.put(propertyName, property);
        }
        property.setValue(propertyValue);
    }

    public void setIntProperty(String propertyName, int propertyValue)
    {
        throw new UnsupportedOperationException();
    }

    public void setBooleanProperty(String propertyName, boolean propertyValue)
    {
        throw new UnsupportedOperationException();
    }

    public void setStringProperty(String propertyName, String propertyValue)
    {
        setProperty(propertyName, propertyValue);
    }

    public boolean addNestedModel(String nestedModelName, IDataModel dataModel)
    {
        throw new UnsupportedOperationException();
   }

    public IDataModel removeNestedModel(String nestedModelName)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isNestedModel(String nestedModelName)
    {
        throw new UnsupportedOperationException();
    }

    public IDataModel getNestedModel(String nestedModelName)
    {
        throw new UnsupportedOperationException();
    }

    public Collection<?> getNestedModels()
    {
        throw new UnsupportedOperationException();
    }

    public Collection<?> getNestedModelNames()
    {
        throw new UnsupportedOperationException();
    }

    public Collection<?> getNestingModels()
    {
        throw new UnsupportedOperationException();
    }

    public Collection<?> getBaseProperties()
    {
        throw new UnsupportedOperationException();
    }

    public Collection<?> getNestedProperties()
    {
        throw new UnsupportedOperationException();
    }

    public Collection<?> getAllProperties()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isBaseProperty(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isProperty(String propertyName)
    {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isNestedProperty(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isPropertySet(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isPropertyEnabled(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isPropertyValid(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public IStatus validateProperty(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public boolean isValid()
    {
        throw new UnsupportedOperationException();
    }

    public IStatus validate()
    {
        throw new UnsupportedOperationException();
    }

    public IStatus validate(boolean stopAtFirstFailure)
    {
        throw new UnsupportedOperationException();
    }

    public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public DataModelPropertyDescriptor[] getValidPropertyDescriptors(
            String propertyName)
    {
        throw new UnsupportedOperationException();
    }

    public void addListener(IDataModelListener dataModelListener)
    {
        throw new UnsupportedOperationException();
    }

    public void removeListener(IDataModelListener dataModelListener)
    {
        throw new UnsupportedOperationException();
    }

    public void notifyPropertyChange(String propertyName, int eventType)
    {
        throw new UnsupportedOperationException();
    }

    public void dispose()
    {
        throw new UnsupportedOperationException();
    }

    public static class MockPropertyHolder
    {
        private Object    _value;
        private final Object    _defaultValue;
        public MockPropertyHolder(Object value, Object defaultValue)
        {
            super();
            _value = value;
            _defaultValue = defaultValue;
        }
        public MockPropertyHolder(Object defaultValue)
        {
            this(null, defaultValue);
        }
        public MockPropertyHolder()
        {
            this(null, null);
        }

        public Object getValue()
        {
            return _value;
        }
        public Object getDefaultValue()
        {
            return _defaultValue;
        }
        public void setValue(Object value)
        {
            _value = value;
        }
    }
}
