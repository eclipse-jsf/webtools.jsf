package org.eclipse.jst.jsf.core.internal.types;

import org.eclipse.emf.common.util.Diagnostic;

/**
 * Static utility class used to compare two CompositeTypes for compatability
 * 
 * @author cbateman
 *
 */
public final class TypeComparator
{
    /**
     * @param firstType
     * @param secondType
     * @return true if firstType is assignable to secondType or vice-versa,
     * depending on their assignment and runtime types
     */
    public static Diagnostic calculateTypeCompatibility(CompositeType firstType,
                                                        CompositeType secondType)
    {
        return Diagnostic.OK_INSTANCE;
    }
}
