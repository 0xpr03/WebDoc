package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.Color;

import javax.swing.SwingConstants;

import webdoc.webdoc.Config;

import java.awt.Component;
import java.awt.Window.Type;
import java.awt.Font;

public class WAbout extends JDialog {

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
				JLabel lblwebdoc = new JLabel("<html><br>WebDoc<br><br>\r\nVersion %v<br></html>".replace("%v", Config.getStrValue("version")));
				lblwebdoc.setFont(new Font("Times New Roman", Font.PLAIN, 14));
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
				JLabel lblNewLabel = new JLabel("<html>\r\n<b>Used librarys:<b><br>\r\n<br>\r\n<li><b>eclipse mig layout<b><br>\r\n<br>\r\n<li><b>mariafoundation JDBC<b><br>\r\n<br>\r\n\r\n</html>");
				lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
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
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
