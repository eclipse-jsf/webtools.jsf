/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.internal.jsflibraryregistry.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * Encapsulates a chain of one or more version steps into a full upgrade.
 * 
 * @author cbateman
 * @deprecated
 */
public class UpgradeOperation extends AbstractOperation {

	private final List/*<VersionUpgradeOperation*/ _stepOperations;

	/**
	 * @param label
	 */
	public UpgradeOperation(String label) {
		super(label);
		_stepOperations = new ArrayList();
	}

	/**
	 * Add upgrade operation to the list of operations.
	 * Execute, undo and redo call each operation's corresponding
	 * method in the order they are added to the list.
	 * @param operation
	 */
	public void addVersionUpgrade(VersionUpgradeOperation operation)
	{
		_stepOperations.add(operation);
	}
	
	/**
	 * @param monitor 
	 * @param info 
	 * @return an OK status if all goes well.  The status for the first op
	 * that fails otherwise.
	 * @throws ExecutionException 
	 */
	public IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
	    
	    boolean upgraded = false;
	    
		for (final Iterator it = _stepOperations.iterator(); it.hasNext();)
		{
			VersionUpgradeOperation op = (VersionUpgradeOperation) it.next();
			// TODO: move to covariant
			UpgradeStatus status = (UpgradeStatus) op.execute(monitor, info);
			
			// fail fast
			if (status.getSeverity() != IStatus.OK)
			{
				return status;
			}
			
			// once an upgrade is flagged, set flag
			if (status.isUpgradeOccurred())
			{
			    upgraded = true;
			}
		}

		return new UpgradeStatus(IStatus.OK, upgraded, Messages.UpgradeOperation_Success); 
	}

	/**
	 * @param monitor 
	 * @param info 
	 * @return an OK status if all goes well.  The status for the first op
	 * that fails otherwise.
	 * @throws ExecutionException 
	 */
	public IStatus redo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		for (final Iterator it = _stepOperations.iterator(); it.hasNext();)
		{
			VersionUpgradeOperation op = (VersionUpgradeOperation) it.next();
			IStatus status = op.redo(monitor, info);
			
			// fail fast
			if (status.getSeverity() != IStatus.OK)
			{
				return status;
			}
		}
		
		return new UpgradeStatus(IStatus.OK, true, Messages.UpgradeOperation_Success); 
	}

	/**
	 * @param monitor 
	 * @param info 
	 * @return an OK status if all goes well.  The status for the first op
	 * that fails otherwise.
	 * @throws ExecutionException 
	 */
	public IStatus undo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		for (final Iterator it = _stepOperations.iterator(); it.hasNext();)
		{
			VersionUpgradeOperation op = (VersionUpgradeOperation) it.next();
			IStatus status = op.undo(monitor, info);
			
			// fail fast
			if (status.getSeverity() != IStatus.OK)
			{
				return status;
			}
		}
		
		return Status.OK_STATUS;
	}

	/**
	 * @return an OK status if all goes well.  The status for the first op
	 * that fails otherwise.
	 * @throws ExecutionException 
	 */
	public IStatus commit() throws ExecutionException
	{
		for (final Iterator it = _stepOperations.iterator(); it.hasNext();)
		{
			VersionUpgradeOperation op = (VersionUpgradeOperation) it.next();
			
			if (op.canCommit())
			{
				IStatus status = op.commit();
				
				// fail fast
				if (status.getSeverity() != IStatus.OK)
				{
					return status;
				}
			}
		}
		
		return Status.OK_STATUS;
	}

	public boolean canExecute()
	{
		for (final Iterator it = _stepOperations.iterator(); it.hasNext();)
		{
			IUndoableOperation op = (IUndoableOperation) it.next();
			
			// fail fast
			if (!op.canExecute())
			{
				return false;
			}
		}
		
		return true;
	}

	public boolean canRedo() {
		for (final Iterator it = _stepOperations.iterator(); it.hasNext();)
		{
			IUndoableOperation op = (IUndoableOperation) it.next();
			
			// fail fast
			if (!op.canRedo())
			{
				return false;
			}
		}
		
		return true;
	}

	public boolean canUndo() {
		for (final Iterator it = _stepOperations.iterator(); it.hasNext();)
		{
			IUndoableOperation op = (IUndoableOperation) it.next();
			
			// fail fast
			if (!op.canUndo())
			{
				return false;
			}
		}
		
		return true;
	}

	public void dispose() 
	{
		for (final Iterator it = _stepOperations.iterator(); it.hasNext();)
		{
			IUndoableOperation op = (IUndoableOperation) it.next();
			op.dispose();
		}
	}
	
}
