/*******************************************************************************
 * Copyright (c) 2011 Department of Computational Linguistics, University of Cologne, Germany.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Department of Computational Linguistics, University of Cologne, Germany - initial API and implementation
 ******************************************************************************/
package org.schwiebert.cloudio.application.ui.customization;

import org.eclipse.swt.graphics.Color;
import org.schwiebert.cloudio.application.data.Type;
import org.schwiebert.cloudio.application.ui.TypeLabelProvider;

/**
 * 
 * @author sschwieb
 *
 */
public class CharacterLabelProvider extends TypeLabelProvider {

	@Override
	public Color getColor(Object element) {
		Type t = (Type) element;
		char firstChar = Character.toLowerCase(t.getString().charAt(0));
		if(firstChar < 'g') {
			return colorList.get(2);
		}
		if(firstChar < 'm') {
			return colorList.get(1);
		}
		if(firstChar < 's') {
			return colorList.get(0);
		}
		return colorList.get(3);
	}

}
