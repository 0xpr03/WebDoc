/*******************************************************************************
 * Copyright (c) 2015 by Aron Heinecke & Jonathan Peper
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class WOptions extends JFrame{
	public WOptions() {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new MigLayout("", "[][]", "[]"));
		
		JButton btnCancel = new JButton("Cancel");
		panel.add(btnCancel, "cell 0 0");
		
		JButton btnSpeicher = new JButton("Speicher");
		panel.add(btnSpeicher, "cell 1 0");
		
		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Aktuelle Lizenz:");
		
		JLabel label_1 = new JLabel("Lizenzcode:");
		panel_1.setLayout(new MigLayout("", "[74px][4px][339px]", "[20px][20px][14px][18.00px][]"));
		panel_1.add(label, "flowx,cell 0 0,alignx left,aligny center");
		panel_1.add(label_1, "flowx,cell 0 1,alignx left,aligny center");
		
		JLabel label_2 = new JLabel("Fenster:");
		panel_1.add(label_2, "cell 0 3,alignx left,aligny top");
		
		JCheckBox checkBox = new JCheckBox("Verwende komplexes Anamnesenfenster");
		panel_1.add(checkBox, "cell 0 4,alignx left,aligny top");
		
		textField = new JTextField();
		textField.setColumns(10);
		panel_1.add(textField, "cell 0 0,growx,aligny top");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panel_1.add(textField_1, "cell 0 1 2 1,growx,aligny top");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 4364488189057647985L;
	private JTextField textField;
	private JTextField textField_1;

	
	
	public static void main(String[] args) {
		try {
			WOptions dialog = new WOptions();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
