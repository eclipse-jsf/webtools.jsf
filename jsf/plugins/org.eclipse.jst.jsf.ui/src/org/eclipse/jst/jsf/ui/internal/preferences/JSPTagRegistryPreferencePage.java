package org.eclipse.jst.jsf.ui.internal.preferences;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jst.jsf.common.internal.policy.OrderedListProvider;
import org.eclipse.jst.jsf.common.internal.policy.OrderedListProvider.OrderableObject;
import org.eclipse.jst.jsf.common.ui.internal.preferences.StrategyOrderingPanel;
import org.eclipse.jst.jsf.core.internal.JSFCorePlugin;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDRegistryPreferences;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.registry.TLDRegistryPreferences.StrategyIdentifier;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

/**
 * Preferences for the JSP tag registry.
 * 
 * @author cbateman
 * 
 */
public class JSPTagRegistryPreferencePage extends PreferencePage implements
        IWorkbenchPreferencePage
{
    private final TLDRegistryPreferences _tldRegistryPreferences;
    private StrategyOrderingPanel        _panel;
    private OrderedListProvider       _provider;

    /**
     * Default constructor: required by the preference page
     */
    public JSPTagRegistryPreferencePage()
    {
        _tldRegistryPreferences = new TLDRegistryPreferences(
                getPreferenceStore());
        _tldRegistryPreferences.load();
    }

    @Override
    protected Control createContents(final Composite parent)
    {
        _provider = _tldRegistryPreferences.getOrderedListProvider();
        _panel = new StrategyOrderingPanel(
                _provider,
                new MyLabelProvider(),
                "Set the order in which tag resolvers are consulted when constructing the JSP tag registry");
        final Control contents = _panel.createContents(parent);
        _panel.refresh();
        return contents;
    }

    public void init(final IWorkbench workbench)
    {
        // do nothing
    }

    protected void performApply()
    {
        if (_tldRegistryPreferences.isDirty())
        {
            _tldRegistryPreferences.commit(getPreferenceStore());
        }
    }

    protected void performDefaults()
    {
        _tldRegistryPreferences.setDefaults();
        //_provider.setStrategies(_tldRegistryPreferences.getStrategyIdOrdering());
        _panel.refresh();
        super.performDefaults();
    }

    public boolean performOk()
    {
        performApply();
        return true;
    }

    @Override
    protected IPreferenceStore doGetPreferenceStore()
    {
        return JSFCorePlugin.getDefault().getPreferenceStore();
    }


    private static class MyLabelProvider extends LabelProvider implements
            ITableLabelProvider
    {
        public Image getColumnImage(final Object element, final int columnIndex)
        {
            // no image.
            return null;
        }

        public String getColumnText(final Object element, final int columnIndex)
        {
            switch (columnIndex)
            {
                case 0:
                    if (element instanceof OrderableObject)
                    {
                        return ((StrategyIdentifier)(((OrderableObject) element).getObject())).getDisplayName();
                    }
                default:
                    Assert.isTrue(false);
                    return null;
            }
        }
    }
}
