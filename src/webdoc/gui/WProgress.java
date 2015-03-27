package webdoc.gui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalExclusionType;

/**
 * ProgressWindow for external usage
 * @author "Aron Heinecke"
 *
 */
public class WProgress extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -945912363222219370L;
	private final JPanel contentPanel = new JPanel();
	private JProgressBar progressBarTop;
	private JLabel lblNewLabel_Top;
	private JProgressBar progressBarSub;
	private JLabel lblNewLabel_Sub;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		try {
			WProgress dialog = new WProgress();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public WProgress() {
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		progressBarTop = new JProgressBar();
		
		lblNewLabel_Top = new JLabel("New label");
		lblNewLabel_Top.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_Top.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel_Top.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		progressBarSub = new JProgressBar();
		
		lblNewLabel_Sub = new JLabel("New label");
		lblNewLabel_Sub.setVerticalTextPosition(SwingConstants.TOP);
		lblNewLabel_Sub.setVerticalAlignment(SwingConstants.TOP);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_Sub, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblNewLabel_Top, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
						.addComponent(progressBarSub, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
						.addComponent(progressBarTop, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel_Top, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBarTop, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_Sub, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(progressBarSub, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	public void setMax(int i){
		progressBarTop.setMaximum(i);
	}
	public void setProgress(int i){
		progressBarTop.setValue(i);
	}
	/**
	 * Add x to progress
	 * @param i
	 */
	public void addProgress(int i){
		progressBarTop.setValue(progressBarTop.getValue()+i);
	}
	public void setText(String text){
		lblNewLabel_Top.setText(text);
	}
	
	public void setSubMax(int i){
		progressBarSub.setMaximum(i);
	}
	public void setSubProgress(int i){
		progressBarSub.setValue(i);
	}
	/**
	 * Add x to progress
	 * @param i
	 */
	public void addSubProgress(int i){
		progressBarSub.setValue(progressBarSub.getValue()+i);
	}
	public void setSubText(String text){
		lblNewLabel_Sub.setText(text);
	}
}
