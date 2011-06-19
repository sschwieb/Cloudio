package org.schwiebert.cloudio.ui.tests;

import org.eclipse.jface.viewers.BaseLabelProvider;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.schwiebert.cloudio.ICloudLabelProvider;

public class TestLabelProvider extends BaseLabelProvider implements ICloudLabelProvider {
	
	public static final double WEIGHT = 0.987D;
	public static final float ANGLE = 12.34F;
	public static Color COLOR = new Color(Display.getDefault(), new RGB(100,100,100));
	public static FontData[] FONT_DATA = Display.getDefault().getShells()[0].getFont().getFontData();

	@Override
	public String getLabel(Object element) {
		return element.toString();
	}

	@Override
	public double getWeight(Object element) {
		return WEIGHT;
	}

	@Override
	public Color getColor(Object element) {
		return COLOR;
	}

	@Override
	public FontData[] getFontData(Object element) {
		return FONT_DATA.clone();
	}

	@Override
	public float getAngle(Object element) {
		return ANGLE;
	}

	@Override
	public String getToolTip(Object element) {
		return getLabel(element);
	}
	
}
