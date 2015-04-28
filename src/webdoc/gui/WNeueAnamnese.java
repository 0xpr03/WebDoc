package webdoc.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.BoxLayout;

import java.awt.BorderLayout;
import java.util.Calendar;

import javax.swing.JSpinner.DateEditor;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerDateModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;

public class WNeueAnamnese extends JInternalFrame {

	private JFrame frame;
	private boolean editabel;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JSpinner spinBirthdate_1;

	/**
	 * Launch the application.
	 */
	public static void main(final boolean editable) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WNeueAnamnese window = new WNeueAnamnese(editable, null);
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
	public WNeueAnamnese(boolean editable,String strName) {
		this.editabel = editable;
		initialize(editable, strName);
		setFrameIcon(null);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		
		JPanel pHeader = new JPanel();
		
		JPanel tabber = new JPanel();
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(new MigLayout("", "[][]", "[]"));
		
		JButton btnOk = new JButton("Ok");
		panel_3.add(btnOk, "cell 0 0");
		
		JButton btnCancel = new JButton("Cancel");
		panel_3.add(btnCancel, "cell 1 0");
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pHeader, GroupLayout.DEFAULT_SIZE, 1339, Short.MAX_VALUE)
					.addGap(2))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(18)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 247, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1076, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pHeader, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tabber, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
					.addGap(1)
					.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		
		JPanel pContent = new JPanel();
		
		JPanel panel_2 = new JPanel();
		
		JLabel label = new JLabel("Änderungen im Familiengefüge:");
		
		JScrollPane scrollPane_5 = new JScrollPane();
		
		JLabel label_1 = new JLabel("Name:");
		
		JLabel label_2 = new JLabel("Rufname:");
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		
		JLabel label_3 = new JLabel("Verwendungszweck:");
		
		JLabel label_4 = new JLabel("Im Besitz seit:");
		
		JLabel label_5 = new JLabel("Tier stammt von:");
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		spinBirthdate_1 = new JSpinner();
		spinBirthdate_1.setModel(model);
		DateEditor dateEditor = new JSpinner.DateEditor(spinBirthdate_1, "dd-MM-yyyy");
		spinBirthdate_1.setEditor(dateEditor);
		spinBirthdate_1.setEnabled(editable);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_1)
						.addComponent(label_2))
					.addGap(2)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_5)
						.addComponent(textField_6, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
					.addContainerGap(163, Short.MAX_VALUE))
				.addComponent(separator_1, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(label_3)
						.addComponent(label_4)
						.addComponent(label_5))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING, false)
						.addComponent(textField_7, GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
						.addComponent(spinBirthdate_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(textField_8))
					.addGap(167))
				.addGroup(gl_panel_2.createSequentialGroup()
					.addComponent(label)
					.addContainerGap())
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_1)
						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_2)
						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 4, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_3)
						.addComponent(textField_8, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_4)
						.addComponent(spinBirthdate_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_5, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
					.addGap(1))
		);
		
		JTextPane textPane = new JTextPane();
		scrollPane_5.setViewportView(textPane);
		panel_2.setLayout(gl_panel_2);
		
		JPanel panel_1 = new JPanel();
		
		JLabel lblAuslandsaufenthalte = new JLabel("Auslandsaufenthalte:");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		JLabel lblVerhaltensauflligkeiten = new JLabel("Verhaltensaufälligkeiten:");
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JLabel lblVerletzungen = new JLabel("Verletzungen:");
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		JLabel lblNarben = new JLabel("Narben:");
		
		JScrollPane scrollPane_4 = new JScrollPane();
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblVerletzungen)
							.addGap(56)
							.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
							.addComponent(lblNarben)
							.addGap(84)
							.addComponent(scrollPane_4, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblVerhaltensauflligkeiten)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblAuslandsaufenthalte)
									.addGap(18)
									.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)))
							.addGap(12))))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAuslandsaufenthalte)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
					.addGap(17)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(lblVerhaltensauflligkeiten)
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(19)
							.addComponent(lblVerletzungen))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(33)
							.addComponent(lblNarben))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		
		JTextPane textPane_4 = new JTextPane();
		scrollPane_2.setViewportView(textPane_4);
		
		JTextPane textPane_3 = new JTextPane();
		scrollPane_3.setViewportView(textPane_3);
		
		JTextPane textPane_2 = new JTextPane();
		scrollPane_4.setViewportView(textPane_2);
		
		JTextPane textPane_1 = new JTextPane();
		scrollPane_1.setViewportView(textPane_1);
		panel_1.setLayout(gl_panel_1);
		
		JPanel panel = new JPanel();
		
		JLabel lblInfektionserkrankungen = new JLabel("Infektionserkrankungen:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblRegelmigeImpfungen = new JLabel("Regelmäßige Impfungen:");
		
		JScrollPane scrollPane_6 = new JScrollPane();
		
		JLabel lblHerzkreislauf = new JLabel("Herz/Kreislauf:");
		
		JScrollPane scrollPane_7 = new JScrollPane();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblInfektionserkrankungen)
						.addComponent(lblRegelmigeImpfungen, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblHerzkreislauf))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_6, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE))
							.addGap(48))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane_7, GroupLayout.PREFERRED_SIZE, 299, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(48, Short.MAX_VALUE))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblInfektionserkrankungen))
					.addGap(13)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblRegelmigeImpfungen)
							.addGap(102)
							.addComponent(lblHerzkreislauf))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_7, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(120, Short.MAX_VALUE))
		);
		
		JTextPane textPane_7 = new JTextPane();
		scrollPane_7.setViewportView(textPane_7);
		
		JTextPane textPane_6 = new JTextPane();
		scrollPane_6.setViewportView(textPane_6);
		
		JTextPane textPane_5 = new JTextPane();
		scrollPane.setViewportView(textPane_5);
		panel.setLayout(gl_panel);
		GroupLayout gl_pContent = new GroupLayout(pContent);
		gl_pContent.setHorizontalGroup(
			gl_pContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pContent.createSequentialGroup()
					.addGap(1)
					.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_pContent.setVerticalGroup(
			gl_pContent.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pContent.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pContent.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_pContent.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
							.addGap(21))
						.addGroup(Alignment.TRAILING, gl_pContent.createSequentialGroup()
							.addGroup(gl_pContent.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
								.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
							.addGap(21))))
		);
		pContent.setLayout(gl_pContent);
		GroupLayout gl_tabber = new GroupLayout(tabber);
		gl_tabber.setHorizontalGroup(
			gl_tabber.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tabber.createSequentialGroup()
					.addGap(1)
					.addComponent(pContent, GroupLayout.DEFAULT_SIZE, 1307, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_tabber.setVerticalGroup(
			gl_tabber.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_tabber.createSequentialGroup()
					.addGap(1)
					.addComponent(pContent, GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
					.addGap(1))
		);
		tabber.setLayout(gl_tabber);
		
		JSpinner spinBirthdate = new JSpinner();
		SpinnerDateModel model = new SpinnerDateModel();
		model.setCalendarField(Calendar.MINUTE);
		getContentPane().setLayout(groupLayout);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(boolean editable, String strName) {
		frame = new JFrame();
		setTitle("Anamnese von " + strName);
		setBounds(100, 100, 1351, 687);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
