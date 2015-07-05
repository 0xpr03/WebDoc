/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import webdoc.webdoc.Config;
import javax.swing.JTextPane;

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
		setBounds(100, 100, 450, 322);
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
			panelAbout.setBorder(null);
			panelAbout.setBackground(Color.WHITE);
			tabbedPane.addTab("About", null, panelAbout, null);
			panelAbout.setLayout(new BoxLayout(panelAbout, BoxLayout.X_AXIS));
			{
				JPanel jpanel = new JPanel();
				jpanel.setBackground(Color.WHITE);
				jpanel.setLayout(new BorderLayout(0, 0));
				JLabel lblwebdoc = new JLabel("<html>WebDoc<br><br>Version: %v<br><br>Written by<br>Aron Heinecke (aron.heinecke@t-online.de)<br>Jonathan Peper</div><br><br>For license details please see under \"License\"</html>".replace("%v", Config.getStrValue("version")));
				lblwebdoc.setOpaque(true);
				lblwebdoc.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));
				lblwebdoc.setAlignmentY(0.0f);
				lblwebdoc.setBackground(Color.WHITE);
				lblwebdoc.setVerticalAlignment(SwingConstants.TOP);
				JScrollPane scrollPane = new JScrollPane(lblwebdoc);
				scrollPane.setOpaque(false);
				scrollPane.setBorder(null);
				scrollPane.setBackground(Color.WHITE);
				{
					Component placeHolder = Box.createHorizontalStrut(10);
					jpanel.add(placeHolder, BorderLayout.WEST);
				}
				{
					Component placeHolder = Box.createVerticalStrut(10);
					jpanel.add(placeHolder, BorderLayout.NORTH);
				}
				{
					Component placeHolder = Box.createHorizontalStrut(5);
					jpanel.add(placeHolder, BorderLayout.EAST);
				}
				{
					Component placeHolder = Box.createVerticalStrut(5);
					jpanel.add(placeHolder, BorderLayout.SOUTH);
				}
				jpanel.add(scrollPane);
				panelAbout.add(jpanel);
			}
		}
		contentPanel.setLayout(new BorderLayout(0, 0));
		contentPanel.add(tabbedPane, BorderLayout.CENTER);
		{
			JPanel panelCopyright = new JPanel();
			panelCopyright.setBorder(null);
			panelCopyright.setBackground(Color.WHITE);
			tabbedPane.addTab("3rd Librarys", null, panelCopyright, null);
			panelCopyright.setLayout(new BoxLayout(panelCopyright, BoxLayout.X_AXIS));
			{
				JLabel lblNewLabel = new JLabel("<html><b>Used librarys:</b><br><br><li>eclipse mig layout<br><br><li>mariafoundation JDBC<br><br><li>Apache HttpComponents<br><br><li>SnakeYAML<br><br><li>Log4j<br><br><li>Json Simple<br><br></html>");
				lblNewLabel.setOpaque(true);
				lblNewLabel.setBorder(null);
				lblNewLabel.setBackground(Color.WHITE);
				lblNewLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN,14));
				lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
				JScrollPane scrollPane = new JScrollPane(lblNewLabel);
				scrollPane.setBorder(null);
				scrollPane.setBackground(Color.WHITE);
				JPanel jpanel = new JPanel();
				jpanel.setOpaque(false);
				jpanel.setLayout(new BorderLayout(0, 0));
				jpanel.add(scrollPane, BorderLayout.CENTER);
				panelCopyright.add(jpanel);
				{
					Component placeHolder = Box.createHorizontalStrut(5);
					jpanel.add(placeHolder, BorderLayout.WEST);
				}
				{
					Component placeHolder = Box.createVerticalStrut(4);
					jpanel.add(placeHolder, BorderLayout.SOUTH);
				}
				{
					Component placeHolder = Box.createVerticalStrut(5);
					jpanel.add(placeHolder, BorderLayout.NORTH);
				}
				{
					Component placeHolder = Box.createHorizontalStrut(5);
					jpanel.add(placeHolder, BorderLayout.EAST);
				}
			}
		}
		{
			JPanel panelLicense = new JPanel();
			tabbedPane.addTab("CC", null, panelLicense, null);
			panelLicense.setLayout(new BoxLayout(panelLicense, BoxLayout.X_AXIS));
			{
				JScrollPane scrollPane = new JScrollPane();
				panelLicense.add(scrollPane);
				{
					JTextPane textPane = new JTextPane();
					textPane.setEditable(false);
					textPane.setText(GUIFunctions.getLicense(this));
					scrollPane.setViewportView(textPane);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBorder(null);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblCopyrightWebdoc = new JLabel("Copyright © 2015 WebDoc group");
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
