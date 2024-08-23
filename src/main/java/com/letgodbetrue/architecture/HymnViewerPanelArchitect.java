package com.letgodbetrue.architecture;

import java.awt.Color;

import javax.swing.JPanel;

/**
 * Responsible for building the common structure for hymn viewer panels.
 */
public abstract class HymnViewerPanelArchitect {

	protected final JPanel panel = new JPanel();

	public HymnViewerPanelArchitect(Color backgroundColor, int boundX, int boundY, int boundWidth, int boundHeight) {
		panel.setBackground(backgroundColor);
		panel.setBounds(boundX, boundY, boundWidth, boundHeight);
	}

	public JPanel provideJPanel() {
		return panel;
	}
}
