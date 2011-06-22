package com.resumerobot.rssparser.server;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.resumerobot.rssparser.server.FeedItem;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.resumerobot.rssparser.server.ServerConstants;

public class XmlReader
{
	public XmlReader()
	{
		//System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
		//System.setProperty ("org.apache.commons.logging.simplelog.showdatetime", "true");
		//System.setProperty("org.apache.commons.logging.simplelog.log.org.apache.commons.httpclient","error");
	}
	
	public String getEmail(String link) {
	    try {
	        URL url = new URL(link);
	        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        String line;
	        String email = new String();
	        while ((line = reader.readLine()) != null) {
	           if(line.contains(ServerConstants.XML_EMAIL_START)) {
	        	   int start = line.indexOf(ServerConstants.XML_EMAIL_START);
	        	   int end = line.indexOf(ServerConstants.XML_EMAIL_END);
	        	   email = line.substring(start, end);
	        	   if(email.length() > 0) {
	        		   start = email.indexOf(ServerConstants.XML_EMAIL_MAILTO_START);
	        		   end = email.indexOf(ServerConstants.XML_EMAIL_MAILTO_END);
	        		   if(start > 0 && end > 0 && start < end) {
	        			   email = email.substring(start, end);
	        		   }
	        	   }
	        	   break;
	           }
	        }
	        reader.close();
	        return email;
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	private FeedItem getFeedItem(Element rssEl) {
		FeedItem newItem = new FeedItem();
		newItem.setTitle(getTextValue(rssEl, ServerConstants.XML_TITLE));
		newItem.setLink(getTextValue(rssEl, ServerConstants.XML_LINK));
		newItem.setDescription(getTextValue(rssEl, ServerConstants.XML_DESCRIPTION));
		newItem.setPubDate(getTextValue(rssEl, ServerConstants.XML_PUBLISHED_DATE));
		return newItem;
	}
	
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	
	private Boolean checkPublishedDate(String pubDate) {
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(ServerConstants.XML_PUBLISH_DATE_FORMAT);
        calendar1.add(Calendar.DATE, -1);
        Calendar calendar2 = Calendar.getInstance();
        try {
            if(pubDate.contains(dateFormat.format(calendar1.getTime())) || pubDate.contains(dateFormat.format(calendar2.getTime()))) {
            	return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	public ArrayList<FeedItem> parse(InputStream xml)
	{
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(xml);
			doc.getDocumentElement().normalize();
			ArrayList<FeedItem> records = new ArrayList<FeedItem>();
			NodeList nodeLst = doc.getElementsByTagName(ServerConstants.XML_ITEM);
			for (int s = 0; s < nodeLst.getLength(); s++) {
			    Element el = (Element)nodeLst.item(s);
			    FeedItem fi = getFeedItem(el);
			    if(checkPublishedDate(fi.getPubDate())) {
			    	fi.setEmail(getEmail(fi.getLink()));
			    	records.add(fi);
			    }
			}
			return records;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
	}
}
