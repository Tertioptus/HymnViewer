package com.letgodbetrue.director;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import com.letgodbetrue.Mover;

public class DefaultDirector implements Director {

	@Override
	public void start(Mover mover, int start, int goal) {

	    TimerTask task = new TimerTask() {
	        public void run() {
	            System.out.println("Task performed on: " + new Date() + "n" +
	              "Thread's name: " + Thread.currentThread().getName());
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 1000L;
	    timer.schedule(task, delay);
	}

}
