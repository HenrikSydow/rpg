package main;

import java.awt.Image;
import java.util.Timer;
import java.util.TimerTask;

public class GifContainer {
	
	private Timer timer = new Timer();
	
	private Image gif;
	
	private int timeInMs;
	private int loopCount;
	
	private boolean counting = false;
	
	public GifContainer(Image gif, int timeInMs) {
		this.gif = gif;
		this.timeInMs = timeInMs;
	}
	
	public void startLoopCount() {
		System.out.println("counting");
		counting = true;
		loopCount = 0;
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				loopCount += 1;
			}
		}, timeInMs * 10, timeInMs * 10);
	}
	
	public void stopLoopCount() {
		timer.cancel();
		loopCount = 0;
		counting = false;
	}

	
	//Getters:
	public Image getGif() {
		return gif;
	}

	public int getTimeInMs() {
		return timeInMs;
	}
	
	public int getLoopCount() {
		return loopCount;
	}
	
	public boolean isCounting() {
		return counting;
	}
}
