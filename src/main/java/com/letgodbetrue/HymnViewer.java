package com.letgodbetrue;

import com.letgodbetrue.choreography.direction.DefaultDirector;
import com.letgodbetrue.common.ExternalPropertiesMapEngineer;
import com.letgodbetrue.production.DefaultHymnViewProducer;

/**
	Encapsulated GUI that is launched from calling the "start" method.
**/
public class HymnViewer {

	public static void main(String[] args) throws NumberFormatException, Exception {
		new DefaultHymnViewProducer(
				new DefaultDirector(0, 1l), 
				new ExternalPropertiesMapEngineer("general.properties")
		);
	}
}
