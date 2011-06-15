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
package org.schwiebert.cloudio.application.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.schwiebert.cloudio.application.about.AboutDialog;

/**
 * 
 * @author sschwieb
 *
 */
public class AboutAction extends Action implements IWorkbenchAction {
	
	public AboutAction() {
		super.setId("about");
		setText("About");
	}

	@Override
	public void run() {
		AboutDialog dialog = new AboutDialog(Display.getCurrent().getActiveShell());
		dialog.open();
	}

	@Override
	public void dispose() {
		
	}

}
