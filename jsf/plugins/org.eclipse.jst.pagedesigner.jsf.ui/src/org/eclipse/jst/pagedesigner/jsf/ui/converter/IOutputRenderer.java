package org.eclipse.jst.pagedesigner.jsf.ui.converter;

import org.w3c.dom.Element;

public interface IOutputRenderer {

	public Element render(ITagConverterContext tagConverterContext);

}
