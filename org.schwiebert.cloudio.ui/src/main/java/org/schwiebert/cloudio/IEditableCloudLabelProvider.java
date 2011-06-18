package org.schwiebert.cloudio;

import java.util.List;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;

public interface IEditableCloudLabelProvider extends ICloudLabelProvider {

	public void setColors(List<RGB> colors);

	public void setFonts(List<FontData> fonts);

	public void setAngles(List<Float> list);
	
}
