package org.eclipse.jst.jsf.ui.internal.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jst.jsf.common.runtime.internal.model.IDesigntimeAdapter;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentTypeInfo;

/**
 * Generates UI form text strings for certain view objects.
 * 
 * @author cbateman
 * 
 */
public final class ViewObjectPresenter
{
    /**
     * @param typeInfo
     * @return a presentation string containing all the summary information for
     *         a typeInfo. Excludes interfaces and superclasses.
     */
    public static String present(final ComponentTypeInfo typeInfo)
    {
        final String formatText = "<form>%s</form>"; //$NON-NLS-1$
        final String componentType = typeInfo.getComponentType();
        final String componentFamily = typeInfo.getComponentFamily();
        final String renderType = typeInfo.getRenderFamily();
        final String componentClass = typeInfo.getClassName();
        final List<TitleValuePair> values = new ArrayList<TitleValuePair>();

        values.add(new TitleValuePair(Messages.ViewObjectPresenter_ComponentType,
                componentType == null ? "" //$NON-NLS-2$
                        : componentType));
        values.add(new TitleValuePair(Messages.ViewObjectPresenter_ComponentClass,
                componentClass == null ? "" //$NON-NLS-2$
                        : componentClass));
        values.add(new TitleValuePair(Messages.ViewObjectPresenter_ComponentFamily,
                componentFamily == null ? "" //$NON-NLS-2$
                        : componentFamily));
        values.add(new TitleValuePair(Messages.ViewObjectPresenter_RenderType,
                renderType == null ? "" : renderType)); //$NON-NLS-2$
        return String.format(formatText, ViewObjectPresenter.createLines(values));
    }
    
    /**
     * @param compInfo
     * @param typeInfo
     * @return a presentation string containing all the interfaces in compInfo
     * including adapters.
     */
    public static String presentCompInterfaces(final ComponentTypeInfo typeInfo, final ComponentInfo compInfo)
    {
        final Set<String> interfaces = new HashSet<String>();

        interfaces.addAll(Arrays.asList(typeInfo.getInterfaces()));

        if (compInfo != null)
        {
            for (final Map.Entry entry : (Set<Map.Entry>) compInfo.getAllAdapters()
                    .entrySet())
            {
                final Object infObject = entry.getValue();
                if (infObject instanceof IDesigntimeAdapter)
                {
                    interfaces.addAll(Arrays.asList(((IDesigntimeAdapter)infObject).getInterfaces()));
                }
            }
        }
        final List<String> sortedInterfaceNames = new ArrayList<String>(
                interfaces);
        Collections.sort(sortedInterfaceNames);

        String text = "";
        for (final String name : sortedInterfaceNames)
        {
            text += ViewObjectPresenter.createLine(null, name);
        }
        return String.format("<form>%s</form>", text);
    }

    /**
     * @param values
     * @return a grouping of createLine style lines using the key/value pairs in
     *         values for the title and value.
     */
    public static String createLines(final List<TitleValuePair> values)
    {
        String lines = ""; //$NON-NLS-1$
        for (final TitleValuePair valueEntry : values)
        {
            final String title = valueEntry.getTitle();
            final String value = valueEntry.getValue();

            lines += createLine(title, value);
        }
        return lines;
    }

    /**
     * @param title
     *            may be null
     * @param value
     * @return a single paragraphed line containing value with title preceding
     *         in bold if not null
     */
    public static String createLine(final String title, final String value)
    {
        if (title == null)
        {
            return String.format("<p>%s</p>", value); //$NON-NLS-1$
        }
        return String.format("<p><b>%s</b>: %s</p>", title, value); //$NON-NLS-1$
    }

    /**
     * A title/value pair. Title may be null. Comparable canonically based title
     * (ascending).
     * 
     * @author cbateman
     * 
     */
    public final static class TitleValuePair implements
            Comparable<TitleValuePair>
    {
        private final String _title;
        private final String _value;

        /**
         * @param title
         * @param value
         */
        public TitleValuePair(String title, String value)
        {
            super();
            if (value == null)
            {
                throw new IllegalArgumentException("Value must not be null");
            }

            _title = title;
            _value = value;
        }

        /**
         * @return the title
         */
        protected final String getTitle()
        {
            return _title;
        }

        /**
         * @return the value
         */
        protected final String getValue()
        {
            return _value;
        }

        public int compareTo(TitleValuePair other)
        {
            if (this == other)
            {
                return 0;
            }
            
            if (other == null)
            {
                return 1;
            }
            
            if (_title == null)
            {
                return other.getTitle() == null ? 0 : 1;
            }
            else if (other.getTitle() == null)
            {
                // _title is not null
                return 1;
            }
            
            return _title.compareTo(other.getTitle());
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            
            if (obj instanceof TitleValuePair)
            {
                
                final TitleValuePair other = (TitleValuePair) obj;
                final String otherValue =  other.getValue();
                
                if (!otherValue.equals(_value))
                {
                    return false;
                }
                
                final String otherTitle = other.getTitle();
                if (_title == null)
                {
                    return otherTitle == null;
                }
                else if (otherTitle == null)
                {
                    return _title == null;
                }
                return _title.equals(otherTitle);
            }
            return false;
        }

        @Override
        public int hashCode()
        {
            return getTitle().hashCode();
        }
        
        
    }

    private ViewObjectPresenter()
    {
        // no instantiation
    }
}
