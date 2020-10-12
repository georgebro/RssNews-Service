package com.georgebrodsky.RESTServiceRSSNews;

import java.util.ArrayList;

import beans.RSSNews;

//import beans.RssNews;

/**
 * 
 * Singleton Design pattern type of class.
 * Creating only ONE Object of RssRask.
 * 
 */

public class RssTask implements NotifyTimerOperation{

	private Timer timer;
	private Thread thread;
	ArrayList<RSSNews> arrList = new ArrayList<>();

	// CTOR empty
	public RssTask() {}

	// CTOR 
	public static RssTask getInstance() {
		return instance;
	}

	// Creating once object RssTask . 
	private static RssTask instance = new RssTask();

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setThread(Timer timer) {
		this.thread = new Thread(timer);
	}

	public Thread getThread() {
		return thread;
	}

	// Start the thread.
	public void startTimer() {
		timer = new Timer();
		timer.onNotifyTimerOperation(this); //Announces whether time has come.
		thread = new Thread(timer);
		thread.setDaemon(true);
		thread.start();
	}

	// Stop the thread. 
	public void stopTimer() {
		timer.stopTimer();
		try { thread.join(); } catch (Exception ex) { }
	}

	// Boolean function checking if thread live. 
	public boolean isAlive() {
		return thread != null && thread.isAlive();
	}

	// Function that create news list and notify.
	public void onNotify() {
		String linkUrl = "https://www.ynet.co.il/3rdparty/mobile/rss/ynetnews/3082/";
		arrList = RSSFetcher.getArrayOfNews(linkUrl);
//		for (int i = 0; i < arrList.size(); i++) {
//			arrList.get(i).display();
//		}
//		System.out.println("-----ENTER PLIASE YOUR CHOICE----- : ");
//		System.out.println("     1 - FETCH NEWS! ");
//		System.out.println("     2 - PAUSE! ");
//		System.out.println("     3 - RESUME! ");
//		System.out.println("     4 - BY AUTHOR! ");
//		System.out.println("     5 - EXIT ");
	}
}
