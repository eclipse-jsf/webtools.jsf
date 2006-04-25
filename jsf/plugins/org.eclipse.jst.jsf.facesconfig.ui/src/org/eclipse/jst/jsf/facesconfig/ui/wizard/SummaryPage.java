/*******************************************************************************
 * Copyright (c) 2004, 2005 Sybase, Inc. and others. All rights reserved. This
 * program and the accompanying materials are made available under the terms of
 * the Eclipse Public License v1.0 which accompanies this distribution, and is
 * available at http://www.eclipse.org/legal/epl-v10.html Contributors: Sybase,
 * Inc. - initial API and implementation
 ******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.wizard;

import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.jst.jsf.facesconfig.common.guiutils.SWTUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * Common wizard page used to summarize information entered in previous pages.
 * 
 * @author plevin
 * @version 1.0
 */
public class SummaryPage extends WizardPage
{
	private static final String WIZARD_SUMMARY_PAGE = "WizardSummaryPage";

	/** The source of the summary items */
	private ISummaryDataSource source;

	/** The table control that displays the summary items */
	private Table table;

	/**
	 * This Constructor creates the summary page
	 */
	public SummaryPage()
	{
		super( WIZARD_SUMMARY_PAGE );
		setTitle( WizardsResourcesNLS.WizardSummaryPage_Title_WizardSummary );
		setDescription( WizardsResourcesNLS.WizardSummaryPage_Summary_SummaryDesc );
	}

	/**
	 * This Constructor initializes the data source.
	 * 
	 * @param source -
	 *            Summary data source
	 */
	public SummaryPage( ISummaryDataSource source )
	{
		this();
		this.source = source;
	}

	/**
	 * Determines if the wizard can enable the Next button
	 * 
	 * @return boolean - the state of the Next button
	 */
	public boolean canFlipToNextPage()
	{
		return false;
	}

	/**
	 * Creates the page controls
	 * 
	 * @param parent -
	 *            the wizard composite
	 */
	public void createControl( Composite parent )
	{

		Composite composite = SWTUtils.createComposite( parent, 1 );

		table = new Table( composite, SWT.BORDER );
		table.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		table.setHeaderVisible( true );
		table.setLinesVisible( true );

		TableLayout layout = new TableLayout();
		table.setLayout( layout );

		layout.addColumnData( new ColumnPixelData( 163 ) );
		layout.addColumnData( new ColumnPixelData( 350 ) );

		TableColumn keyCol = new TableColumn( table, SWT.NONE );
		keyCol.setText( WizardsResourcesNLS.WizardSummaryPage_Label_Field );

		TableColumn valueCol = new TableColumn( table, SWT.NONE );
		valueCol
				.setText( WizardsResourcesNLS.WizardSummaryPage_Label_Value );

		setControl( composite );
		setPageComplete( true );
	}

	/**
	 * Populates the table with summary information.
	 */
	public void loadSummaryData()
	{
		if ( source == null )
		{
			return;
		}
		Object[] data = source.getSummaryData().toArray();
		table.removeAll();
		for ( int i = 0; i < data.length; i++ )
		{
			TableItem item = new TableItem( table, SWT.NONE );
			item.setText( (String[]) data[i] );
		}
		return;
	}

	/**
	 * Sets summary page data source.
	 * 
	 * @param s -
	 *            Data source.
	 */
	public void setSummaryDataSource( ISummaryDataSource s )
	{
		source = s;
	}

	/**
	 * Populates the table with summary items when the page becomes visible.
	 * 
	 * @param visible -
	 *            the visible state of the page
	 */
	public void setVisible( boolean value )
	{
		super.setVisible( value );

		if ( value == true )
		{
			loadSummaryData();
		}
	}
}
