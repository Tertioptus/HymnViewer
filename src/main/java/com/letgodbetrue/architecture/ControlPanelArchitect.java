package com.letgodbetrue.architecture;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.letgodbetrue.production.HymnViewProducer;

public final class ControlPanelArchitect extends HymnViewerPanelArchitect {
	
	public ControlPanelArchitect(HymnViewProducer producer, Color backgroundColor, int boundX, int boundY, int boundWidth, int boundHeight) {
		super(backgroundColor, boundX, boundY, boundWidth, boundHeight);
		panel.setPreferredSize(new Dimension(100, 500));
		JButton stageButton = new JButton("Stage");
		JButton cutButton = new JButton("Cut");
		cutButton.setEnabled(false);
		JButton backButton = new JButton("Back");
		JButton nextButton = new JButton("Next");	
		JButton quarterButton = new JButton("1");
		JButton halfButton = new JButton("2");
		JButton threeQuartersButton = new JButton("3");
		JButton wholeButton = new JButton("4");	
		panel.add(stageButton);
		panel.add(cutButton);
		panel.add(backButton);
		panel.add(nextButton);
		panel.add(quarterButton);
		panel.add(halfButton);
		panel.add(threeQuartersButton);
		panel.add(wholeButton);
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				producer.performBackTransition();
			}
		}); 
		wholeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				producer.performTransition(1000, 2);
			}
		}); 
	}
}
