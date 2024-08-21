package com.letgodbetrue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
	Encapsulated GUI that is launched from calling the "start" method.
**/
public class HymnViewer {
	
	private JPanel createTopPanel(JFrame parentFrame) {
		JPanel panel = new JPanel();
		parentFrame.add(panel, BorderLayout.NORTH);
		panel.setBounds(0, 0, 300, 100);
		panel.setBackground(Color.GRAY);
		JLabel hymnCodeLabel = new JLabel("Hymn Code");
		JTextField hymnCode = new JTextField(10);
		JButton sbmt = new JButton("Submit");
		panel.add(hymnCodeLabel);
		panel.add(hymnCode);
		panel.add(sbmt);
		return panel;
	}	

	private JPanel createLeftPanel(JFrame parentFrame) {
		JPanel panel = new JPanel();
		parentFrame.add(panel, BorderLayout.WEST);
		return panel;
	}	

	private JPanel createRightPanel(JFrame parentFrame, JPanel centerPanel) {
		JPanel panel = new JPanel();
		parentFrame.add(panel, BorderLayout.EAST);
		panel.setPreferredSize(new Dimension(100, 500));
		JButton stageButton = new JButton("Stage");
		JButton cutButton = new JButton("Cut");
		cutButton.setEnabled(false);
		JButton backButton = new JButton("Back");
		int y = -200;
		for (int i = 0; i < 3; i++) {
			JLabel carouselLabel = new JLabel(String.valueOf(i));
			carouselLabel.setBounds(0, y, 1000, 400);
			carouselLabel.setForeground(Color.white);
			carouselLabel.setIcon(createImageIcon("sample-"+ i + ".png", carouselLabel));
			centerPanel.add(carouselLabel);
			y+=400;
		}		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component component:centerPanel.getComponents()) {
					component.setBounds(component.getX(),component.getY()==-400? 800: component.getY()-50, component.getWidth(), component.getHeight());
				}

				System.out.println("Go back.");
			}
		}); 

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
		return panel;
	}	

	private JPanel createBottomPanel(JFrame parentFrame) {
		JPanel panel = new JPanel();
		parentFrame.add(panel, BorderLayout.SOUTH);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 200, 200);
		return panel;
	}	

	private JPanel createCenterPanel(JFrame parentFrame) {
		JPanel panel = new JPanel();
		parentFrame.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		panel.setBackground(Color.WHITE);
		return panel;
	}	

	public void start() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createTopPanel(frame);
		createBottomPanel(frame);
		createRightPanel(frame, createCenterPanel(frame));
		createLeftPanel(frame);
		frame.setSize(1250, 800);
		frame.setLocation(100, 100);
		frame.setVisible(true);		
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path, JLabel label) {
		java.net.URL imgURL = HymnViewer.class.getResource(path);
		try {
			BufferedImage image = ImageIO.read(new File(imgURL.getPath()));
		    return new ImageIcon(image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		    System.err.println("Couldn't find file: " + path);
		}
		return null;
	}

	public static void main(String[] args) {

		new HymnViewer().start();
	}
}
