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

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.AbstractOperation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * Implements a single step (vX -> v(X+1)) version upgrade as an
 * Eclipse operation.  Adds an additional  "commit" abstract method.  This
 * can be called after execute in situations where further actions require
 * user approval etc.  See the javadocs for the method, it contains important
 * API rules
 * 
 * @author cbateman
 * @deprecated
 */
public abstract class VersionUpgradeOperation extends AbstractOperation 
{
	/**
	 * the version being upgraded from
	 */
	protected final int		_oldVersion;
	/**
	 * the version being upgraded to
	 */
	protected final int		_newVersion;
	
	private boolean				_hasExecuted;
	private boolean				_hasCommitted;
	private boolean				_hasCleanState = true;
	
	/**
	 * @param label
	 * @param oldVersion 
	 * @param newVersion 
	 */
	public VersionUpgradeOperation(String label, int oldVersion, int newVersion) {
		super(label);
		_oldVersion = oldVersion;
		_newVersion = newVersion;
	}
	
	/**
	 * Allows selected functionality to be called after execute is called.
	 * Commit must conform to following contract:
	 * 
	 *  1) should do nothing if execute() has not been called.
	 *  2) must do nothing if canCommit == false
	 *  3) once executed, undo should undo commit() first, then execute()
	 *  4) once undone, redo should call commit only if it was called before undo()
	 * @return must conform to same contract as execute() 
	 * @throws ExecutionException 
	 *  
	 */
	public final IStatus commit() throws ExecutionException
	{
		if (canCommit())
		{
			IStatus result =  doCommit();
			if (result.getSeverity() == IStatus.OK)
			{
				_hasCommitted = true;
			}
			else
			{
				_hasCommitted = false;
				_hasCleanState = false;
			}
			return result;
		}
		throw new ExecutionException("Cannot execute"); //$NON-NLS-1$
	}

	/**
	 * @return the result of the actual commit
	 * @throws ExecutionException
	 */
	protected abstract IStatus doCommit() throws ExecutionException;

	/**
	 * @return true if the operation can be committed
	 */
	public boolean canCommit() 
	{
		return _hasExecuted && !_hasCommitted && _hasCleanState;
	}

	public final IStatus execute(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException 
	{
		if (canExecute())
		{
			IStatus result = doExecute(monitor, info);
			if (result.getSeverity() == IStatus.OK)
			{
				_hasExecuted = true;
			}
			else
			{
				_hasExecuted = false;
				_hasCleanState = false;
			}
			return result;
		}
		throw new ExecutionException("Cannot execute"); //$NON-NLS-1$
	}

	/**
	 * @param monitor
	 * @param info
	 * @return the status of the real execution
	 * @throws ExecutionException 
	 */
	protected abstract IStatus doExecute(IProgressMonitor monitor, IAdaptable info)throws ExecutionException;

	public final IStatus redo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		if (canRedo())
		{
			IStatus result = doRedo(monitor, info);
			if (result.getSeverity() == IStatus.OK)
			{
				_hasExecuted = true;
			}
			else
			{
				_hasExecuted = false;
				_hasCleanState = false;
			}
			return result;
		}
		throw new ExecutionException("Cannot redo"); //$NON-NLS-1$
	}

	/**
	 * @param monitor
	 * @param info
	 * @return the status of the real redo
	 * @throws ExecutionException 
	 */
	protected abstract IStatus doRedo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException;

	public final IStatus undo(IProgressMonitor monitor, IAdaptable info)
			throws ExecutionException {
		if (canUndo())
		{
			IStatus result = doUndo(monitor, info);
			if (result.getSeverity() == IStatus.OK)
			{
				_hasExecuted = false;
			}
			else
			{
				_hasExecuted = true;
				_hasCleanState = false;
			}
			return result;
		}
		throw new ExecutionException("Cannot redo"); //$NON-NLS-1$
	}

	/**
	 * @param monitor
	 * @param info
	 * @return the status of the real redo
	 * @throws ExecutionException 
	 */
	protected abstract IStatus doUndo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException;

	public boolean canExecute() {
		return !_hasExecuted && _hasCleanState;
	}

	public boolean canRedo() {
		return canExecute();
	}

	public boolean canUndo() {
		return _hasExecuted && _hasCleanState;
	}

	/**
	 * @param executed
	 */
	protected void setHasExecuted(boolean executed) {
		_hasExecuted = executed;
	}

	/**
	 * @param committed
	 */
	protected void setHasCommitted(boolean committed) {
		_hasCommitted = committed;
	}

	/**
	 * @param cleanState
	 */
	protected void setHasCleanState(boolean cleanState) {
		_hasCleanState = cleanState;
	}

	/**
	 * @return true if has executed
	 */
	protected boolean hasExecuted() {
		return _hasExecuted;
	}

	/**
	 * @return true if has committed
	 */
	protected boolean hasCommitted() {
		return _hasCommitted;
	}

	/**
	 * @return true if has clean state
	 */
	protected boolean hasCleanState() {
		return _hasCleanState;
	}
	
}
