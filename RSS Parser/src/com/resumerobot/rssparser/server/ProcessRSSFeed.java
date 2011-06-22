package com.resumerobot.rssparser.server;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.io.IOException;
import java.net.HttpURLConnection;
import com.resumerobot.rssparser.server.ServerConstants;
import com.resumerobot.rssparser.server.XmlReader;

public class ProcessRSSFeed {
	
	private String location;
	private String searchTerms;
	private StringBuffer result;
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
	
	public String getResult() {
		return this.result.toString();
	}
	
	public ProcessRSSFeed() {
		//System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		//System.setProperty ("org.apache.commons.logging.simplelog.showdatetime", "true");
		//System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient","error");		
	}
	
	public ArrayList<FeedItem> getFeedItems(String location, String type) {
		try {
			String rssFeed = ServerConstants.getCraiglistFeed(location, type);
			URL url = new URL(rssFeed.trim());
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            XmlReader newXML = new XmlReader();
            ArrayList<FeedItem> records = newXML.parse(connection.getInputStream());
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return records;
            } else {
                result.append(ServerConstants.HTML_COMMUNICATION_ERROR);
                return null;
            }
        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void execute() {
		if(location != null && searchTerms != null) {
			String[] terms = searchTerms.split(ServerConstants.SEARCH_TERMS_SPLIT);
			result = new StringBuffer();
			ArrayList<FeedItem> records = new ArrayList<FeedItem>();
			for (String craigsType : ServerConstants.getCraiglistItems()) {
		        ArrayList<FeedItem> temp = getFeedItems(location, craigsType);
		        if(temp != null && temp.size() > 0) {
		        	records.addAll(temp);
		        }				
			}
	        if(records != null && records.size() > 0) {
	        	result.append(ServerConstants.HTML_TABLE_START + 
	        				ServerConstants.HTML_TABLE_ROW_START +
	        				ServerConstants.HTML_TABLE_COLUMN_HEADER_START +
	        				ServerConstants.HTML_TABLE_NAME +
	        				ServerConstants.HTML_TABLE_COLUMN_HEADER_END + 
	        				ServerConstants.HTML_TABLE_COLUMN_HEADER_START +
	        				ServerConstants.HTML_TABLE_EMAIL +
	        				ServerConstants.HTML_TABLE_COLUMN_HEADER_END +
	        				ServerConstants.HTML_TABLE_ROW_END);
		        for(FeedItem f : records) {
					for(String e : terms) {
						if(f.getTitle() != null && f.getDescription() != null) {
							if((f.getTitle().contains(e) || f.getDescription().contains(e)) && f.getEmail() != null) {
								result.append(ServerConstants.HTML_TABLE_ROW_START +
										ServerConstants.HTML_TABLE_COLUMN_START + 
										f.getTitle() +
										ServerConstants.HTML_TABLE_COLUMN_END +
										ServerConstants.HTML_TABLE_COLUMN_START + 
										f.getEmail() +
										ServerConstants.HTML_TABLE_COLUMN_END);
							}
						}
					}
				}
		        result.append(ServerConstants.HTML_TABLE_END);
	        } else {
	        	result.append(ServerConstants.HTML_NO_RESULTS);
	        }
		}
	}

}
