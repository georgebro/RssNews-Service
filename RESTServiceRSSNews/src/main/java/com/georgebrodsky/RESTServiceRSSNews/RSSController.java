package com.georgebrodsky.RESTServiceRSSNews;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import beans.Error;
import beans.RSSNews;

/* LINK TO CHACK -> http://localhost:8080/api/rssnews */
@RestController
@RequestMapping("api/rssnews")
public class RSSController {

	@Autowired
	//	private RSSFetcher fetcher;
	private static ArrayList<RSSNews> allRSSNews = new ArrayList<>();
	private static String URL = "https://www.ynet.co.il/3rdparty/mobile/rss/ynetnews/3082/";

	// static CTOR of the RSSController 
	static {
		allRSSNews = RSSFetcher.getArrayOfNews(URL);
	}

	@GetMapping
	public ResponseEntity<?> getAllRssnews(){
		try {
			return new ResponseEntity<>(allRSSNews, HttpStatus.OK);
		}
		catch (Exception ex) {
			return new ResponseEntity<>(new Error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// BY AUTHOR.
	@GetMapping("{author}") // "{author}" = One Item from the list JSON.
	public ArrayList<RSSNews> getOneProduct(@PathVariable("author") String author){
		List<RSSNews> array = new ArrayList<RSSNews>();
		for (RSSNews news : allRSSNews) {
			if(news.getAuthor().equals(author)) {
				array.add(news);
			}
		}
		return (ArrayList<RSSNews>) array;
	}	


	// STOP.
	@PostMapping("/stop")
	public ResponseEntity<?> getStop(){
		try {
			if(RssTask.getInstance().isAlive()) {
				RssTask.getInstance().stopTimer();
				return new ResponseEntity<>("SUCCSESS: " + true , HttpStatus.OK);
			}
			return new ResponseEntity<>("SUCCSESS: " + false + " TIMER ALREADY STOPPED! ", HttpStatus.NOT_FOUND);
		}
		catch (Exception ex) {
			return new ResponseEntity<>(new Error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}

	// RESUME.
	@PostMapping("/resume")
	public ResponseEntity<?> getResume(){
		try {
			if(!RssTask.getInstance().isAlive()) {
				RssTask.getInstance().startTimer();
				return new ResponseEntity<>(" SUCCSESS: " + true , HttpStatus.OK);
			}
			return new ResponseEntity<>(" SUCCSESS: " + false + " TIMER ALREADY STOPPED ", HttpStatus.NOT_FOUND);
		}
		catch (Exception ex) {
			return new ResponseEntity<>(new Error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}	
	}	
}
