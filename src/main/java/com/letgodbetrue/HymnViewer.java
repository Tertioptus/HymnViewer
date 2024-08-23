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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.letgodbetrue.architecture.InfoPanelArchitect;
import com.letgodbetrue.architecture.LoadPanelArchitect;
import com.letgodbetrue.choreography.direction.DefaultDirector;
import com.letgodbetrue.choreography.direction.Director;
import com.letgodbetrue.choreography.movement.ComponentMover;

/**
	Encapsulated GUI that is launched from calling the "start" method.
**/
public class HymnViewer {

	private JPanel createRightPanel(JFrame parentFrame, JPanel centerPanel) {
		JPanel panel = new JPanel();
		parentFrame.add(panel, BorderLayout.EAST);
		panel.setPreferredSize(new Dimension(100, 500));
		JButton stageButton = new JButton("Stage");
		JButton cutButton = new JButton("Cut");
		cutButton.setEnabled(false);
		JButton backButton = new JButton("Back");
		JButton nextButton = new JButton("Next");
		int y = 0;
		final int height = 400;
		int imageCount =  6;
		int bottomOfView = (imageCount - 1) * height;
		for (int i = 0; i < imageCount; i++) {
			JLabel carouselLabel = new JLabel(String.valueOf(i));
			carouselLabel.setBounds(0, y, 1000, height);
			carouselLabel.setForeground(Color.white);
			carouselLabel.setIcon(createImageIcon("sample-"+ i + ".png", carouselLabel));
			centerPanel.add(carouselLabel);
			y+=400;
		}		
		backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component component:centerPanel.getComponents()) {
					component.setBounds(component.getX(),component.getY()>=bottomOfView? 0: component.getY()+height, component.getWidth(), component.getHeight());
				}

				System.out.println("Go back.");
			}
		}); 
		
		
		Director director = new DefaultDirector(0L, 1L);
		nextButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component component:centerPanel.getComponents()) {
					component.setBounds(component.getX(),component.getY()<=-height? bottomOfView: component.getY()-height, component.getWidth(), component.getHeight());
				}
			}
		}); 

		JButton quarterButton = new JButton("1");
		quarterButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component component:centerPanel.getComponents()) {
					director.start(new ComponentMover(component, bottomOfView), component.getY(), component.getY() - height, 250);
				}
			}
		}); 
	
		JButton halfButton = new JButton("2");
		halfButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component component:centerPanel.getComponents()) {
					director.start(new ComponentMover(component, bottomOfView), component.getY(), component.getY() - height, 500);
				}
			}
		}); 
	JButton threeQuartersButton = new JButton("3");

	threeQuartersButton.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			for(Component component:centerPanel.getComponents()) {
				director.start(new ComponentMover(component, bottomOfView), component.getY(), component.getY() - height, 750);
			}
		}
	}); 
		JButton wholeButton = new JButton("4");
		wholeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(Component component:centerPanel.getComponents()) {
					director.start(new ComponentMover(component, bottomOfView), component.getY(), component.getY() - height, 1000,2);
				}
			}
		}); 
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
		frame.add(new LoadPanelArchitect(Color.GRAY, 0, 0, 300, 100).provideJPanel(), BorderLayout.NORTH);
		frame.add(new InfoPanelArchitect(Color.DARK_GRAY, 0, 0, 200, 200).provideJPanel(), BorderLayout.SOUTH);
		createRightPanel(frame, createCenterPanel(frame));
		frame.setSize(1250, 900);
		frame.setLocation(100, 100);
		frame.setVisible(true);		
		frame.setTitle("Hymn Viewer");
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path, JLabel label) {
		try {
			BufferedImage image = ImageIO.read(new File("c:\\temp\\" + path));
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
