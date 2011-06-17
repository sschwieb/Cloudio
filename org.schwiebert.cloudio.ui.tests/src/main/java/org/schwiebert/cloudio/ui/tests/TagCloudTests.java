/*******************************************************************************
 * Copyright (c) 2009 Matthew Hall and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Matthew Hall - initial API and implementation (bug 293508)
 *******************************************************************************/
package org.schwiebert.cloudio.ui.tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.Assert;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.schwiebert.cloudio.TagCloud;
import org.schwiebert.cloudio.util.Word;

public class TagCloudTests {
	
	private Display display;
	private boolean createdDisplay = false;
	private Composite composite;

	@Before
	public void setUp() throws Exception {
		display = Display.getCurrent();
		if( display == null ){
			display = new Display();
			createdDisplay = true;
		}
		composite = new Shell(display);
		composite.setLayout(new FillLayout());
	}

	@After
	public void tearDown() throws Exception {
		composite.dispose();
		if(createdDisplay){
			display.dispose();
		}
	}
	
	// Lifecycle:

	@Test(expected=IllegalArgumentException.class)
	public void testConstructor_NullParent() {
		new TagCloud(null, SWT.NONE);
	}
	
	@Test
	public void testConstructor_ValidParent() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		Assert.assertNotNull(cloud);
	}
	
	@Test
	public void testDispose() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.dispose();
		Assert.assertTrue(cloud.isDisposed());
	}

	// Background Color:
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetInvalidBackgroundColor() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setBackground(null);
	}
	
	@Test
	public void testSetValidBackgroundColor() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		Color color = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		cloud.setBackground(color);
		Assert.assertEquals(color, cloud.getBackground());
	}
	
	@Test
	public void testDefaultBackgroundColor() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		Assert.assertNotNull(cloud.getBackground());
	}
	
	// Selection Color:
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetInvalidSelectionColor() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setSelectionColor(null);
	}
	
	@Test
	public void testSetValidSelectionColor() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		Color color = Display.getCurrent().getSystemColor(SWT.COLOR_RED);
		cloud.setSelectionColor(color);
		Assert.assertEquals(color, cloud.getSelectionColor());
	}
	
	@Test
	public void testDefaultSelectionColor() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		Assert.assertNotNull(cloud.getSelectionColor());
	}
	
	// Font Size:
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetInvalidMaxFontSize() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setMaxFontSize(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetInvalidMinFontSize() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setMinFontSize(0);
	}
	
	@Test
	public void testSetValidMaxFontSize() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		int size = cloud.getMaxFontSize()+1;
		cloud.setMaxFontSize(size*2);
		Assert.assertEquals(size*2, cloud.getMaxFontSize());
	}
	
	@Test
	public void testSetValidMinFontSize() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		int size = cloud.getMinFontSize()+1;
		cloud.setMinFontSize(size*2);
		Assert.assertEquals(size*2, cloud.getMinFontSize());
	}
	
	// Set Words:
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalWords1() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setWords(null, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalWords2() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		words.add(null);
		cloud.setWords(words, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalWords3() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		Word word = getWord();
		word.color = null;
		words.add(word);
		cloud.setWords(words, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalWords4() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		Word word = getWord();
		word.fontData = null;
		words.add(word);
		cloud.setWords(words, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalWords5() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		Word word = getWord();
		word.angle = -180;
		words.add(word);
		cloud.setWords(words, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalWords6() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		Word word = getWord();
		word.angle = 180;
		words.add(word);
		cloud.setWords(words, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalWords7() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		Word word = getWord();
		word.weight = -1;
		words.add(word);
		cloud.setWords(words, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalWords8() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		Word word = getWord();
		word.weight = 2;
		words.add(word);
		cloud.setWords(words, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetIllegalWords9() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		Word word = new Word(null);
		words.add(word);
		cloud.setWords(words, null);
	}
	
	private Word getWord() {
		Word w = new Word("Word");
		w.color = Display.getDefault().getSystemColor(SWT.COLOR_GRAY);
		w.fontData = composite.getFont().getFontData();
		w.weight = Math.random();
		return w;
	}

	@Test
	public void testSetEmptyWordList() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		cloud.setWords(words, null);
	}
	
	@Test
	public void testSetWordList() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		for(int i = 0; i < words.size(); i++) {
			words.add(getWord());
		}
		cloud.setWords(words, null);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetInvalidOpacity1() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setOpacity(-1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetInvalidOpacity2() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setOpacity(256);
	}
	
	// Layouter
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetInvalidLayouter() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setLayouter(null);
	}
	
	// Zoom
	
	@Test
	public void testZoomIn() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setWords(Arrays.asList(getWord()), null);
		double zoom = cloud.getZoom();
		cloud.zoomIn();
		Assert.assertTrue(cloud.getZoom() > zoom);
	}
	
	@Test
	public void testZoomReset() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setWords(Arrays.asList(getWord()), null);
		double zoom = cloud.getZoom();
		cloud.zoomReset();
		Assert.assertTrue(cloud.getZoom() > zoom);
		Assert.assertEquals(cloud.getZoom(), 1.0);
	}
	
	@Test
	public void testZoomOut() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setWords(Arrays.asList(getWord()), null);
		cloud.zoomReset();
		double zoom = cloud.getZoom();
		cloud.zoomOut();
		Assert.assertTrue(cloud.getZoom() < zoom);
	}
	
	@Test
	public void testZoomFit() {
		TagCloud cloud = new TagCloud(composite, SWT.V_SCROLL | SWT.H_SCROLL);
		cloud.setWords(Arrays.asList(getWord()), null);
		cloud.zoomReset();
		double zoom = cloud.getZoom();
		cloud.zoomFit();
		Assert.assertTrue(cloud.getZoom() < zoom);
		// TODO: Test if the cloud really fits the area!
	}
	
	// Image:
	
	@Test
	public void testGetImageData() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		Assert.assertNotNull(cloud.getImageData());
	}
		
	// TODO: Test Selection
	
	@Test
	public void testInitialSelection() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		Set<Word> selection = cloud.getSelection();
		Assert.assertNotNull(selection);
		Assert.assertTrue(selection.isEmpty());
	}
	
	@Test
	public void testSetSelection() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		words.add(getWord());
		words.add(getWord());
		cloud.setWords(words, null);
		Set<Word> sel = new HashSet<Word>();
		sel.add(words.get(0));
		cloud.setSelection(sel);
		Set<Word> selection = cloud.getSelection();
		Assert.assertEquals(sel, selection);
		cloud.setSelection(new HashSet<Word>());
		selection = cloud.getSelection();
		Assert.assertTrue(selection.isEmpty());
	}
	
	@Test
	public void testSetNotExistingSelection1() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		List<Word> words = new ArrayList<Word>();
		words.add(getWord());
		words.add(getWord());
		cloud.setWords(words, null);
		Set<Word> sel = new HashSet<Word>();
		sel.add(getWord());
		cloud.setSelection(sel);
		Set<Word> selection = cloud.getSelection();
		Assert.assertTrue(selection.isEmpty());
	}
	
	@Test
	public void testSetNotExistingSelection2() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		Set<Word> sel = new HashSet<Word>();
		sel.add(getWord());
		cloud.setSelection(sel);
		Set<Word> selection = cloud.getSelection();
		Assert.assertTrue(selection.isEmpty());
	}
	
	// Boost
	

	@Test(expected=IllegalArgumentException.class)
	public void testSetInvalidBoost() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setBoost(-1);
	}
	
	@Test
	public void testSetValidBoost() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		Assert.assertEquals(0, cloud.getBoost());
		cloud.setBoost(3);
		Assert.assertEquals(3, cloud.getBoost());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetInvalidBoostFactor() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setBoostFactor(0);
	}
	
	@Test
	public void testSetValidBoostFactor() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.setBoostFactor(3.3F);
		Assert.assertEquals(3.3F, cloud.getBoostFactor());
		cloud.setBoostFactor(-2.2F);
		Assert.assertEquals(-2.2F, cloud.getBoostFactor());
	}
	
	class UniversalListener implements MouseListener, MouseTrackListener, MouseWheelListener, MouseMoveListener {
	
		private int mouseUp;
		private int mouseDown;
		private int mouseDC;
		private int mouseMove;
		private int mouseScrolled;
		private int mouseExit;
		private int mouseEnter;
		private int mouseHover;

		@Override
		public void mouseUp(MouseEvent e) {
			mouseUp++;
		}
		
		@Override
		public void mouseDown(MouseEvent e) {
			mouseDown++;
		}
		
		@Override
		public void mouseDoubleClick(MouseEvent e) {
			mouseDC++;
		}

		@Override
		public void mouseMove(MouseEvent e) {
			mouseMove++;
		}

		@Override
		public void mouseScrolled(MouseEvent e) {
			mouseScrolled++;
		}

		@Override
		public void mouseEnter(MouseEvent e) {
			mouseEnter++;
		}

		@Override
		public void mouseExit(MouseEvent e) {
			mouseExit++;
		}

		@Override
		public void mouseHover(MouseEvent e) {
			mouseHover++;
		}
		
	}
	
	@Test
	public void testMouseListener() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		MouseListener ml = new UniversalListener();
		cloud.addMouseListener(ml);
		// TODO: Fire & count events
		cloud.removeMouseListener(ml);
	}
	
	@Test
	public void testMouseTrackListener() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		MouseTrackListener ml = new UniversalListener();
		cloud.addMouseTrackListener(ml);
		// TODO: Fire & count events
		cloud.removeMouseTrackListener(ml);
	}
	
	@Test
	public void testMouseMoveListener() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		MouseMoveListener ml = new UniversalListener();
		cloud.addMouseMoveListener(ml);
		// TODO: Fire & count events
		cloud.removeMouseMoveListener(ml);
	}
	
	@Test
	public void testMouseWheelListener() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		MouseWheelListener ml = new UniversalListener();
		cloud.addMouseWheelListener(ml);
		// TODO: Fire & count events
		cloud.removeMouseWheelListener(ml);
	}
	
	@Test
	public void testLayout1() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.layoutCloud(null, false);
		// TODO: Assert something...
	}
	
	@Test
	public void testLayout2() {
		TagCloud cloud = new TagCloud(composite, SWT.NONE);
		cloud.layoutCloud(null, true);
		// TODO: Assert something...
	}
	
}
