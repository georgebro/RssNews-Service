package com.georgebrodsky.RESTServiceRSSNews;

	import java.net.URL;
	import java.util.ArrayList;

	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.w3c.dom.Node;
	import org.w3c.dom.NodeList;

import beans.RSSNews;

	/**
	 * 
	 *  Used by site for DOM parse XML.
	 *  https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser .
	 *  
	 *  Function that connecting to URL, 
	 *  searching at XML file element by tag name,
	 *  and adds them to ArrayList.
	 *
	 */

	@Component
	public class RSSFetcher {

		public static  ArrayList<RSSNews> getArrayOfNews(String url){

			ArrayList<RSSNews> rssNewsArray = new ArrayList<RSSNews>();
			try{	
				URL myUrl = new URL(url);
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(myUrl.openStream());			
				doc.getDocumentElement().normalize();

				NodeList nList = doc.getElementsByTagName("item");			
				for (int temp = 0; temp < nList.getLength(); temp++) {
					RSSNews rssNews = new RSSNews();
					Node nNode = nList.item(temp);	
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;					
						rssNews.setId(Long.parseLong(eElement.getElementsByTagName("id").item(0).getTextContent()));
						rssNews.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
						rssNews.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
						rssNews.setLink(eElement.getElementsByTagName("link").item(0).getTextContent());			
						rssNews.setPubDate((eElement.getElementsByTagName("pubDate").item(0).getTextContent()));
						rssNews.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
					}
					rssNewsArray.add(rssNews);					
				}
			}
			catch(Exception ex){
				if(ex.getMessage() != null ) { ex.getMessage();}
			}
			return rssNewsArray;		
		}
	}

	

