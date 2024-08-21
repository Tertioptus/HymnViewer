package com.letgodbetrue.director;

import org.junit.Assert;
import org.junit.Test;

import com.letgodbetrue.Mover;


public class DefaultDirectorTest {
	
	@Test
	public void proveThatDirectorReachesGoalInTheAllotedTime() {
		Assert.fail();
	}
	
	@Test
	public void proveThatDirectorStopsWhenGoalIsReached() throws InterruptedException {
		
		int start = 0;
		int goal = 10;
		Mover mover = new Mover() {
			int position = 0;

			@Override
			public void moveTo(int position) {
				this.position = position;
			}

			@Override
			public int tellPosition() {
				return position;
			}
		};
		(new DefaultDirector(0L, 1L)).start(mover, start, goal);
		Thread.sleep(1000);
		Assert.assertEquals(goal, mover.tellPosition());
	
	}

	
	@Test
	public void proveThatTimeRuns() throws InterruptedException{
		int start = 0;
		int goal = 255;
		Mover mover = new Mover() {
			int position = 0;

			@Override
			public void moveTo(int position) {
				this.position = position;
			}

			@Override
			public int tellPosition() {
				return position;
			}
		};
		(new DefaultDirector(100L, 100L)).start(mover, start, goal);
		Thread.sleep(1000);
		Assert.assertEquals(9, mover.tellPosition());
	}
}
