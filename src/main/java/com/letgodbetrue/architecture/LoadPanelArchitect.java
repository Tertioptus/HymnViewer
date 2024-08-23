package com.letgodbetrue.architecture;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoadPanelArchitect extends HymnViewerPanelArchitect {
	
	public LoadPanelArchitect(Color backgroundColor, int boundX, int boundY, int boundWidth, int boundHeight) {
		super(backgroundColor, boundX, boundY, boundWidth, boundHeight);
		JLabel hymnCodeLabel = new JLabel("Hymn Code");
		JTextField hymnCode = new JTextField(10);
		JButton sbmt = new JButton("Submit");
		panel.add(hymnCodeLabel);
		panel.add(hymnCode);
		panel.add(sbmt);	
	}

}
