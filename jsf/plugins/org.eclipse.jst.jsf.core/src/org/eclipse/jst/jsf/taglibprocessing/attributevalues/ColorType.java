/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation., and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Yury Kats/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.taglibprocessing.attributevalues;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.PossibleValue;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

/**
 * Meta-data processing type representing a color.
 * A color is defined as in html spec http://www.w3.org/TR/html4/types.html#type-color
 * 
 * <p><b>Provisional API - subject to change</b></p>
 * 
 * @author ykats
 */

public class ColorType extends EnumerationType implements IPossibleValues {
	
	/**
	 * List of standard colors
	 * See http://www.w3.org/TR/html4/types.html#type-color
	 */
	private final static String[] COLORS = {"Black", "Silver", "Gray", "White", "Maroon", "Red", "Purple", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
				"Fuchsia", "Green", "Lime", "Olive", "Yellow", "Navy", "Blue", "Teal","Aqua"}; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$ //$NON-NLS-8$ //$NON-NLS-9$
		//if ordering changes, must change RGB[] ordering

	private RGB[] _rgb;

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.taglibprocessing.attributevalues.EnumerationType#getReturnType()
	 */
	protected String getReturnType(){ return "java.lang.String";} //$NON-NLS-1$

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IValidValues#isValidValue(java.lang.String)
	 */
	public boolean isValidValue(String value) {	
		boolean bValid = true;
		String trimmedVal = value.trim();
		if (trimmedVal.length() == 0)
			bValid = false;
		else if (trimmedVal.charAt(0) == '#' && trimmedVal.length() == 7) {
			for (int i=1; i<7; i++) {
				if (trimmedVal.charAt(i) < '0' ||
					(trimmedVal.charAt(i) > '9' && trimmedVal.charAt(i) < 'A') ||
					(trimmedVal.charAt(i) > 'F' && trimmedVal.charAt(i) < 'a') ||
					trimmedVal.charAt(i) > 'f') {
					bValid = false;
					break;
				}
			}
		}
		else {
			bValid = false;
			for (int i = 0; i < COLORS.length; i++) {
				if(trimmedVal.equalsIgnoreCase(COLORS[i])) {
					bValid = true;
					break;
				}
			}
		}
		
		if(!bValid) {
			addNewValidationMessage(Messages.ColorType_invalid_color);
		}
		
		return getValidationMessages().isEmpty();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues#getPossibleValues()
	 */
	public List getPossibleValues() {
		List ret = new ArrayList(COLORS.length);
		for (int i=0;i < COLORS.length;i++){
			PossibleValue pv = new PossibleValue(COLORS[i]);
			pv.setIcon(createImage(i));
			ret.add(pv);
		}
		return ret;
	}

	private ImageDescriptor createImage(int color) {
		PaletteData pd = getPaletteData(color);
		return ImageDescriptor.createFromImageData(new ImageData(16, 16, 1, pd));
	}

	private PaletteData getPaletteData(int color){
		if (_rgb == null){
			_rgb = new RGB[16];
			_rgb[0] = new RGB(0,0,0);//black
			_rgb[1] = new RGB(192,192,192);//silver
			_rgb[2] = new RGB(128,128,128);//gray
			_rgb[3] = new RGB(255,255,255);//white
			_rgb[4] = new RGB(128,0,0);//Maroon
			_rgb[5] = new RGB(255,0,0);//Red
			_rgb[6] = new RGB(128,0,128);//Purple
			_rgb[7] = new RGB(255,0,255);//Fuchsia
			_rgb[8] = new RGB(0,128,0);//Green
			_rgb[9] = new RGB(0,255,0);//Lime
			_rgb[10] = new RGB(128,128,0);//Olive
			_rgb[11] = new RGB(255,255,0);//Yellow
			_rgb[12] = new RGB(0,0,128);//Navy
			_rgb[13] = new RGB(0,0,255);//Blue
			_rgb[14] = new RGB(0,128,128);//Teal
			_rgb[15] = new RGB(0,255,255);//Aqua
		}
		RGB[] rgbColor = new RGB[1];
		System.arraycopy(_rgb, color, rgbColor, 0, 1);
		return new PaletteData(rgbColor);
	}
}
