package com.letgodbetrue.architecture;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public final class ViewPanelArchitect extends HymnViewerPanelArchitect {
	
	public ViewPanelArchitect(Color backgroundColor, int boundX, int boundY, int boundWidth, int boundHeight) {
		super(backgroundColor, boundX, boundY, boundWidth, boundHeight);
		panel.setLayout(null);
	}
	
	public Component[] loadImages (List<String> imageFilePaths, int width, int height) {
		panel.removeAll();
		int bottomOfContent = 0;
		for (String path: imageFilePaths) {
			JLabel carouselLabel = new JLabel(path);
			carouselLabel.setBounds(0, bottomOfContent, width, height);
			carouselLabel.setForeground(Color.white);
			carouselLabel.setIcon(createImageIcon(path, carouselLabel));
			panel.add(carouselLabel);
			bottomOfContent+=height;
		}
		panel.repaint();
		return panel.getComponents();
	}
	
	/** Returns an ImageIcon, or null if the path was invalid. */
	private ImageIcon createImageIcon(String path, JLabel label) {
		try {
			BufferedImage image = ImageIO.read(new File(path));
		    return new ImageIcon(image.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
		} catch (IOException e) {
			e.printStackTrace();
		    System.err.println("Couldn't find file: " + path);
		}
		return null;
	}
}
