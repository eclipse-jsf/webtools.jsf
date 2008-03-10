package org.eclipse.jst.jsf.ui.internal.tagregistry;

import java.util.Iterator;
import java.util.Set;

import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.jst.jsf.core.internal.TagRegistryFactoryInfo;
import org.eclipse.jst.jsf.ui.internal.tagregistry.TaglibContentProvider.TagRegistryInstance;

/**
 * Details form for a tag registry
 * 
 * @author cbateman
 * 
 */
public class TagRegistryDetailsForm extends AbstractDetailsForm
{
    private TagRegistryInstance _tagRegistry;

    @Override
    protected void doUpdateSelection(final Object newSelection)
    {
        if (newSelection instanceof TagRegistryInstance)
        {
            _tagRegistry = (TagRegistryInstance) newSelection;
            TagRegistryFactoryInfo info = _tagRegistry.getInfo();

            if (info != null)
            {
                final String format = "<form><p><b>Description:</b> %s</p> <p><b>Id:</b> %s</p> <p><b>Content-Types:</b> %s</p></form>";
                final String description = info.getDescription();
                final String id = info.getId();
                Set<IContentType> contentTypes = info.getContentTypes();
                String contentTypeLabel = "";
                Iterator<IContentType> it = contentTypes.iterator();
                for (int i = 0; i < contentTypes.size() - 1 && it.hasNext(); i++)
                {
                    final IContentType ctype = it.next();
                    contentTypeLabel += ctype.getName() + ",";
                }

                if (it.hasNext())
                {
                    final IContentType ctype = it.next();
                    contentTypeLabel += ctype.getName();
                }

                getTextSection().setText(
                        String
                                .format(format, description, id,
                                        contentTypeLabel), true, false);
                getTextSection().refresh();
            }
        }

    }

    @Override
    protected String getTitle()
    {
        return "Tag Registry";
    }

}
