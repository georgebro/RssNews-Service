package beans;

import org.springframework.stereotype.Component;

@Component
public class RSSNews {

	private long id;
	private String title;
	private String author;
	private String link;
	private String pubDate;
	private String description;

	// CTOR EMPTY.
	public RSSNews() {

		id = 0;
		title = "";
		author = "";
		link = "";
		pubDate = "";
		description = "";
	}

	// CTOR FULL.
	public RSSNews(long id, String title, String author, String link, String pubDate, String description) {

		this.setId(id);
		this.setTitle(title);
		this.setAuthor(author);
		this.setLink(link);
		this.setPubDate(pubDate);
		this.setDescription(description);

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void display() {

		System.out.println("****************************************************************************************\n");

		System.out.println("    ID : "+id);
		System.out.println(" TITLE : "+title);
		System.out.println("AUTHOR : "+author);
		System.out.println("LINK OF: "+link);
		System.out.println("DATE IS: "+pubDate);

		if(!description.equals("None")) {
			String str1 = description.substring( 0, description.length()/2 );
			String str2 = description.substring( description.length()/2, description.length() );
			System.out.println("TEXT OF: "+str1+"\n         "+str2);
		}
		else {
			System.out.println("TEXT OF: "+description);
		}
		System.out.println("****************************************************************************************");
	}
}




