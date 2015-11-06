package xyz.bingesurfing;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Feeder {

	private final static String FEEDURL = "https://news.google.com/?output=rss&num=100";
	private List<String> urls;

	public void read() {
		try {
			URL source = new URL(FEEDURL);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(source));
			@SuppressWarnings("unchecked")
			List<SyndEntry> entries = feed.getEntries();
			urls = new ArrayList<String>();
			for(SyndEntry entry : entries) {
				//String sTitle = entry.getTitle();
				String sURL = entry.getLink();
				urls.add(sURL);
				//System.out.println(sTitle + ": " + sURL);
			} // for
		} catch (IllegalArgumentException | FeedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<String> getUrls() {
		return urls;
	}

}
