package com.resumerobot.rssparser.server;

import com.resumerobot.rssparser.client.ServerService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.resumerobot.rssparser.server.ProcessRSSFeed;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.resumerobot.rssparser.server.ServerConstants;
import com.resumerobot.rssparser.server.Location;

import java.util.ArrayList;
import java.util.List;
import com.resumerobot.rssparser.client.GeoLocation;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ServerServiceImpl extends RemoteServiceServlet implements
		ServerService {

	public List<GeoLocation> getMapLocations() {
		ArrayList<GeoLocation> geoLocations = new ArrayList<GeoLocation>();
		Location chicago = new Location();
		chicago.setLocation("Chicago IL");
		chicago.setURLHeader("chicago");
		chicago.setLatitude(41.85);
		chicago.setLongitude(-87.65);
		geoLocations.add(chicago.getGeoLocationInstance());
		Location denver = new Location();
		denver.setLocation("Denver CO");
		denver.setURLHeader("denver");
		denver.setLatitude(39.74);
		denver.setLongitude(-104.99);
		geoLocations.add(denver.getGeoLocationInstance());
		Location sanfran = new Location();
		sanfran.setLocation("San Francisco CA");
		sanfran.setURLHeader("sfbay");
		sanfran.setLatitude(37.78);
		sanfran.setLongitude(-122.42);
		geoLocations.add(sanfran.getGeoLocationInstance());
		return geoLocations;
	}
	
	public String getUserData() {
		UserService userService = UserServiceFactory.getUserService();
		String data = "<p>Hi There, " + userService.getCurrentUser().getNickname() +"!<br/>";
		data += "For reference your User Id is " + userService.getCurrentUser().getUserId() + " <br/>";
		data += "To Logout click here: <a href=\"" + userService.createLogoutURL("http://www.google.com/") + "\">Logout</a>";
		return data;
	}
	
	public String getRSSProcessServer(String locationinput, String searchinput) {
		ProcessRSSFeed callRSSFeed = new ProcessRSSFeed();
		callRSSFeed.setLocation(locationinput);
		callRSSFeed.setSearchTerms(searchinput);
		callRSSFeed.execute();
		UserService userService = UserServiceFactory.getUserService();
		OutgoingMail.sendEmailToRecipient(callRSSFeed.getResult(), userService.getCurrentUser().getNickname(), userService.getCurrentUser().getEmail());
		return callRSSFeed.getResult();
	}
}
