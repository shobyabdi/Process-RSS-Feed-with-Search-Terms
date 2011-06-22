package com.resumerobot.rssparser.server;

import com.google.gdata.client.spreadsheet.*;
import com.google.gdata.data.*;
import com.google.gdata.data.spreadsheet.*;
import com.google.gdata.util.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GeoSpreadsheetServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
    	try {
    		resp.setContentType("text/plain");
    		SpreadsheetService service = new SpreadsheetService("exampleCo-exampleApp-1");
    		service.setCookieManager(null);
    		service.setConnectTimeout(0);
    		service.setReadTimeout(0);
    		service.setUserCredentials(req.getParameter("u"), req.getParameter("p"));
			URL metafeedUrl = new URL("http://spreadsheets.google.com/feeds/spreadsheets/private/full");
			SpreadsheetFeed feed = service.getFeed(metafeedUrl, SpreadsheetFeed.class);
			List<SpreadsheetEntry> spreadsheets = feed.getEntries();
			for (int i = 0; i < spreadsheets.size(); i++) {
			  SpreadsheetEntry entry = spreadsheets.get(i);
			  if(entry.getTitle().getPlainText().equals(req.getParameter("s"))) {
				  List<WorksheetEntry> worksheets = entry.getWorksheets();
				  for (int j = 0; j < worksheets.size(); j++) {
				    WorksheetEntry worksheet = worksheets.get(j);
				    if(worksheet.getTitle().getPlainText().equals(req.getParameter("w"))) {
				    	URL listFeedUrl = worksheet.getListFeedUrl();
				    	ListFeed feed1 = service.getFeed(listFeedUrl, ListFeed.class);
				    	for (ListEntry entry1 : feed1.getEntries()) {
				    		resp.getWriter().println(entry1.getTitle().getPlainText());
				    	  for (String tag : entry1.getCustomElements().getTags()) {
				    		  resp.getWriter().println("  <gsx:" + tag + ">" + entry1.getCustomElements().getValue(tag) + "</gsx:" + tag + ">");
				    	  }
				    	}
				    }
				  }
			  }
			}
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}