package com.letgodbetrue.director;

import com.letgodbetrue.Mover;

public interface Director {
	
	void start(Mover mover, int start, int goal);

}
