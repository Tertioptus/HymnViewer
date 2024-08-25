package com.letgodbetrue.production;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;

import com.letgodbetrue.architecture.ControlPanelArchitect;
import com.letgodbetrue.architecture.InfoPanelArchitect;
import com.letgodbetrue.architecture.LoadPanelArchitect;
import com.letgodbetrue.architecture.ViewPanelArchitect;
import com.letgodbetrue.choreography.direction.Director;
import com.letgodbetrue.choreography.movement.ComponentMover;
import com.letgodbetrue.common.MapEngineer;

public final class DefaultHymnViewProducer implements HymnViewProducer {

	private int height;
	private int width;
	private final String path;
	private final Director director;
	private final ViewPanelArchitect viewPanelArchitect;
	private final ControlPanelArchitect controlPanelArchitect;
	private Component[] musicLines;

	public DefaultHymnViewProducer(Director director, MapEngineer<String, String> mapEngineer) throws NumberFormatException, Exception {
		this.director = director;
		height = Integer.parseInt(mapEngineer.value("height"));
		width = Integer.parseInt(mapEngineer.value("width"));
		path = mapEngineer.value("hymn.root.path");
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new LoadPanelArchitect(this, Color.GRAY, 0, 0, 300, 100).provideJPanel(), BorderLayout.NORTH);
		frame.add(new InfoPanelArchitect(Color.DARK_GRAY, 0, 0, 200, 200).provideJPanel(), BorderLayout.SOUTH);
		frame.add((viewPanelArchitect = new ViewPanelArchitect(Color.WHITE, 0, 0, 0, 0)).provideJPanel(), BorderLayout.CENTER);
		frame.add((controlPanelArchitect = new ControlPanelArchitect(this, Color.GRAY, 0, 0, 0, 0)).provideJPanel(), BorderLayout.EAST);
		frame.setSize(1250, 900);
		frame.setLocation(100, 100);
		frame.setVisible(true);		
		frame.setTitle("Hymn Viewer");
	}

	@Override
	public void performDimensionUpdate(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void performBackTransition() {
		int bottomOfView = (musicLines.length - 1) * height;
		for(Component musicLine:musicLines) {
			musicLine.setBounds(musicLine.getX(),musicLine.getY()>=bottomOfView? 0: musicLine.getY()+height, musicLine.getWidth(), musicLine.getHeight());
		}
	}

	@Override
	public void performTransition(int duration, int acceleration) {
		int bottomOfView = (musicLines.length - 1) * height;
		for(Component musicLine:musicLines) {
			director.start(new ComponentMover(musicLine, bottomOfView), musicLine.getY(), musicLine.getY() - height, duration, acceleration);
		}
	}

	@Override
	public void loadHymn(String hymnCode) {
		String[] hymnCodeTokens = hymnCode.split("-");
		String book = hymnCodeTokens[0];
		String hymn = hymnCodeTokens[1];
		
		File[] files = new File(path + (path.matches("(\\d|\\w)$") ? "": "\\") + book + "\\" + hymn + "\\").listFiles();
		
		List<File> fileList = Arrays.asList(files);
		
		List<String> imageFilePaths = fileList.stream()
			.filter(f -> f.getName().matches("^line-\\d{2}.*"))
			.map(f -> f.getAbsolutePath())
			.sorted()
			.collect(Collectors.toList());
		
		musicLines = viewPanelArchitect.loadImages(imageFilePaths, width, height);
	}
}
