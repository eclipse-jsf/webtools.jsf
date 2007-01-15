package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

import java.util.ArrayList;
import java.util.List;


public class SimpleResultSet/*<T>*/ implements IResultSet/*<T>*/ {
	
	private List/*<T>*/ results;
	private int pos = -1;
	
	public SimpleResultSet(List/*<T>*/ results){
		super();
		this.results = results;
	}
	
	public SimpleResultSet(){
		results = new ArrayList/*<T>*/();
	}
	
	public boolean hasMoreElements() {
		if (results == null || results.size() ==0)
			return false;
		
		if (pos == results.size())
			return false;
				
		return true;
	}

	//public T nextElement() {
	public Object nextElement() {
		if (pos == -1) pos = 0;
		//T ret = (T)results.get(pos);
		Object ret = results.get(pos);
		pos++;
		return ret;
	}
	
	public void close() {
		//nothing to do really
		results = null;
	}
	
	//public void addItem(T item){
	public void addItem(Object item){
		results.add(item);
	}

	public int size(){
		return results.size();
	}
}
