package xyz.bingesurfing;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class Feeder {

	private List<String> urls;

	public void read() {
		try {
			URL source = new URL(Defaults.FEEDURL);
			SyndFeedInput input = new SyndFeedInput();
			SyndFeed feed = input.build(new XmlReader(source));
			@SuppressWarnings("unchecked")
			List<SyndEntry> entries = feed.getEntries();
			urls = new ArrayList<String>();
			for(SyndEntry entry : entries) {
				String sURL = entry.getLink();
				urls.add(sURL);
			} // for
		} catch (IllegalArgumentException | FeedException | IOException e) {
			e.printStackTrace();
		}

		long seed = System.nanoTime();
		Collections.shuffle(urls, new Random(seed));
	}

	public List<String> getUrls() {
		return urls;
	}

}
