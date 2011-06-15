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
package org.schwiebert.cloudio.util;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;

/**
 * Helper class which stores all data
 * required to render an element.
 * @author sschwieb
 *
 */
public class Word {
	
	public Word(String string) {
		this.string = string;
	}

	public final String string;
	
	public double weight;

	public int x;

	public int y;

	public Color color;
	
	public RectTree tree;

	public float angle;

	public FontData[] fontData;

	public short id;

	public int height;

	public int width;

	public Object data;

	public Point stringExtent;
	
	@Override
	public String toString() {
		return string;
	}

}
