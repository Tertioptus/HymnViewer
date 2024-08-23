package com.letgodbetrue.director;

import java.util.Timer;
import java.util.TimerTask;

import com.letgodbetrue.Mover;

public class DefaultDirector implements Director {
	private long delay;
	private long period;

	public DefaultDirector(long delay, long period) {
		this.delay = delay;
		this.period = period;
	}

	@Override
	public void start(Mover mover, int start, int goal) {
		final Timer timer = new Timer("Timer");
		TimerTask task = new TimerTask() {
			public void run() {
				mover.moveTo(mover.tellPosition() + 1);
				if (mover.tellPosition() >= goal) {
					timer.cancel();
				}
			}
		};
		timer.schedule(task, this.delay, this.period);
	}

	@Override
	public void start(Mover mover, int start, int goal, int duration) {
		start(mover, start, goal, duration, 1);
	}

	@Override
	public void start(Mover mover, int start, int goal, int duration, int acceleration) {
		if (duration > 0 && acceleration > 0) {
			final float displacement = goal - start;
			final double speed = displacement / Math.pow((float)duration, acceleration);
			final long startTime = System.currentTimeMillis();
			final Timer timer = new Timer("Timer");
			TimerTask task = new TimerTask() {
				public void run() {
					long elapsedTime = System.currentTimeMillis() - startTime;
					int position = (int)(Math.round(Math.pow(elapsedTime, acceleration) * speed) + start);
					if (position > goal) {
						mover.moveTo(position);
					} else {
						timer.cancel();
						mover.moveTo(goal);
					}
				}
			};
			timer.schedule(task, this.delay, this.period);
		} else {
			mover.moveTo(goal);
		}		
	}
	
	private void advance(/* accept anonymous function */) {
		final Timer timer = new Timer("Timer");
		TimerTask task = new TimerTask() {
			public void run() {
				// run anonymous function
			}
		};
		timer.schedule(task, this.delay, this.period);
	}
}
