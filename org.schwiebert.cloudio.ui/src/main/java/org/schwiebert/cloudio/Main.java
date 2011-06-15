package org.schwiebert.cloudio;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Transform;
import org.eclipse.swt.printing.Printer;

public class Main {
	
	public static void main(String[] args) {
		System.out.println(Math.sin(Math.toRadians(-90)));
		Printer device = new Printer();
		GC gc = new GC(device);
		String test = new String("Zapfino! ");
		Path pa = new Path(device);
		FontData[] fontData = gc.getFont().getFontData();
		int fontSize = 200;
		for (FontData data : fontData) {
			data.setHeight(fontSize);
			data.setName("Zapfino");
		}
		Font font = new Font(device, fontData);
		pa.addString(test, 0, 0, font);
		float[] bounds = new float[4];
		pa.getBounds(bounds);
		pa.dispose();
		
		float angle = -45; 
		gc.setFont(font);
		Point stringExtent = gc.stringExtent(test);
		// Bug? 
		//stringExtent.x = (int) Math.max(stringExtent.x, bounds[2]);
		stringExtent.y = (int) Math.max(stringExtent.y, bounds[3]);
		
		double radian = Math.toRadians(angle);
		final double sin = Math.abs(Math.sin(radian));
		final double cos = Math.abs(Math.cos(radian));
		int x = (int) ((cos*stringExtent.x) + (sin*stringExtent.y));
		int y = (int) ((cos*stringExtent.y) + (sin*stringExtent.x));
		Image img = new Image(null, x, y);
		gc = new GC(img);
		gc.setFont(font);
		gc.setForeground(device.getSystemColor(SWT.COLOR_BLACK));
		
		//drawAt(gc, p, fontSize, angle, img, sin, cos, xOffset, yOffset);
		
		Transform t = new Transform(img.getDevice());
		if(angle < 0) {
			t.translate(0,0 + img.getBounds().height - (int) (cos*stringExtent.y));
		} else {
			t.translate(0 + (int) (sin*stringExtent.y), 0);
		}
		t.rotate(angle);
		gc.setTransform(t);
		gc.drawString(test,0,0);
		gc.setTransform(null);
		
		ImageLoader il = new ImageLoader();
		il.data = new ImageData[] {img.getImageData()};
		il.save("/Volumes/MacOs/img/test2.png", SWT.IMAGE_PNG);

		
	}

}
