package com.resumerobot.rssparser.server;

import java.util.ArrayList;

public class ServerConstants {
	//Email constants
	public static String EMAIL_SUBJECT = "Your RSS Items";
	public static String EMAIL_ADDRESS = "shobyabdi@gmail.com";
	public static String EMAIL_NAME = "Shoby Abdi";
	
	//HTML constants
	public static String HTML_BREAK = "<br/>";
	public static String HTML_NO_RESULTS = "<b>No Results Returned</b>";
	public static String HTML_COMMUNICATION_ERROR = "<b>Error in HTTP Communication</b>";
	public static String HTML_TABLE_START = "<table><tbody>";
	public static String HTML_TABLE_END = "</tbody></table>";
	public static String HTML_TABLE_COLUMN_HEADER_START = "<th>";
	public static String HTML_TABLE_COLUMN_HEADER_END = "</th>";
	public static String HTML_TABLE_COLUMN_START = "<td>";
	public static String HTML_TABLE_COLUMN_END = "</td>";
	public static String HTML_TABLE_ROW_START = "<tr>";
	public static String HTML_TABLE_ROW_END = "</tr>";
	public static String HTML_TABLE_NAME = "Name";
	public static String HTML_TABLE_EMAIL = "Email";
	
	
	//Search Terms constants
	public static String SEARCH_TERMS_SPLIT = ",";
	
	//XML constants
	public static String XML_EMAIL_START = "<a href=\"mailto:";
	public static String XML_EMAIL_END = "</a>";
	public static String XML_EMAIL_MAILTO_START = "mailto:";
	public static String XML_EMAIL_MAILTO_END = "?";
	public static String XML_ITEM = "item";
	public static String XML_TITLE = "title";
	public static String XML_LINK = "link";
	public static String XML_DESCRIPTION = "description";
	public static String XML_PUBLISHED_DATE = "dc:date";
	public static String XML_PUBLISH_DATE_FORMAT = "yyyy-MM-dd";
	
	public static String getCraiglistFeed(String location, String type) {
		return "http://" + location + ".craigslist.org/" + type + "/index.rss";
	}
	
	public static ArrayList<String> getCraiglistItems() {
		ArrayList<String> records = new ArrayList<String>();
		records.add("tch");
		records.add("eng");
		records.add("sof");
		records.add("web");
		return records;
	}
	
	
}
