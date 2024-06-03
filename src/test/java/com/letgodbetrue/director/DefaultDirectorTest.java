package com.letgodbetrue.director;

import org.junit.Assert;
import org.junit.Test;

import com.letgodbetrue.Mover;


public class DefaultDirectorTest {

	
	@Test
	public void proveThatTimeRuns(){
		int start = 0;
		int goal = 255;
		Mover mover = new Mover() {
			
			@Override
			public void moveTo(int position) {
				System.out.write(position);
			}
		};
		(new DefaultDirector()).start(mover, start, goal);
		Assert.fail();
	}
}
