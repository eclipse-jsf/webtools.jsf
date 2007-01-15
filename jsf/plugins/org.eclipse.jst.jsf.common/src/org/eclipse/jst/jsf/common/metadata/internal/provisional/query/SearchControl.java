package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

public class SearchControl {
	
	//scope levels
	public static final int CURRENT_LEVEL = 0;
	public static final int ONE_LEVEL = 1;
	public static final int ALL_LEVELS = 2;
	
	//No count limit var
	public static final int COUNT_LIMIT_NONE = -1;
	
	//default settings
	private int countLimit = COUNT_LIMIT_NONE;
	private int scope = ALL_LEVELS;
	
	public SearchControl(){
		//use default settings
	}
	
	public SearchControl(int countLimit, int scope){
		this.scope = scope;
		this.countLimit = countLimit;
	}
	
	public void setCountLimit(int limit){
		this.countLimit = limit;
	}
	
	public int getCountLimit(){
		return countLimit;
	}
	
	public void setScope(int scope){
		this.scope= scope;
	}
	
	public int getScope(){
		return scope;
	}
}
