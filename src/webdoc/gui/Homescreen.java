package webdoc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import java.awt.TextArea;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Homescreen {

	private JFrame frmWebdoc;
	private JTextField txtSuche;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homescreen window = new Homescreen();
					window.frmWebdoc.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Homescreen() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWebdoc = new JFrame();
		frmWebdoc.setTitle("WebDoc");
		frmWebdoc.setBounds(100, 100, 743, 496);
		frmWebdoc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmWebdoc.setJMenuBar(menuBar);
		
		JMenu mnMenu = new JMenu("Menu");
		menuBar.add(mnMenu);
		
		JMenuItem mntmNeuerPatient = new JMenuItem("Neuer Patient");
		mnMenu.add(mntmNeuerPatient);
		
		JMenuItem mntmNeuerPartner = new JMenuItem("Neuer Partner");
		mnMenu.add(mntmNeuerPartner);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnMenu.add(mntmClose);
		
		JPanel panel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(frmWebdoc.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(128)
							.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
							.addGap(135)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
					.addGap(25))
		);
		
		JTextPane textPane = new JTextPane();
		textPane.setBackground(new Color(255, 255, 153));
		scrollPane.setViewportView(textPane);
		
		JButton btnNewButton = new JButton("Neuer Partner");
		btnNewButton.setToolTipText("Erstellen sie einen neuen Partner\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNeuerPatient = new JButton("Neuer Patient");
		
		txtSuche = new JTextField();
		txtSuche.setText("Suche");
		txtSuche.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnNewButton)
					.addGap(18)
					.addComponent(txtSuche, GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(btnNeuerPatient, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
					.addComponent(btnNeuerPatient, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addComponent(txtSuche, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		frmWebdoc.getContentPane().setLayout(groupLayout);
	}
}
