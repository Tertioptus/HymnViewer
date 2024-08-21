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
		if (duration > 0) {
			float displacement = goal - start;
			final float speed = displacement / duration;
			final long startTime = System.currentTimeMillis();
			// TODO clean up by passing a function into a function
			final Timer timer = new Timer("Timer");
			TimerTask task = new TimerTask() {
				public void run() {
					long elapsedTime = System.currentTimeMillis() - startTime;
					int position = Math.round(elapsedTime * speed) + start;
					if (position < goal) {
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

}
