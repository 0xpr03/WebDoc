/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

public class WPartner extends WNeuerPatient {
	
	private static final long serialVersionUID = 2732770291121677822L;

	public WPartner(boolean editable,long id) {
		super(editable, id);
		this.setVisible(true);
	}

}
