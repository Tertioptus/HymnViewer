package com.letgodbetrue.choreography.movement;

import java.awt.Component;

public class ComponentMover implements Mover {
	
	private Component component;
	private int bottomOfView;
	
	public ComponentMover(Component component, int bottomOfView) {
		this.component = component;
		this.bottomOfView = bottomOfView;
	}

	@Override
	public int tellPosition() {
		return component.getY();
	}

	@Override
	public void moveTo(int position) {
		component.setBounds(component.getX(), position <= -component.getHeight() ? bottomOfView : position, component.getWidth(), component.getHeight());
	}
}
