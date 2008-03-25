
package org.eclipse.jst.jsf.ui.internal.validation;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.jst.jsf.common.internal.strategy.IIdentifiableStrategy;

/**
 * A strategy for overriding the severity of diagnostics.
 */
abstract class SeverityOverrideStrategy implements IIdentifiableStrategy<Diagnostic, Integer, String>
{
    private final String        _id;

    public SeverityOverrideStrategy(final String id)
    {
        _id = id;
    }

    public abstract Integer override(Diagnostic diagnostic);

    public abstract String getDisplayName();

    public Integer getNoResult()
    {
        return Integer.valueOf(-1);
    }

    public final Integer perform(Diagnostic diagnostic) throws Exception
    {
        return override(diagnostic);
    }

    public final String getId()
    {
        return _id;
    }
}