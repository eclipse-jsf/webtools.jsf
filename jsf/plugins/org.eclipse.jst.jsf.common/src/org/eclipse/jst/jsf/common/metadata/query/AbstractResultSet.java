package org.eclipse.jst.jsf.common.metadata.query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Default implementation of {@link IResultSet}.   Developers may subclass.
 *
 */
public abstract class AbstractResultSet/*<T>*/ implements IResultSet/*<T>*/ {
	
	private List/*<T>*/ results;
	
	/**
	 * Constructor passing a list to hold the results
	 * @param results 
	 */
	public AbstractResultSet(List/*<T>*/ results){
		super();
		this.results = results;
	}
	
	/**
	 * Constructor
	 */
	public AbstractResultSet(){
		results = new ArrayList/*<T>*/();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IResultSet#close()
	 */
	public void close() {
		//nothing to do really
		if (results != null)
			results.clear();
	}
	
	/**
	 * @param item
	 */
	public void addItem(Object item){
		getInternalResults().add(item);
	}

    // API: should return Collection so that subs can use
    // co-variance to return other sorts of collections
    // API: consider making final and make getInternalResults protected abstract
	public List/*<T>*/ getResults() {
		if (results == null)
			return Collections.EMPTY_LIST;
	
		return results;
	}

	/**
	 * @return resultset size
	 */
    // API: should make this final and force
    // implementers to handle getInternalResults
	public int size(){
		if (results == null)
			return 0;
		
		return getInternalResults().size();
	}

	private List getInternalResults() {
		if (results == null){
			results = new ArrayList();
		}
		return results;
	}

}
