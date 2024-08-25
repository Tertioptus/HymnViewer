package com.letgodbetrue.production;

public interface HymnViewProducer {
	
	void loadHymn(String hymnCode);

	void performDimensionUpdate(int width, int height);

	void performBackTransition();

	void performTransition(int duration, int acceleration);
}
