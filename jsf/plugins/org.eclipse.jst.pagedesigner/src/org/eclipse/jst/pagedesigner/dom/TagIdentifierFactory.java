package org.eclipse.jst.pagedesigner.dom;

import org.w3c.dom.Element;

public final class TagIdentifierFactory 
{
    public static TagIdentifier createJSPTagWrapper(final String uri, final String tagName)
    {
        return new JSPTagIdentifier(uri, tagName);
    }
    
    public static TagIdentifier createDocumentTagWrapper(final Element element)
    {
        return new DocumentTagIdentifier(element);
    }
    
    private TagIdentifierFactory()
    {
        // static class, no external instantiation
    }
    
//    private static class CacheMap extends AbstractMap
//    {
//        private Set  _entrySet = new HashSet();
//        
//        public Set entrySet() {
//            return _entrySet;
//        }
//        
//        public Object put(Object key, Object value) 
//        {
//            if (! (key instanceof String))
//            {
//                throw new IllegalArgumentException("key must be string: "+key);
//            }
//            
//            if (! (value instanceof TagWrapper))
//            {
//                throw new IllegalArgumentException("value must be a TagWrapper: "+value);
//            }
//                
//            final String keyStr = (String) key;
//            final TagWrapper tagValue = (TagWrapper) value;
//            
//            
//            
//        }
//
//        private static class MapEntry implements Entry
//        {
//            private String    _key;
//            private Map       _value;
//            
//            public Object getKey() {
//                return _key;
//            }
//
//            public Object getValue() {
//                return _value;
//            }
//
//            public Object setValue(Object arg0) {
//                Object oldValue = _value;
//                _value = (Map) arg0;
//                return oldValue;
//            }
//            
//        }
//    }
}

