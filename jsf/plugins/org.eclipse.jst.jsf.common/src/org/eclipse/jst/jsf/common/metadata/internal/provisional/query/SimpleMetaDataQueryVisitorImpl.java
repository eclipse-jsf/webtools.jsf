package org.eclipse.jst.jsf.common.metadata.internal.provisional.query;

import java.util.Iterator;
import java.util.Stack;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;


public class SimpleMetaDataQueryVisitorImpl implements IEntityQueryVisitor, ITraitQueryVisitor  {
	int level = 0;

	private String entityQuery;
	private String traitQuery;
	private Entity foundEntity;
	private Trait foundTrait;
	private SearchControl control;
	private boolean _stop;

	private Entity initialEntity;
	private EntityQueryComparator entityComparator;
	private SimpleResultSet/*<Entity>*/ entityResultSet;
	private SimpleResultSet/*<Trait>*/ traitResultSet;
//	private EntityStack eStack;


	public SimpleMetaDataQueryVisitorImpl() {
		super();
		control = new SearchControl();
	}
	
	public SimpleMetaDataQueryVisitorImpl(SearchControl control) {
		super();
		this.control = control;
	}
	

//	public Trait findTrait(final EObject root, final String entityQuery, final String traitQuery){
//		_stop = false;
//		foundEntity = null;
//		this.entityQuery = entityQuery;
//		Entity entity = findEntity(root, entityQuery) ;
//		if (entity != null){			
//			this.traitQuery = traitQuery;			
//			for (Iterator/*<Trait>*/ it=entity.getTraits().iterator();it.hasNext();){
//				Trait t = (Trait)it.next();
//				t.accept(this);
//				if (foundTrait != null)
//					break;
//			}
//		}
//		return foundTrait;
//	}

	
	public IResultSet/*<Trait>*/ findTraits(final Entity entity, final String traitQuery){
		_stop = false;
		
		if (entity != null){			
			this.traitQuery = traitQuery;			
			for (Iterator/*<Trait>*/ it=entity.getTraits().iterator();it.hasNext();){
				Trait t = (Trait)it.next();
				t.accept(this);
				if (stopVisiting())
					break;
			}
		}
		return getTraitResultSet();
	}
	
//	
//	/* (non-Javadoc)
//	 * @see org.eclipse.jst.jsf.common.metadata.internal.provisional.query.IMetaDataQueryVisitor#findEntity(org.eclipse.emf.ecore.EObject, java.lang.String)
//	 */
//	public Entity findEntity(final EObject root, final String entityQuery){
//		_stop = false;
//		foundEntity = null;
//		this.entityQuery = entityQuery;
//		
//		((Model)root).accept(this);				
//		return foundEntity;
//	}

	
//	public void visit(ModelKeyDescriptor key) {
//		ModelKeyDescriptor entity = (ModelKeyDescriptor)key;
////		String qry = getQueryPart(qry.Model);
////		if (getFullId(entity).equals(qry))
////			found = true;
//		
////		found = false;
//		
//	}
	


	public void visit(Trait trait) {		
		if (trait.equals(traitQuery))
			getTraitResultSet().addItem(trait);		
		
		checkShouldStopVisitingTraits();
	}


	public IResultSet/*<Entity>*/ findEntities(Entity initialEntityContext,
			String entityKey) {
		entityComparator = new EntityQueryComparator(this, initialEntityContext, entityKey);		
		initialEntityContext.accept(this);			
		
		return getEntityResultSet();
	}

	private SimpleResultSet/*<Entity>*/ getEntityResultSet(){
		if (entityResultSet == null){
			entityResultSet = new SimpleResultSet/*<Entity>*/();
		}
		return entityResultSet;
	}

	private SimpleResultSet/*<Trait>*/ getTraitResultSet(){
		if (traitResultSet == null){
			traitResultSet = new SimpleResultSet/*<Trait>*/();
		}
		return traitResultSet;
	}
	
	public void visit(Entity key) {		
		switch (entityComparator.compareTo(key)){
			case 0:
				getEntityResultSet().addItem(key);
				break;
			default:
				break;

		}
		checkShouldStopVisitingEntities();
	}
	public void visitCompleted() {
		entityComparator.popContext();
	}
	
	public boolean stopVisiting() {
		return _stop;
	}

	private void checkShouldStopVisitingEntities(){
		//implement how to set stop to signal to the entity accept() to skip visiting
		if (control.getCountLimit()== getEntityResultSet().size() && control.getCountLimit() != SearchControl.COUNT_LIMIT_NONE )
			_stop = true;
	}
	
	private void checkShouldStopVisitingTraits(){
		//this simple visitor will only find a single trait as it checks for exact match only currently 
		if (getTraitResultSet().size() > 0 )
			_stop = true;
	}


	private class EntityQueryComparator implements Comparable/*<Entity>*/{

		private String entityKey;
		private EntityStack stack;

		public EntityQueryComparator(SimpleMetaDataQueryVisitorImpl visitor, Entity initialEntity, String entityKey){
//			this.initialEntity = initialEntity;
			this.entityKey = entityKey;			
			stack = new EntityStack(initialEntity);
		}
		
		public int compareTo(Object entity) {			
			stack.push(entity);
			return entityKey.compareTo(getRelativeId());			
		}
		
		public void popContext(){
			stack.pop();
		}
		
		private String getRelativeId(){
			int size = stack.size();
			int i = 1;
			StringBuffer buf = new StringBuffer();
			while(i < size){
				Entity e = (Entity)stack.elementAt(i);
				if (buf.length()>0) 
					buf.append("/");
				buf.append(e.getId());			
				i++;
			}
			return buf.toString();
		}
	}
	
	private class EntityStack extends Stack/*<Entity>*/ {

		private Entity initialEntityContext;

		public EntityStack(Entity initialEntityContext) {
			this.initialEntityContext = initialEntityContext;
		}
	}

}
