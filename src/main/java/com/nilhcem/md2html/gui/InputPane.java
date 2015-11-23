package com.nilhcem.md2html.gui;

import javax.swing.*;
import javax.swing.text.Document;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

/**
 * Scrolled text area where will be inpute markdown data to be converted.
 * Also got Undo/redo support
 *
 * @author Nilhcem
 * @author janhoy
 * @since 1.0
 */
public final class InputPane extends Observable {
	private final JScrollPane inputPane = new JScrollPane();
	private final JTextArea inputTextArea = new JTextArea();

	/**
	 * Creates the text area and add a key listener to call observer every time a key is released.
	 */
	public InputPane() {
		inputPane.getViewport().add(inputTextArea, null);

		final UndoManager undo = new UndoManager();
		Document doc = inputTextArea.getDocument();

		// Listen for undo and redo events
		doc.addUndoableEditListener(evt -> undo.addEdit(evt.getEdit()));

		// Create an undo action and add it to the text component
		inputTextArea.getActionMap().put("Undo",
		    new AbstractAction("Undo") {
		        public void actionPerformed(ActionEvent evt) {
		            try {
		                if (undo.canUndo()) {
		                    undo.undo();
		                }
		            } catch (CannotUndoException ignored) {
		            }
		        }
		   });

		// Bind the undo action to ctl-Z
		inputTextArea.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "Undo");
		inputTextArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.META_DOWN_MASK), "Undo");

		// Create a redo action and add it to the text component
		inputTextArea.getActionMap().put("Redo",
		    new AbstractAction("Redo") {
		        public void actionPerformed(ActionEvent evt) {
		            try {
		                if (undo.canRedo()) {
		                    undo.redo();
		                }
		            } catch (CannotRedoException ignored) {
		            }
		        }
		    });

		// Bind the redo action to ctl-Y
		inputTextArea.getInputMap().put(KeyStroke.getKeyStroke("control Y"), "Redo");
		inputTextArea.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.META_DOWN_MASK | KeyEvent.SHIFT_DOWN_MASK), "Redo");

		inputTextArea.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}
			@Override
			public void keyReleased(KeyEvent e) {
				setChanged();
				notifyObservers(inputTextArea.getText());
			}
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	/**
	 * Returns the JScrollPane object.
	 *
	 * @return the JScrollPane object.
	 */
	public JScrollPane get() {
		return inputPane;
	}
}
