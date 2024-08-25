package com.letgodbetrue.architecture;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.letgodbetrue.production.HymnViewProducer;

public final class LoadPanelArchitect extends HymnViewerPanelArchitect {
	
	private final JTextField hymnCode = new JTextField(10);

	public LoadPanelArchitect(HymnViewProducer producer, Color backgroundColor, int boundX, int boundY, int boundWidth, int boundHeight) {
		super(backgroundColor, boundX, boundY, boundWidth, boundHeight);
		JLabel hymnCodeLabel = new JLabel("Hymn Code");
		JButton sbmt = new JButton("Submit");
		panel.add(hymnCodeLabel);
		panel.add(hymnCode);
		panel.add(sbmt);	
		sbmt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				producer.loadHymn(hymnCode.getText());
			}
		});
	}
}
