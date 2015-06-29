/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import webdoc.gui.utils.ACElement;
import webdoc.gui.utils.ACElement.ElementType;
import webdoc.gui.utils.GenderEnumObj;
import webdoc.gui.utils.JSearchTextField;
import webdoc.gui.utils.JSearchTextField.searchFieldAPI;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;

/**
 * Test class for JUnit tests like JSearchTextField
 * @author "Aron Heinecke"
 *
 */
public class test extends JInternalFrame {

	private static final long serialVersionUID = -8772029628747927216L;
	private JSearchTextField searchField;
	private PreparedStatement searchStm = null;
	private Logger logger = LogManager.getLogger();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					test window = new test();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public test() {
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnTest = new JMenu("test");
		menuBar.add(mnTest);
		
		JMenuItem menuItem = new JMenuItem("1");
		mnTest.add(menuItem);
		
		JSeparator separator = new JSeparator();
		mnTest.add(separator);
		
		JMenuItem menuItem_1 = new JMenuItem("2");
		menuItem_1.setEnabled(false);
		mnTest.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("3");
		mnTest.add(menuItem_2);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setTitle("Testframe");
		setBounds(100, 100, 828, 436);
		
		searchField = new JSearchTextField(false);
		
		JPanel panelResize = new JPanel();
		
		JPanel panelDown = new JPanel();
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelResize, GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
							.addGap(1))
						.addComponent(searchField, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelDown, GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(1)
					.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelResize, GroupLayout.DEFAULT_SIZE, 315, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panelDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel panelFixedSize = new JPanel();
		
		JPanel panelSideResizable = new JPanel();
		GroupLayout gl_panelResize = new GroupLayout(panelResize);
		gl_panelResize.setHorizontalGroup(
			gl_panelResize.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResize.createSequentialGroup()
					.addGap(1)
					.addComponent(panelFixedSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(1)
					.addComponent(panelSideResizable, GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panelResize.setVerticalGroup(
			gl_panelResize.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelResize.createSequentialGroup()
					.addGap(1)
					.addComponent(panelFixedSize, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(Alignment.TRAILING, gl_panelResize.createSequentialGroup()
					.addGap(1)
					.addComponent(panelSideResizable, GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
					.addGap(1))
		);
		panelSideResizable.setLayout(new BoxLayout(panelSideResizable, BoxLayout.X_AXIS));
		
		JSplitPane splitPane = new JSplitPane();
		panelSideResizable.add(splitPane);
		
		JPanel panelInnerResizing1 = new JPanel();
		splitPane.setLeftComponent(panelInnerResizing1);
		
		JLabel titleCenter1 = new JLabel("Mein Textfeld");
		titleCenter1.setHorizontalAlignment(SwingConstants.CENTER);
		
		JTextPane textResizing = new JTextPane();
		GroupLayout gl_panelInnerResizing1 = new GroupLayout(panelInnerResizing1);
		gl_panelInnerResizing1.setHorizontalGroup(
			gl_panelInnerResizing1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInnerResizing1.createSequentialGroup()
					.addGap(1)
					.addComponent(titleCenter1, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(Alignment.TRAILING, gl_panelInnerResizing1.createSequentialGroup()
					.addGap(2)
					.addComponent(textResizing, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panelInnerResizing1.setVerticalGroup(
			gl_panelInnerResizing1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelInnerResizing1.createSequentialGroup()
					.addComponent(titleCenter1)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textResizing, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
					.addGap(1))
		);
		panelInnerResizing1.setLayout(gl_panelInnerResizing1);
		
		JPanel panelInnerResizing2 = new JPanel();
		splitPane.setRightComponent(panelInnerResizing2);
		
		JTextPane textPane = new JTextPane();
		
		JLabel label_9 = new JLabel("Mein Textfeld");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_panelInnerResizing2 = new GroupLayout(panelInnerResizing2);
		gl_panelInnerResizing2.setHorizontalGroup(
			gl_panelInnerResizing2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 550, Short.MAX_VALUE)
				.addGroup(gl_panelInnerResizing2.createSequentialGroup()
					.addGap(1)
					.addComponent(label_9, GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(Alignment.TRAILING, gl_panelInnerResizing2.createSequentialGroup()
					.addGap(2)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_panelInnerResizing2.setVerticalGroup(
			gl_panelInnerResizing2.createParallelGroup(Alignment.LEADING)
				.addGap(0, 330, Short.MAX_VALUE)
				.addGroup(gl_panelInnerResizing2.createSequentialGroup()
					.addComponent(label_9)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textPane, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
					.addGap(1))
		);
		panelInnerResizing2.setLayout(gl_panelInnerResizing2);
		
		JLabel titelMiddleFixed = new JLabel("Meine Überschrift");
		titelMiddleFixed.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel label = new JLabel("Name des Tieres:");
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("Rufname:");
		
		JLabel label_2 = new JLabel("Rasse:");
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setColumns(10);
		
		JComboBox<GenderEnumObj> comboBox = new JComboBox<GenderEnumObj>();
		comboBox.setEditable(false);
		
		JLabel label_3 = new JLabel("Geschlecht:");
		
		JLabel label_4 = new JLabel("Geburtsdatum:");
		
		JSpinner spinner = new JSpinner();
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		
		JLabel label_5 = new JLabel("Haarkleid/Farbe:");
		
		JLabel label_6 = new JLabel("Gewicht:");
		
		textField_4 = new JTextField();
		textField_4.setEditable(false);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);
		
		JLabel label_7 = new JLabel("Identifizierung:");
		
		JLabel label_8 = new JLabel("Zugehöriger Partner:");
		
		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);
		GroupLayout gl_panelFixedSize = new GroupLayout(panelFixedSize);
		gl_panelFixedSize.setHorizontalGroup(
			gl_panelFixedSize.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panelFixedSize.createSequentialGroup()
					.addGap(1)
					.addComponent(titelMiddleFixed, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
					.addGap(1))
				.addGroup(Alignment.LEADING, gl_panelFixedSize.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addComponent(label, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
							.addGap(40)
							.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
							.addGap(53)
							.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addComponent(label_3, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
							.addGap(12)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addGap(42)
							.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addComponent(label_7, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addComponent(label_8, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addGap(4)
							.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panelFixedSize.setVerticalGroup(
			gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelFixedSize.createSequentialGroup()
					.addComponent(titelMiddleFixed)
					.addGap(26)
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addGap(3)
							.addComponent(label))
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addGap(3)
							.addComponent(label_1))
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addGap(3)
							.addComponent(label_2))
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addGap(4)
							.addComponent(label_3))
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addGap(3)
							.addComponent(label_4))
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addGap(3)
							.addComponent(label_5))
						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addGap(3)
							.addComponent(label_6))
						.addComponent(textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(9)
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addGap(3)
							.addComponent(label_7))
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_panelFixedSize.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelFixedSize.createSequentialGroup()
							.addGap(3)
							.addComponent(label_8))
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(28, Short.MAX_VALUE))
		);
		panelFixedSize.setLayout(gl_panelFixedSize);
		panelResize.setLayout(gl_panelResize);
		panelDown.setLayout(new BorderLayout(0, 0));
		
		JPanel panelFixedOrder = new JPanel();
		panelDown.add(panelFixedOrder, BorderLayout.WEST);
		panelFixedOrder.setLayout(new MigLayout("", "[][][]", "[]"));
		
		JButton btnOk = new JButton("Ok");
		panelFixedOrder.add(btnOk, "cell 0 0");
		
		JButton btnNewButton = new JButton("Cancel");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelFixedOrder.add(btnNewButton, "cell 1 0");
		
		JButton btnNewButton_1 = new JButton("Neue Anamnese");
		panelFixedOrder.add(btnNewButton_1, "cell 2 0");
		getContentPane().setLayout(groupLayout);
		/**
		 * Default DataProvider for these kinds
		 * @author "Aron Heinecke"
		 */
		class Provider implements searchFieldAPI{
			@Override
			public List<ACElement> getData(String text){
				List<ACElement> list = new ArrayList<ACElement>();
				try {
					text = "%"+text+"%";
					searchStm.setString(1, text);
					searchStm.setString(2, text);
					searchStm.setString(3, text);
					searchStm.setString(4, text);
					searchStm.setString(5, text);
					ResultSet result = searchStm.executeQuery();
					
					while(result.next()){
						list.add(new ACElement(result.getString(1),result.getString(2),result.getLong(3), ElementType.ANIMAL));
					}
					result.close();
					
				} catch (SQLException e) {
					GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e,true));
				}
				return list;
			}
			@Override
			public boolean changedSelectionEvent(ACElement element) {
				logger.debug("Element chosen: {}",element);
				return true;
			}
			@Override
			public String listRenderer(ACElement element) {
				return "<html>" + element.getName() + " " + element.getOptname() + " <font size=-5><i>"
						+ element.getType() + "</i></font></html>";
			}
		}
		searchField.setAPI(new Provider());
		
		try {
			searchStm = Database.prepareMultiSearchStm();
		} catch (SQLException e) {
			GUIManager.showDBErrorDialog(this, Database.DBExceptionConverter(e,true));
		}
	}
}
