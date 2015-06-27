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
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import webdoc.webdoc.Config;

/**
 * About window
 * @author "Aron Heinecke"
 */
public class WAbout extends JDialog {

	private static final long serialVersionUID = -777183505820473274L;
	private final JPanel contentPanel = new JPanel();
	private JTabbedPane tabbedPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			WAbout dialog = new WAbout();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public WAbout() {
		setAlwaysOnTop(true);
		setResizable(false);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
			tabbedPane.setBorder(null);
		}
		{
			JPanel panelAbout = new JPanel();
			panelAbout.setBackground(Color.WHITE);
			tabbedPane.addTab("About", null, panelAbout, null);
			panelAbout.setLayout(new BoxLayout(panelAbout, BoxLayout.X_AXIS));
			{
				JLabel lblwebdoc = new JLabel("<html><div style=\"borderl-left: 5px\"><br>WebDoc<br><br>Version %v<br></div></html>".replace("%v", Config.getStrValue("version")));
				lblwebdoc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
				lblwebdoc.setAlignmentY(0.0f);
				lblwebdoc.setBackground(Color.WHITE);
				lblwebdoc.setVerticalAlignment(SwingConstants.TOP);
				panelAbout.add(lblwebdoc);
			}
		}
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(tabbedPane, BorderLayout.CENTER);
		{
			JPanel panelCopyright = new JPanel();
			panelCopyright.setBackground(Color.WHITE);
			tabbedPane.addTab("3rd Librarys", null, panelCopyright, null);
			panelCopyright.setLayout(new BoxLayout(panelCopyright, BoxLayout.X_AXIS));
			{
				JLabel lblNewLabel = new JLabel("<html><b>Used librarys:</b><br><br><li>eclipse mig layout<br><br><li>mariafoundation JDBC<br><br><li>Apache HttpComponents<br><br><li>SnakeYAML<br><br><li>Log4j<br><br><li>Json Simple<br><br></html>");
				lblNewLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,14));
				lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
				panelCopyright.add(lblNewLabel);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblCopyrightWebdoc = new JLabel("Copyright 2015 WebDoc group");
				buttonPane.add(lblCopyrightWebdoc);
			}
			{
				JButton cancelButton = new JButton("Schließen");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		this.setVisible(true);
	}

}
