package com.letgodbetrue.architecture;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.letgodbetrue.common.MapEngineer;
import com.letgodbetrue.production.HymnViewProducer;

public final class ControlPanelArchitect extends HymnViewerPanelArchitect {
	
	private int accelerationLevel = 1;
	private int duration = 1000;
	
	public ControlPanelArchitect(HymnViewProducer producer, MapEngineer<String, String> mapEngineer, Color backgroundColor, int boundX, int boundY, int boundWidth, int boundHeight) throws NumberFormatException, Exception {
		super(backgroundColor, boundX, boundY, boundWidth, boundHeight);
		duration = Integer.parseInt(mapEngineer.value("duration"));
		panel.setPreferredSize(new Dimension(100, 500));
		JButton backButton = new JButton("Back");
		JButton nextButton = new JButton("Next");	
		JButton quarterButton = new JButton("d/4");
		JButton halfButton = new JButton("d/2");
		JButton threeQuartersButton = new JButton("3d/4");
		JButton wholeButton = new JButton("d");	
		panel.add(backButton);
		panel.add(nextButton);
		panel.add(quarterButton);
		panel.add(halfButton);
		panel.add(threeQuartersButton);
		panel.add(wholeButton);
		panel.add(new JLabel("Accelerator"));
		JRadioButton constant = new JRadioButton("Constant (0)");
		JRadioButton linear = new JRadioButton("Linear (x)");
		JRadioButton quadratic = new JRadioButton("Quadratic (x^2)");
		JRadioButton cubic = new JRadioButton("Cubic (x^3)");
		JRadioButton quartic = new JRadioButton("Quartic (x^4)");
		JRadioButton quintic = new JRadioButton("Quintic (x^5)");
		JPanel acceleratorGroup = new JPanel();
		acceleratorGroup.setPreferredSize(new Dimension(100, 500));
		acceleratorGroup.setLayout(new BoxLayout(acceleratorGroup, BoxLayout.Y_AXIS));
		acceleratorGroup.setAlignmentX(Component.LEFT_ALIGNMENT);
		acceleratorGroup.add(constant);
		acceleratorGroup.add(linear);
		acceleratorGroup.add(quadratic);
		acceleratorGroup.add(cubic);
		acceleratorGroup.add(quartic);
		acceleratorGroup.add(quintic);
		panel.add(acceleratorGroup);
		ButtonGroup group = new ButtonGroup();
		group.add(constant);
		group.add(linear);
		group.add(quadratic);
		group.add(cubic);
		group.add(quartic);
		group.add(quintic);
		linear.setEnabled(true);
		constant.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					accelerationLevel = 0;
				}
			}
		});
		linear.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					accelerationLevel = 1;
				}
			}
		});
		quadratic.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					accelerationLevel = 2;
				}
			}
		});
		cubic.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					accelerationLevel = 3;
				}
			}
		});
		quartic.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					accelerationLevel = 4;
				}
			}
		});
		quintic.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					accelerationLevel = 5;
				}
			}
		});
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				producer.performBackTransition();
			}
		});  
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				producer.performTransition(0, accelerationLevel);
			}
		}); 
		quarterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				producer.performTransition(Math.floorDiv(duration, 4), accelerationLevel);
			}
		});  
		halfButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				producer.performTransition(Math.floorDiv(duration, 2), accelerationLevel);
			}
		});  
		threeQuartersButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				producer.performTransition(Math.floorDiv(3 * duration, 4), accelerationLevel);
			}
		});  
		wholeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				producer.performTransition(duration, accelerationLevel);
			}
		}); 
	}
}
