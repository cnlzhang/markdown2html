package com.nilhcem.md2html.gui;

import com.nilhcem.md2html.App;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import org.pegdown.PegDownProcessor;

import javax.swing.*;
import java.io.*;
import java.util.Observable;
import java.util.Observer;
import java.util.stream.Collectors;

/**
 * Scrolled text area where will be displayed the HTML preview.
 *
 * @author Nilhcem
 * @since 1.0
 */
public final class PreviewPane implements Observer {
	private JFXPanel javafxPanel;
	private WebView webComponent;
	private static String css;

	/**
	 * Creates the HTML Pane and adds Github stylesheet
	 */
	public PreviewPane() {
		javafxPanel = new JFXPanel();

		// Load style sheet into string for inclusion in HTML
		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream("github-markdown.css");
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		css = reader.lines().collect(Collectors.joining(System.getProperty("line.separator")));

		Platform.runLater(() -> {
            BorderPane borderPane = new BorderPane();
            webComponent = new WebView();
            webComponent.getEngine().loadContent("");
            borderPane.setCenter(webComponent);
            Scene scene = new Scene(borderPane,750,500);
            javafxPanel.setScene(scene);
        });

	}

	/**
	 * Returns the JScrollPane object.
	 *
	 * @return the JScrollPane object.
	 */
	public JFXPanel get() {
		return javafxPanel;
	}

	/**
	 * Updates the content of the label by converting the input data to html and setting them to the JavaFX view
	 * <p>
	 * This method will be called by an {@code InputPane} observable.
	 * </p>
	 *
	 * @param o the observable element which will notify this class.
	 * @param data a String object containing the input data to be converted into HTML.
	 */
	@Override
	public void update(final Observable o, final Object data) {
		if (o instanceof InputPane) {
			SwingUtilities.invokeLater(() -> {
                String content = (String)data;
                PegDownProcessor processor = new PegDownProcessor(App.pegdownOptions);

                Platform.runLater(() -> {
String html = String.format("<html>" +
		"<head><style>%s</style></head>" +
		"<body>%s</body>" +
		"</html>", PreviewPane.css, processor.markdownToHtml(content)).replaceAll("src=\"", "src=\"file:");
webComponent.getEngine().loadContent(html);
});
            });
		}
	}
}
