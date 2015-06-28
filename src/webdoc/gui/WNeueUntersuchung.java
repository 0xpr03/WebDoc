package webdoc.gui;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import webdoc.gui.utils.WModelPane;
import webdoc.lib.Database;
import webdoc.lib.GUIManager;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingUtilities;
import javax.xml.crypto.Data;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.Calendar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class WNeueUntersuchung extends WModelPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1755313522984992683L;
	private JFrame frmNeueUntersuchung;
	private JTextField tFName;
	private JTextPane tPBefund;
	private JSpinner spDate;
	private JButton btnSpeichern;
	private JButton btnCancel;
	private Boolean editable;
	private long id;



	public WNeueUntersuchung(long id) {
		initialize(id);
	}

	/**
	 * Initialize the contents of the frame.
	 */
		private void initialize(long id) {
			frmNeueUntersuchung = new JFrame();
			frmNeueUntersuchung.setTitle("Neue Untersuchung");
			frmNeueUntersuchung.setBounds(100, 100, 450, 335);
			frmNeueUntersuchung.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			JPanel panel = new JPanel();
			frmNeueUntersuchung.getContentPane().add(panel, BorderLayout.SOUTH);
			panel.setLayout(new MigLayout("", "[][][][]", "[]"));
			
			btnSpeichern = new JButton("Speichern");
			panel.add(btnSpeichern, "cell 1 0");
			
			btnCancel = new JButton("Cancel");
			panel.add(btnCancel, "cell 2 0");
			
			JPanel panel_1 = new JPanel();
			frmNeueUntersuchung.getContentPane().add(panel_1, BorderLayout.CENTER);
			
			JLabel lblNameDesTieres = new JLabel("Name des Tieres:");
			
			tFName = new JTextField();
			tFName.setColumns(10);
			
			JLabel lblDatum = new JLabel("Datum:");
			
			spDate = new JSpinner();
			spDate.setModel(new SpinnerDateModel(new Date(1435442400000L), null, null, Calendar.DAY_OF_YEAR));
			panel_1.setLayout(new MigLayout("", "[83px][333px,grow]", "[20px][20px][grow]"));
			panel_1.add(lblNameDesTieres, "cell 0 0,alignx left,aligny center");
			panel_1.add(tFName, "cell 1 0,growx,aligny top");
			panel_1.add(lblDatum, "cell 0 1,alignx left,aligny center");
			panel_1.add(spDate, "cell 1 1,alignx left,aligny top");
			
			JLabel lblBefund = new JLabel("Befund");
			panel_1.add(lblBefund, "cell 0 2");
			
			JScrollPane scrollPane = new JScrollPane();
			panel_1.add(scrollPane, "cell 1 2,grow");
			
			tPBefund = new JTextPane();
			scrollPane.setViewportView(tPBefund);
			tFName.setEditable(false);
			
			
			
			setEditable();
		}
	
		private void setEditable() {
			tPBefund.setEditable(editable);
			spDate.setEnabled(editable);
			refreshBtn();
		}
	
		private void refreshBtn() {
			btnSpeichern.setText(editable ? "Speichern" : "Schließen");
			btnCancel.setText(editable ? "Cancel" : "Edit");		
		}
		
		private boolean allSet(){
			return tPBefund.getText() != "";
		}
		
		private void addExamination() {
			if (allSet()) {
				setGlassPaneVisible(true);
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						Thread t = new Thread(new Runnable() {
							public void run() {
								try {
									id = Database.insertExamination(getID(), spDate.getValue().toString(), tPBefund.getText().toString());
									editable = false;
									setEditable();
								} catch (SQLException e) {
									GUIManager.showDBErrorDialog(null, Database.DBExceptionConverter(e, true));
								}
								setGlassPaneVisible(false);
							}}
						);
						t.start();
					}
				});
			} else {
				GUIManager.showFieldErrorDialog(getFrame());
			}
		}
		private void getExamination(){
			ResultSet rs = Database.getExamination();
			try {
				id = rs.getLong(1);
				spDate.setValue(rs.getDate(2));
				tPBefund.setText(rs.getString(3));
				editable = false;
				setEditable();
			} catch (SQLException e) {
				
			}
		}
		private long getID() {
			return id;
			}
}
