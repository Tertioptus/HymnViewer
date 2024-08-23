package com.letgodbetrue.choreography.direction;

import com.letgodbetrue.choreography.movement.Mover;

public interface Director {
	
	void start(Mover mover, int start, int goal);
	void start(Mover mover, int start, int goal, int duration);
	void start(Mover mover, int start, int goal, int duration, int acceration);
}
