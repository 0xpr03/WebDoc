package webdoc.gui.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Custom Texfield with autocomplete support Experimental! Highly modded
 * textfield
 * 
 * @author "Aron Heinecke"
 */
public class JSearchTextField extends JTextField {

	/**
	 * Interface for communicating with the search field.
	 * 
	 * @author "Aron Heinecke"
	 */
	public interface searchFieldAPI {
		/**
		 * Function fired when the search text changes, needs to provide a list
		 * of Element to display.
		 * 
		 * @param text
		 * @return List<ACElement>
		 */
		public List<ACElement> getData(String text);

		/**
		 * Fired event when an element from the list is chosen.
		 * 
		 * @param element
		 */
		public void changedSelectionEvent(ACElement element);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1317632194630187821L;

	private static final int MAX_VISIBLE_ROWS = 8;
	private static final int MIN_CHARS = 3;
	private searchFieldAPI api = null;
	private ACElement chosenElement = null;

	private final JPopupMenu popup = new JPopupMenu() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 563474143628228526L;

		public Dimension getPreferredSize() {
			Dimension dimension = super.getPreferredSize();

			dimension.width = JSearchTextField.this.getWidth();

			return dimension;
		}
	};

	private final JList<ACElement> list = new JList<ACElement>(new DefaultListModel<ACElement>());

	private String userText;

	private boolean notificationDenied;

	public void setAPI(searchFieldAPI api) {
		this.api = api;
	}

	public JSearchTextField() {
		JScrollPane scrollPane = new JScrollPane(list, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBar(null);
		scrollPane.setBorder(null);

		list.setFocusable(false);
		list.setCellRenderer(new SearchCellRenderer());

		popup.add(scrollPane);
		popup.setFocusable(false);
		popup.setBorder(new LineBorder(Color.BLACK, 1));

		getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				onTextChanged();
			}

			public void removeUpdate(DocumentEvent e) {
				onTextChanged();
			}

			public void changedUpdate(DocumentEvent e) {
				onTextChanged();
			}
		});

		list.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent e) {
				int index = list.locationToIndex(e.getPoint());
				if (index >= 0 && list.getSelectedIndex() != index) {
					list.setSelectedIndex(index);
				}
			}
		});

		list.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					setTextWithoutNotification(list.getSelectedValue().toString());
					popup.setVisible(false);
					api.changedSelectionEvent(chosenElement);
				}
			}
		});

		addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				popup.setVisible(false);
			}
		});

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (popup.isShowing()) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP: {
						changeListSelectedIndex(-1);
						break;
					}

					case KeyEvent.VK_PAGE_UP: {
						changeListSelectedIndex(-list.getVisibleRowCount());
						break;
					}

					case KeyEvent.VK_DOWN: {
						changeListSelectedIndex(1);
						break;
					}

					case KeyEvent.VK_PAGE_DOWN: {
						changeListSelectedIndex(list.getVisibleRowCount());
						break;
					}

					case KeyEvent.VK_ESCAPE: {
						popup.setVisible(false);
						setTextWithoutNotification(userText);
						break;
					}

					case KeyEvent.VK_ENTER:
						api.changedSelectionEvent(chosenElement);
					case KeyEvent.VK_LEFT:
					case KeyEvent.VK_RIGHT: {
						popup.setVisible(false);
						break;
					}
					}
				} else {
					if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP
							|| e.getKeyCode() == KeyEvent.VK_PAGE_UP || e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
						userText = getText();

						showFilteredHistory(api.getData(userText));
					}
				}
			}
		});
	}

	private void changeListSelectedIndex(int delta) {
		int size = list.getModel().getSize();
		int index = list.getSelectedIndex();
		chosenElement = list.getSelectedValue();

		int newIndex;

		if (index < 0) {
			newIndex = delta > 0 ? 0 : size - 1;
		} else {
			newIndex = index + delta;
		}

		if (newIndex >= size || newIndex < 0) {
			newIndex = newIndex < 0 ? 0 : size - 1;
			if (index == newIndex) {
				newIndex = -1;
			}
		}

		if (newIndex < 0) {
			list.getSelectionModel().clearSelection();
			list.ensureIndexIsVisible(0);
			setTextWithoutNotification(userText);
		} else {
			list.setSelectedIndex(newIndex);
			list.ensureIndexIsVisible(newIndex);
			setTextWithoutNotification(list.getSelectedValue().toString());
		}
	}

	private void setTextWithoutNotification(String text) {
		notificationDenied = true;
		try {
			setText(text);
		} finally {
			notificationDenied = false;
		}
	}

	private void onTextChanged() {
		if (!notificationDenied) {
			userText = getText();
			if (userText.length() >= MIN_CHARS) {
				showFilteredHistory(api.getData(userText));
			} else {
				popup.setVisible(false);
			}
		}
	}

	private void showFilteredHistory(List<ACElement> data) {
		list.getSelectionModel().clearSelection();

		DefaultListModel<ACElement> model = (DefaultListModel<ACElement>) list.getModel();

		model.clear();

		for (ACElement s : data) {
			model.addElement(s);
		}

		int size = model.size();

		if (size == 0) {
			popup.setVisible(false);
		} else {
			list.setVisibleRowCount(size < MAX_VISIBLE_ROWS ? size : MAX_VISIBLE_ROWS);

			popup.pack();

			if (!popup.isShowing()) {
				popup.show(JSearchTextField.this, 0, getHeight());
			}
		}
	}

	/**
	 * Custom cell renderer for ACElement Lists
	 * 
	 * @author "Aron Heinecke"
	 */
	class SearchCellRenderer extends JLabel implements ListCellRenderer<ACElement> {
		private static final long serialVersionUID = 6562532559604303553L;
		private Color HIGHLIGHT_COLOR = new Color(51, 153, 255);

		public SearchCellRenderer() {
			setOpaque(true);
			setIconTextGap(12);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends ACElement> list, ACElement element, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText("<html>" + element.getName() + " " + element.getOptname() + " <font size=-5><i>"
					+ element.getType() + "</i></font></html>");
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
}
