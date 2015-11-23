package com.nilhcem.md2html;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.UIManager;
import com.nilhcem.md2html.console.ArgsParser;
import com.nilhcem.md2html.console.ConsoleMode;
import com.nilhcem.md2html.console.DisplayUsageException;
import com.nilhcem.md2html.gui.MainFrame;
import org.pegdown.Extensions;

/**
 * Entry point of the application.
 * 
 * @author Nilhcem
 * @since 1.0
 */
public final class App {
	public static int pegdownOptions =
			Extensions.ABBREVIATIONS
			| Extensions.ATXHEADERSPACE
			| Extensions.AUTOLINKS
			| Extensions.DEFINITIONS
			| Extensions.EXTANCHORLINKS
			| Extensions.FENCED_CODE_BLOCKS
			| Extensions.FORCELISTITEMPARA
			| Extensions.HARDWRAPS
			| Extensions.RELAXEDHRULES
			| Extensions.SMARTS
			| Extensions.STRIKETHROUGH
			| Extensions.TABLES
			| Extensions.TASKLISTITEMS;

	private App() {}

	/**
	 * Launches the program in command line or using a GUI.
	 * <p>
	 * If the program is launched in GUI mode, sets some properties for a better look and feel integration.
	 * </p>
	 * 
	 * @param args a list of arguments.
	 */
	public static void main(String[] args) {
		try {
			int idxDir = -1;
			for(int i=0; i<args.length; i++){
				if(args[i].equals("-d")){
					idxDir = i+1;
				}
			}
			
			if(idxDir>0){
				try {
					File dir = new File(args[idxDir]);
					//System.out.println(dir.getAbsolutePath());
					if(!dir.isDirectory()){
						System.out.println(args[idxDir] + " must be dir.");
					}else{
						DoDir.doDir(dir);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				return;
			}
			
			
			ArgsParser params = new ArgsParser();
			params.checkArgs(args);

			if (params.isConsoleMode()) { // Command line
				new ConsoleMode().process(params);
			} else { // GUI
				EventQueue.invokeLater(() -> {
                    System.setProperty("apple.laf.useScreenMenuBar", "true");
                    System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Markdown editor");
                    UIManager.put("swing.boldMetal", Boolean.FALSE);
                    try {
                        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    } catch (Exception e) {
                        System.err.println("error: " + e.getMessage());
                        e.printStackTrace();
                    }
                    new MainFrame();
                });
			}
		} catch (DisplayUsageException e) {
			System.err.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.err.println(String.format("File not found: %s", e.getMessage()));
		}
	}
}
