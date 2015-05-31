/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;

import webdoc.lib.GUIManager;
import webdoc.webdoc.Config;

/**
 * License Agreement window
 * @author "Aron Heinecke"
 */
public class WLicense extends JDialog {

	private static final long serialVersionUID = -2976489095615500990L;
	private final JPanel licensePanel = new JPanel();
	private JRadioButton rdbtnIAgree;
	private JRadioButton rdbtnIDoNot;
	private JButton okButton;

	/**
	 * Launch the application.
	 */
	public WLicense(boolean main) {
		WLicense dialog = new WLicense();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

	/**
	 * Create the dialog.
	 */
	public WLicense() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				System.exit(1);
			}
		});
		setModal(true);
		setTitle("WebDoc License Agreement");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		setLocationRelativeTo(null); // center it
		getContentPane().setLayout(new BorderLayout(0, 0));
		licensePanel.setBorder(null);
		getContentPane().add(licensePanel, BorderLayout.CENTER);
		
		rdbtnIDoNot = new JRadioButton("I do not accept the agreement");
		rdbtnIDoNot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okButton.setEnabled(false);
				rdbtnIAgree.setSelected(false);
			}
		});
		rdbtnIDoNot.setSelected(true);
		{
			rdbtnIAgree = new JRadioButton("I accept the agreement");
			rdbtnIAgree.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					okButton.setEnabled(true);
					rdbtnIDoNot.setSelected(false);
				}
			});
			rdbtnIAgree.setSelected(false);
		}
		
		JLabel lblpleaseReadThe = new JLabel("<html>Please read the following License Agreement. You must accept the terms of this agreement before coninuing with the installation</html>");
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_licensePanel = new GroupLayout(licensePanel);
		gl_licensePanel.setHorizontalGroup(
			gl_licensePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_licensePanel.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_licensePanel.createParallelGroup(Alignment.LEADING)
						.addGap(1)
						.addComponent(lblpleaseReadThe)
						.addComponent(rdbtnIAgree)
						.addComponent(rdbtnIDoNot)
						.addGroup(gl_licensePanel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
							.addGap(17)))
					.addGap(10))
		);
		gl_licensePanel.setVerticalGroup(
			gl_licensePanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_licensePanel.createSequentialGroup()
					.addComponent(lblpleaseReadThe)
					.addGap(3)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnIAgree)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnIDoNot)
					.addGap(1))
		);
		
		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		textPane.setText(getLicense());
		scrollPane.setViewportView(textPane);
		licensePanel.setLayout(gl_licensePanel);
		{
			JPanel downPane = new JPanel();
			downPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(downPane, BorderLayout.PAGE_END);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(rdbtnIAgree.isSelected() && !rdbtnIDoNot.isSelected()){
							dispose();
						}
					}
				});
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				downPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(1);
					}
				});
				cancelButton.setActionCommand("Cancel");
				downPane.add(cancelButton);
			}
		}
		{
			JPanel panelTop = new JPanel();
			panelTop.setBackground(Color.WHITE);
			panelTop.setBorder(new EmptyBorder(7, 5, 5, 0));
			getContentPane().add(panelTop, BorderLayout.PAGE_START);
			panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));
			
			JSeparator separator = new JSeparator();
			
			
			JLabel lblLicenseAgreement = new JLabel("<html><b>License Agreement</b><br><div style=\"margin-left:10px;\">Please read the following important information before continuing.</div></html>");
			panelTop.add(lblLicenseAgreement);
			panelTop.add(separator, BoxLayout.Y_AXIS);
		}
	}

	private String getLicense() {
		try {
			InputStream is = getClass().getResourceAsStream(Config.getStrValue("licenseFilePath"));
		    InputStreamReader isr = new InputStreamReader(is, "UTF8");
			BufferedReader br = new BufferedReader(isr);
			
			StringBuilder sb = new StringBuilder();
			
			for(String line = br.readLine(); line != null;line=br.readLine()){
				sb.append(line+"\n");
			}
			return sb.toString();
		}catch(IOException | NullPointerException e){
			LogManager.getLogger().fatal("Error loading license!\n{}",e);
			GUIManager.showErrorDialog(this, "Unable to load license!\nAborting..", "Error: License Loading");
			System.exit(404);
			return null;
		}
	}
}
