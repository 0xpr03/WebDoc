/*******************************************************************************
 * Copyright (c) 2015 by the WebDoc group
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the WebDoc license.
 * Available inside this binary and at http://webdoc.proctet.net/license.txt
 *******************************************************************************/
package webdoc.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Custom Texfield with autocomplete support
 * Experimental!
 * @author "Aron Heinecke"
 */
public class JAutoCompleteTextField extends JTextField {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1317632194630187821L;

	private static final int MAX_VISIBLE_ROWS = 8;

    private final List<String> history = new ArrayList<String>();

    private final JPopupMenu popup = new JPopupMenu() {
        /**
		 * 
		 */
		private static final long serialVersionUID = 563474143628228526L;

		public Dimension getPreferredSize() {
            Dimension dimension = super.getPreferredSize();

            dimension.width = JAutoCompleteTextField.this.getWidth();

            return dimension;
        }
    };

    private final JList list = new JList(new DefaultListModel());

    private String userText;

    private boolean notificationDenied;

    public JAutoCompleteTextField() {
        JScrollPane scrollPane = new JScrollPane(list,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBar(null);
        scrollPane.setBorder(null);

        list.setFocusable(false);

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
                    setTextWithoutNotification((String) list.getSelectedValue());

                    popup.setVisible(false);
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
                        case KeyEvent.VK_LEFT:
                        case KeyEvent.VK_RIGHT: {
                            popup.setVisible(false);

                            break;
                        }
                    }
                } else {
                    if (e.getKeyCode() == KeyEvent.VK_DOWN ||
                            e.getKeyCode() == KeyEvent.VK_UP ||
                            e.getKeyCode() == KeyEvent.VK_PAGE_UP ||
                            e.getKeyCode() == KeyEvent.VK_PAGE_DOWN) {
                        userText = getText();

                        showFilteredHistory();
                    }
                }
            }
        });
    }

    private void changeListSelectedIndex(int delta) {
        int size = list.getModel().getSize();
        int index = list.getSelectedIndex();

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

            setTextWithoutNotification((String) list.getSelectedValue());
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

            showFilteredHistory();
        }
    }

    private void showFilteredHistory() {
        list.getSelectionModel().clearSelection();

        DefaultListModel model = (DefaultListModel) list.getModel();

        model.clear();

        for (String s : history) {
            if (s.contains(userText)) {
                model.addElement(s);
            }
        }

        int size = model.size();

        if (size == 0) {
            popup.setVisible(false);
        } else {
            list.setVisibleRowCount(size < MAX_VISIBLE_ROWS ? size : MAX_VISIBLE_ROWS);

            popup.pack();

            if (!popup.isShowing()) {
                popup.show(JAutoCompleteTextField.this, 0, getHeight());
            }
        }
    }

    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }

    public void setHistory(List<? extends String> history) {
        this.history.clear();
        this.history.addAll(history);
    }
}
