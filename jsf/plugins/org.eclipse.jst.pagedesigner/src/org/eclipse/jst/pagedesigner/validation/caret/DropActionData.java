package org.eclipse.jst.pagedesigner.validation.caret;

import java.util.Collections;
import java.util.List;

public class DropActionData extends ActionData
{
    public DropActionData(int action, DropData data ) 
    {
        super(action, data);
    }

    public DropData getDropData()
    {
        return (DropData) getData();
    }
    
    public static class DropData
    {
        private final List    _tagIds;
        
        public DropData(List tagIds)
        {
            _tagIds = Collections.unmodifiableList(tagIds);
        }
        
        /**
         * @return the list of tag ids being dropped.  List
         * is unmodifiable
         */
        public List getTagIdentifiers()
        {
            return _tagIds;
        }
    }
}
