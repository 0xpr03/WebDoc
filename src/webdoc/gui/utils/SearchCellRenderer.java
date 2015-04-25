package webdoc.gui.utils;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SearchCellRenderer extends JLabel implements ListCellRenderer<ACElement> {
	private static final long serialVersionUID = 6562532559604303553L;
	private Color HIGHLIGHT_COLOR = new Color(0, 0, 128);
	private Logger logger = LogManager.getLogger();

	public SearchCellRenderer() {
		setOpaque(true);
		setIconTextGap(12);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends ACElement> list, ACElement element, int index, boolean isSelected,
			boolean cellHasFocus) {
		logger.debug("running custom cell renderer..");
		setText("<html>" + element.getName() + " " + element.getOptname() + " <font size=-10>" + element.getType()
				+ "</font></html>");
		if (isSelected) {
			setBackground(HIGHLIGHT_COLOR);
			setForeground(Color.white);
		} else {
			setBackground(Color.white);
			setForeground(Color.black);
		}
		return this;
	}	
}
