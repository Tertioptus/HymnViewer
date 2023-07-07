package com.letgodbetrue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class HymnViewer {
	
	public void start() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel topPanel = new JPanel();
		topPanel.setBounds(0, 0, 300, 100);
		topPanel.setBackground(Color.red);
		JLabel hymnCodeLabel = new JLabel("Hymn Code");
		JTextField hymnCode = new JTextField(10);
		JButton sbmt = new JButton("Submit");
		topPanel.add(hymnCodeLabel);
		topPanel.add(hymnCode);
		topPanel.add(sbmt);
		JPanel bottomPanel = new JPanel();
		bottomPanel.setBackground(Color.PINK);
		bottomPanel.setBounds(0, 0, 200, 200);
		JPanel rightPanel = new JPanel();
		rightPanel.setPreferredSize(new Dimension(100, 500));
		JButton stageButton = new JButton("Stage");
		JButton cutButton = new JButton("Cut");
		cutButton.setEnabled(false);
		JButton backButton = new JButton("Back");
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Go back.");
			}
		});
		JButton nextButton = new JButton("Next");
		JButton quarterButton = new JButton("1");
		JButton halfButton = new JButton("2");
		JButton threeQuartersButton = new JButton("3");
		JButton wholeButton = new JButton("4");
		rightPanel.add(stageButton);
		rightPanel.add(cutButton);
		rightPanel.add(backButton);
		rightPanel.add(nextButton);
		rightPanel.add(quarterButton);
		rightPanel.add(halfButton);
		rightPanel.add(threeQuartersButton);
		rightPanel.add(wholeButton);
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(null);
		centerPanel.setBackground(Color.BLUE);
		JLabel activeLabel = new JLabel("2");
		activeLabel.setBounds(0, -200, 2000, 400);
		activeLabel.setForeground(Color.white);
		activeLabel.setIcon(createImageIcon("be right back.jpg"));
		//activeLabel.setPreferredSize(new Dimension(700, 200));
		activeLabel.setBorder(BorderFactory.createLineBorder(Color.orange));
		JLabel stagedLabel = new JLabel("3");
		stagedLabel.setIcon(createImageIcon("be right back.jpg"));
		stagedLabel.setForeground(Color.white);
		stagedLabel.setBounds(0, 400, 2000, 400);
		//stagedLabel.setPreferredSize(new Dimension(700, 200));
		stagedLabel.setBorder(BorderFactory.createLineBorder(Color.orange));
		centerPanel.add(activeLabel);
		centerPanel.add(stagedLabel);
		JPanel leftPanel = new JPanel();
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.add(centerPanel, BorderLayout.CENTER);
		frame.add(leftPanel, BorderLayout.WEST);
		frame.setSize(300, 300);
		//frame.setLayout(null);
		frame.setVisible(true);		
		//frame.pack(); // Will Collapse frame just around elements.
	}
	
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = HymnViewer.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

	public static void main(String[] args) {

		new HymnViewer().start();
	}
}
