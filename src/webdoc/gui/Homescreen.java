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

public class Homescreen {

	private JFrame frmWebdoc;

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
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnMenu.add(mntmClose);
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmWebdoc.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(390, Short.MAX_VALUE))
		);
		
		JButton btnNewButton = new JButton("Neuer Partner");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnNeuerPatient = new JButton("Neuer Patient");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(btnNewButton)
					.addPreferredGap(ComponentPlacement.RELATED, 495, Short.MAX_VALUE)
					.addComponent(btnNeuerPatient, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
					.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
					.addComponent(btnNeuerPatient, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
		);
		panel.setLayout(gl_panel);
		frmWebdoc.getContentPane().setLayout(groupLayout);
	}
}
