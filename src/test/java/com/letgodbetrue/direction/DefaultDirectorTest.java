package com.letgodbetrue.direction;

import org.junit.Assert;
import org.junit.Test;

import com.letgodbetrue.choreography.direction.DefaultDirector;
import com.letgodbetrue.choreography.movement.Mover;


public class DefaultDirectorTest {
	
	@Test
	public void proveThatDirectorReachesGoalInTheAllotedTime() throws InterruptedException {
			
		int start = 0;
		int goal = 30;
		int duration = 500;
		final long startTime = System.currentTimeMillis();
		System.out.println("Start Time: " + startTime);
		float displacement = goal - start;
		final float speed = displacement / duration;
		Mover mover = new Mover() {
			int position = 0;

			@Override
			public void moveTo(int position) {
				this.position = position;
				System.out.println("Position: " + position 
						+ " Time elapsed: " + (System.currentTimeMillis() - startTime)
						+ " Speed: " + speed
						+ " Displacement: " + displacement);
			}

			@Override
			public int tellPosition() {
				return position;
			}
		};
		(new DefaultDirector(0L, 1L)).start(mover, start, goal, duration);
		Thread.sleep(2000);
		Assert.assertEquals(goal, mover.tellPosition());
	}
	
	@Test
	public void proveThatDirectorStopsWhenGoalIsReached() throws InterruptedException {
		
		/**
		 * On ThinkPad, i'm only getting 60 hits per second.  Perhaps this will suffice
		 * considering the 60 frames per second rate of standard film.
		 */
		int start = 0;
		int goal = 60;
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
