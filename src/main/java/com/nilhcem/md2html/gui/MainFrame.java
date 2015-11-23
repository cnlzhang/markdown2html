package com.nilhcem.md2html.gui;

import java.awt.Dimension;
import javax.swing.*;

/**
 * Provides the main window of the application.
 *
 * @author Nilhcem
 * @since 1.0
 */
public final class MainFrame {

	/**
	 * Creates the main window and makes it visible.
	 */
	public MainFrame() {
		Dimension frameSize = new Dimension(900, 500);

		JFrame mainFrame = new JFrame("Markdown editor");
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setSize(frameSize);
		mainFrame.setMinimumSize(frameSize);

		MenuBar menu = new MenuBar();
		mainFrame.setJMenuBar(menu.get());
		MainPanel panel = new MainPanel();
		mainFrame.getContentPane().add(panel.get());
		mainFrame.setLocationRelativeTo(null); // Center main frame
		mainFrame.setVisible(true);
	}
}
