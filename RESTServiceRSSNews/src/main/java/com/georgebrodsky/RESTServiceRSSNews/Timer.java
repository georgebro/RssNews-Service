package com.georgebrodsky.RESTServiceRSSNews;

import java.util.Date;

public class Timer implements Runnable {

	private boolean quit = false;
	private NotifyTimerOperation notifyTimerOperation;

	// Announces whether time has come to run after 30 minutes.
	public void onNotifyTimerOperation(NotifyTimerOperation notifyTimerOperation) {
		this.notifyTimerOperation = notifyTimerOperation;
	}

	// Overriding run function.
	public void run() {

		while(!quit) {
			if(isTimeToWork()) {
				notifyTimerOperation.onNotify();
			}
			try { Thread.sleep(1000); } catch (Exception e) { }
		}
	}

	// Checking boolean flag. 
	public void stopTimer() {
		quit = true;
	}	

//	// Do something each 30 minutes: 
//	@SuppressWarnings("unused")
//	private boolean isTimeToWork() {
//		Date d = new Date();
//		@SuppressWarnings("deprecation")
//		int minutes = d.getMinutes();
//		return minutes % 30 == 0;
//	}

		// Helper Function to check the project. 
		private boolean isTimeToWork() {
			Date d = new Date();
			@SuppressWarnings("deprecation")
			int seconds = d.getSeconds();
			return seconds % 5 == 0;
		}
}

